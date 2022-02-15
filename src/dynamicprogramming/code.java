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
    int longestCommonSubsequenceI(String str1, String str2) {
        Map<String, Integer> memo = new HashMap<>();
        return longestCommonSubsequenceI(memo, str1, str2, 0, 0);
    }
    int longestCommonSubsequenceI(Map<String, Integer> memo,String str1, String str2, int i, int j) {
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);
        if (i == str1.length() || j == str2.length()) return 0; // check for empty for either str1 or str2
        if (str1.charAt(i) == str2.charAt(j)) {
            memo.put(key, 1 + longestCommonSubsequenceI(memo, str1, str2, i + 1, j + 1)) ;
        } else {
            memo.put(key, Math.max(
                    longestCommonSubsequenceI(memo, str1, str2, i + 1, j ),
                    longestCommonSubsequenceI(memo, str1, str2, i , j + 1)
            )) ;
        }
        return memo.get(key);
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
    int longestPalindromeSubsequenceI(String s) {
        Map<String, Integer> memo = new HashMap<>();
        return longestPalindromeSubsequenceI(s, memo, 0, s.length() - 1);
    }
    int longestPalindromeSubsequenceI(String s, Map<String, Integer> memo, int startIndex, int endIndex) {
        String key = startIndex + "," + endIndex;
        if (memo.containsKey(key)) return memo.get(key);
        if (startIndex == endIndex) return 1; // every single character is a palindrome
        if (startIndex > endIndex) return 0; // represent empty string; pointers cross
        if (s.charAt(startIndex) == s.charAt(endIndex)) {
            memo.put(key, 2 + longestPalindromeSubsequenceI(s, memo, startIndex + 1, endIndex - 1)) ; // matched two characters
        } else { // remove the first character or remove the last character
            memo.put(key, Math.max(
                            longestPalindromeSubsequenceI(s, memo, startIndex + 1, endIndex),
                            longestPalindromeSubsequenceI(s, memo, startIndex, endIndex - 1)
                    )) ;
        }
        return memo.get(key);
    }

    static int lengthOfLongestIncreaseSubsequence(int[] nums) {
        Map<String, Integer> memo = new HashMap<>();
        int previous = Integer.MIN_VALUE;
        return lengthOfLongestIncreaseSubsequence(nums, memo, 0, previous);
    }
    static int lengthOfLongestIncreaseSubsequence(int[] nums, Map<String, Integer> memo, int index, int previous) {
        String key = index + "," + previous;
        if (memo.containsKey(key)) return memo.get(key);
        if (index == nums.length) return 0;
        int current = nums[index];
        int skipCurrent = lengthOfLongestIncreaseSubsequence(nums, memo, index + 1, previous);
        int takeCurrent = 0;
        if (current > previous) {
            takeCurrent = 1 + lengthOfLongestIncreaseSubsequence(nums, memo, index + 1, current);
        }
        memo.put(key, Math.max(takeCurrent, skipCurrent));
        return memo.get(key);
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

    public int fibonacci(int n) {
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return dp(memo, n);
    }
    public int dp(Map<Integer, Integer> memo, int i){
        if(i == 0) return 0;
        if(i == 1) return 1;
        if(memo.containsKey(i)) return memo.get(i);
        memo.put(i, dp(memo, i - 1) + dp(memo, i - 2));
        return memo.get(i);
    }

    public int tribonacci(int n) {
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return tribonacci(memo, n);
    }
    public int tribonacci(Map<Integer, Integer> memo, int i){
        if(i == 0) return 0;
        if(i == 1) return 1;
        if(i == 2) return 1;
        if(memo.containsKey(i)) return memo.get(i);
        memo.put(i, tribonacci(memo, i - 1) + tribonacci(memo, i - 2) + tribonacci(memo, i - 3));
        return memo.get(i);
    }

    public int climbStairs(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return climbStairs(memo, n);
    }
    public int climbStairs(Map<Integer, Integer> memo, int i){
        if(i == 1) return 1;
        if(i == 2) return 2;
        if(memo.containsKey(i)) return memo.get(i);
        memo.put(i, climbStairs(memo, i - 1) + climbStairs(memo, i - 2));
        return memo.get(i);
    }

    public int climbStairsII(int n) {
        Map<String, Integer> memo = new HashMap<>();
        return climbStairsII(memo, 0, n);
    }
    public int climbStairsII(Map<String, Integer> memo, int i, int n){
        if(i > n) return 0;
        if(i == n) return 1;
        String currentStep = i + "," + n;
        if(memo.containsKey(currentStep)) return memo.get(currentStep);
        memo.put(currentStep, climbStairsII(memo, i + 1, n) + climbStairsII(memo, i + 2, n));
        return memo.get(currentStep);
    }

    static int numDecodings(String s) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dp(memo, s, 0);
    }
    static int dp(Map<Integer, Integer> memo, String s, int index){
        if(index == s.length()) return 1; // end of string
        if(s.charAt(index) == '0') return 0; // zero decode not possible
        if(index == s.length() - 1) return 1; // last position and previous case is checked
        if(memo.containsKey(index)) return memo.get(index); // Have we already seen this substring
        int canDecodeSingleDigit = dp(memo, s, index + 1);// recursive call to decode single digit to a character
        int twoDigits = Integer.parseInt(s.substring(index, index + 2));
        int canDecodeTwoDigits = 0;
        if(twoDigits <= 26)canDecodeTwoDigits = dp(memo, s,  index + 2);
        memo.put(index, canDecodeSingleDigit + canDecodeTwoDigits);
        return memo.get(index);
    }

    public int uniquePaths(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return uniquePaths(m, n, memo);
    }
    public int uniquePaths(int m, int n, Map<String, Integer> memo) {
        String key = m + "," + n;
        if(memo.containsKey(key)) return memo.get(key);
        if(m == 1 || n == 1) return 1;
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

    // dfs + backtracking
    public int uniquePathsIII(int[][] grid) {
        // Count the 0's, starting x index, starting y index
        int zeroCount = 0 , sRow = 0 , sCol = 0;
        for (int row = 0; row < grid.length; row++){
            for (int col = 0 ; col < grid[0].length; col++){
                if (grid[row][col] == 0) zeroCount++;
                else if (grid[row][col] == 1) {
                    sRow = row;
                    sCol = col;
                }
            }
        }
        return uniquePathsIII(grid, sRow, sCol, zeroCount);
    }
    public int uniquePathsIII(int[][] grid, int row, int col, int zeroCount){
        boolean rowInbounds = row >= 0 && row < grid.length;
        boolean columnInbounds = col >= 0 && col < grid[0].length;
        if (!rowInbounds || !columnInbounds || grid[row][col] == 1) return 0;
        if (grid[row][col] == 2) return zeroCount == -1 ? 1 : 0; // Why zero = -1, because in above example we have 9 zero's. So, when we reach the final cell we are covering one cell extra then the zero count.
        // If that's the case we find the path and return '1' otherwise return '0';
        grid[row][col] = -1; // mark the visited cells as -1;
        zeroCount--; // and reduce the zero by 1
        int totalPath = uniquePathsIII(grid, row + 1, col, zeroCount) +
                uniquePathsIII(grid, row - 1, col, zeroCount) +
                uniquePathsIII(grid, row, col + 1, zeroCount) +
                uniquePathsIII(grid, row, col - 1, zeroCount);
        grid[row][col] = 0; // Let's say if we are not able to count all the paths. Now we use Backtracking over here
        zeroCount++;
        return totalPath; // if we get all the paths, simply return it.
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
            int numSquare = summingSquares(n - square, memo);
            if(minimumSquares > numSquare) minimumSquares = numSquare + 1;
        }
        memo.put(n, minimumSquares);
        return memo.get(n);
    }

    static boolean arrayStepper(int[] nums){
        Map<Integer, Boolean> memo = new HashMap<>();
        return arrayStepper(nums,memo, 0);
    }
    static boolean arrayStepper(int[] nums, Map<Integer, Boolean> memo, int currentIndex){
        if (memo.containsKey(currentIndex)) return memo.get(currentIndex);
        if (currentIndex >= nums.length - 1) return true;
        int maxStep = nums[currentIndex];
        for (int i = 1; i <= maxStep; i++){
            if (arrayStepper(nums, memo, currentIndex + i)) {
                memo.put(currentIndex, true);
                return true;
            }
        }
        memo.put(currentIndex, false);
        return memo.get(currentIndex);
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

    static int quickestConcat(String s, String[] wordBank){
        Map<String,Integer> memo = new HashMap<>();
        int quick = quickestConcat(s, wordBank, memo);
        if (quick == Integer.MAX_VALUE) return -1;
        else return quick;
    }
    static int quickestConcat(String s, String[] wordBank, Map<String,Integer> memo){
        if (memo.containsKey(s)) return memo.get(s);
        if (s.isEmpty()) return 0;
        int minimumWords = Integer.MAX_VALUE;
        for (String word : wordBank){
            String suffix = s.substring(word.length());
            int attempt = 1 + quickestConcat(s, wordBank, memo);
            minimumWords = Math.min(attempt, minimumWords);
        }
        memo.put(s, minimumWords);
        return memo.get(s);
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

    static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix == null) return 0;
        int m = matrix.length, n = matrix[0].length , longestIncresingPath = 1;
        Map<String,Integer> memo = new HashMap<>();
        for (int row = 0; row < m; row++){
            for (int col = 0; col < n; col++){
                int runningLIPath = longestIncreasingPath(matrix, memo, row, col);
                longestIncresingPath = Math.max(longestIncresingPath, runningLIPath);
            }
        }
        return longestIncresingPath;
    }
    static int longestIncreasingPath(int[][] matrix, Map<String,Integer> memo, int row, int col) {
        String key = row + "," + col;
        if (memo.containsKey(key)) memo.get(key);
        int longestIncreasingPath = 1;
        int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] delta : deltas){
            int neighborRow = row + delta[0];
            int neighborCol = col + delta[1];
            boolean rowInbounds = neighborRow >= 0 && neighborRow < matrix.length;
            boolean colInbounds = neighborCol >= 0 && neighborCol < matrix[0].length;
            if (rowInbounds && colInbounds && matrix[neighborRow][neighborCol] > matrix[row][col]){
                int longest = 1 + longestIncreasingPath(matrix, memo, neighborRow, neighborCol);
                if (longest > longestIncreasingPath) longestIncreasingPath = longest;
            }
        }
        memo.put(key, longestIncreasingPath);
        return memo.get(key);
    }

    static int breakingBoundaries(int m, int n, int k, int r, int c){
        Map<String, Integer> memo = new HashMap<>();
        return breakingBoundaries(m, n, k, r, c, memo);
    }
    static int breakingBoundaries(int m, int n, int k, int r, int c, Map<String, Integer> memo){
        String key = k + "," + r + "," + c;
        if (memo.containsKey(key)) memo.get(key);
        boolean rowInbounds = r >= 0 && r < m;
        boolean colInbounds = c >= 0 && c < n;
        if (!rowInbounds || !colInbounds) return 1;
        if (k == 0) return 0;
        int totalCount = 0;
        int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0, -1}};
        for (int[] delta : deltas){
            int dRow = delta[0] + r;
            int dCol = delta[1] + c;
            totalCount += breakingBoundaries(m, n, k - 1, dRow, dCol, memo);
        }
        memo.put(key, totalCount);
        return memo.get(key);
    }

    static int positioningPlants(int[][] costs){
        Map<String, Integer> memo = new HashMap<>();
        int lastPlant = -1;
        return positioningPlants(costs, 0, lastPlant, memo);
    }
    static int positioningPlants(int[][] costs, int position, int lastPlant, Map<String, Integer> memo){
        String key = position + "," + lastPlant;
        if (memo.containsKey(key)) return memo.get(key);
        if (position == costs.length) return 0;
        int min = Integer.MAX_VALUE;
        for (int plant = 0; plant < costs[position].length; plant++){
            if (plant != lastPlant){
                int attempt = costs[position][plant] + positioningPlants(costs, position + 1, plant, memo);
                min = Math.min(min, attempt);
            }
        }
        memo.put(key, min);
        return memo.get(key);
    }

}
