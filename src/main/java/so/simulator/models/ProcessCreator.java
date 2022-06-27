package so.simulator.models;

import so.util.RandomNumberGenerator;

public class ProcessCreator {

    /**
     * Tiempo maximo que puede tomar una operacion de entrada salida
     */
    private final int maxTimeIO;

    /**
     * Tiempo maximo de vida que puede tener un proceso
     */
    private final int maxTimeProcessLive;
    /**
     * Tiempo maximo en que se creara el proceso luego de la crecion del ultimo proceso
     */
    private final int maxTimeNextProcess;
    /**
     * Tiempo restante para la creacion de un nuevo proceso
     */
    private int timeToNewProcess;

    /**
     * Define el creador de procesos con sus parametros limite para la creacion de nuevos
     * procesos
     * @param maxTimeIO Tiempo maximo que puede tomar una operacion de entrada salida
     * @param maxTimeProcessLive Tiempo maximo de vida que puede tener un proceso
     * @param maxTimeNextProcess Tiempo maximo en que se creeara el proceso luego de la crecion del ultimo proceso
     */
    public ProcessCreator(int maxTimeIO, int maxTimeProcessLive, int maxTimeNextProcess) {
        if (maxTimeIO < 0 || maxTimeProcessLive < 0 || maxTimeNextProcess < 0)
            throw new IllegalArgumentException("todos los parametros deben ser mayores a cero");
        this.maxTimeIO = maxTimeIO;
        this.maxTimeProcessLive = maxTimeProcessLive;
        this.maxTimeNextProcess = maxTimeNextProcess;
        this.timeToNewProcess = 1;
    }

    /**
     * Crea un nuevo proceso con sus parametros aleatorios
     * @see Process
     * @return proceso nuevo
     */
    public Process createProcess(){
        // Define el tiempo de vida del proceso
        int lifeTime = RandomNumberGenerator.getRandIntBetween(1, maxTimeProcessLive+1);
        // Define el segundo de inicio de la operacion de entrada salida 30
        int startIO = RandomNumberGenerator.getRandIntBetween(1, lifeTime+1);
        // Define la duracion de la operacion de entrada salida
        int timeIO = RandomNumberGenerator.getRandIntBetween(0, maxTimeIO+1);
        // Define el tiempo en que se creara el siguiente proceso
        timeToNewProcess = RandomNumberGenerator.getRandIntBetween(1, maxTimeNextProcess+1);
        return new Process(lifeTime, startIO, timeIO);
    }

    public boolean itsTimeToCreate(){
        return timeToNewProcess == 0;
    }

    public Process update(){
        timeToNewProcess--;
        if (itsTimeToCreate()){
            return createProcess();
        }
        return null;
    }

    public int getTimeToNewProcess() {
        return timeToNewProcess;
    }

    @Override
    public String toString() {
        return "ProcessCreator{" +
                "maxTimeIO=" + maxTimeIO +
                ", maxTimeProcessLive=" + maxTimeProcessLive +
                ", maxTimeNextProcess=" + maxTimeNextProcess +
                '}';
    }
}
