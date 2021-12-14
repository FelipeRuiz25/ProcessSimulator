package so.simulator.models;

import so.util.observer.Observer;
import so.util.queue.Queue;

import java.util.ArrayList;
import java.util.Comparator;

public class CPU {

    private ArrayList<Process> blockedList;
    private Queue<Process> readyQueue;
    private ProcessExecutor executor;

    public CPU(int executionTime) {
        //crea la lista de bloqueados
        blockedList = new ArrayList<>();
        //Crea una cola que compara los procesos por su nombre
        readyQueue = new Queue<>(Comparator.comparing(Process::getProcessName));
        //Crea la el objeto encargado de manejar el turno de uso de la CPU
        executor = new ProcessExecutor(executionTime);
    }

    public ArrayList<String> getReadyQueue(){
        ArrayList<String> queue = new ArrayList<>();
        for (Process p : readyQueue) {
            queue.add(p.getProcessName());
        }
        return queue;
    }

    public ArrayList<String> getBlockedList(){
        ArrayList<String> list = new ArrayList<>();
        blockedList.forEach(process -> list.add(process.getProcessName()));
        return list;
    }

    public void addExecutionObserver(Observer observer){
        executor.addObserver(observer);
    }

    public void addProcess(int secondsOfExecution){
        Process process = new Process(secondsOfExecution);
        readyQueue.push(process);
    }

    public void dispatchProcess(){
        try {
            executor.runProcess(readyQueue.poll());
        } catch (ProcessException e) {
            e.printStackTrace();
        }
    }

    public void blockProcess(){
        blockedList.add(executor.reset());
    }

    public void wakeUpProcess(String processName){
        // TODO: 14/12/21 Testear código
        Process process = blockedList.stream()
                .filter(p -> p.getProcessName().equals(processName))
                .findFirst().orElse(null);
    }
}
