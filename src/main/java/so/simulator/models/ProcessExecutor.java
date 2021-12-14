package so.simulator.models;

import so.util.observer.Observable;
import so.util.observer.ObserverEvent;

import java.util.concurrent.TimeUnit;

public class ProcessExecutor extends Observable implements Runnable {

    public static final int TIME_STEP = 1;

    private int executionTime;
    private int executionTimeRemaining;

    private Process processRunning;

    public ProcessExecutor(int executionTime) {
        this.executionTime = executionTime;
    }

    public void runProcess(Process process) throws ProcessException {
        if (processRunning == null) throw new ProcessException();
        executionTimeRemaining = executionTime;
        processRunning = process;
    }

    @Override
    public void run() {
        try {
            while (processRunning.isAlive() && hasTime()) {
                //Resta el tiempo de ejecución para el proceso actual
                executionTimeRemaining -= TIME_STEP;
                //ejecuta el proceso el tiempo asignado
                processRunning.run(TIME_STEP);
                //duerme el hilo segundos
                TimeUnit.SECONDS.sleep(TIME_STEP);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (processRunning.isAlive()){
            notify(ObserverEvent.TIME_EXPIRATION);
        }else {
            notify(ObserverEvent.BLOCK);
        }
    }

    private boolean hasTime() {
        return executionTimeRemaining > 0;
    }
}
