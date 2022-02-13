package tree;

import java.util.*;

public class code {
    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
      int treeSum(TreeNode root){
        if (root == null) return 0;
        int left = treeSum(root.left);
        int right = treeSum(root.right);
        return root.val + left + right;
         // iterative
//         if (root == null) return 0;
//         int sum = 0;
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.add(root);
//         while (!queue.isEmpty()){
//             TreeNode current = queue.poll();
//             sum += current.val;
//             if (current.left != null) queue.add(current.left);
//             if (current.right != null) queue.add(current.right);
//         }
//         return sum;
      }
      boolean treeIncludes(TreeNode root, int target){
        if (root == null) return false;
        if (root.val == target) return true;
        boolean containInLeft = treeIncludes(root.left, target);
        boolean containInRight = treeIncludes(root.right, target);
        return containInLeft || containInRight;
//        if (root == null) return false;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()){
//            TreeNode current = queue.poll();
//            if (current.val == target) return true;
//            if (current.left != null) queue.add(current.left);
//            if (current.right != null) queue.add(current.right);
//        }
//        return false;
    }
      int treeMinValue(TreeNode root){
        if (root == null) return Integer.MAX_VALUE;
        int leftMinimum = treeMinValue(root.left);
        int rightMinimum = treeMinValue(root.right);
        return Math.min(root.val, Math.max(leftMinimum, rightMinimum));
//        if (root == null) return 0;
//        int minimum = Integer.MAX_VALUE;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()){
//            TreeNode current = queue.poll();
//            if (current.val < minimum) minimum = current.val;
//            if (current.left != null) queue.add(current.left);
//            if (current.right != null) queue.add(current.right);
//        }
//        return minimum;
    }
      int treeValueCount(TreeNode root, int target){
        if (root == null) return 0;
        int match = root.val == target ? 1 : 0;
        int left = treeValueCount(root.left, target);
        int right = treeValueCount(root.right, target);
        return match + left + right;
        // iterative
//         if (root == null) return 0;
//         int count = 0;
//         Queue<TreeNode> queue = new LinkedList<>();
//         queue.add(root);
//         while (!queue.isEmpty()){
//             TreeNode current = queue.poll();
//             if (current.val == target) count++;
//             if (current.left != null) queue.add(current.left);
//             if (current.right != null) queue.add(current.right);
//         }
//         return count;
    }
    int howHigh(TreeNode root){
        if (root == null) return -1;
        int leftHeight = howHigh(root.left);
        int rightHeight = howHigh(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    int bottomRightValue(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode current = null;
        while (!queue.isEmpty()){
            current = queue.poll();
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return current.val;
    }

    static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(results, new ArrayList<Integer>(), root, targetSum);
        return results;
    }
    static void backtrack(List<List<Integer>> result, List<Integer> tempList, TreeNode root, int targetSum){
        if(root == null) return;
        // add the node to our path
        tempList.add(root.val);
        if(root.right == null && root.left == null & targetSum == root.val) {
            result.add(new ArrayList<>(tempList));
        } else {
            backtrack(result, tempList, root.right, targetSum - root.val);
            backtrack(result, tempList, root.left, targetSum - root.val);
        }
        // remove the node from our path to process a different path
        tempList.remove(tempList.size() - 1);
    }

    static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), root);
        return result;
    }
    static void backtrack(List<String> result, StringBuilder formedPath, TreeNode root){
        if(root == null) return;
        int tmp = formedPath.length();
        if(root.left == null && root.right == null) {
            formedPath.append(root.val);
            result.add(formedPath.toString());
            formedPath.delete(tmp , formedPath.length());
        } else {
            formedPath.append(root.val).append("->");
            backtrack(result, formedPath, root.left);
            backtrack(result, formedPath, root.right);
            formedPath.delete(tmp,formedPath.length());
        }
    }

    static int maxRootToLeafPathSum(TreeNode root){
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int maxLeftPath = maxRootToLeafPathSum(root.left);
        int maxRightPath = maxRootToLeafPathSum(root.right);
        return Math.max(maxLeftPath, maxRightPath) + root.val;
    }

    static int totalSum;
    static int sumNumbers(TreeNode root) {
        totalSum = 0;
        backtrack(new StringBuilder(), root);
        return totalSum;
    }
    static void backtrack(StringBuilder formedNumber, TreeNode root){
        if(root == null) return;
        formedNumber.append(root.val);
        if(root.right == null && root.left == null){
            totalSum += Integer.parseInt(formedNumber.toString());
        } else {
            backtrack( formedNumber, root.left);
            backtrack( formedNumber, root.right);
        }
        formedNumber.deleteCharAt(formedNumber.length() - 1);
    }

    static boolean flipEquiv(TreeNode root1, TreeNode root2) {
         if (root1 == null && root2 == null) return true;
         if (root1 == null || root2 == null || root1.val != root2.val) return false;
         boolean checkOne = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
         boolean checkTwo = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
         return checkOne || checkTwo;
    }
    static List<Integer> leafList(TreeNode root) {
         List<Integer> results = new ArrayList<>();
         if (root == null) return results;
         Stack<TreeNode> stack = new Stack<>();
         stack.push(root);
         while (!stack.isEmpty()){
             TreeNode current = stack.pop();
             if (current.left == null && current.right == null) results.add(current.val);
             if (current.right != null) stack.push(current.right);
             if (current.left != null) stack.push(current.left);
         }
         return results;
      }
      static int findBottomRightValue(TreeNode root) {
          Queue<TreeNode> q = new LinkedList<>();
          q.add(root);
          TreeNode current = null;
          while(!q.isEmpty()) {
              current = q.poll();
              if(current.left != null) q.add(current.left);
              if(current.right != null) q.add(current.right);
          }
          return current.val;
      }
      static boolean hasPathSum(TreeNode root, int targetSum) {
          if(root == null) return false;
          if(root.left == null && root.right == null && targetSum - root.val == 0) return true;
          return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
      }

    static List<Integer> pathFinder (TreeNode root, int target) {
        List<Integer> results = new ArrayList<>();
        findPath(root, target);
        return results;
    }
    static List<Integer> findPath(TreeNode root, Integer target) {
        if (root == null) return null;
        if (root.val == target){
            ArrayList<Integer> results = new ArrayList<>();
            results.add(root.val);
            return results;
        }
        List<Integer> leftPath = findPath(root.left, target);
        if(leftPath != null) {
            leftPath.add(root.val);
            return leftPath;
        }
        List<Integer> rightPath = findPath(root.right, target);
        if(rightPath != null) {
            rightPath.add(root.val);
            return rightPath;
        }
        return null;
    }

    static List<List<Integer>> allTreePaths(TreeNode root) {
         List<List<Integer>> results = new ArrayList<>();
         backtrack(results,new ArrayList<>(), root);
         return  results;
    }
    static void backtrack(List<List<Integer>> results, List<Integer> tempList, TreeNode root){
        if (root == null) return;
        tempList.add(root.val);
        if (root.left == null && root.right == null) {
            results.add(new ArrayList<>(tempList));
        } else {
            backtrack(results, tempList, root.left);
            backtrack(results, tempList, root.right);
        }
        tempList.remove(tempList.size() - 1);
    }

    static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null) return results;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> levelList = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode current = q.poll();
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);
                levelList.add(current.val);
            }
            results.add(levelList);
        }
        return results;
    }

    static List<Integer> levelAverages(TreeNode root)  {
        List<Integer> results = new ArrayList<>();
        if(root == null) return results;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            int sum = 0;
            for(int i = 0; i < size; i++) {
                TreeNode current = q.poll();
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);
                sum += current.val;;
            }
            results.add(sum / size);
        }
        return results;
    }

}
