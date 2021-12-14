package so.simulator.controllers;

import so.simulator.models.ProcessStateManager;
import so.simulator.models.exceptions.CPUException;
import so.simulator.views.GuiManager;
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
                int timeRest = guiManager.getTimeRestUCP();

                break;
            case Commands.BTN_CREATE_PROCESS:
                /*int nameProcess = guiManager.getNameNewProcess();
                int time = guiManager.getTimeNewProcess();
                */
                int i = 0;
                guiManager.addProcessReadyQueue(i);
                //i++;
                break;
        }
    }

    @Override
    public void update(ObserverEvent event) {
        System.out.println(event);
        switch (event){
            case UPDATE_TIME:
                updateTime();
                break;
            case BLOCK:
                blockProcess();
                break;
            case TIME_EXPIRATION:

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
        //Bloque el proceso de la UCP
        stateManager.blockProcess();
        try {
            stateManager.dispatchProcess();
            // TODO: 14/12/21 actualizar cola en la vista
        } catch (CPUException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Toca actualizar la pinshi cola en la vista >:");
    }
}
