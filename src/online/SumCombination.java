package online;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumCombination {

    public static void main(String[] args) {
        List<List<Integer>> arr = combinationSum(5);
        print(arr);
    }

    static List<List<Integer>> combinationSum(int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(),  0, target, 1);
        return result;
    }


    static void backtrack(List<List<Integer>> result, ArrayList<Integer> tempList, int sum, int target, int start){
        if ( target == sum) result.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < target; i++){
                int currentSum = sum + i;
                if (currentSum <= target) {
                    tempList.add(i);
                    backtrack(result, tempList, currentSum, target, i);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    static void print(List<List<Integer>> arr) {
        for (int i = 0; i < arr.size(); i++){
            System.out.print("[");
            for (int j = 0; j < arr.get(i).size(); j++) {
                System.out.print(arr.get(i).get(j) + ",");
            }
            System.out.println("]");
        }
    }
}
