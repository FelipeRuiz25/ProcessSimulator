package so.simulator.views.panels;

import so.simulator.views.Constants;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelCreateProcess extends JPanel {

    private ActionListener listener;

    public PanelCreateProcess(ActionListener listener) {
        this.listener = listener;
        setBorder(BorderFactory.createTitledBorder(Constants.TITTLE_PANEL_CREATION_PROCESS));
    }

}
