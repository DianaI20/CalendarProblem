package com.company;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MeetingCalendar extends Calendar {

    private int meetingTimeInMinutes;

    public MeetingCalendar() {
        super();
    }

    public int getMeetingTimeInMinutes() {
        return meetingTimeInMinutes;
    }

    public void setMeetingTimeInMinutes(int meetingTimeInMinutes) {
        this.meetingTimeInMinutes = meetingTimeInMinutes;
    }

    /**
     *
     * @param a the first localtime
     * @param b the second localtime
     * @return the minimum between two arguments of type LocalTime
     */

    private LocalTime getMinimumTime(LocalTime a, LocalTime b) {
        if (a.isBefore(b)) {
            return a;
        } else {
            return b;
        }
    }

    /**
     *
     * @param a the first localtime
     * @param b the second localtime
     * @return the maximum between two arguments of type LocalTime
     */
    private LocalTime getMaximumTime(LocalTime a, LocalTime b) {
        if (a.isAfter(b)) {
            return a;
        } else {
            return b;
        }
    }

    public TimeInterval getIntersectionOfRanges(TimeInterval range1, TimeInterval range2){
        return new TimeInterval( getMaximumTime(range1.getStartingTime(), range2.getStartingTime()), getMinimumTime(range1.getEndingTime(), range2.getEndingTime()));
    }

    public void setAvailableRange(Calendar bookedCalendar1, Calendar bookedCalendar2){
        this.setMeetingRange(getIntersectionOfRanges(bookedCalendar1.getMeetingRange(), bookedCalendar2.getMeetingRange()));
    }

    /**
     *
     * @param bookedCalendar1 is the first list of booked times
     * @param bookedCalendar2 is the second list of booked times
     * @return  a list of available times that can fit into both schedules
     */
    public List<TimeInterval> getAvailableMeetingIntervals(Calendar bookedCalendar1, Calendar bookedCalendar2){
        List<TimeInterval> availableTimeCalendar1 = bookedCalendar1.getAvailableTimeIntervals();
        List<TimeInterval> availableTimeCalendar2 = bookedCalendar2.getAvailableTimeIntervals();
        List<TimeInterval> meetingTimes = new ArrayList<>();
        for(TimeInterval t1 : availableTimeCalendar1){
            for(TimeInterval t2: availableTimeCalendar2){
                if(t1.overlaps(t2)){
                    TimeInterval meetingTime = new TimeInterval(getMaximumTime(t1.getStartingTime(), t2.getStartingTime()), getMinimumTime(t2.getEndingTime(),t2.getEndingTime()));
                    if(meetingTime.getStartingTime().until(meetingTime.getEndingTime(), ChronoUnit.MINUTES) >= meetingTimeInMinutes){
                        meetingTimes.add(meetingTime);
                    }
                }
            }
        }
return meetingTimes;
    }

}
