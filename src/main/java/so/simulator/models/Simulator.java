package so.simulator.models;

import so.simulator.models.exceptions.CPUException;
import so.simulator.models.exceptions.ErrorCode;
import so.util.observer.Observable;
import so.util.observer.Observer;
import so.util.observer.ObserverEvent;
import so.util.queue.Queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class Simulator extends Observable implements Runnable{

    private final SimulationStatus status;
    private ArrayList<Process> blockedList;
    private Queue<Process> readyQueue;
    private CPU cpu;
    private ProcessCreator creator;

    public Simulator(int simulatorTime, CPU cpu, ProcessCreator creator) {
        //crea la lista de bloqueados
        blockedList = new ArrayList<>();

        //Crea una cola que compara los procesos por su nombre
        readyQueue = new Queue<>(Comparator.comparing(Process::getProcessName));
        //Crea la el objeto encargado de manejar el turno de uso de la CPU
        this.cpu = cpu;
        this.creator = creator;
        status = new SimulationStatus(simulatorTime);
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

    @Override
    public void run() {
        try {
            while (status.isRunning()) {
                this.update();
                //duerme el hilo segundos
                TimeUnit.SECONDS.sleep(1);
                //notifica a los observadores que actualicen la vista
                this.notify();
            }
        } catch (InterruptedException | CPUException e) {
            e.printStackTrace();
        }
    }

    public SimulationStatus update() throws CPUException {
        status.update();
        Process newProcess = creator.update();
        Process lastProcess = cpu.update();
        if (newProcess != null){
            readyQueue.push(newProcess);
            status.processCreated = true;
        }
        if (lastProcess != null && lastProcess.isAlive()){
            readyQueue.push(lastProcess);
            status.cpuExpirationTime = true;
        }
        if (cpu.isFree() && hasProcessesReady()){
            cpu.runProcess(readyQueue.poll());
            status.newProcessRunning = true;
        }
        if (!cpu.isFree() && cpu.getProcessRunning().isBlocked()){
            status.processBlocked = true;
        }
        return status;
    }

    /**
     * Indica si aún hay procesos en la cola de espera
     */
    public boolean hasProcessesReady(){
        return !readyQueue.isEmpty();
    }


    /**
     * Obtienen el tiempo de ejcución restante que la CPU
     * dispone para el proceso actual
     * @return segundos de ejecución para el proceso actual
     */
    public int getCPUTimeRemaining() {
        return cpu.getExecutionTimeRemaining();
    }

    /**
     * Obtiene el tiempo que le queda al proceso en ejecución en la CPU
     * para completar su tiempo de ejecución general
     * @return segundos restantes de ejecución total del proceso
     */
    public int getProcessTimeRemaining() {
        return cpu.getProcessRunning().getLifeTimeRemaining();
    }

    public Process getRunningProcess(){
        return cpu.getProcessRunning();
    }

    public boolean hasCPUAvailable(){
        return cpu.isFree();
    }

    public SimulationStatus getStatus() {
        return status;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }
}
