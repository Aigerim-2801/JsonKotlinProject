package com.example.firstgitkotlin.json

data class MainItem(
    val IUPSid: String,
    val Title: String,
    val DocumentURL: String,
    val AcademicYearId: String,
    val AcademicYear: String,
    val Semesters: List<SemestersItem>
)

