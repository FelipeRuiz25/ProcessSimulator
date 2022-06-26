import so.simulator.models.CPU;
import so.simulator.models.ProcessCreator;
import so.simulator.models.SimulationStatus;
import so.simulator.models.Simulator;
import so.simulator.models.exceptions.CPUException;

public class SimulatorTest {

    public SimulatorTest() {
    }

    public static void main(String[] args) {
        CPU cpu = new CPU(5);
        ProcessCreator creator = new ProcessCreator(5, 6,30);
        Simulator simulator = new Simulator(100,cpu, creator);
        SimulationStatus status = simulator.getStatus();
        while (status.isRunning()){
            try {
                System.out.println(simulator.update());
            } catch (CPUException e) {
                e.printStackTrace();
            }
        }
    }
}
