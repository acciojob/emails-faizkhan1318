package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        Collections.sort(calendar, Comparator.comparing(Meeting::getStartTime));

        int maxMeetings = 0;
        int currentMeetings = 0;
        LocalTime currentTime = LocalTime.MIN;

        for (Meeting meeting : calendar) {
            // If the meeting starts after the current time, we can attend it
            if (meeting.getStartTime().isAfter(currentTime)) {
                currentMeetings++;
                currentTime = meeting.getEndTime();

                // Update the maximum number of meetings
                maxMeetings = Math.max(maxMeetings, currentMeetings);
            } else {
                // If the meeting has already started, we cannot attend it
                // We need to find a meeting that starts after the current meeting ends
                int i = calendar.indexOf(meeting);
                while (i < calendar.size() && meeting.getEndTime().isAfter(calendar.get(i).getStartTime())) {
                    i++;
                }

                if (i < calendar.size()) {
                    // We found a meeting that starts after the current meeting ends
                    currentMeetings--;
                    currentTime = calendar.get(i).getEndTime();
                } else {
                    // We reached the end of the calendar, so there are no more meetings to attend
                    break;
                }
            }
        }

        return maxMeetings;
    }
}
