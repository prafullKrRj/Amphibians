package com.example.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibianDetail(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src") val imgSrc: String,
)