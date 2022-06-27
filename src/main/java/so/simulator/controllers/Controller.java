package so.simulator.controllers;

import so.simulator.models.*;
import so.simulator.models.Process;
import so.simulator.views.GuiManager;
import so.simulator.views.ViewGraphics;
import so.simulator.views.components.Output;
import so.util.observer.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
            case Commands.BTN_FORWARD_SIMULATION:
                guiManager.setEnableLists(true);
                guiManager.setEnablePanelAdminUCP(false);
                guiManager.setEnableLists(true);
                guiManager.setEnablePanelCreateProcess(true);
                guiManager.setEnableBtnWakeProcess(true);
                break;
            case Commands.BTN_CREATE_PROCESS:
                processCreated();
                guiManager.setEnablePanelProcessExecution(true);
                guiManager.sumCountName();
                break;
            case Commands.BTN_FINISH_SIMULATION:
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
                new ViewGraphics(getListTimeOfLife(),getListTimeOfBlock());
                break;
            case Commands.BTN_START_SIMULATION:
                if(simulator != null) simulator.stopSimulation();
                createSimulation();
                break;
        }
    }

    private void createSimulation() {
        guiManager.getPanelSimulationInfo().setQuantum(QUANTUM);
        ProcessCreator creator = new ProcessCreator(
                guiManager.getPanelCreateSimulation().getMaxTimeIOOperation(),
                guiManager.getPanelCreateSimulation().getMaxProcessTimeLife(),
                guiManager.getPanelCreateSimulation().getMaxTimeNextProcess()
        );
        guiManager.getPanelCreateProcess().setCreatorInfo(
                creator.getMaxTimeIO(), creator.getMaxTimeProcessLife()
        );
        int simulationTime = guiManager.getPanelCreateSimulation().getSimulationTime();
        guiManager.getPanelSimulationInfo().setSimulationTime(simulationTime);
        simulator = new Simulator(simulationTime,new CPU(QUANTUM), creator);
        simulator.addObserver(this);
        simulator.startSimulation();
    }

    @Override
    public void update(SimulationStatus status) {
        updateTime();
        if (status.processCreated()){
            processCreated();
        }
    }

    private void updateTime() {
        int timeClock = simulator.getStatus().getSimulatorClock();
        guiManager.getPanelSimulationInfo().setTimeClock(timeClock);
        int timeNextProcess = simulator.getProcessCreator().getTimeToNewProcess();
        guiManager.getPanelCreateProcess().setTimeToNewProcess(timeNextProcess);
    }

    private void processCreated() {
        guiManager.getPanelCreateProcess().addCount();
        guiManager.updateReadyQueue(simulator.getReadyQueue());
    }

    private ArrayList<Integer> getListTimeOfLife(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Process process : simulator.getProcessCreator().getListProces()){
            list.add(process.getTimeLife());
        }
        return list;
    }

    private ArrayList<Integer> getListTimeOfBlock(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Process process : simulator.getProcessCreator().getListProces()){
            list.add(process.getTimeIOOperation());
        }
        return list;
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
