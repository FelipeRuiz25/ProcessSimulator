package so.simulator.models;

public class ProcessException extends Exception{

    public static final String NO_ASSIGNED_PROCESS = "No hay procesos asignados para la ejecución";

    public ProcessException() {
        super(NO_ASSIGNED_PROCESS);
    }
}
