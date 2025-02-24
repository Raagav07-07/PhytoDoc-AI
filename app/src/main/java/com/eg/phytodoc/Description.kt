package com.eg.phytodoc

sealed class Description(val desc:String){
    data object Apple:Description("Apple Overview: Nutritious fruit rich in fiber and antioxidants.\n" + "\n" +
            "Common Diseases: Apple Scab, Fire Blight, Powdery Mildew, Cedar Apple Rust.\n" + "\n" +
            "Prevention: Prune trees, remove infected parts, use resistant varieties.\n" + "\n" +
            "Control: Apply fungicides, organic sprays, and improve air circulation.")
    data object Blueberry:Description("Blueberry Overview: Rich in antioxidants, supports brain and heart health.\n" +
            "\n" +
            "Common Diseases: Mummy Berry, Anthracnose, Root Rot, Powdery Mildew.\n" +
            "\n" +
            "Prevention: Ensure drainage, prune infected parts, improve airflow.\n" +
            "\n" +
            "Control: Apply fungicides, remove diseased berries, mulch properly.")
    data object Cherry:Description("Cherry Overview: Sweet, tart fruit high in vitamins and antioxidants.\n" +
            "\n" +
            "Common Diseases: Brown Rot, Leaf Spot, Bacterial Canker, Powdery Mildew.\n" +
            "\n" +
            "Prevention: Prune trees, remove infected fruit, use disease-free plants.\n" +
            "\n" +
            "Control: Apply copper sprays, fungicides, and improve air circulation.")
    data object Corn:Description("Corn Overview: Staple cereal crop rich in fiber and essential nutrients.\n" +
            "\n" +
            "Common Diseases: Rust, Smut, Northern Leaf Blight, Stewart’s Wilt.\n" +
            "\n" +
            "Prevention: Rotate crops, use resistant varieties, avoid wet soil.\n" +
            "\n" +
            "Control: Apply fungicides, remove infected plants, manage weeds.")
    data object Grape:Description("Grape Overview: Popular fruit for wine, juice, and fresh consumption.\n" +
            "\n" +
            "Common Diseases: Downy Mildew, Powdery Mildew, Black Rot, Pierce’s Disease.\n" +
            "\n" +
            "Prevention: Prune vines, ensure airflow, remove infected leaves.\n" +
            "\n" +
            "Control: Use sulfur sprays, fungicides, and resistant grape varieties.\n" +
            "\n")
    data object Orange:Description("Orange Overview: Citrus fruit rich in vitamin C and immune-boosting compounds.\n" +
            "\n" +
            "Common Diseases: Citrus Canker, Greening, Root Rot, Sooty Mold.\n" +
            "\n" +
            "Prevention: Use certified trees, manage pests, maintain soil health.\n" +
            "\n" +
            "Control: Apply copper sprays, remove infected leaves, control insects.")
    data object Peach:Description("Peach Overview: Juicy fruit rich in vitamins A and C, promotes skin health.\n" +
            "\n" +
            "Common Diseases: Peach Leaf Curl, Brown Rot, Bacterial Spot, Gummosis.\n" +
            "\n" +
            "Prevention: Prune trees, plant resistant varieties, avoid overhead watering.\n" +
            "\n" +
            "Control: Apply fungicides, remove infected fruit, use copper sprays.")
    data object Pepperball:Description("Pepperball Overview: Spicy fruit used for seasoning and medicinal purposes.\n" +
            "\n" +
            "Common Diseases: Bacterial Spot, Anthracnose, Mosaic Virus, Wilt.\n" +
            "\n" +
            "Prevention: Use disease-free seeds, avoid overhead watering, rotate crops.\n" +
            "\n" +
            "Control: Remove infected plants, apply fungicides, manage pests.")
    data object Potato:Description("Potato Overview: Staple tuber crop, rich in carbohydrates and vitamins.\n" +
            "\n" +
            "Common Diseases: Late Blight, Blackleg, Scab, Potato Virus Y.\n" +
            "\n" +
            "Prevention: Use certified seeds, rotate crops, manage soil health.\n" +
            "\n" +
            "Control: Remove infected plants, apply fungicides, control pests.")
    data object Soybeans:Description("Soybeans Overview: Protein-rich legume used in food and animal feed.\n" +
            "\n" +
            "Common Diseases: Soybean Rust, Root Rot, Brown Spot, Mosaic Virus.\n" +
            "\n" +
            "Prevention: Rotate crops, use resistant varieties, manage moisture.\n" +
            "\n" +
            "Control: Apply fungicides, remove diseased plants, ensure drainage.")
    data object Squash:Description("Squash Overview: Nutrient-rich vegetable used in various cuisines.\n" +
            "\n" +
            "Common Diseases: Powdery Mildew, Mosaic Virus, Wilt, Anthracnose.\n" +
            "\n" +
            "Prevention: Space plants properly, use disease-resistant varieties.\n" +
            "\n" +
            "Control: Remove infected leaves, apply fungicides, control insects.")
    data object Raspberry:Description("Raspberry Overview: Sweet, tart berry high in fiber and antioxidants.\n" +
            "\n" +
            "Common Diseases: Cane Blight, Powdery Mildew, Root Rot, Mosaic Virus.\n" +
            "\n" +
            "Prevention: Prune canes, avoid wet soil, use disease-resistant plants.\n" +
            "\n" +
            "Control: Apply fungicides, remove infected canes, manage irrigation.\n" +
            "\n")
    data object Tomato:Description("Tomato Overview: Popular vegetable rich in vitamins and antioxidants.\n" +
            "\n" +
            "Common Diseases: Blight, Wilt, Leaf Curl Virus, Mosaic Virus.\n" +
            "\n" +
            "Prevention: Rotate crops, stake plants, ensure proper ventilation.\n" +
            "\n" +
            "Control: Remove infected leaves, apply fungicides, control pests.")
    data object Strawberry:Description("Strawberry Overview: Juicy berry rich in vitamin C and antioxidants.\n" +
            "\n" +
            "Common Diseases: Gray Mold, Leaf Spot, Powdery Mildew, Anthracnose.\n" +
            "\n" +
            "Prevention: Space plants properly, use mulch, avoid overhead watering.\n" +
            "\n" +
            "Control: Apply fungicides, remove infected fruit, improve drainage.")

}

