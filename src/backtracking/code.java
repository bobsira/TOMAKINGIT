package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class code {

    List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        backtrackOne(results, new ArrayList<>(), nums, 0);
        return results;
    }
    void backtrackOne(List<List<Integer>> results, List<Integer> tempList, int[] nums, int startIndex){
        results.add(new ArrayList<>(tempList));
        for (int i = startIndex; i < nums.length; i++){
            tempList.add(nums[i]); // add i into the current combination
            backtrackOne(results, tempList, nums, i + 1); // use next integers to complete the combination
            tempList.remove(tempList.size() - 1); // backtrack
        }
    }
    List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        backtrackTwo(results, new ArrayList<>(), nums, 0);
        return results;
    }
    void backtrackTwo(List<List<Integer>> results, List<Integer> tempList, int[] nums, int startIndex){
        results.add(new ArrayList<>(tempList));
        for (int i = startIndex; i < nums.length; i++){
            if (i > startIndex && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]); // add i into the current combination
            backtrackTwo(results, tempList, nums, i + 1); // use next integers to complete the combination
            tempList.remove(tempList.size() - 1); // backtrack
        }
    }

    List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums); // not necessary
        backtrackThree(results, new ArrayList<>(), nums);
        return  results;
    }
    void backtrackThree(List<List<Integer>> results, List<Integer> tempList, int[] nums){
        if (tempList.size() == nums.length) results.add(new ArrayList<>(tempList));
        else {
            for (int num : nums) {
                if (tempList.contains(num)) continue;
                tempList.add(num);
                backtrackThree(results, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean [] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrackFour(results, new ArrayList<>(), nums, used);
        return  results;
    }
    void backtrackFour(List<List<Integer>> results, List<Integer> tempList, int[] nums, boolean[] used){
        if (tempList.size() == nums.length) results.add(new ArrayList<>(tempList));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                tempList.add(nums[i]);
                used[i] = true;
                backtrackFour(results, tempList, nums, used);
                tempList.remove(tempList.size() - 1);
                used[i] = false;
            }
        }
    }

    List<List<Integer>> combinationSumI(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackSix(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    void backtrackSix(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int target, int startIndex){
        if (target < 0) return;
        else if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        } else {
            for (int i = startIndex; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                backtrackSix(result, tempList, candidates, target - candidates[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    List<List<Integer>> combinationSumII(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackSeven(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    void backtrackSeven(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int target, int startIndex){
        if (target < 0) return;
        else if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        } else {
            for (int i = startIndex; i < candidates.length; i++){
                if (i > startIndex && candidates[i] == candidates[i - 1]) continue; // skip duplicates
                tempList.add(candidates[i]);
                backtrackSeven(result, tempList, candidates, target - candidates[i], i + 1); // we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrackEight(result, new ArrayList<>(), s, 0);
        return result;
    }
    void backtrackEight(List<List<String>> result, List<String> tempList, String s, int startIndex){
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = startIndex; i < s.length(); i++){
                if (isPalindrome(s, startIndex, i )) {
                    tempList.add(s.substring(startIndex, i + 1));
                    backtrackEight(result, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
    boolean isPalindrome(String s, int low, int high){
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--))
                return false;
        return true;
    }

    List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<Character, String> map = Map.of(
                '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
                '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
        if (digits.length() == 0) return result;
        backtrackNine(result, digits, map, new StringBuilder(), 0);
        return result;
    }
    void backtrackNine(List<String> result, String digits, Map<Character, String> map, StringBuilder currentPath, int currentIndex){
        if (currentPath.length() == digits.length()){
            result.add(currentPath.toString());
        } else {
            String possibleLetter = map.get(digits.charAt(currentIndex));
            for (char letter : possibleLetter.toCharArray()){
                currentPath.append(letter);
                backtrackNine(result, digits, map, currentPath, currentIndex + 1);
                currentPath.deleteCharAt(currentPath.length() - 1);
            }
        }
    }

    List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrackTen(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    void backtrackTen(List<String> result, StringBuilder currentPath, int openParenCount, int closeParenCount, int n){
        if (currentPath.length() == n * 2) {
            result.add(currentPath.toString());
            return;
        }
        if (openParenCount < n) {
            currentPath.append("(");
            backtrackTen(result, currentPath, openParenCount + 1, closeParenCount, n);
            currentPath.deleteCharAt(currentPath.length() - 1);
        }
        if (closeParenCount < n){
            currentPath.append(")");
            backtrackTen(result, currentPath, openParenCount, closeParenCount + 1, n);
            currentPath.deleteCharAt(currentPath.length() - 1);
        }
    }

}
