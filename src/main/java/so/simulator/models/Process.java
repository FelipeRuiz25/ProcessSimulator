package so.simulator.models;

import java.util.concurrent.TimeUnit;

public class Process extends Thread {

    //Contador para los nombres de los procesos
    private static int sequential;

    //Segundos que el proceso va estar en ejecucuión
    private int secondsOfExecution;
    //Segundos que le quedan al proceso del tiempo de ejecucucion
    private int secondsOfExecutionRemaining;

    /**
     * Crea un nuevo proceso y le asigna su tiempo de ejecución
     * @param secondsOfExecution tiempo de ejecución en segundos
     */
    public Process(int secondsOfExecution) {
        this.setName(String.valueOf(sequential));
        this.secondsOfExecution = secondsOfExecution;
        secondsOfExecutionRemaining = secondsOfExecution;
    }


    @Override
    public void run() {
        super.run();
        try {
            while (secondsOfExecutionRemaining > 0) {
                //duerme el hilo un segundo
                TimeUnit.SECONDS.sleep(1);
                secondsOfExecutionRemaining--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void blockProcess(){
        secondsOfExecutionRemaining = secondsOfExecution;
        super.interrupt();
    }
}
