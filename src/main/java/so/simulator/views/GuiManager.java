package so.simulator.views;

import so.simulator.views.panels.PanelAdminUCP;
import so.simulator.views.panels.PanelCreateProcess;
import so.simulator.views.panels.PanelProcessExecution;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GuiManager extends JFrame {

    private ActionListener listener;
    private PanelAdminUCP panelAdminUCP;
    private PanelCreateProcess panelCreateProcess;
    private PanelProcessExecution panelProcessExecution;

    public GuiManager(ActionListener listener) {
        super(Constants.tittle);
        this.listener = listener;
        this.panelAdminUCP = new PanelAdminUCP(listener);
        this.panelCreateProcess = new PanelCreateProcess(listener);
        this.panelProcessExecution = new PanelProcessExecution(listener);
        this.init();
    }

    private void init() {
        setSize(Constants.SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.fill();
    }

    private void fill() {
        this.setLayout(null);
        this.panelAdminUCP.setBounds(20, 20, 360, 100);
        this.panelCreateProcess.setBounds(400, 20, 360, 100);
        add(panelAdminUCP);
        add(panelCreateProcess);
    }
}
