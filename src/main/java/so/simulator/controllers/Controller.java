package so.simulator.controllers;

import so.simulator.models.ProcessStateManager;
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
                break;
            case BLOCK:
                break;
            case TIME_EXPIRATION:
                break;
        }
    }
}
