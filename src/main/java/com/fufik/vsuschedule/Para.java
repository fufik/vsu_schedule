package com.fufik.vsuschedule;

import java.util.ArrayList;

class Para {
    String beginTime;
    String endTime;
    String name;
    String room;
    String teacher;
    ParaState state;
    private static String[] btimes = {"08:00","09:45","11:30","13:25","15:10","16:55","18:40"};
    private static String[] etimes = {"09:35","11:20","13:05","15:00","16:45","18:30","20:00"};
    Para(String beginTime,String endTime,String name, String room, String teacher,ParaState state){
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.name = name;
        this.room = room;
        this.teacher = teacher;
        this.state = state;
    }
    Para(String beginTime,String endTime,String name, String room, String teacher){
        this(beginTime,endTime,name,room,teacher,ParaState.Usual);
    }
    Para(int number,String name, String room, String teacher,ParaState state){
        this(btimes[number],etimes[number],name,room,teacher,state);
    }
    Para(int number,String name, String room, String teacher){
        this(btimes[number],etimes[number],name,room,teacher,ParaState.Usual);
    }
}