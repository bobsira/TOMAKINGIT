package arraystrings;

import java.util.*;

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

    static String findReplaceString(String s, int[] indices, String[] sources, String[] targets){
        if (s.length() == 0 || indices.length == 0) return s;
        StringBuilder result = new StringBuilder();

        // 1. create an index map  of indices in index.
        Map<Integer, Integer> indicesPositionMap = new HashMap<>();
        for (int i = 0; i < indices.length; i++)
            indicesPositionMap.put(indices[i], i);
        // 2. sort the indices array so we can process from left to right
        Arrays.sort(indices);

        int current = 0;
        for (int index : indices) {
            int indexPosition = indicesPositionMap.get(index);
            int subStringLength = index + sources[indexPosition].length();
            // Check validity of each substring from source with original string
            if (!s.substring(index, subStringLength).equals(sources[indexPosition])) continue;
            // append substring from last process till current index
            result.append(s.substring(current, index));
            // append current string
            result.append(targets[indexPosition]);
            current = subStringLength;
        }

        // 6. Finally, append the residual string
        result.append(s.substring(current));
        return result.toString();
    }

    static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int indexToNegate = Math.abs(nums[i]) - 1;
            if (nums[indexToNegate] > 0) nums[indexToNegate] = nums[indexToNegate] * -1;
        }

        for (int i = 1; i <=nums.length; i++){
            if (nums[i - 1] > 0) results.add(i);
        }
        return results;
    }

    static List<Integer> findDuplicates(int[] nums) {
        List<Integer> results = new ArrayList<>();
        for (int num:  nums) {
            int indexToNegate = Math.abs(num) - 1;
            nums[indexToNegate] = nums[indexToNegate] * -1;
        }

        for (int num: nums){
            int indexToCheck = Math.abs(num) - 1;
            if (nums[indexToCheck] > 0) {
                results.add(Math.abs(num));
                nums[indexToCheck] = nums[indexToCheck] * -1;
            }
        }
        return results;
    }

    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        for (int i = 1; i < numRows; i++){
            List<Integer> previousRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();
            // populate the columns in the current row
            currentRow.add(1);
            for (int j = 1; j < i; j++){
                currentRow.add(previousRow.get(j - 1) + previousRow.get(j));
            }
            currentRow.add(1);
            triangle.add(currentRow);
        }
        return  triangle;
    }

    static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);
        for (int i = 0; i < rowIndex; i++){
            for (int j = i; j > 0; j--){
                row.set(j, row.get(j - 1) + row.get(j));
            }
            row.add(1);
        }
        return row;
    }
}
