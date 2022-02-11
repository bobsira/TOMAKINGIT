package binarysearch;

public class code {
    static int firstOccurrence(int[] arr, int x){
        int result = - 1, start = 0 , end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (x == arr[mid]) {
                result = mid;
                end = mid - 1;
            } else if (x < arr[mid]) end = mid - 1;
            else start = mid + 1;
        }
        return result;
    }

    static int countElement(int[] arr, int n, int x){
        // find first occurrence
        // find last occurrence
        // sub track the results
        return 0;
    }

    // find how many times a sorted array is rotated
    // is just the index of the minimum element of this array
    static int findRotationCount(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            if (arr[low] <= arr[high]) return low; // case 1
            int mid = low + (high - low) / 2;
            int next = (mid + 1) % arr.length , previous = (mid + arr.length - 1) % arr.length;
            if (arr[mid] <= arr[next] && arr[mid] <= arr[previous]) // case 3
                return mid;
            // either of these two will not be sorted
            else if (arr[mid] <= arr[high]) high = mid - 1;
            else if(arr[mid] >= arr[low]) low = mid + 1;
        }
        return -1;
    }


    // // return the index
    // return -1 when not found
    static int binarySearch(int[] arr, int target) {
        int start = 0 , end = arr.length - 1;
        while (start <= end){
            int mid = start + ( end - start) / 2;
            if (target < arr[mid]) end = mid - 1;
            else if (target > arr[mid]) start = mid + 1;
            else return mid;
        }
        return -1;
    }

    // find whether the array is sorted in ascending or descending
    // apply binary search
    static int orderAgnosticBS(int[] arr, int target, int start, int end) {
        boolean isAsc = arr[start] < arr[end];
        while (start <= end){
            int mid = start + ( end - start) / 2;
            if (target < arr[mid]) end = mid - 1;
            if (isAsc) {
                if (target < arr[mid]) end = mid - 1;
                else  start = mid + 1;
            } else {
                if (target > arr[mid]) end = mid - 1;
                else  start = mid + 1;
            }
        }
        return -1;
    }

    // return the index of smallest no >= target
    // return start when not found
    static int ceiling(int[] arr, int target) {
        if (target > arr[arr.length - 1]) return -1;
        int start = 0 , end = arr.length - 1;
        while (start <= end){
            int mid = start + ( end - start) / 2;
            if (target < arr[mid]) end = mid - 1;
            else if (target > arr[mid]) start = mid + 1;
            else return mid;
        }
        return start;
    }

    // return the index: greatest number <= target
    // return end when not found
    static int floor(int[] arr, int target) {
        int start = 0 , end = arr.length - 1;
        while (start <= end){
            int mid = start + ( end - start) / 2;
            if (target < arr[mid]) end = mid - 1;
            else if (target > arr[mid]) start = mid + 1;
            else return mid;
        }
        return end;
    }

    // same concept as ceiling (smallest number greater than target, strictly greater)
    static char nextGreatestLetter(char[] letters, char target) {
        int start = 0 , end = letters.length - 1;
        while (start <= end){
            int mid = start + ( end - start) / 2;
            if (target < letters[mid]) end = mid - 1;
            else  start = mid + 1;
        }
        return letters[start % letters.length];
    }

    // https://leetcode.com/problems/peak-index-in-a-mountain-array/
    // https://leetcode.com/problems/find-peak-element/
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0 , end = arr.length - 1;
        while (start < end){
            int mid = start + ( end - start) / 2;
            if (arr[mid] > arr[mid + 1]) {
                // you are in dec part of array
                // this may be the ans, but look at left
                // this is why end != mid - 1
                end = mid;
            }
            else {
                // you are in asc part of array
                start = mid + 1; // because we know that mid+1 element > mid element
            }
        }
        return start;// or return end as both are =
        // in the end, start == end and pointing to the largest number because of the 2 checks above
        // start and end are always trying to find max element in the above 2 checks
        // hence, when they are pointing to just one element, that is the max one because that is what the checks say
        // more elaboration: at every point of time for start and end, they have the best possible answer till that time
        // and if we are saying that only one item is remaining, hence cuz of above line that is the best possible ans
    }


}
