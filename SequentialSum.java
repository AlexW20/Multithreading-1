public class SequentialSum {
    int sum(int[] array, int start, int end) {
        if (end - start == 1) return array[start] + array[end];

        if (end - start == 0) return array[start];

        int mid = start + (end - start) / 2;

        return sum(array, start, mid) + sum(array, mid + 1, end);
    }
}
