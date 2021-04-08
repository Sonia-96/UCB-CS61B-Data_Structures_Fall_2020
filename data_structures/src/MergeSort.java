public class MergeSort {
    public static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int l = 0;
        int r = 0;
        int index = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                res[index] = left[l];
                l += 1;
            } else {
                res[index] = right[r];
                r += 1;
            }
            index += 1;
        }
        for (; l < left.length; l++, index++) {
            res[index] = left[l];
        }
        for (; r < right.length; r++, index++) {
            res[index] = right[r];
        }
        return res;
    }

    public static int[] mergeSort(int[] arr, int low, int high) {
        if (arr.length == 0) {
            int[] res = {};
            return res;
        }
        if (low == high) {
            int[] res = {arr[low]};
            return res;
        }
        int mid = (low + high) / 2;
        int[] left = mergeSort(arr, low, mid);
        int[] right = mergeSort(arr, mid + 1, high);
        return merge(left, right);
    }

    public static void main(String[] args) {
        int[] A = {5, 3, 7, 1, 12, 0};
        int[] res = mergeSort(A, 0, A.length - 1);
        for (int s : res) {
            System.out.print(s + " ");
        }
    }
}
