import java.util.concurrent.RecursiveTask;

public class ParallelSum extends RecursiveTask<Integer> {
    private int[] array;
    private int start;
    private int end;

    public ParallelSum(int [] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {
        if (end - start == 1) return array[start] + array[end];
        if (end - start == 0) return array[start];

        int mid = start + (end-start)/2;
        ParallelSum left = new ParallelSum(array, start, mid);
        ParallelSum right = new ParallelSum(array, mid + 1, end);
        invokeAll(left, right);
        return left.getRawResult() + right.getRawResult();
    }
}
