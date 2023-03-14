package com.example.firstgitkotlin.json

data class DisciplineItem(

    val DisciplineId: String,
    val DisciplineName: LanguageItem,
    val Lesson: List<LessonItem>
)
