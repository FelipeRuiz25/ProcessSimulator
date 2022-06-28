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
            case Commands.BTN_FINISH_SIMULATION:
                finishSimulation();
                break;
            case Commands.BTN_OPEN_GRAPHICS:
                new ViewGraphics(getListTimeOfLife(),getListTimeOfBlock(),getListTimeReady());
                break;
            case Commands.BTN_START_SIMULATION:
                if(simulator != null) finishSimulation();
                createSimulation();
                break;
        }
    }

    private void finishSimulation() {
        guiManager.resetNameProcess();
        guiManager.resetSpinnerUCP();
        guiManager.resetComponentsPanelCurrentProcess();
        guiManager.clearLists();
        Process.resetSequential();
        if (simulator != null && simulator.getStatus().isRunning()) {
            simulator.finishSimulation();
            Output.showInfoMessage("Simulacion finalizada.");
        }
    }

    private void createSimulation() {
        guiManager.getPanelSimulationInfo().setQuantum(QUANTUM);
        guiManager.getPanelProcessExecution().setProgressBarTimeTask(QUANTUM);
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
        if (status.newProcessRunning() || status.cpuExpirationTime()){
            guiManager.updateReadyQueue(simulator.getReadyQueue());
        }
        if (!simulator.hasCPUAvailable()){
            if (simulator.getRunningProcess().isBlocked()) {
                updateProcessBlockedView();
            }else {
                updateProcessRunningView();
                guiManager.getPanelProcessBlocked().clear();
            }
        }else {
            guiManager.getPanelProcessExecution().clear();
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

    private ArrayList<Integer> getListTimeReady(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Process process : simulator.getProcessCreator().getListProces()){
            list.add(process.getTimeReady());
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


    private void updateProcessRunningView() {
        if (!simulator.hasCPUAvailable()) {
            Process process = simulator.getRunningProcess();
            guiManager.setProcessActual(
                    process.getProcessName(),
                    process.getTimeLife(),
                    process.getLifeTimeRemaining());
            guiManager.getPanelProcessExecution().setProgressBarValue(
                    QUANTUM - simulator.getCPUTimeRemaining()
            );
        }
    }

    private void updateProcessBlockedView(){
        if (!simulator.hasCPUAvailable()) {
            Process process = simulator.getRunningProcess();
            guiManager.getPanelProcessBlocked().setProcessActual(
                    process.getProcessName(),
                    process.getTimeIOOperation(),
                    process.getTimeIORemaining()-1);
        }
    }
}
