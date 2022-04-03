package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {

    private String path;
    File myObj;
    Scanner myReader;
    private int meetingTime = 0;
    public FileParser(String path) {
        this.path = path;
        try {
            myObj = new File(path);
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error when reading the input.");
            e.printStackTrace();
        }
    }

    public Calendar[] parseFile(){

            Calendar c1 = new Calendar();
            Calendar c2 = new Calendar();
            String bookedCalendar1;
            String bookedCalendar2;
            if (myReader.hasNextLine()) {
                bookedCalendar1 = myReader.nextLine();
                c1.setMeetingRange(this.getTimeIntervalsFromLine(bookedCalendar1).get(0));
            }
            if (myReader.hasNextLine()) {
                bookedCalendar1 = myReader.nextLine();
                c1.setMeetingIntervals(this.getTimeIntervalsFromLine(bookedCalendar1));
            }
            if (myReader.hasNextLine()) {
                bookedCalendar2 = myReader.nextLine();
                c2.setMeetingRange(this.getTimeIntervalsFromLine(bookedCalendar2).get(0));
            }
            if (myReader.hasNextLine()) {
                bookedCalendar2 = myReader.nextLine();
                c2.setMeetingIntervals(this.getTimeIntervalsFromLine(bookedCalendar2));
            }
            myReader.close();
            return new Calendar[]{c1, c2};
    }

    private List<TimeInterval> getTimeIntervalsFromLine(String line){

        List<TimeInterval> timeIntervals = new ArrayList<>();
        String[] intervals = line.split(" ");
        for(String s : intervals){
           timeIntervals.add(parseTimeInterval(s));
        }
        return timeIntervals;
    }

    private TimeInterval parseTimeInterval(String timeInterval){
        String temp = timeInterval.substring(1,timeInterval.length() - 1);
        String[] limits = temp.split(",");
        return new TimeInterval(parseTime(limits[0]), parseTime(limits[1]));
    }

    private LocalTime parseTime(String time){
        String[] times = time.split(":");
        return LocalTime.of(Integer.parseInt(times[0]),Integer.parseInt(times[1]));
    }
    public int getMeetingTime(){
        if (myReader.hasNextLine()) {
            meetingTime =Integer.parseInt( myReader.nextLine());
        }
        return meetingTime;
    }


}
