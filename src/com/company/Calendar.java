package com.company;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

    private TimeInterval meetingRange = new TimeInterval();;
    private List<TimeInterval> meetingIntervals  = new ArrayList<>();


    public Calendar(List<TimeInterval> timeInterval, LocalTime lowRangeLimit, LocalTime highRangeLimit) {
        this.meetingIntervals = timeInterval;
        this.meetingRange.setStartingTime( lowRangeLimit);
        this.meetingRange.setEndingTime(highRangeLimit);
    }

    public Calendar() {
    }

    public List<TimeInterval> getMeetingIntervals() {
        return meetingIntervals;
    }

    public void setMeetingIntervals(List<TimeInterval> meetingIntervals) {
        this.meetingIntervals = meetingIntervals;
    }

    public TimeInterval getMeetingRange() {
        return meetingRange;
    }

    public void setMeetingRange(TimeInterval meetingRange) {
        this.meetingRange = meetingRange;
    }

    /**
     *
     * @return a list of available time intervals
     * */
    public List<TimeInterval> getAvailableTimeIntervals(){

        List<TimeInterval> availableTimeIntervals = new ArrayList<>();
        TimeInterval newTimeInterval = new TimeInterval();
        newTimeInterval.setStartingTime( meetingRange.getStartingTime());

        for(int i = 0; i < meetingIntervals.size() ; i++){
            if(newTimeInterval.getStartingTime().isBefore(meetingIntervals.get(i).getStartingTime()) ){
                TimeInterval addTimeInt = new TimeInterval(newTimeInterval.getStartingTime(), meetingIntervals.get(i).getStartingTime());
                availableTimeIntervals.add(addTimeInt);
            }
            newTimeInterval.setStartingTime(meetingIntervals.get(i).getEndingTime());
        }
        if(newTimeInterval.getStartingTime().isBefore(meetingRange.getEndingTime())){
            availableTimeIntervals.add( new TimeInterval(newTimeInterval.getStartingTime(), meetingRange.getEndingTime()));
        }
        return availableTimeIntervals;
    }
}
