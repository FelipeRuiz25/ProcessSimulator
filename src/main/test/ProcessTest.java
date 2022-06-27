import so.simulator.models.Process;

public class ProcessTest {

    Process process;

    public void test() {
        System.out.println("Process: " +
                process.getProcessName() +
                "TL " + process.getTimeLife() +
                "IO start " + process.getStartIOOperation() +
                "IO time " + process.getTimeIOOperation()
        );
        while (process.isAlive()) {
            process.update();
        }
        System.out.println("Passed");
    }

    public void outputTest() {
        System.out.println("Process: " +
                process.getProcessName() +
                " TL " + process.getTimeLife() +
                " IO start " + process.getStartIOOperation() +
                " IO time " + process.getTimeIOOperation()
        );
        do {
            System.out.print("BL = " + process.isBlocked() + " TR=");
            System.out.println(process.getLifeTimeRemaining());
            process.update();
        } while (process.isAlive());
        System.out.println("Passed");
    }

    public static void main(String[] args) {
        ProcessTest test = new ProcessTest();

        Process[] list = new Process[]{
                new Process(10, 40, 5),
                new Process(-1, 40, 5),
                new Process(10, 5, -1),
                new Process(10, 5, 2),
                new Process(10, 5, 0),
                new Process(0, 0, 0),
                new Process(10, 10, 2)
        };


        for (Process p: list) {
            test.process = p;
            test.outputTest();
        }
    }
}
