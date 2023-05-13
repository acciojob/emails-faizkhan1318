package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;


public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE); // Call the super constructor with emailId and maximum inbox capacity
        this.calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        Meeting[] sortedMeetings = calendar.toArray(new Meeting[calendar.size()]);
        for (int i = 1; i < sortedMeetings.length; i++) {
            Meeting currentMeeting = sortedMeetings[i];
            int j = i - 1;
            while (j >= 0 && sortedMeetings[j].getEndTime().compareTo(currentMeeting.getEndTime()) > 0) {
                sortedMeetings[j + 1] = sortedMeetings[j];
                j--;
            }
            sortedMeetings[j + 1] = currentMeeting;
        }

        // Greedily select the maximum number of non-overlapping meetings
        int maxMeetings = 0;
        LocalTime lastEndTime = null;
        for (Meeting meeting : sortedMeetings) {
            if (lastEndTime == null || meeting.getStartTime().compareTo(lastEndTime) >= 0) {
                maxMeetings++;
                lastEndTime = meeting.getEndTime();
            }
        }
        return maxMeetings;
    }
}
