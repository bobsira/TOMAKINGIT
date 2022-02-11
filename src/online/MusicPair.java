package online;

import java.util.HashMap;
import java.util.Map;

public class MusicPair {

    public int numPairsDivisibleBy60(int[] time) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : time){
            int complement = (60 - t % 60) % 60;
            result += map.getOrDefault(complement, 0);
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }
        return result;
    }
}
