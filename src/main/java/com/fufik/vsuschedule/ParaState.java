package com.fufik.vsuschedule;

public enum ParaState{
    Denominator(0),
    Numerator(1),
    Usual(2);

    private int state;
    public int getValue(){
        return this.state;
    }
    private ParaState(int state){
        this.state = state;
    }
}
