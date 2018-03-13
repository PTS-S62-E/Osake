package com.pts62.common.data

data class Auto(
        val kenteken: String,
        val merk: String,
        val type: String,
        val hardwareSv: String,
        val eigenaren: List<String>,
        val categorie: String
)