package com.narinc.base.data.model

data class ItemList(
    val status: String,
    val totalResults: Int,
    val articles: List<Item>
)