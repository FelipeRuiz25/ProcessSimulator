package so.simulator.models;

public class Process {

    //Contador para los nombres de los procesos
    private volatile static int sequential;

    private final String processName;
    //Segundos que el proceso va a estar en ejecución
    private final int timeLife;
    //Segundos que le quedan al proceso del tiempo de ejecucion
    private int lifeTimeRemaining;
    //segundo de inicio del proceso de entrada y salida
    private final int startIOOperation;
    //tiempo de ejecucion de la operacion de entrada y salida
    private final int timeIOOperation;
    //segundos que le quedan a la operacion de entrada y salida
    private int timeIORemaining;
    //TIempo que lleva el proceso en la
    private int timeReady;

    private boolean blocked;

    /**
     * Crea un nuevo proceso y le asigna su tiempo de ejecución
     * @param timeLife tiempo de ejecución en segundos
     */
    public Process(int timeLife, int startIOOperation, int timeIOOperation) {
        this.processName = String.valueOf(sequential);
        this.timeLife = timeLife;
        lifeTimeRemaining = timeLife;
        this.timeIOOperation = timeIOOperation;
        timeIORemaining = timeIOOperation;
        this.startIOOperation = startIOOperation;
        sequential++;
    }

    public void update() {
        if (blocked) {
            timeIORemaining--;
            blocked = !(timeIORemaining <= 0);
        } else {
            lifeTimeRemaining--;
            blocked = (timeLife - lifeTimeRemaining == startIOOperation) && timeIOOperation > 0;
        }
    }

    public boolean isAlive() {
        return lifeTimeRemaining > 0;
    }

    public String getProcessName() {
        return processName;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public int getLifeTimeRemaining() {
        return lifeTimeRemaining;
    }

    public static void resetSequential(){
        sequential = 0;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getStartIOOperation() {
        return startIOOperation;
    }

    public int getTimeIOOperation() {
        return timeIOOperation;
    }

    public int getTimeIORemaining() {
        return timeIORemaining;
    }

    public int getTimeReady() {
        return timeReady;
    }

    public void addTimeReady(){
        timeReady++;
    }

    public static int getSequential() {
        return sequential;
    }

    @Override
    public String toString() {
        return "name='" + processName + '\'' +
                ", TL=" + timeLife +
                ", TLR=" + lifeTimeRemaining +
                ", SIO=" + startIOOperation +
                ", TIO=" + timeIOOperation +
                ", TIOR=" + timeIORemaining +
                ", B=" + blocked;
    }
}
