package so.simulator.controllers;

import so.simulator.models.CPU;
import so.simulator.models.Process;
import so.simulator.models.ProcessCreator;
import so.simulator.models.Simulator;
import so.simulator.views.GuiManager;
import so.simulator.views.ViewGraphics;
import so.simulator.views.components.Output;
import so.util.observer.Observer;
import so.util.observer.ObserverEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, Observer {

    public static final int QUANTUM = 5;

    private GuiManager guiManager;
    private Simulator simulator;

    public Controller() {
        this.guiManager = new GuiManager(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case Commands.BTN_STAR_UCP:
                guiManager.setEnableLists(true);
                guiManager.setEnablePanelAdminUCP(false);
                guiManager.setEnableLists(true);
                guiManager.setEnablePanelCreateProcess(true);
                guiManager.setEnableBtnWakeProcess(true);
                break;
            case Commands.BTN_CREATE_PROCESS:
                createProcess();
                guiManager.resetSpinnerPanelCreateProcess();
                guiManager.setEnablePanelProcessExecution(true);
                guiManager.sumCountName();
                break;
            case Commands.BTN_FINISH_UCP:
                Process.resetSequential();
                Output.showInfoMessage("Simulacion finalizada.");
                guiManager.setEnablePanelAdminUCP(true);
                guiManager.setEnablePanelCreateProcess(false);
                guiManager.setEnableLists(false);
                guiManager.setEnableBtnWakeProcess(false);
                guiManager.setEnablePanelProcessExecution(false);
                guiManager.resetSpinnerUCP();
                guiManager.resetComponentsPanelCurrentProcess();
                guiManager.clearLists();
                break;
            case Commands.BTN_OPEN_GRAPHICS:
                new ViewGraphics();
                break;
        }
    }

    private void createProcess() {
        // int time = guiManager.getTimeNewProcess();
        // if (simulator.hasCPUAvailable())
        // guiManager.updateReadyQueue(simulator.getReadyQueue());
    }

    @Override
    public void update(ObserverEvent event) {
        updateTime();

        switch (event) {
            case UPDATE_TIME:
                break;
            case BLOCK:
                //blockProcess();
                break;
            case TIME_EXPIRATION:
                nextProcess();
                break;
            case PROCESS_CHANGED:
                updateProcessView();
                break;
        }
    }

    /**
     * Actualiza los tiempos restantes de ejecución
     */
    private void updateTime() {
        int ucpTime = simulator.getCPUTimeRemaining();
        guiManager.setTimeRestUCP(ucpTime);
        int processTime = simulator.getProcessTimeRemaining();
        guiManager.setTimeRestProcess(processTime);
    }


    private void updateProcessView() {
        if (!simulator.hasCPUAvailable()) {
            Process process = simulator.getRunningProcess();
            guiManager.setProcessActual(
                    process.getProcessName(),
                    process.getTimeLife(),
                    process.getLifeTimeRemaining());
        }
    }

    private void nextProcess() {
        Process process = simulator.getRunningProcess();
        guiManager.setProcessActual(
                process.getProcessName(),
                process.getTimeLife(),
                process.getLifeTimeRemaining()
        );
        guiManager.updateReadyQueue(simulator.getReadyQueue());
    }

    private void updateListAndQueue() {
        guiManager.updateBlockedList(simulator.getBlockedList());
        guiManager.updateReadyQueue(simulator.getReadyQueue());
    }
}
