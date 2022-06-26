package so.simulator.models;

/**
 *
 */
public class SimulationStatus {

    /**
     * Tiempo Total(en segundos) de ejecucion de la simulaci�n
     */
    final int maxSimulatorTime;
    /**
     * Tiempo transcurrido de la simulacion
     */
    int simulatorClock;

    boolean usingCPU, processCreated, cpuExpirationTime, processBlocked, newProcessRunning;

    public SimulationStatus(int simulatorTime) {
        this.maxSimulatorTime = simulatorTime;
    }

    public boolean isRunning(){
        return maxSimulatorTime > simulatorClock;
    }

    public void update() {
        simulatorClock++;
        processCreated = false;
        cpuExpirationTime = false;
        processBlocked = false;
        newProcessRunning = false;
        usingCPU = false;
    }

    public int getMaxSimulatorTime() {
        return maxSimulatorTime;
    }

    public int getSimulatorClock() {
        return simulatorClock;
    }

    public boolean isProcessCreated() {
        return processCreated;
    }

    public boolean isCpuExpirationTime() {
        return cpuExpirationTime;
    }

    public boolean isProcessBlocked() {
        return processBlocked;
    }

    public boolean isNewProcessRunning() {
        return newProcessRunning;
    }

    @Override
    public String toString() {
        return "{Simulation time=" + maxSimulatorTime +
                ", clock=" + simulatorClock +
                ", processCreated=" + processCreated +
                ", cpuExpTime=" + cpuExpirationTime +
                ", processBlocked=" + processBlocked +
                ", newProcessRunning=" + newProcessRunning +
                "}";
    }
}
