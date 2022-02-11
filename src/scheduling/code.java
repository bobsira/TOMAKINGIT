package scheduling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

// Sweep-line algorithm.
// TreeMap resolves Scheduling Problem by sorting the intervals and recording the overlaps.

// Main Idea
// Load all intervals to the TreeMap, where keys are intervals' start/end boundaries, and values accumulate the changes at that point in time.
// Traverse the TreeMap (in other words, sweep the timeline). If a new interval starts, increase the counter (k value) by 1, and the counter decreases by 1, if an interval has finished.
// Calculate the number of the active ongoing intervals.

public class code {
    static boolean canAttendMeetings(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals){
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        int room = 0;
        for (int value : map.values()){
            room += value;
            if (room > 1) return false;
        }
        return true;
    }
    static int minMeetingRooms(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals){
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        int room = 0, k = 0;
        for (int value : map.values()){
            room += value;
            k = Math.max(k, room);
        }
        return k;
    }

    static TreeMap<Integer, Integer> calendar = new TreeMap<>();
    // calendar II
    static boolean bookII(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        int booking = 0, k = 0;
        for(Integer value : calendar.values()) {
            booking += value;
            k = Math.max(k, booking);
            if(booking > 2) {
                calendar.put(start, calendar.get(start) - 1);
                calendar.put(end, calendar.get(end) + 1);
                return false;
            }
        }

        return true;
    }

    // calendar III
    static int bookIII(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);

        int booking = 0, k = 0;
        for(Integer value : calendar.values()) {
            booking += value;
            k = Math.max(k, booking);
        }
        return k;
    }

    static int[][] merge(int[][] intervals) {
        LinkedList<int[]> results = new LinkedList<>();
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int[] interval : intervals){
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        int count = 0, start = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (count == 0) start = entry.getKey();
            // if cnt is 0, that means a full interval has been completed.
            count += entry.getValue();
            if( count == 0)
                results.add(new int[] { start, entry.getKey()});
        }
        return results.toArray(new int[results.size()][]);
    }

    static boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int[] trip : trips){
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }

        int runningCapacity = 0;
        for(int value : map.values()) {
            runningCapacity += value;
            if(runningCapacity > capacity) return false;
        }

        return true;
    }

//    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
//Most interval-related problems can be solved by the Sweep-line algorithm.
//
// Free time is a period of time such that no employees scheduled to work, i.e. the gap between every two non-overlapping intervals.
// If we meet a start of interval, score++; or else(if we meet an end of interval), score--. Non-overlapping intervals exist when score equals to 0.
// We add current gap interval to the result list when score is not 0 any more.

//        List<Interval> result = new ArrayList<>();
//        Map<Integer, Integer> map = new TreeMap<>(); // Key: time point, value: score.
//        for (List<Interval> list : schedule) {
//            for (Interval interval : list) {
//                map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
//                map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
//            }
//        }
//
//        int start = -1, score = 0;
//        for (int point : map.keySet()) {
//            score += map.get(point);
//            if (score == 0 && start == -1) {
//                start = point;
//            } else if (start != -1 && score != 0) {
//                result.add(new Interval(start, point));
//                start = -1;
//            }
//        }
//
//        return result;
//    }

}
