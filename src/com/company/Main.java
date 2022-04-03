package com.company;

public class Main {

    public static void main(String[] args) {

        FileParser fileParser = new FileParser("src\\input.txt");
        MeetingPlanner fileWriter = new MeetingPlanner(fileParser,"src\\output.txt");
        fileWriter.writeMeetingIntervals();
    }
}
