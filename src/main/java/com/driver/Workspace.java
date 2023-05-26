package com.driver;
import org.apache.commons.lang3.tuple.Pair;
import javax.lang.model.type.ArrayType;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Workspace extends Gmail{
    private ArrayList<Meeting> calendar;
    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }
    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }
    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // at a particular time, you can only be present in at most one meeting
        // if you want to attend a meeting, you must join it at its start time and leave at end time.

        List<Meeting> sortedCalender = new ArrayList<>(List.copyOf(calendar));
        Collections.sort(sortedCalender, (x,y) -> x.getEndTime().compareTo(y.getEndTime()) );

//        ArrayList<Pair<LocalTime, Integer>> endTimes = new ArrayList<>();

//        for (int i = 0; i < calendar.size(); i++) {
//            endTimes.add(Pair.of(calendar.get(i).getEndTime(), i));
//        }
//
//        Collections.sort(endTimes);
//
//        LocalTime time_limit = endTimes.get(0).getLeft();
        List<Meeting> maxMeetings = new ArrayList<>();
        LocalTime time_limit = sortedCalender.get(0).getStartTime();
//        int cnt = 0;
//        if(!sortedCalender.isEmpty()) {
//            cnt += 1;
//        }
        for (Meeting meeting: sortedCalender) {
            if (meeting.getStartTime().compareTo(time_limit) >= 0) {
                maxMeetings.add(meeting);
                time_limit = meeting.getStartTime();
            }
        }

        return maxMeetings.size();
    }
}
//package com.driver;
//
//import org.apache.commons.lang3.tuple.Pair;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//
//
//public class Workspace extends Gmail{
//
//    private ArrayList<Meeting> calendar; // Stores all the meetings
//
//    public Workspace(String emailId) {
//        // The inboxCapacity is equal to the maximum value an integer can store.
//        super(emailId, Integer.MAX_VALUE); // Call the super constructor with emailId and maximum inbox capacity
//        this.calendar = new ArrayList<>();
//
//    }
//
//    public void addMeeting(Meeting meeting){
//        //add the meeting to calendar
//        this.calendar.add(meeting);
//
//    }
//
//    public int findMaxMeetings(){
//        // find the maximum number of meetings you can attend
//        // 1. At a particular time, you can be present in at most one meeting
//        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
//        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
//        Meeting[] sortedMeetings = calendar.toArray(new Meeting[calendar.size()]);
//        for (int i = 1; i < sortedMeetings.length; i++) {
//            Meeting currentMeeting = sortedMeetings[i];
//            int j = i - 1;
//            while (j >= 0 && sortedMeetings[j].getEndTime().compareTo(currentMeeting.getEndTime()) > 0) {
//                sortedMeetings[j + 1] = sortedMeetings[j];
//                j--;
//            }
//            sortedMeetings[j + 1] = currentMeeting;
//        }
//
//        // Greedily select the maximum number of non-overlapping meetings
//        int maxMeetings = 0;
//        LocalTime lastEndTime = null;
//        for (Meeting meeting : sortedMeetings) {
//            if (lastEndTime == null || meeting.getStartTime().compareTo(lastEndTime) >= 0) {
//                maxMeetings++;
//                lastEndTime = meeting.getEndTime();
//            }
//        }
//        return maxMeetings;
//    }
//}
