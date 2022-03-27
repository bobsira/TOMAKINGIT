package heap;

import java.util.*;

public class code {
    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>( (n1, n2) -> n1 - n2);
        for (int num  : nums ){
            minHeap.add(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.poll();
    }
    static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == k) return nums;
        // build hash map
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        // build the min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>( (n1, n2) -> map.get(n1) - map.get(n2) );
        // keep k top frequent elements in the heap
        for (int num : map.keySet()){
            minHeap.add(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        // build an output array
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) result[i] = minHeap.poll();
        return result;
    }
    static  int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) maxHeap.add(stone);
        while (maxHeap.size() > 1){
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            if (stone1 != stone2) maxHeap.add(stone1 - stone2);
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
    static int[] kWeakestRows(int[][] mat, int k) {
        //Create a Priority Queue that measures firstly on strength and then indexes.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            else return b[0] - a[0];
        });
        // Add strength/index pairs to the pq. Whenever length > k, remove the largest.
        for (int i = 0; i < mat[0].length; i++){
            int strength = binarySearch(mat[i]);
            int[] entry = new int[]{ strength, i};
            maxHeap.add(entry);
            if (maxHeap.size() > k) maxHeap.poll();
        }
        // Pull the indexes out of the priority queue.
        int[] indexes = new int[k];
        for (int i = k - 1; i >= 0; i--){
            int[] pair = maxHeap.poll();
            indexes[i] = pair[1];
        }
        return indexes;
    }
    static int binarySearch(int[] row){
        int low = 0;
        int high = row.length;
        while (low < high){
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) low = mid + 1;
            else high = mid;
        }
        return low;
    }
    static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < intervals.length; i++){
            if (!minHeap.isEmpty() && intervals[i][0] >=  minHeap.peek())
                minHeap.poll();
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
    static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < points.length; i++){
            int[] entry = new int[] {squaredDistance(points[i]), i};
            if (maxHeap.size()  < k) {
                maxHeap.add(entry);
            } else if (!maxHeap.isEmpty() && entry[0] < maxHeap.peek()[0]){
                maxHeap.poll();
                maxHeap.add(entry);
            }
        }
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++){
            int entryIndex = maxHeap.poll()[1];
            result[i] = points[entryIndex];
        }
        return result;
    }
    static int squaredDistance(int[] points){
        return points[0] * points[0] + points[1] * points[1];
    }
    static  int connectSticks(int[] sticks) {
        int totalCost = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int stick : sticks) minHeap.add(stick);
        while (minHeap.size() > 1){
            int smallest = minHeap.poll();
            int secondSmallest = minHeap.poll();
            int cost = secondSmallest + smallest;
            totalCost += cost;
            minHeap.add(cost);
        }
        return totalCost;
    }
}
