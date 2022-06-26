import so.simulator.models.CPU;
import so.simulator.models.Process;
import so.simulator.models.exceptions.CPUException;
import so.util.queue.Queue;

import java.util.Comparator;

public class CPUTest {

    public static void main(String[] args) {
        Queue<Process> readyQueue = new Queue<>(Comparator.comparing(Process::getProcessName));
        readyQueue.push(new Process(10, 40, 5));
        readyQueue.push(new Process(-1, 40, 5));
        readyQueue.push(new Process(-1, -1, -1));
        readyQueue.push(new Process(10, 5, -1));
        readyQueue.push(new Process(10, 5, 0));
        readyQueue.push(new Process(0, 0, 0));
        readyQueue.push(new Process(10, 10, 2));

        CPU cpu = new CPU(5);
        for (int i = 0; i < 100; i++) {
            if (cpu.isFree() && !readyQueue.isEmpty()){
                try {
                    cpu.runProcess(readyQueue.poll());
                    System.out.println("Proceso en ejecucion " + cpu.getProcessRunning());
                } catch (CPUException e) {
                    System.out.println(e.getCode());
                }
            }else {
                System.out.print(cpu.getProcessRunning());
                Process process = cpu.update();
                if (process != null && process.isAlive()){
                    readyQueue.push(process);
                }
            }
            System.out.println(" IDLE? = "+ cpu.isFree());
        }
    }
}
