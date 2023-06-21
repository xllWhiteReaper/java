package programming_2.sorting_algorithms_benchmarking.utils;

import java.util.function.Supplier;

public class TimeManager {
    private long startTime;
    private Supplier<Long> currentTime = System::currentTimeMillis;
    private String taskName;
    private final String UNITS = "ms";

    public TimeManager() {
        this("");
    }

    public TimeManager(String taskName) {
        this.taskName = taskName;
        startTimer();
    }

    public void startTimer() {
        restartTimer();
    }

    public void restartTimer() {
        startTime = currentTime.get();
    }

    public long getElapsedTime() {
        return currentTime.get() - startTime;
    }

    public void printElapsedTime() {
        final String message = taskName != "" ? "Task " + taskName + " took " + getElapsedTime() + UNITS
                : "Task" + " took " + getElapsedTime() + UNITS;
        System.out.println(message);
    }
}
