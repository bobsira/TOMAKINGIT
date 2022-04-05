package tree;

import java.util.*;

public class code {
    static class TreeNode {
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

    static int maximumPathSum;
    static int maxPathSum(TreeNode root) {
        maximumPathSum = Integer.MIN_VALUE;
        maxPathDown(root);
        return maximumPathSum;
    }
    static int maxPathDown(TreeNode root) {
        if (root == null) return 0;
        int leftPath = Math.max(0, maxPathDown(root.left));
        int rightPath = Math.max(0, maxPathDown(root.right));
        maximumPathSum = Math.max(maximumPathSum, leftPath + rightPath + root.val);
        return Math.max(leftPath, rightPath) + root.val;
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

    static List<Integer> leftyNodes(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if(root == null) return results;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode current = q.poll();
                if (i == 0) { //  left most node
                    results.add(current.val);
                }
                if(current.left != null) q.add(current.left);
                if(current.right != null) q.add(current.right);

            }
        }
        return results;
    }

    static TreeNode flipTree(TreeNode root){
        if (root == null) return null;
        TreeNode left = flipTree(root.left);
        TreeNode right = flipTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    static boolean binarySearchTreeIncludes(TreeNode root, int target){
        if (root == null) return false;
        if (root.val == target) return true;
        if (target < root.val) return binarySearchTreeIncludes(root.left, target);
        else return binarySearchTreeIncludes(root.right, target);
    }

    static boolean isBinarySearchTree(TreeNode root){
        List<Integer> values = new ArrayList<>();
        inOrderTraversal(root, values);
        return isSorted(values);
    }
    static void inOrderTraversal(TreeNode root, List<Integer> values){
        if (root == null) return;
        inOrderTraversal(root.left, values);
        values.add(root.val);
        inOrderTraversal(root.right, values);
    }
    static boolean isSorted(List<Integer> values){
        for (int i = 1; i < values.size(); i++){
            int current = values.get(i);
            int previous = values.get(i - 1);
            if (current < previous) return false;
        }
        return true;
    }

    static void postOrderTraversal(TreeNode root, List<Integer> values){
        if (root == null) return;
        postOrderTraversal(root.left, values);
        postOrderTraversal(root.right, values);
        values.add(root.val);
    }

    static TreeNode buildTreeInPost(int[] inorder, int[] postorder){
        if (inorder.length == 0 || postorder.length == 0) return null;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);

        int midIndex = 0;
        for (int val : inorder){
            if (val == root.val) break;
            midIndex++;
        }

        int[] leftInOrder = Arrays.copyOfRange(inorder, 0 , midIndex);
        int[] rightInOrder = Arrays.copyOfRange(inorder, midIndex + 1, inorder.length);
        int[] leftPostOrder = Arrays.copyOfRange(postorder, 0, leftInOrder.length);
        int[] rightPostOrder = Arrays.copyOfRange(postorder, leftInOrder.length, postorder.length - 1);

        root.left = buildTreeInPost(leftInOrder, leftPostOrder);
        root.right = buildTreeInPost(rightInOrder, rightPostOrder);

        return root;
    }

    static TreeNode buildTreeInPre(int[] inorder, int[] preorder){
        if (inorder.length == 0 || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);

        int midIndex = 0;
        for (int val : inorder){
            if (val == root.val) break;
            midIndex++;
        }

        int[] leftInOrder = Arrays.copyOfRange(inorder, 0 , midIndex);
        int[] rightInOrder = Arrays.copyOfRange(inorder, midIndex + 1, inorder.length);
        int[] leftPreOrder = Arrays.copyOfRange(preorder, 1, leftInOrder.length + 1);
        int[] rightPreOrder = Arrays.copyOfRange(preorder, leftInOrder.length + 1, preorder.length );

        root.left = buildTreeInPost(leftInOrder, leftPreOrder);
        root.right = buildTreeInPost(rightInOrder, rightPreOrder);

        return root;
    }

    static TreeNode sortedArrayToBST(int[] num) {
        if (num.length == 0) return null;
        TreeNode root = sortedArrayToBST(num, 0, num.length - 1);
        return root;
    }
    static TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBST(num, start, end - 1);
        root.right = sortedArrayToBST(num, start + 1, end);
        return root;
    }
    static TreeNode balanceBST(TreeNode root) {
        // inOrderTraversal(root);
        //return sortedArrayToBST(nums);
        return null;
    }

    // The height or depth of a binary tree is the total number of edges or nodes on the longest path from the root node to the leaf node.
    static int height(TreeNode root) {
        // The height of a subtree rooted at any node will be one more than the maximum height of its left and right subtree.
         if(root == null) return 0;
         int left = height(root.left) + 1;
         int right = height(root.right) + 1;
         return Math.max(left, right);

        // Recursively obtain the height of a tree. An empty tree has -1 height
        // if(root == null) return -1;
        // return 1 + Math.max(height(root.left), height(root.right));
     }
    static int heightIterative(TreeNode root) {
        // Then the height of a tree is equal to the total number of levels in it.
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode current = queue.poll();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            depth++;
        }
        return depth;
    }

    static boolean isBalanced(TreeNode root) {
        // An empty tree satisfies the definition of a balanced tree
        if(root == null) return true;
        // Check if subtrees have height within 1. If they do, check if thesubtrees are balanced
        return Math.abs(height(root.left) - height(root.right)) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    static boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return isValidBST(root, null, null);
    }
    static boolean isValidBST(TreeNode root, Integer min, Integer max){
        if(root == null) return true;
        if(( min != null && root.val <= min  ) || (max != null && root.val >= max)) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // The successor of a node p is the node with the smallest key greater than p.val.
        TreeNode successor = null;
        while(root != null){
            if(p.val >= root.val) root = root.right;
            else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    // Lowest Common Ancestor of a Binary Search Tree
    // static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
    //     if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
    //     return root;
    // Start traversing the tree from the root node.
// If both the nodes p and q are in the right subtree, then continue the search with right subtree starting step 1.
// If both the nodes p and q are in the left subtree, then continue the search with left subtree starting step 1.
// If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's subtrees.
// and hence we return this common node as the LCA.
    // }
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if(p.val < root.val && q.val < root.val) root = root.left;
            else if(p.val > root.val && q.val > root.val) root = root.right;
            else return root;
        }
        return null;
    }

    // Lowest Common Ancestor of a Binary Tree
    //     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         if(root == null) return null;
//         if(root.val == p.val || root.val == q.val) return root;
//         TreeNode leftSearch = lowestCommonAncestor(root.left, p , q);
//         TreeNode rightSearch = lowestCommonAncestor(root.right, p, q);

//         if(leftSearch == null) return rightSearch;
//         if(rightSearch == null) return leftSearch;

//         return root;
    //The key is that we want to root ourselves at a node and then search left and then right for either of the 2 nodes given.
//If we see either node, we will return it, if we do not find the node in a subtree search the value of null will be returned and bubbled up.
//After we search both left and right we ask ourselves what our results mean.
//If we found nothing to the left, we just bubble up what is on the right (whatever that search result may be). This node we sit at cannot be the LCA since the left and right did not yield the 2 nodes we want.
//If we found nothing to the right, we just bubble up what is on the left (whatever that search result may be). This node we sit at cannot be the LCA since the left and right did not yield the 2 nodes we want.
//If both the right and left result are not null, we have found our LCA.
    // https://www.youtube.com/watch?v=py3R23aAPCA&ab_channel=BackToBackSWE
//     }

    // static TreeNode searchBST(TreeNode root, int val) {
    //     if(root == null || val == root.val) return root;
    //     if(val > root.val) return searchBST(root.right, val);
    //     if(val < root.val ) return searchBST(root.left, val);
    //     return root;
    // }

    static TreeNode searchBST(TreeNode root, int val) {
        while(root != null && root.val != val){
            root = root.val < val ? root.right : root.left ;
        }
        return root;
    }

    static TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        if(root.val > val) root.left = insertIntoBST(root.left, val);
        if(root.val < val) root.right = insertIntoBST(root.right, val);
        return root;
    }

    static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if(key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else {
                root.val = minimum(root.right);
                root.right = deleteNode(root.right, root.val);
                return root;
            }
        }
    }
    static int minimum(TreeNode node){
        while(node.left != null) node = node.left;
        return node.val;
    }

    /**
     * findSuccessor - Helper function for finding successor
     * In BST, successor of root is the leftmost child in root's right subtree
     */
//    private TreeNode findSuccessor(TreeNode root) {
//        TreeNode cur = root.right;
//        while (cur != null && cur.left != null) {
//            cur = cur.left;
//        }
//        return cur;
//    }
//    public TreeNode deleteNode(TreeNode root, int key) {
//        // return null if root is null
//        if (root == null) {
//            return root;
//        }
//
//        // delete current node if root is the target node
//        if (root.val == key) {
//            // replace root with root->right if root->left is null
//            if (root.left == null) {
//                return root.right;
//            }
//
//            // replace root with root->left if root->right is null
//            if (root.right == null) {
//                return root.left;
//            }
//
//            // replace root with its successor if root has two children
//            TreeNode p = findSuccessor(root);
//            root.val = p.val;
//            root.right = deleteNode(root.right, p.val);
//            return root;
//        }
//        if (root.val < key) {
//            // find target in right subtree if root->val < key
//            root.right = deleteNode(root.right, key);
//        } else {
//            // find target in left subtree if root->val > key
//            root.left = deleteNode(root.left, key);
//        }
//        return root;
//    }

    }
