package com.fufik.vsuschedule

import java.util.*

object Schedules {
    var groups: MutableMap<Int, Map<String, ArrayList<Para>>> = HashMap()
    private val week8 = object : HashMap<String, ArrayList<Para>>() {

    }
    private val week9 = object : HashMap<String, ArrayList<Para>>() {

    }
    private val MONDAY8 = ArrayList(Arrays.asList(
            Para(0, "Ин. язык 1 гр.", "308П", "Вострикова", ParaState.Numerator),
            Para(1, "История", "292", "Лавлинский"),
            Para(3, "Фунд. и комп.алгебра", "505П", "Вахитова", ParaState.Denominator),
            Para(4, "Матанализ", "505П", "Семёнов")))
    private val TUESDAY8 = ArrayList(Arrays.asList(
            Para(0, "История", "479", "Лавлинский"),
            Para(1, "Ин. язык", "308П 309П", "Стрельникова Вострикова"),
            Para(4, "Физвоспитание", "", "")))
    private val WEDNESDAY8 = ArrayList(Arrays.asList(
            Para(0, "Фунд. и комп.алгебра", "292", "Вахитова"),
            Para(1, "Фунд. и комп.алгебра", "505П", "Вахитова"),
            Para(3, "Дискр. математика", "478", "Лобода")))
    private val THURSDAY8 = ArrayList(Arrays.asList(
            Para(0, "Ин. язык 2 гр.", "308П", "Стрельникова", ParaState.Numerator),
            Para(2, "Тех. прогр.", "Л4", "Хлебов", ParaState.Numerator),
            Para(2, "Тех. прогр.", "Л4 Л1", "Хлебов", ParaState.Denominator),
            Para(3, "Тех. прогр.", "292", "Хлебов"),
            Para(4, "Физвоспитание", "", "")))
    private val FRIDAY8 = ArrayList(Arrays.asList(
            Para(2, "Матанализ", "307П", ""),
            Para(3, "Матанализ", "307П", "", ParaState.Denominator),
            Para(4, "Дискр. математика", "505П", "Каверина"),
            Para(5, "Матанализ", "505П", "", ParaState.Numerator)))
    private val SATURDAY8 = ArrayList(Arrays.asList(
            Para(2, "Матанализ", "292", "Семенов", ParaState.Numerator)))


    private val MONDAY9 = ArrayList(Arrays.asList(
            Para(3, "Фунд. и комп.алгебра", "505П", "Вахитова", ParaState.Denominator),
            Para(4, "Матанализ", "505П", "Семёнов"),
            Para(5, "Матанализ", "305П", "Мелешенко"),
            Para(6, "Матанализ", "305П", "Мелешенко")))
    private val TUESDAY9 = ArrayList(Arrays.asList(
            Para(0, "История", "479", "Лавлинский"),
            Para(3, "История", "297", "Лавлинский"),
            Para(4, "Физвоспитание", "", "")))
    private val WEDNESDAY9 = ArrayList(Arrays.asList(
            Para(0, "Фунд. и комп.алгебра", "292", "Вахитова"),
            Para(1, "Ин.яз", "233", ""),
            Para(2, "Фунд. и комп.алгебра", "305П", "Вахитова"),
            Para(3, "Дискр. математика", "478", "Лобода")))
    private val THURSDAY9 = ArrayList(Arrays.asList(
            Para(2, "Ин.яз", "309П 231", ""),
            Para(3, "Тех. прогр.", "292", "Хлебов"),
            Para(4, "Физвоспитание", "", "")))
    private val FRIDAY9 = ArrayList(Arrays.asList(
            Para(2, "Тех. прогр.", "293", "Хлебов", ParaState.Numerator),
            Para(2, "Тех. прогр.", "293 Л6", "Хлебов", ParaState.Denominator),
            Para(3, "Дискр. математика", "297", "Каверина")))
    private val SATURDAY9 = ArrayList(Arrays.asList(
            Para(2, "Матанализ", "292", "Семенов", ParaState.Numerator)))

    private val RINGS = ArrayList(Arrays.asList(
            Para(0, "I", "", ""),
            Para(1, "II", "", ""),
            Para(2, "III", "", ""),
            Para(3, "IV", "", ""),
            Para(4, "V", "", ""),
            Para(5, "VI", "", ""),
            Para(6, "VII", "", "")))

    init {
        week8.put("Monday", Schedules.MONDAY8)
        week8.put("Tuesday", Schedules.TUESDAY8)
        week8.put("Wednesday", Schedules.WEDNESDAY8)
        week8.put("Thursday", Schedules.THURSDAY8)
        week8.put("Friday", Schedules.FRIDAY8)
        week8.put("Saturday", Schedules.SATURDAY8)
        week8.put("Rings", Schedules.RINGS)
        week9.put("Monday", Schedules.MONDAY9)
        week9.put("Tuesday", Schedules.TUESDAY9)
        week9.put("Wednesday", Schedules.WEDNESDAY9)
        week9.put("Thursday", Schedules.THURSDAY9)
        week9.put("Friday", Schedules.FRIDAY9)
        week9.put("Saturday", Schedules.SATURDAY9)
        week9.put("Rings", Schedules.RINGS)
        groups.put(8, week8)
        groups.put(9, week9)
    }

    fun getKey(day: Int): String {
        return if (day == Calendar.MONDAY) {
            "Monday"
        } else if (day == Calendar.TUESDAY) {
            "Tuesday"
        } else if (day == Calendar.WEDNESDAY) {
            "Wednesday"
        } else if (day == Calendar.THURSDAY) {
            "Thursday"
        } else if (day == Calendar.FRIDAY) {
            "Friday"
        } else if (day == Calendar.SATURDAY) {
            "Saturday"
        } else {
            "Rings"
        }

    }
}
