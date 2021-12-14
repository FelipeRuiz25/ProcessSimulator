package so.simulator.models;

import so.util.observer.Observable;
import so.util.observer.Observer;
import so.util.observer.ObserverEvent;

import java.util.concurrent.TimeUnit;

public class ProcessExecutor extends Thread {

    private final Observable observable = new Observable();
    public static final int TIME_STEP = 1;

    private int executionTime;
    private int executionTimeRemaining;

    private Process processRunning;

    public ProcessExecutor(int executionTime) {
        this.executionTime = executionTime;
    }

    public void runProcess(Process process) throws ProcessException {
        if (process == null) throw new ProcessException();
        reset();
        processRunning = process;
        start();
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
            observable.notify(ObserverEvent.TIME_EXPIRATION);
        }else {
            observable.notify(ObserverEvent.BLOCK);
        }
    }

    private boolean hasTime() {
        return executionTimeRemaining > 0;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    @Override
    public void interrupt(){
        super.interrupt();
        reset();
    }

    public Process reset(){
        executionTimeRemaining = executionTime;
        return processRunning;
    }
}
