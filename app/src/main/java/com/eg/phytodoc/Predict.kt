package com.eg.phytodoc

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.eg.phytodoc.ml.ConvertedModel
import com.eg.phytodoc.ui.theme.Green3
import com.eg.phytodoc.ui.theme.Poppins
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Composable
fun Predict(selectedLan:String) {
    val Lang=if(selectedLan=="ta") "Tamil" else "English"
    val context = LocalContext.current
    var outputClass by remember { mutableStateOf("") }
    var selectedImages by remember { mutableStateOf(listOf<Uri>()) }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        selectedImages = it
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column {
            Button(
                onClick = {
                    if (selectedImages.isNotEmpty()) {
                        val bitImg = uriToBitmap(context, selectedImages[0])
                        if (bitImg != null) {
                            OutputClassify(context, bitImg,Lang) { result ->
                                outputClass = result
                            }
                        } else {
                            Log.e("Predict", "Failed to convert URI to Bitmap! (Possible DRM Image)")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Green3)
            ) {
                Text(text = stringResource(id = R.string.predict))
            }

            LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.padding(top = 16.dp)) {
                items(selectedImages) { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(text = if (outputClass.isNotEmpty()) {outputClass} else if(selectedImages.isNotEmpty()){stringResource(R.string.processing)}
            else{""},style= TextStyle(fontFamily = Poppins, fontSize = 20.sp)
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            IconButton(onClick = { galleryLauncher.launch("image/*") }) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Upload", modifier = Modifier.size(50.dp), tint = Green3)
            }
        }
    }
}

/** Runs model inference on the image and returns the predicted class label */
@SuppressLint("NewApi")
fun OutputClassify(context: Context, imageBit: Bitmap,Lang:String, onResult: (String) -> Unit) {
    val model = ConvertedModel.newInstance(context)
    val resizedBitmap = Bitmap.createScaledBitmap(imageBit, 128, 128, true)
    val byteBuffer = bitmapToFloatBuffer(resizedBitmap, 128)

    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 128, 128, 3), DataType.FLOAT32)
    inputFeature0.loadBuffer(byteBuffer)

    inputFeature0.loadBuffer(byteBuffer)
    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
    val scores = outputFeature0.floatArray
    val predictedClassIndex = scores.indices.maxByOrNull { scores[it] } ?: 0
    val pred = listOf("Apple Apple scab", "Apple Black rot", "Apple Cedar apple rust", "Apple healthy",
        "Blueberry healthy", "Cherry (including sour) Powdery mildew", "Cherry (including sour) healthy",
        "Corn (maize) Cercospora leaf spot Gray leaf spot", "Corn (maize) Common rust",
        "Corn (maize) Northern Leaf Blight", "Corn (maize) healthy", "Grape Black rot",
        "Grape Esca (Black Measles)", "Grape Leaf blight (Isariopsis Leaf Spot)", "Grape healthy",
        "Orange Haunglongbing (Citrus greening)", "Peach Bacterial spot", "Peach healthy",
        "Pepper, bell Bacterial spot", "Pepper, bell healthy", "Potato Early blight",
        "Potato Late blight", "Potato healthy", "Raspberry healthy", "Soybean healthy",
        "Squash Powdery mildew", "Strawberry Leaf scorch", "Strawberry healthy",
        "Tomato Bacterial spot", "Tomato Early blight", "Tomato Late blight", "Tomato Leaf Mold",
        "Tomato Septoria leaf spot", "Tomato Spider mites Two-spotted spider mite", "Tomato Target Spot",
        "Tomato Tomato Yellow Leaf Curl Virus", "Tomato Tomato mosaic virus", "Tomato healthy")
    val predRes = pred[predictedClassIndex]
    Log.i("pred",predRes)
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = SendMessage("Give the response the given format " +
                   " if it is healthy give some suggestions based on the maintanence of the plant or else give the second point as Preventive measures not more than two lines and third line give the recent occurance of the disease (text format)." +
                    "Conditions:" +
                    "1)Without astericks required for bolding" +
                    "2)content should be simple and not more than 30 lines. " +
                    "3)Give the recent occurence of the disease if it is a disease"+
                    "4)The response should be in $Lang"+
                    "The name is $predRes")


            withContext(Dispatchers.Main) {
                onResult(response)
            }
        } catch (e: Exception) {
            Log.e("OutputClassify", "Error: ${e.message}")
        }
    }

    model.close()
}

/** Converts a Bitmap to a Float32 ByteBuffer for TensorFlow Lite */
fun bitmapToFloatBuffer(bitmap: Bitmap, size: Int): ByteBuffer {
    val byteBuffer = ByteBuffer.allocateDirect(4 * size * size * 3) // FLOAT32 (4 bytes per value)
    byteBuffer.order(ByteOrder.nativeOrder())

    val intValues = IntArray(size * size)
    bitmap.getPixels(intValues, 0, size, 0, 0, size, size)

    for (pixelValue in intValues) {
        val r = ((pixelValue shr 16) and 0xFF) / 255.0f
        val g = ((pixelValue shr 8) and 0xFF) / 255.0f
        val b = (pixelValue and 0xFF) / 255.0f

        byteBuffer.putFloat(r)
        byteBuffer.putFloat(g)
        byteBuffer.putFloat(b)
    }
    return byteBuffer
}

/** Converts an image URI to a Bitmap and resizes it to prevent memory issues */
fun uriToBitmap(context: Context, uri: Uri): Bitmap? {
    return try {
        val source = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.createSource(context.contentResolver, uri)
        } else {
            return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

        val originalBitmap = try {
            ImageDecoder.decodeBitmap(source)
        } catch (e: Exception) {
            Log.e("BitmapError", "Image decoding failed: ${e.localizedMessage}")
            return null
        }

        val maxSize = 256
        val aspectRatio = originalBitmap.width.toFloat() / originalBitmap.height.toFloat()
        val newWidth = if (originalBitmap.width > originalBitmap.height) maxSize else (maxSize * aspectRatio).toInt()
        val newHeight = if (originalBitmap.height > originalBitmap.width) maxSize else (maxSize / aspectRatio).toInt()

        val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true)

        val optimizedBitmap = resizedBitmap.copy(Bitmap.Config.RGB_565, false)
        resizedBitmap.recycle()

        optimizedBitmap
    } catch (e: Exception) {
        Log.e("BitmapError", "Failed to process image: ${e.localizedMessage}")
        null
    }
}

// Initialize Generative AI Model
var generativeModel: GenerativeModel = GenerativeModel(
    modelName = "gemini-1.5-pro-002",
    apiKey = "AIzaSyCCX6-dy8i7xj9QCM5Nv_uXh_6UhXWlC8I"
)


/** Sends a message to Gemini AI and returns the response */
suspend fun SendMessage(q: String): String {
    return try {
        val chat = generativeModel.startChat()
        val resp = chat.sendMessage(q)
        resp.text.toString()
    } catch (e: Exception) {
        Log.e("SendMessage", "Error: ${e.message}")
        "Error processing request"
    }
}
