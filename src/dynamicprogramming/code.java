package dynamicprogramming;
import java.util.*;

public class code {

    int low;
    int maximumLength;
    String longestPalindrome(String s){
        if (s.length() < 2) return s;
        for (int i = 0; i < s.length(); i++){
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return s.substring(low, low + maximumLength);
    }
    void  expandAroundCenter(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        if (maximumLength < right - left - 1){
            low = left + 1;
            maximumLength = right - left - 1;
        }
    }

    int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for(int col = text2.length() - 1; col >= 0; col--){
            for(int row = text1.length() - 1; row >= 0; row--){
                if(text1.charAt(row) == text2.charAt(col))
                    dp[row][col] = 1 + dp[row + 1][col + 1];
                else dp[row][col] = Math.max(dp[row][col + 1], dp[row + 1][col]);
            }
        }
        return dp[0][0];
    }

     int longestPalindromeSubsequence(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        String t = new StringBuilder(s).reverse().toString();
        for(int col = s.length() - 1; col >= 0; col--){
            for(int row = s.length() - 1; row >= 0; row--){
                if(s.charAt(row) == t.charAt(col))
                    dp[row][col] = 1 + dp[row + 1][col + 1];
                else dp[row][col] = Math.max(dp[row][col + 1], dp[row + 1][col]);
            }
        }
        return dp[0][0];
    }


    int rob(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        return rob(nums, 0, memo);

    }
    int rob(int[] nums, int currentIndex, Map<Integer, Integer> memo) {
        if(currentIndex >= nums.length) return 0;
        if(memo.containsKey(currentIndex)) return memo.get(currentIndex);
        int takeCurrentValue = nums[currentIndex] + rob(nums, currentIndex + 2, memo);
        int skipCurrentValue = rob(nums, currentIndex + 1, memo);
        memo.put(currentIndex, Math.max(takeCurrentValue, skipCurrentValue));
        return memo.get(currentIndex);
    }

    int minCostClimbingStairs(int[] cost) {
        Map<Integer, Integer> memo = new HashMap<Integer,Integer>();
        return dp(cost, cost.length, memo);
    }
    int dp(int[] cost, int i, Map<Integer, Integer> memo) {
        if(i == 0) return 0;
        if(i == 1) return 0;
        if(!memo.containsKey(i))
            memo.put(i, Math.min(cost[i - 1] + dp(cost, i - 1, memo), cost[i - 2] + dp(cost, i - 2, memo)));
        return memo.get(i);
    }

    public int tribonacci(int n) {
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return dp(n, memo);
    }
    public int dp(int i, Map<Integer, Integer> memo){
        if(i == 0) return 0;
        if(i == 1) return 1;
        if(i == 2) return 1;
        if(!memo.containsKey(i)) memo.put(i, dp(i - 1, memo) + dp(i - 2, memo) + dp(i - 3, memo));
        return memo.get(i);
    }

    public int uniquePaths(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return uniquePaths(m, n, memo);
    }
    public int uniquePaths(int m, int n, Map<String, Integer> memo) {
        String key = m + "," + n;
        if(memo.containsKey(key)) return memo.get(key);
        if(m == 1 && n == 1) return 1;
        if(m == 0 || n == 0) return 0;
        memo.put(key, uniquePaths(m - 1, n, memo) + uniquePaths(m, n - 1, memo) );
        return memo.get(key);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePathsWithObstacles(obstacleGrid, 0, 0, new HashMap<String, Integer>());
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid, int row, int column, Map<String, Integer> memo) {
        String key = row + "," + column;
        if(memo.containsKey(key)) return memo.get(key);
        if(row == obstacleGrid.length || column == obstacleGrid[0].length || obstacleGrid[row][column] == 1) return 0;
        if(row == obstacleGrid.length - 1 && column == obstacleGrid[0].length - 1) {
            if(obstacleGrid[row][column] == 1) return 0;
            else return 1;
        }
        memo.put(key, uniquePathsWithObstacles(obstacleGrid, row + 1, column, memo) + uniquePathsWithObstacles(obstacleGrid, row , column + 1, memo));
        return memo.get(key);
    }

    public int minPathSum(int[][] grid) {
        Map<String, Integer> memo = new HashMap<>();
        return minPathSum(grid, 0, 0, memo);
    }
    public int minPathSum(int[][] grid, int row, int column,Map<String, Integer> memo) {
        String position = row + "," + column;
        if(memo.containsKey(position)) return memo.get(position);
        if(row > grid.length - 1 || column > grid[0].length - 1) return Integer.MAX_VALUE;
        else if(row == grid.length - 1 && column == grid[0].length - 1) return grid[row][column];
        int down = minPathSum(grid, row + 1, column, memo);
        int right = minPathSum(grid, row, column + 1, memo);
        memo.put(position, Math.min(down, right) + grid[row][column]);
        return memo.get(position);
    }

    static boolean canSum(int targetSum, int[] numbers){
        Map<Integer, Boolean> memo = new HashMap<>();
        return canSum(targetSum, numbers, memo);
    }
    static boolean canSum(int targetSum, int[] numbers, Map<Integer, Boolean> memo){
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return true;
        if (targetSum < 0) return false;
        for (int number : numbers) {
            int remainder = targetSum - number;
            if (canSum(remainder, numbers, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }

    static int minimumChange(int amount, int[] coins){
        Map<Integer, Integer> memo = new HashMap<>();
        int answer = minimumChange(amount, coins, memo);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    static int minimumChange(int amount, int[] coins, Map<Integer,Integer> memo){
        if (amount < 0) return Integer.MAX_VALUE;
        if (amount == 0) return 0;
        if (memo.containsKey(amount)) return memo.get(amount);
        int minimum = Integer.MAX_VALUE;
        for (int coin : coins) {
            int numsCoins = minimumChange(amount - coin, coins,  memo);
            if (minimum > numsCoins) minimum = numsCoins + 1;
        }
        memo.put(amount, minimum);
        return memo.get(amount);
    }

    static int maxNonAdjacentSum(int [] nums){
        Map<Integer, Integer> memo = new HashMap<>();
        return maxNonAdjacentSum(nums, 0, memo);
    }
    static int maxNonAdjacentSum(int [] nums, int currentIndex, Map<Integer, Integer> memo){
        if (currentIndex >= nums.length) return 0;
        if (memo.containsKey(currentIndex)) return memo.get(currentIndex);
        int takeCurrentValue = nums[currentIndex] + maxNonAdjacentSum(nums, currentIndex + 2, memo);
        int skipCurrentValue = maxNonAdjacentSum(nums, currentIndex + 1, memo);
        memo.put(currentIndex, Math.max(takeCurrentValue, skipCurrentValue));
        return memo.get(currentIndex);
    }

    static int summingSquares(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return summingSquares(n, memo);
    }
    static int summingSquares(int n, Map<Integer, Integer> memo){
        if (memo.containsKey(n)) return n;
        if (n == 0) return 0;
        int minimumSquares = Integer.MAX_VALUE;
        for (int i = 1; i <= Math.sqrt(n); i++){
            int square = i * i;
            int numSquares = summingSquares(n - square, memo);
            minimumSquares = Math.min(minimumSquares, numSquares);
        }
        memo.put(n, minimumSquares);
        return memo.get(n);
    }

    static List<Integer> howSum(int targetSum, int[] numbers){
        Map<Integer, List<Integer>> memo = new HashMap<>();
        return howSum(targetSum, numbers, memo);
    }
    static List<Integer> howSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo){
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return new ArrayList<>();
        if (targetSum < 0) return null;
        for (int number : numbers) {
            int remainder = targetSum - number;
            List<Integer> combinationList = howSum(remainder, numbers, memo);
            if (combinationList != null) {
                combinationList.add(number);
                memo.put(targetSum, combinationList);
                return memo.get(targetSum);
            }
        }
        memo.put(targetSum, null);
        return null;
    }

    static List<Integer> bestSum(int targetSum, int[] numbers){
        Map<Integer, List<Integer>> memo = new HashMap<>();
        return bestSum(targetSum, numbers, memo);
    }
    static List<Integer> bestSum(int targetSum, int[] numbers, Map<Integer, List<Integer>> memo){
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return new ArrayList<>();
        if (targetSum < 0) return null;
        List<Integer> shortestCombination = null;
        for (int number : numbers) {
            int remainder = targetSum - number;
            List<Integer> combinationList = bestSum(remainder, numbers, memo);
            if (combinationList != null) {
                combinationList.add(number);
                if (shortestCombination == null || combinationList.size() < shortestCombination.size()) {
                    shortestCombination = new ArrayList<>(combinationList);
                }
            }
        }
        memo.put(targetSum, shortestCombination);
        return shortestCombination;
    }

    static boolean canConstruct(String target, String[] wordBank) {
        Map<String, Boolean> memo = new HashMap<>();
        return canConstruct(target, wordBank, memo);
    }
    static boolean canConstruct(String target, String[] wordBank, Map<String, Boolean> memo){
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return true;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                if (canConstruct(suffix, wordBank, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }

    static boolean canJump(int[] numbers) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return canJump(numbers,0, memo);
    }
    static boolean canJump(int[] numbers, int currentIndex, Map<Integer, Boolean> memo) {
        if (currentIndex >= numbers.length - 1) return true;
        if (memo.containsKey(currentIndex)) return memo.get(currentIndex);

        int possibleJumpLimit = numbers[currentIndex];
        for (int step = 1; step <= possibleJumpLimit; step++){
            int nextJump = step + currentIndex;
            if (canJump(numbers, nextJump, memo)) {
                memo.put(currentIndex, true);
                return true;
            }
        }
        memo.put(currentIndex, false);
        return memo.get(currentIndex);
    }

    static int numberOfWaysConstruct(String target, String[] wordBank){
        Map<String, Integer> memo = new HashMap<>();
        return numberOfWaysConstruct(target, wordBank, memo);
    }
    static int numberOfWaysConstruct(String target, String[] wordBank, Map<String, Integer> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return 1;
        int count = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                count += numberOfWaysConstruct(suffix, wordBank, memo);
            }
        }
        memo.put(target, count);
        return count;
    }

    static int countPaths(char[][] grid){
        Map<String, Integer> memo = new HashMap<>();
        return countPaths(grid, 0, 0, memo);
    }
    static int countPaths(char[][] grid, int row, int column, Map<String, Integer> memo){
        String position = row + "," + column;
        if (memo.containsKey(position)) return memo.get(position);
        if (row == grid.length || column == grid[0].length || grid[row][column] == 'X') return 0;
        memo.put(position, countPaths(grid, row + 1, column, memo) + countPaths(grid, row, column + 1, memo));
        return memo.get(position);
    }

    static int countingChange(int[] coins, int amount){
        Map<String, Integer> memo = new HashMap<>();
        return countingChange(coins, amount, 0, memo);
    }
    static int countingChange(int[] coins, int amount, int currentIndex, Map<String, Integer> memo){
        String key = amount + "," + currentIndex;
        if (memo.containsKey(key)) return memo.get(key);

        if (amount == 0) return 1;
        if (currentIndex == coins.length) return 0;

        int coin = coins[currentIndex];

        int count = 0;
        for (int qty = 0; (qty * coin) <= amount; qty++){
            int remainder = amount - (coin * qty);
            count += countingChange(coins, amount - qty, currentIndex + 1, memo);
        }

        memo.put(key, count);
        return memo.get(key);
    }

    static int maxPathSum(int[][] grid){
        Map<String, Integer> memo = new HashMap<>();
        return maxPathSum(grid, 0, 0, memo);
    }
    static int maxPathSum(int[][] grid, int row, int column, Map<String, Integer> memo) {
        String position = row + "," + column;
        if (memo.containsKey(position)) return memo.get(position);

        if (row > grid.length- 1 || column > grid[0].length - 1) return Integer.MIN_VALUE;
        else if (row == grid.length - 1 && column == grid[0].length - 1) return grid[row][column];

        int down = maxPathSum(grid, row + 1, column, memo);
        int right = maxPathSum(grid, row, column + 1, memo);
        memo.put(position, Math.max(down, right) + grid[row][column]);
        return memo.get(position);
    }

    static int maxPalindromeSubsequence(String string){
        Map<String, Integer> memo = new HashMap<>();
        return maxPalindromeSubsequence(string, 0, string.length() - 1, memo);
    }
    static int maxPalindromeSubsequence(String string, int start, int end, Map<String, Integer> memo){
        String key = start + "," + end;
        if (memo.containsKey(key)) return memo.get(key);
        if (start == end) return 1;
        if (start > end) return 0;

        if (string.charAt(start) == string.charAt(end)) {
            memo.put(key, 2 + maxPalindromeSubsequence(string, start + 1, end - 1, memo));
        } else {
            memo.put(key, Math.max(
                    maxPalindromeSubsequence(string, start + 1, end, memo),
                    maxPalindromeSubsequence(string, start, end - 1, memo)
            ));
        }
        return memo.get(key);
    }

    static int overlapSubsequence(String string1, String string2) {
        Map<String, Integer> memo = new HashMap<>();
        return overlapSubsequence(string1, string2, 0, 0, memo);
    }
    static int overlapSubsequence(String str1, String str2, int i, int j, Map<String, Integer> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);
        if (i == str1.length() || j == str2.length()) return 0;
        if (str1.charAt(i) == str2.charAt(j)) {
            memo.put(key, 1 + overlapSubsequence(str1, str2, i + 1, j + 1, memo));
        } else {
            memo.put(key, Math.max(
                    overlapSubsequence(str1, str2, i + 1, j, memo),
                    overlapSubsequence(str1, str2, i, j + 1, memo)
            ));
        }
        return memo.get(key);
    }

    public static void main(String[] args){
        String target = "purple";
        String[] wordBank = { "purp", "p", "ur", "le", "purpl"};
        System.out.println(numberOfWaysConstruct(target, wordBank));
//        String target = "abcdef";
//        String[] wordBank = { "ab", "abc", "cd", "def", "abcd"};
//        System.out.println(canConstruct(target, wordBank));
//        int targetSum = 8;
//        int[] numbers = { 1, 4, 5};
//        System.out.println(canSum(targetSum, numbers));
//
//        System.out.println();
//
//        List<Integer> output = howSum(targetSum, numbers);
//        if (output != null ) for (Integer integer : output) System.out.print( integer + " ");
//        else System.out.println(output);
//
//        System.out.println();
//
//        List<Integer> output1 = bestSum(targetSum, numbers);
//        if (output1 != null ) for (Integer integer : output1) System.out.print( integer + " ");
//        else System.out.println(output1);
    }


}
