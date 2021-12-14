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
