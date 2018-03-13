package com.pts62.common.facade

data class AutoFacade (
        val kenteken: String,
        val merk: String,
        val type: String,
        val hardwareSv: String,
        val eigenaren: List<String> = emptyList(),
        val categorie: String
)