package so.simulator.controllers;

import so.simulator.models.Process;
import so.simulator.models.ProcessStateManager;
import so.simulator.models.exceptions.CPUException;
import so.simulator.views.Constants;
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
                stateManager.setCpuExecuteTime(timeAssign);
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
                guiManager.sumCountName();
                break;
            case Commands.BTN_FINISH_UCP:
                guiManager.setEnablePanelAdminUCP(true);
                guiManager.setEnablePanelCreateProcess(false);
                guiManager.setEnableLists(false);
                guiManager.clearLists();
                guiManager.setEnableBtnWakeProcess(false);
                guiManager.setEnablePanelProcessExecution(false);
                guiManager.resetSpinnerUCP();
                //Crea una nueva simulacion
                stateManager.blockActualProcess();
                guiManager.resetComponentsPanelCurrentProcess();
                Process.resetSequential();
                Output.showInfoMessage("Simulacion finalizada.");
                stateManager = new ProcessStateManager(this);
                break;
            case Commands.BTN_WAKE_PROCESS:
                wakeProcess();
                break;
            case Commands.BTN_STOP_PROCESS:
                stateManager.blockActualProcess();
                break;
        }
    }

    private void wakeProcess() {
        String selected = guiManager.getSelectItem();
        System.out.println("Seleccionado: " + selected);
        stateManager.wakeUpProcess(selected);
        if (stateManager.hasCPUAvailable()) {
            try {
                stateManager.dispatchNextProcess();
            } catch (CPUException e) {
                e.printStackTrace();
            }
        }
        updateListAndQueue();
    }

    private void createProcess() {
        int time = guiManager.getTimeNewProcess();
        if (stateManager.hasCPUAvailable()) {
            try {
                stateManager.addProcess(time);
                stateManager.dispatchNextProcess();
            } catch (CPUException e) {
                e.printStackTrace();
            }
        } else {
            stateManager.addProcess(time);
        }
        guiManager.updateReadyQueue(stateManager.getReadyQueue());
    }

    @Override
    public void update(ObserverEvent event) {
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
            case PROCESS_CHANGED:
                updateProcessView();
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
        guiManager.setTimeRestProcess(processTime);
    }

    private void blockProcess() {
        //Bloquea el proceso de la UCP
        stateManager.blockProcess();
        try {
            if (stateManager.hasProcessesReady()) {
                stateManager.dispatchNextProcess();
                updateProcessView();
            } else {
                guiManager.resetComponentsPanelCurrentProcess();
                Output.showInfoMessage(Constants.NO_HAY_MAS_PROCESOS_POR_EJECUTAR);
            }
        } catch (CPUException e) {
            e.printStackTrace();
        }
        updateListAndQueue();
    }

    private void updateProcessView() {
        if (!stateManager.hasCPUAvailable()) {
            Process process = stateManager.getRunningProcess();
            guiManager.setProcessActual(
                    process.getProcessName(),
                    process.getSecondsOfExecution(),
                    process.getSecondsOfExecutionRemaining());
        }
    }

    private void nextProcess() {
        try {
            Process process = stateManager.finishProcessTurn();
            guiManager.setProcessActual(
                    process.getProcessName(),
                    process.getSecondsOfExecution(),
                    process.getSecondsOfExecutionRemaining()
            );
            updateListAndQueue();
        } catch (CPUException e) {
            e.printStackTrace();
        }
    }

    private void updateListAndQueue() {
        guiManager.updateBlockedList(stateManager.getBlockedList());
        guiManager.updateReadyQueue(stateManager.getReadyQueue());
    }
}
