package so.simulator.views.panels;

import so.simulator.controllers.Commands;
import so.simulator.views.Constants;
import so.simulator.views.components.MyJButton;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelCreateProcess extends JPanel {

    private JLabel labelNameProcess;
    private JTextField textFieldNameProcess;
    private JLabel labelTextTimeProcess;
    private JSpinner spinnerSegProcess;
    private JLabel labelSeg;
    private MyJButton btnCreateProcess;

    public PanelCreateProcess(ActionListener listener) {
        setBorder(BorderFactory.createTitledBorder(Constants.TITTLE_PANEL_CREATION_PROCESS));
        init(listener);

    }

    private void init(ActionListener listener) {
        this.labelNameProcess = new JLabel(Constants.TEXT_LABEL_NAME_PROCESS);
        this.textFieldNameProcess = new JTextField();
        this.textFieldNameProcess.setEnabled(false);
        this.labelTextTimeProcess = new JLabel(Constants.TEXT_LABEL_TIME_PROCESS);
        this.spinnerSegProcess = new JSpinner();
        this.labelSeg = new JLabel(Constants.TEXT_SEG);
        this.btnCreateProcess = new MyJButton(listener, Commands.BTN_CREATE_PROCESS, Constants.TEXT_BTN_CREATE_PROCESS);
        fill();
    }

    private void fill() {
        this.setLayout(null);
        this.labelNameProcess.setBounds(20, 20, 60, 20);
        this.textFieldNameProcess.setBounds(80, 20, 80, 20);
        this.labelTextTimeProcess.setBounds(20, 50, 60, 20);
        this.spinnerSegProcess.setBounds(80, 50, 60, 20);
        this.labelSeg.setBounds(145, 50, 60, 20);
        this.btnCreateProcess.setBounds(200, 20, 120, 50);
        add(labelNameProcess);
        add(textFieldNameProcess);
        add(labelTextTimeProcess);
        add(spinnerSegProcess);
        add(labelSeg);
        add(btnCreateProcess);
    }

    public int getNameProcess() {
        return Integer.parseInt(this.textFieldNameProcess.getText());
    }

    public int getTimeProcess() {
        return (int) spinnerSegProcess.getValue();
    }
}
