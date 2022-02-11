package online;

public class SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int longestKeyIndex = -1;
        int longestDuration = -1;
        int previousKeyReleaseTime = 0;
        for (int i = 0; i < releaseTimes.length; i++){
            int currentDuration = releaseTimes[i] - previousKeyReleaseTime;
            if (currentDuration > longestDuration) {
                longestDuration = currentDuration;
                longestKeyIndex = i;
            }
            if (currentDuration == longestDuration) {
                if (keysPressed.charAt(i) > keysPressed.charAt(longestKeyIndex)){
                    longestKeyIndex = i;
                }
            }
            previousKeyReleaseTime = releaseTimes[i];
        }
        return keysPressed.charAt(longestKeyIndex);
    }
}
