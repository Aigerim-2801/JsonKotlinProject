package com.example.firstgitkotlin.json

import java.io.Serializable

data class SemestersItem(
    val Number: String,
    val Disciplines: List<DisciplineItem>
) : Serializable
