package com.fufik.vsuschedule;

import java.util.*;

public class Schedules {
    public static Map<Integer,Map<String,ArrayList<Para>>> groups = new HashMap<Integer, Map<String, ArrayList<Para>>>();
    private static Map<String,ArrayList<Para>> week8 = new HashMap<String, ArrayList<Para>>() {};
    private static Map<String,ArrayList<Para>> week9 = new HashMap<String, ArrayList<Para>>() {};
    private static ArrayList<Para> MONDAY8 = new ArrayList<>(Arrays.asList(
            new Para(0,"Ин. язык 1 гр.", "308П", "Вострикова", ParaState.Numerator),
            new Para(1, "История", "292", "Лавлинский"),
            new Para(3, "Фунд. и комп.алгебра", "505П", "Вахитова",ParaState.Denominator),
            new Para(4, "Матанализ", "505П", "Семёнов")));
    private static ArrayList<Para> TUESDAY8 = new ArrayList<>(Arrays.asList(
            new Para(0, "История", "479", "Лавлинский"),
            new Para(1, "Ин. язык", "308П 309П", "Стрельникова Вострикова"),
            new Para(4, "Физвоспитание", "", "")));
    private static ArrayList<Para> WEDNESDAY8 = new ArrayList<>(Arrays.asList(
            new Para(0, "Фунд. и комп.алгебра", "292", "Вахитова"),
            new Para(1, "Фунд. и комп.алгебра", "505П", "Вахитова"),
            new Para(3, "Дискр. математика", "478", "Лобода")));
    private static ArrayList<Para> THURSDAY8 = new ArrayList<>(Arrays.asList(
            new Para(0, "Ин. язык 2 гр.", "308П","Стрельникова",ParaState.Numerator),
            new Para(2, "Тех. прогр.", "Л4", "Хлебов",ParaState.Numerator),
            new Para(2, "Тех. прогр.", "Л4 Л1", "Хлебов",ParaState.Denominator),
            new Para(3, "Тех. прогр.", "292", "Хлебов"),
            new Para(4, "Физвоспитание", "", "")));
    private static ArrayList<Para> FRIDAY8 = new ArrayList<>(Arrays.asList(
            new Para(2, "Матанализ", "307П", ""),
            new Para(3, "Матанализ", "307П", "",ParaState.Denominator),
            new Para(4, "Дискр. математика", "505П", "Каверина"),
            new Para(5, "Матанализ", "505П", "",ParaState.Numerator)));
    private static ArrayList<Para> SATURDAY8 = new ArrayList<>(Arrays.asList(
            new Para(0, "Матанализ", "292", "Семенов",ParaState.Numerator)));



    private static ArrayList<Para> MONDAY9 = new ArrayList<>(Arrays.asList(
            new Para(3, "Фунд. и комп.алгебра", "505П", "Вахитова",ParaState.Denominator),
            new Para(4, "Матанализ", "505П", "Семёнов"),
            new Para(5,"Матанализ","305П","Мелешенко"),
            new Para(6,"Матанализ","305П","Мелешенко")));
    private static ArrayList<Para> TUESDAY9 = new ArrayList<>(Arrays.asList(
            new Para(0, "История", "479", "Лавлинский"),
            new Para(3, "История", "297", "Лавлинский"),
            new Para(4, "Физвоспитание", "", "")));
    private static ArrayList<Para> WEDNESDAY9 = new ArrayList<>(Arrays.asList(
            new Para(0, "Фунд. и комп.алгебра", "292", "Вахитова"),
            new Para(1, "Ин.яз", "233", ""),
            new Para(2, "Фунд. и комп.алгебра", "305П", "Вахитова"),
            new Para(3, "Дискр. математика", "478", "Лобода")));
    private static ArrayList<Para> THURSDAY9 = new ArrayList<>(Arrays.asList(
            new Para(2,"Ин.яз","309П 231",""),
            new Para(3, "Тех. прогр.", "292", "Хлебов"),
            new Para(4, "Физвоспитание", "", "")));
    private static ArrayList<Para> FRIDAY9 = new ArrayList<>(Arrays.asList(
            new Para(2, "Тех. прогр.", "293", "Хлебов",ParaState.Numerator),
            new Para(2, "Тех. прогр.", "293 Л6", "Хлебов",ParaState.Denominator),
            new Para(3, "Дискр. математика", "297", "Каверина")));
    private static ArrayList<Para> SATURDAY9 = new ArrayList<>(Arrays.asList(
            new Para(0, "Матанализ", "292", "Семенов",ParaState.Numerator)));

    private static ArrayList<Para> RINGS = new ArrayList<>(Arrays.asList(
            new Para(0,"I","",""),
            new Para(1,"II","",""),
            new Para(2,"III","",""),
            new Para(3,"IV","",""),
            new Para(4,"V","",""),
            new Para(5,"VI","",""),
            new Para(6,"VII","","")));
    static{
        week8.put("Monday",Schedules.MONDAY8);
        week8.put("Tuesday",Schedules.TUESDAY8);
        week8.put("Wednesday",Schedules.WEDNESDAY8);
        week8.put("Thursday",Schedules.THURSDAY8);
        week8.put("Friday",Schedules.FRIDAY8);
        week8.put("Saturday",Schedules.SATURDAY8);
        week8.put("Rings",Schedules.RINGS);
        week9.put("Monday",Schedules.MONDAY9);
        week9.put("Tuesday",Schedules.TUESDAY9);
        week9.put("Wednesday",Schedules.WEDNESDAY9);
        week9.put("Thursday",Schedules.THURSDAY9);
        week9.put("Friday",Schedules.FRIDAY9);
        week9.put("Saturday",Schedules.SATURDAY9);
        week9.put("Rings",Schedules.RINGS);
        groups.put(8,week8);
        groups.put(9,week9);
    }
    public static String getKey(int day){
        if (day == Calendar.MONDAY) {
            return "Monday";
        } else if (day == Calendar.TUESDAY) {
            return "Tuesday";
        } else if (day == Calendar.WEDNESDAY) {
            return "Wednesday";
        } else if (day == Calendar.THURSDAY) {
            return "Thursday";
        } else if (day == Calendar.FRIDAY) {
            return "Friday";
        } else if (day == Calendar.SATURDAY) {
            return "Saturday";
        } else if (day == Calendar.SUNDAY) {
            return "Rings";
        }else return null;

    }
}
