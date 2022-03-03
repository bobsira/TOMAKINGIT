package arraystrings;

import java.util.Arrays;

public class code {

    static String uncompress(String s){
        int i = 0, j = 0;
        StringBuilder result = new StringBuilder();
        while (j < s.length()) {
            if (Character.isDigit(s.charAt(j))) {
                j++;
            } else {
                int number = Integer.parseInt(s.substring(i, j));
                for (int index = 0; index < number; index++){
                    result.append(s.charAt(j));
                }
                j++;
                i = j;
            }
        }
        return result.toString();
    }

    static String compress(String s){
        int i = 0, j = 0;
        StringBuilder result = new StringBuilder();
        while (j < s.length()){
            if (s.charAt(i) == s.charAt(j)){
                j++;
            } else {
                int number = j - i;
                if (number == 1) result.append(s.charAt(i));
                else result.append(number).append(s.charAt(i));
                i = j;
            }
        }
        return result.toString();
    }

    static int maxDistToClosest(int[] seats){
        int N = seats.length;
        int [] left = new int[N];
        int [] right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; i++){
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i - 1] + 1;
        }

        for (int i = N - 1; i >= 0; i--){
            if (seats[i] == 1) right[i] = 0;
            else if (i < N - 1) right[i] = right[i + 1] + 1;
        }

        int answer = 0;
        for (int i = 0; i < N; i++){
            if (seats[i] == 0) answer = Math.max(answer, Math.min(left[i], right[i]));
        }
        return answer;
    }
}
