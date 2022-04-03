package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MeetingPlanner {

    private FileParser fileParser;
    private String path;


    public MeetingPlanner(FileParser fileParser, String path) {
        this.fileParser = fileParser;
        this.path = path;
    }

    /**
     * a method that will write the output in a file
     */
    public void writeMeetingIntervals(){
        try {
            FileWriter myWriter = new FileWriter(path);
            MeetingCalendar meetingCalendar = new MeetingCalendar();
            int meetingTime = fileParser.getMeetingTime();
            meetingCalendar.setMeetingTimeInMinutes(meetingTime);
            Calendar[] c = fileParser.parseFile();
            List<TimeInterval> availableMeetingIntervals = meetingCalendar.getAvailableMeetingIntervals(c[0],c[1]);
            myWriter.write("Available intervals for a " + meetingTime +  " minutes meeting: ");
            for(TimeInterval t: availableMeetingIntervals){
                myWriter.write(t.toString() + " ");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannor reach file " + path);
        }


    }

}
