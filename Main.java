import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main (String [] args) {
        int[] array;
        int[] N = {10, 100, 1000, 10000};
        int[] threadCount = {1, 2, 4, 10};
        Random rand = new Random();
        Run[] runs = new Run[(N.length * threadCount.length) + N.length];

      
        int runCounter = 0;
        for (int value : N) {
            for (int i : threadCount) {
                array = new int[value];
                for (int k = 0; k < array.length; k++) {
                    // get a random number from 1-100
                    array[k] = rand.nextInt(100 - 1) + 1;
                }

                ParallelSum all = new ParallelSum(array, 0, array.length - 1);
                ForkJoinPool pool = new ForkJoinPool(i);

                long startTime = System.nanoTime(); 
               {
                pool.invoke(all);
               }
                long endTime = System.nanoTime();  
                long duration = (endTime - startTime);

                Run run = new Run(runCounter + 1, value, "Parallel", i, duration);
                runs[runCounter++] = run;
            }
        }

        for (int value : N) {
            array = new int[value];
            for (int j = 0; j < array.length; j++) {
                // get a random number from 1-100
                array[j] = rand.nextInt(100 - 1) + 1;
            }

            SequentialSum seqsum = new SequentialSum();

            long startTime = System.nanoTime(); 
             {
            seqsum.sum(array, 0, array.length - 1);
             }
            long endTime = System.nanoTime();   
            long duration = (endTime - startTime);

            Run run = new Run(runCounter + 1, value, "Sequential", 1, duration);
            runs[runCounter++] = run;
        }

      
        long slowest = runs[0].getTimeToComplete();
        for (Run run : runs) {
            if (run.getTimeToComplete() > slowest) slowest = run.getTimeToComplete();
        }

       
        for (Run run : runs) {
            run.setEfficiencyFactor(slowest / run.getTimeToComplete());
        }

      
        System.out.println("Run | Array Size | Algorithm | Threads | Efficiency");
        for (Run run : runs) {
            System.out.println(" " + run.getId() + "  |     " + run.getArraySize() + "     | " + run.getAlgorithm() + "  |    " + run.getThreadsUsed() + "    |     " + run.getEfficiencyFactor());
        }
    }
}
