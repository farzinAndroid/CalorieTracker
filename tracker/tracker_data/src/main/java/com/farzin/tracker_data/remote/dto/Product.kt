package com.farzin.tracker_data.remote.dto


data class Product(
    val image_front_thumb_url: String?,
    val nutriments: Nutriments,
    val product_name: String?
)