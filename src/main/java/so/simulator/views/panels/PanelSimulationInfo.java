package so.simulator.views.panels;

import so.simulator.controllers.Commands;
import so.simulator.views.Constants;
import so.simulator.views.components.MyJButton;
import so.util.TimeParser;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelSimulationInfo extends JPanel {

    private MyJButton btnForwardSimulation;
    private MyJButton btnFinishSimulation;
    private JLabel timeClock;
    private JLabel textTimeClock;
    private JLabel timeAssign;
    private JLabel textTimeAssign;

    private JLabel quantum;
    private JLabel textQuantum;

    public PanelSimulationInfo(ActionListener listener) {
        this.setBorder(BorderFactory.createTitledBorder(Constants.TITTLE_PANEL_SIMULATION));
        this.btnForwardSimulation = new MyJButton(listener, Commands.BTN_FORWARD_SIMULATION, Constants.TEXT_BTN_FORWARD_SIMULATION);
        this.btnFinishSimulation = new MyJButton(listener, Commands.BTN_FINISH_SIMULATION, Constants.TEXT_BTN_FINISH_SIMULATION);
        this.timeAssign = new JLabel(Constants.TEXT_LABEL_TIME_ASSIGN);
        this.textTimeAssign = new JLabel("0:00");
        this.timeClock = new JLabel(Constants.TEXT_LABEl_CLOCK);
        this.textTimeClock = new JLabel("0:00");
        this.quantum = new JLabel(Constants.TEXT_LABEL_QUANTUM);
        this.textQuantum = new JLabel("0");
        this.btnFinishSimulation.setEnabled(false);
        addToolTips();
        fill();
    }

    private void addToolTips(){
        this.btnFinishSimulation.setToolTipText("Presione si desea detener la simulacion");
        this.btnForwardSimulation.setToolTipText("Presione si desea iniciar la simulacion");
    }

    private void fill() {
        this.setLayout(null);
        this.btnForwardSimulation.setBounds(260, 25, 110, 35);
        this.btnFinishSimulation.setBounds(260, 70, 110, 35);

        this.timeAssign.setBounds(20, 20, 150, 30);
        this.textTimeAssign.setBounds(150,20,100,30);

        this.timeClock.setBounds(20, 50, 150, 30);
        this.textTimeClock.setBounds(150, 50, 100, 30);

        this.quantum.setBounds(20, 80, 150, 30);
        this.textQuantum.setBounds(150, 80, 100, 30);
        add(btnForwardSimulation);
        add(btnFinishSimulation);
        add(timeAssign);
        add(textTimeAssign);
        add(timeClock);
        add(textTimeClock);
        add(quantum);
        add(textQuantum);
    }

    public void setTimeClock(int time) {
        this.textTimeClock.setText(TimeParser.secondsToString(time));
    }
    public void setSimulationTime(int time){
        this.textTimeAssign.setText(TimeParser.secondsToString(time));
    }

    public void setQuantum(int quantum){
        textQuantum.setText(String.valueOf(quantum));
    }

    public int getTimeRestUCP() {
        return Integer.parseInt(this.textTimeClock.getText());
    }

    public void resetComponentsUCP() {
        this.textTimeClock.setText("");
    }

    public void setEnableComponents(boolean status){
        this.btnFinishSimulation.setEnabled(!status);
        this.setEnabled(status);
        this.btnForwardSimulation.setEnabled(status);
    }
}
