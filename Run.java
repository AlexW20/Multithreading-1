public class Run {
    private int id;
    private int arraySize;
    private String algorithm;
    private int threadsUsed;
    private long timeToComplete;
    private long efficiencyFactor;

    public Run(int id, int arraySize, String algorithm, int threadsUsed, long timeToComplete) {
        this.id = id;
        this.arraySize = arraySize;
        this.algorithm = algorithm;
        this.threadsUsed = threadsUsed;
        this.timeToComplete = timeToComplete;
    }

    public void setEfficiencyFactor(long efficiencyFactor) {
        this.efficiencyFactor = efficiencyFactor;
    }

    public int getId() {
        return id;
    }

    public int getArraySize() {
        return arraySize;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public int getThreadsUsed() {
        return threadsUsed;
    }

    public long getTimeToComplete() {
        return timeToComplete;
    }

    public long getEfficiencyFactor() {
        return efficiencyFactor;
    }
}
