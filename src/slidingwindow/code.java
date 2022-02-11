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
}
