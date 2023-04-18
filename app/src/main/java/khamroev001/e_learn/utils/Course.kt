package khamroev001.e_learn.utils

data class Course(var name:String, var category: Category, var image:String, var prices:ArrayList<Int>, var durationHour: Int, var durationMin: Int, var hasCertificate:Boolean, var mentor: Mentor, var about:String):java.io.Serializable