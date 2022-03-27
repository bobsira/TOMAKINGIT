package linkedlist;

import java.util.*;

public class code {



     public class ListNode {
          int val;
          ListNode next;
          ListNode random;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

      // determine duplicate by comparing a node value's to the node after it in the list
     // if it is a duplicate, we change the next pointer of the current node so that
      // it skips the next node and points directly to the one after the next node
     public ListNode deleteDuplicates(ListNode head) {
         ListNode current = head;
         while (current != null && current.next != null){
             if (current.val == current.next.val) {
                 current = current.next.next;
             } else {
                 current = current.next;
             }
         }
         return head;
     }

    public ListNode deleteDuplicatesII(ListNode head) {
         ListNode sentinel = new ListNode(0);
        // predecessor = the last node before the sublist of duplicates
         ListNode predecessor = sentinel;

         while (head != null){
             // if it's a beginning of duplicates sublist skip all duplicates
             if (head.next != null && head.val == head.next.val){
                 // move till the end of duplicates sublist
                 while (head.next != null && head.val == head.next.val){
                     head = head.next;
                 }
                 // skip all duplicates
                 predecessor.next = head.next;
             } else {
                 // otherwise, move predecessor
                 predecessor = predecessor.next;
             }
             // move forward
             head = head.next;
         }
         return sentinel.next;
    }

    //Find the end of the first half.
    //Reverse the second half.
    //Determine whether there is a palindrome or not
    //Restore the list.
    //Return the result.
    public boolean isPalindrome(ListNode head) {
         if (head == null);
         ListNode firstHalfEnd = endOfFirstHalf(head);
         ListNode secondHalfStart = reverseList(firstHalfEnd.next);

         ListNode p1 = head;
         ListNode p2 = secondHalfStart;
         boolean result = true;
         while (result && p2 != null && p1 != null) {
             if (p1.val != p2.val) result = false;
             p1 = p1.next;
             p2 = p2.next;
         }
         firstHalfEnd.next = reverseList(secondHalfStart);
         return result;
    }
    private ListNode reverseList(ListNode head) {
         ListNode current = head;
         ListNode previous = null;
         while (current != null){
             ListNode tempNext = current.next;
             current.next = previous;
             previous = current;
             current = tempNext;
         }
         return previous;
    }
    private ListNode endOfFirstHalf(ListNode head){
         ListNode fast = head;
         ListNode slow = head;
         while (fast.next != null && fast.next.next != null){
             fast = fast.next.next;
             slow = slow.next;
         }
         return slow;
    }

    //  Compute the length of the list n
    // Find the old tail and connect it with the head (form the ring)
    // Find the new tail
    // Break the ring
    public ListNode rotateRight(ListNode head, int k){

         if (head == null ) return null;
         if (head.next == null) return head;

         ListNode oldTail = head;
         int n = 1;
         while(oldTail.next != null) {
             oldTail = oldTail.next;
             n++;
         }

         oldTail.next = head;

         ListNode newTail = head;
         for (int i = 0; i < n - (k % n) - 1; i++)
             newTail = newTail.next;

         ListNode newHead = newTail.next;

         newTail.next = null;

         return  newHead;
    }

    // Initialize current node to dummy head of the returning list.
    //Initialize carry to 00.
    //Initialize pp and qq to head of l1l1 and l2l2 respectively.
    // Loop through lists l1l1 and l2l2 until you reach both ends.
    //Set xx to node pp's value. If pp has reached the end of l1l1, set to 00.
    //Set yy to node qq's value. If qq has reached the end of l2l2, set to 00.
    //Set sum = x + y + carrysum=x+y+carry.
    //Update carry = sum / 10carry=sum/10.
    //Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
    //Advance both pp and qq.
    //Check if carry = 1carry=1, if so append a new node with digit 11 to the returning list.
    //Return dummy head's next node.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         ListNode sentinel = new ListNode(0);
         ListNode tail = sentinel;

         int carry = 0;
         ListNode current1 = l1;
         ListNode current2 = l2;

         while (current1 != null || current2 != null || carry == 1){

             int val1 = current1 != null ? current1.val : 0;
             int val2 = current2 != null ? current2.val : 0;
             int sum = val1 + val2 + carry;

             carry = sum / 10;
             int digit = sum % 10;

             tail.next = new ListNode(digit);
             tail = tail.next;

             if (current1 != null) current1 = current1.next;
             if(current2 != null) current2 = current2.next;
         }
         return sentinel.next;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // Empty list
         if (head == null) return null;
         ListNode previous = null;
         ListNode current = head;

        // Move the two pointers until they reach the proper starting point in the list.
         while (m > 1) {
             previous = current;
             current = current.next;
             m--;
             n--;
         }

        // The two pointers that will fix the final connections.
         ListNode connection = previous;
         ListNode tail = current;

        // Iteratively reverse the nodes until n becomes 0.
         while ( n > 0){
             ListNode tempNext = current.next;
             current.next = previous;
             previous = current;
             current = tempNext;
             n--;
         }

        // Adjust the final connections as explained in the algorithm
        // We use the con pointer to attach to the prev pointer
        if (connection != null) connection.next = previous;
        else  head = previous;
        // use of the tail pointer to connect to the node next to the prev node
        tail.next = current;
        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
         // https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/183356/Java-O(n)-solution-with-super-detailed-explanation-and-illustration
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode pointer = sentinel;
        while (pointer != null) {
            ListNode node = pointer;
            // first check whether there are k nodes to reverse
            for (int i = 0; i < k && node != null; i++) node = node.next;
            if (node == null) break;
            // now we know that we have k nodes, we will start from the first node
            ListNode previous = null;
            ListNode current = pointer.next;
            for (int i = 0; i < k; i++){
                ListNode tempNext = current.next;
                current.next = previous;
                previous = current;
                current = tempNext;
            }
            ListNode tail = pointer.next;
            tail.next = current;
            pointer.next = previous;
            pointer = tail;
        }

        return sentinel.next;
    }

    public ListNode copyRandomList(ListNode head) {
         if(head == null) return null;
         Map<ListNode, ListNode> oldToCopyMap = new HashMap<>();
         ListNode current = head;
         while (current != null) {
             ListNode copy = new ListNode(current.val);
             oldToCopyMap.put(current, copy);
             current = current.next;
         }
         current = head;
         while (current != null){
             ListNode copy = oldToCopyMap.get(current);
             copy.next = oldToCopyMap.get(current.next);
             copy.random = oldToCopyMap.get(current.random);
             current = current.next;
         }
         return oldToCopyMap.get(head);
    }

    public ListNode zipperList(ListNode head1, ListNode head2){
        ListNode tail = head1;

         ListNode current1 = head1.next;
         ListNode current2 = head2;
         int count = 0;

         while (current1 != null && current2 != null) {
             if (count % 2 == 0) {
                 tail.next = current2;
                 current2 = current2.next;
             } else {
                 tail.next = current1;
                 current1 = current1.next;
             }
             count++;
             tail = tail.next;
         }

         if (current1 != null) tail.next = current1;
         if (current2 != null) tail.next = current2;

         return head1;
    }


    public void mergeTwoListsAlternativePositions(ListNode list1, ListNode list2){
        // https://www.youtube.com/watch?v=EPxaQgnxLfE&ab_channel=CodingSimplified

         ListNode list1Next;
         ListNode list2Next;

         ListNode first = list1;
         ListNode second = list2;

         while (list1 != null && list2 != null){
             list1Next = list1.next;
             list1.next = list2;
             list1 = list1Next;

             list2Next = list2.next;
             list2.next = list1Next;
             list2 = list2Next;
         }

         second = list2;

//         ListNode pCurrl1 = list1;
//         ListNode pNextl1 = null;
//         ListNode pCurrl2 = list2;
//         ListNode pNextl2 = null;
//         // while there are available positions in list1
//        while (pCurrl1 != null && pCurrl2 != null){
//            // save next pointers
//            pNextl1 = pCurrl1.next;
//            pNextl2 = pCurrl2.next;
//            //
//            pCurrl2.next = pCurrl1; // change next pointer of l2 current
//            pCurrl1.next = pCurrl2; // change next pointer of l1 current
//
//            // update current pointers for next iteration
//            pCurrl1 = pNextl1;
//            pCurrl2 = pNextl2;
//        }
//        list2 = pCurrl2;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(0);
        ListNode tail = sentinel;
        ListNode current1 = list1;
        ListNode current2 = list2;
        while (current1 != null && current2 != null){
            if (current1.val < current2.val) {
                tail.next = current1;
                current1 = current1.next;
            } else {
                tail.next = current2;
                current2 = current2.next;
            }
            tail = tail.next;
        }
        if(current1 != null) tail.next = current1;
        if(current2 != null) tail.next = current2;
        return sentinel.next;
    }
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
         if (list1 == null) return list2;
         if (list2 == null) return list1;
         if (list1.val < list2.val){
             list1.next = mergeTwoListsRecursive(list1.next, list2);
             return list1;
         } else {
             list2.next = mergeTwoListsRecursive(list1, list2.next);
             return list2;
         }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>( (Comparator<ListNode>) (o1, o2) -> o1.val - o2.val);
        for (ListNode head : lists) {
            if (head != null)
                heap.add(head);
        }
        ListNode sentinel = new ListNode(0);
        ListNode tail = sentinel;

        while (!heap.isEmpty()){
            ListNode current = heap.poll();
            tail.next = current;
            tail = tail.next;

            if (current.next != null) heap.add(current.next);

        }

        return sentinel.next;

    }
}

