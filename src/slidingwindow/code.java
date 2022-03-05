package slidingwindow;
import java.util.*;

public class code {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (char c : t.toCharArray()) map[c]++;
        int left = 0, right = 0, minStart = 0, minLength = Integer.MAX_VALUE, counter = t.length();
        while (right < s.length()) {
            // expand your s window until it satisfies t
            char rightCharacter = s.charAt(right);
            if (map[rightCharacter] > 0) counter--;
            map[rightCharacter]--;
            right++;
            while ( counter == 0) {
                // reduce your window size to find the minimum
                if (minLength > right - left) {
                    minLength = right - left;
                    minStart = left;
                }
                char leftCharacter = s.charAt(left);
                map[leftCharacter]++;
                if (map[leftCharacter] > 0) counter++;
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }
    static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        int right = 0;
        int max_length = 0;
        Map<Character,Integer> window = new HashMap<>(); // character : its rightmost index in the string s
        while(right < s.length()){
            window.put(s.charAt(right), right);
            if(window.size() > k){
                int smallestIndex = Collections.min(window.values()); // get the index of the left most character you saw
                window.remove(s.charAt(smallestIndex)); // delete the leftmost character in the string
                left = smallestIndex + 1; // move left pointer to the next character after the smallest you had seen
            }
            max_length = Math.max(max_length, right - left + 1);
            right++;
        }
        return max_length;
    }
    // Longest Substring - at most 2 distinct characters replace k by two above

    static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int result = 0;
        int left = 0;
        int right = 0;
        while(right < s.length()){
            if(!window.contains(s.charAt(right))){
                window.add(s.charAt(right));
                result = Math.max(result, window.size());
                right++;
            } else {
                window.remove(s.charAt(left));
                left++;
            }
        }
        return result;
    }

    static List<List<Integer>> threeSum(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++){
            if (i == 0 || nums[i] != nums[i - 1]){
                int remainder = -nums[i];
                int start = i + 1;
                int end = nums.length - 1;
                while (start < end){
                    if (nums[start] + nums[end] == remainder) {
                        results.add(new ArrayList<>(Arrays.asList(nums[i], nums[start], nums[end])));
                        while (start < end && nums[start] == nums[start + 1]) start++;
                        while (start < end && nums[end] == nums[end - 1]) end--;
                        start++;
                        end--;
                    } else if (nums[start] + nums[end] > remainder) end--;
                    else start++;
                }
            }
        }
        return results;
    }
    static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++){
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) end--;
                else start++;
                if (Math.abs(sum - target) < Math.abs(result - target)) result = sum;
            }
        }
        return result;
    }
    static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;

        for (int i = 0; i < nums.length - 2; i++){
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if (sum < target){
                    count += end - start;
                    start++;
                } else end--;
            }
        }
        return count;
    }
    static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    static List<List<Integer>> kSum(int[] nums, int target, int index, int k){
        List<List<Integer>> result = new ArrayList<>();
        if (k == 2) {
            int start = index;
            int end = nums.length - 1;
            while (start < end){
                int sum  = nums[index] + nums[start] + nums[end];
                if (sum == target){
                    result.add(new ArrayList<>(Arrays.asList(nums[start], nums[end])));
                    while (start < end && nums[start] == nums[start + 1]) start++;
                    while (start < end && nums[end] == nums[end - 1]) end--;
                    start++;
                    end--;
                } else if (sum > target) end--;
                else start++;
            }
        } else {
            for (int i = index; i < nums.length; i++){
                if (i > index && nums[i] == nums[i + 1]) continue;
                List<List<Integer>>passedUpList = kSum(nums, target, i + 1, k - 1);
                for (List<Integer> subList : passedUpList) subList.add(0, nums[i]);
                result.addAll(passedUpList);
            }
        }
        return result;
    }
}
