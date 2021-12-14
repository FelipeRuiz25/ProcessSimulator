package so.simulator.views.panels;

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
        this.labelNameProcess = new JLabel("Nombre:");

    }

}
