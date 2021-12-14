package so.simulator.views.panels;

import so.simulator.controllers.Commands;
import so.simulator.views.Constants;
import so.simulator.views.components.MyJButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;

public class PanelProcessExecution extends JPanel {


    private ActionListener listener;
    private JLabel labelNameProcess;
    private JLabel labelTimeAssign;
    private JLabel labelTimeRest;
    private JLabel labelSegRest;
    private JLabel labelSegAssign;
    private JTextField textFieldNameProcess;
    private JTextField textFieldTimeAssign;
    private JTextField textFieldTimeRest;
    private MyJButton btnStopProcess;

    public PanelProcessExecution(ActionListener listener) {
        this.listener = listener;
        setBorder(BorderFactory.createTitledBorder(null, "Proceso en ejecucion", TitledBorder.CENTER, TitledBorder.TOP));
        init();
    }

    private void init() {
        this.labelNameProcess = new JLabel(Constants.TEXT_LABEL_NAME_PROCESS);
        this.labelTimeAssign = new JLabel("Tiempo asignado: ");
        this.labelTimeRest = new JLabel("Tiempo restante: ");
        this.textFieldNameProcess = new JTextField();
        this.textFieldTimeAssign = new JTextField();
        this.textFieldTimeRest = new JTextField();
        this.btnStopProcess = new MyJButton(listener, Commands.BTN_STOP_PROCESS, "Detener");
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        this.labelNameProcess.setBounds(20, 40, 120, 20);
        this.labelTimeAssign.setBounds(20, 70, 120, 20);
        this.labelTimeRest.setBounds(20, 100, 120, 20);
        this.textFieldNameProcess.setBounds(125, 40, 120, 20);
        this.textFieldTimeAssign.setBounds(125, 70, 120, 20);
        this.textFieldTimeRest.setBounds(125, 100, 120, 20);
        this.btnStopProcess.setBounds(50, 210, 150, 40);
        add(labelNameProcess);
        add(labelTimeAssign);
        add(labelTimeRest);
        add(textFieldNameProcess);
        add(textFieldTimeAssign);
        add(textFieldTimeRest);
        add(btnStopProcess);
    }
}
