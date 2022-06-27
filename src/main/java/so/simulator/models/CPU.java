package so.simulator.models;

import so.simulator.models.exceptions.CPUException;
import so.simulator.models.exceptions.ErrorCode;

public class CPU {

    private int executionTime;
    private int executionTimeRemaining;

    private Process processRunning;

    /**
     * Crea la unidad de CPU y le asigna el quantum de ejecucion
     * @param executionTime
     */
    public CPU(int executionTime) {
        this.executionTime = executionTime;
        this.executionTimeRemaining = executionTime;
    }

    public void runProcess(Process process) throws CPUException {
        if (process == null) throw new CPUException(ErrorCode.NO_ASSIGNED_PROCESS);
        processRunning = process;
    }

    public Process liberateCPU(){
        executionTimeRemaining = executionTime;
        Process process = processRunning;
        processRunning = null;
        return process;
    }

    public int getExecutionTimeRemaining() {
        return executionTimeRemaining;
    }

    public Process getProcessRunning() {
        return processRunning;
    }

    public Process update(){
        if (!isFree()){
            if (!processRunning.isBlocked())
                executionTimeRemaining--;
            //Ejecuta el proceso
            processRunning.update();
            //Si se acaba el quantum o el tiempo de vida del proceso libera la CPU
            if (executionTimeRemaining < 0 || !processRunning.isAlive()){
                return liberateCPU();
            }
        }
        return null;
    }

    public boolean isFree(){
        return processRunning == null;
    }
}
