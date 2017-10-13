package com.fufik.vsuschedule

class Para(val beginTime: String,val endTime: String,val name: String,val room: String,val teacher: String,val state: ParaState) {
    companion object {
        private val btimes = arrayOf("08:00", "09:45", "11:30", "13:25", "15:10", "16:55", "18:40")
        private val etimes = arrayOf("09:35", "11:20", "13:05", "15:00", "16:45", "18:30", "20:00")
    }

    constructor(beginTime: String, endTime: String, name: String, room: String, teacher: String) : this(beginTime, endTime, name, room, teacher, ParaState.Usual)
    constructor(number: Int, name: String, room: String, teacher: String, state: ParaState) : this(btimes[number], etimes[number], name, room, teacher, state)
    constructor(number: Int, name: String, room: String, teacher: String) : this(btimes[number], etimes[number], name, room, teacher, ParaState.Usual)
}