package sorting;

public class code {

    static void mergeSort(int[] arr, int left, int right){
        if (right > left){
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid - 1);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    static void merge(int[] arr, int left, int mid, int right){
        int leftLen = mid - left + 1;
        int rightLen = right - mid;
        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];
        for (int i = 0; i < leftLen; i++) leftArr[i] = arr[i + left];
        for (int i = 0; i < rightLen; i++) rightArr[i] = arr[i + mid + 1];
        int i = 0, j = 0, k = 0;
        while (i < leftLen && j < rightLen){
            if (leftArr[i] < rightArr[j]) arr[k++] = leftArr[i++];
            else arr[k++] = rightArr[j++];
        }
        while (i < leftLen)arr[k++] = leftArr[i++];
        while (j < rightLen) arr[k++] = rightArr[j++];
    }

    static void quickSort(int[] arr, int left, int right){
        if (right > left){
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }
    static int partition(int[] arr, int left, int right){
        int pivot = right, i = left + 1;
        for (int j = left; j <= right; j++) {
            if (arr[j] <= arr[pivot]){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[right];
        arr[right] = arr[i];
        arr[i] = temp;
        return i;
    }
}
