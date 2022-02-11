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
        hasPath(root, target, results);
        return results;
    }
    static boolean hasPath (TreeNode root, int target, List<Integer> results) {
         if (root == null) return false;
         results.add(root.val);
         if (hasPath(root.left, target, results) || hasPath(root.right, target, results)) return true;
         results.remove(results.size() - 1);
         return false;
    }

    static List<List<Integer>> allTreePaths(TreeNode root) {
         List<List<Integer>> results = allTreePathsHelper(root);
         if (results == null) return null;
         else {
             for (List<Integer> result : results) Collections.reverse(result);
             return results;
         }
    }
    static List<List<Integer>> allTreePathsHelper(TreeNode root) {
         if (root == null) return null;

         if (root.left == null && root.right == null) {
             List<List<Integer>> paths = new ArrayList<>();
             paths.add(new ArrayList<>(List.of(root.val)));
             return paths;
         }

         List<List<Integer>> paths = new ArrayList<>();

        List<List<Integer>> leftSubPaths = allTreePathsHelper(root.left);
        if (leftSubPaths != null) {
            for (List<Integer> subPath : leftSubPaths) {
                subPath.add(root.val);
                paths.add(subPath);
            }
        }

        List<List<Integer>> rightSubPaths = allTreePathsHelper(root.right);
        if (rightSubPaths != null) {
            for (List<Integer> subPath : rightSubPaths) {
                subPath.add(root.val);
                paths.add(subPath);
            }
        }

        return paths;
    }
//
//    static List<Integer> pathFinder (TreeNode root, int target){
//         List<Integer> results = pathFinderHelper(root, target);
//         if (results == null) return null;
//         else {
//             Collections.reverse(results);
//             return results;
//         }
//    }
//    static List<Integer> pathFinderHelper (TreeNode root, int target){
//         if (root == null) return null;
//         if (root.val == target) return new ArrayList<>(root.val);
//
//         List<Integer> leftPath = pathFinderHelper(root.left, target);
//         if (leftPath != null) {
//             leftPath.add(root.val);
//             return leftPath;
//         }
//
//        List<Integer> rightPath = pathFinderHelper(root.right, target);
//        if (rightPath != null) {
//            rightPath.add(root.val);
//            return rightPath;
//        }
//
//        return null;
//    }

//    static List<Integer> findPath(TreeNode root, int targetVal, List<Integer> pathList){
//         if (root == null) return null;
//         if (root.val == targetVal) {
//             pathList.add(root.val);
//             return pathList;
//         }
//         List<Integer> leftPath = findPath(root.left, targetVal, pathList);
//         if (leftPath != null) {
//             pathList.add(root.val);
//             return leftPath;
//         }
//    }
}
