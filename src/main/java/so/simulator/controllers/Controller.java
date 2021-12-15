package so.simulator.controllers;

import so.simulator.models.Process;
import so.simulator.models.ProcessStateManager;
import so.simulator.models.exceptions.CPUException;
import so.simulator.views.GuiManager;
import so.simulator.views.components.Output;
import so.util.observer.Observer;
import so.util.observer.ObserverEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, Observer {

    private GuiManager guiManager;
    private ProcessStateManager stateManager;

    public Controller() {
        this.guiManager = new GuiManager(this);
        this.stateManager = new ProcessStateManager(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case Commands.BTN_STAR_UCP:
                int timeAssign = guiManager.getTimeAssignUCP();
                guiManager.setEnableLists(true);
                guiManager.setEnablePanelAdminUCP(false);
                guiManager.setEnableLists(true);
                guiManager.setEnablePanelCreateProcess(true);
                guiManager.setEnableBtnWakeProcess(true);
                System.out.println(timeAssign);
                break;
            case Commands.BTN_CREATE_PROCESS:
                createProcess();
                guiManager.resetSpinnerPanelCreateProcess();
                guiManager.setEnablePanelProcessExecution(true);

                break;
            case Commands.BTN_FINISH_UCP:
                guiManager.setEnablePanelAdminUCP(true);
                guiManager.setEnablePanelCreateProcess(false);
                guiManager.setEnableLists(false);
                guiManager.clearLists();
                guiManager.setEnableBtnWakeProcess(false);
                guiManager.setEnablePanelProcessExecution(false);
                guiManager.resetSpinnerUCP();
                Output.showInfoMessage("Simulacion finalizada.");
                break;
            case Commands.BTN_WAKE_PROCESS:
                int selectBlockedProcess = guiManager.getSelectItem();


                break;
        }
    }



    private void createProcess() {
        int time = guiManager.getTimeNewProcess();
        stateManager.addProcess(time);
        guiManager.updateReadyQueue(stateManager.getReadyQueue());
    }

    @Override
    public void update(ObserverEvent event) {
        System.out.println(event);
        switch (event) {
            case UPDATE_TIME:
                updateTime();
                break;
            case BLOCK:
                blockProcess();
                break;
            case TIME_EXPIRATION:
                nextProcess();
                break;
        }
    }

    /**
     * Actualiza los tiempos restantes de ejecución
     */
    private void updateTime() {
        int ucpTime = stateManager.getCPUTimeRemaining();
        guiManager.setTimeRestUCP(ucpTime);
        int processTime = stateManager.getProcessTimeRemaining();
        guiManager.setTimeAssignUCP(processTime);
    }

    private void blockProcess() {
    }

    private void nextProcess() {
        try {
            Process process = stateManager.finishProcessTurn();
            guiManager.setProcessActual(
                    process.getProcessName(),
                    process.getSecondsOfExecution(),
                    process.getSecondsOfExecutionRemaining()
            );
        } catch (CPUException e) {
            e.printStackTrace();
        }
    }

    private void updateListAndQueue() {
        guiManager.updateBlockedList(stateManager.getBlockedList());
        guiManager.updateReadyQueue(stateManager.getReadyQueue());
    }
}
