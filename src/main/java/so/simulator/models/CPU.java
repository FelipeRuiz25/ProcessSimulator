package so.simulator.models;

import so.util.observer.Observer;
import so.util.queue.Queue;

import java.util.ArrayList;
import java.util.Comparator;

public class CPU {

    private ArrayList<Process> blockedList;
    private Queue<Process> readyQueue;
    private ProcessExecutor running;

    public CPU(int executionTime) {
        blockedList = new ArrayList<>();
        //Crea una cola que compara los procesos por su nombre
        readyQueue = new Queue<>(Comparator.comparing(Process::getProcessName));
        running = new ProcessExecutor(executionTime);
    }

    public void addExecutionObserver(Observer observer){
        running.addObserver(observer);
    }

    public void addProcess(int secondsOfExecution){
        Process process = new Process(secondsOfExecution);
        readyQueue.push(process);
    }
}
