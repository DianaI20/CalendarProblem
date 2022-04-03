package com.company;

import java.sql.Time;
import java.time.LocalTime;

public class TimeInterval {

    private LocalTime startingTime;
    private LocalTime endingTime;

    public TimeInterval(LocalTime startingTime, LocalTime endingTime) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public TimeInterval() {
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public boolean overlaps(TimeInterval t2){
        if(this.getEndingTime().isBefore(t2.getStartingTime()) || this.getEndingTime().equals( t2.getStartingTime())){
            return  false;
        }
        if(t2.getEndingTime().isBefore(this.getStartingTime()) || t2.getEndingTime().equals( this.getStartingTime())){
            return  false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + startingTime + "," + endingTime + "]";
    }
}
