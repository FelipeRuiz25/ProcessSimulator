package so.simulator.models;

import so.simulator.models.exceptions.CPUException;
import so.simulator.models.exceptions.ErrorCode;
import so.util.observer.Observer;
import so.util.queue.Queue;

import java.util.ArrayList;
import java.util.Comparator;

public class ProcessStateManager {

    private ArrayList<Process> blockedList;
    private Queue<Process> readyQueue;
    private CPU cpu;

    public ProcessStateManager(Observer observer) {
        //crea la lista de bloqueados
        blockedList = new ArrayList<>();

        //Crea una cola que compara los procesos por su nombre
        readyQueue = new Queue<>(Comparator.comparing(Process::getProcessName));

        //Crea la el objeto encargado de manejar el turno de uso de la CPU
        cpu = new CPU(observer);
    }

    /**
     * Obtiene la lista de los nombres de los procesos
     * que se encuentran en la cola de espera
     * @return lista de nombres de procesos
     */
    public ArrayList<String> getReadyQueue(){
        ArrayList<String> queue = new ArrayList<>();
        for (Process p : readyQueue) {
            queue.add(p.getProcessName());
        }
        return queue;
    }

    /**
     * Obtiene la lista de los nombres de los procesos
     * que se encuentran en la lista de procesos bloqueados
     * @return lista de nombres de procesos
     */
    public ArrayList<String> getBlockedList(){
        ArrayList<String> list = new ArrayList<>();
        blockedList.forEach(process -> list.add(process.getProcessName()));
        return list;
    }

    public void addCPUObserver(Observer observer){
        cpu.addObserver(observer);
    }

    /**
     * Crea un nuevo proceso y lo ubica en la cola de espera
     * @param secondsOfExecution Los segundos que se ejecutará este procesos
     */
    public void addProcess(int secondsOfExecution){
        Process process = new Process(secondsOfExecution);
        readyQueue.push(process);
    }

    /**
     *
     */
    public void dispatchProcess() throws CPUException {
        Process process = readyQueue.poll();
        if (process == null) throw new CPUException(ErrorCode.NO_PROCESS_READY);
        cpu.runProcess(process);
    }

    public void blockProcess(){
        blockedList.add(cpu.reset());
    }

    public void wakeUpProcess(String processName){
        // TODO: 14/12/21 Testear código
        Process process = blockedList.stream()
                .filter(p -> p.getProcessName().equals(processName))
                .findFirst().orElse(null);
    }

    public boolean hasProcessesReady(){
        return !readyQueue.isEmpty();
    }

    /**
     * Establece el tiempo que la UCP otorgará a cada proceso
     * @param seconds segundos de ejecución
     */
    public void setCpuExecuteTime(int seconds){
       cpu.setExecutionTime(seconds);
    }
}
