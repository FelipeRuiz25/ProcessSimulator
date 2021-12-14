package so.simulator.views.panels;

import so.simulator.controllers.Commands;
import so.simulator.views.Constants;
import so.simulator.views.components.MyJButton;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelAdminUCP extends JPanel {

    private MyJButton btnStartUCP;
    private MyJButton btnFinishUCP;
    private JLabel timeRest;
    private JLabel timeAssign;
    private JTextField textTimeAssign;
    private JTextField textTimeRest;
    private JLabel textSegTimeAssign;
    private JLabel textSegTimeRest;

    public PanelAdminUCP(ActionListener listener) {
        this.setBorder(BorderFactory.createTitledBorder(Constants.TITTLE_PANEL_ADMIN_UCP));
        this.btnStartUCP = new MyJButton(listener, Commands.BTN_STAR_UCP, Constants.TEXT_BTN_START_UCP);
        this.btnFinishUCP = new MyJButton(listener, Commands.BTN_FINISH_UCP, Constants.TEXT_BTN_FINISH_UCP);
        this.timeAssign = new JLabel(Constants.TEXT_LABEL_TIME_ASSIGN);
        this.timeRest = new JLabel(Constants.TEXT_LABEl_TIME_REST);
        this.textSegTimeAssign = new JLabel(Constants.TEXT_SEG);
        this.textSegTimeRest = new JLabel(Constants.TEXT_SEG);
        this.textTimeAssign = new JTextField("1");
        this.textTimeRest = new JTextField();
        textTimeRest.setEnabled(false);
        fill();
    }

    private void fill() {
        this.setLayout(null);
        this.btnStartUCP.setBounds(240, 15, 100, 30);
        this.btnFinishUCP.setBounds(240, 50, 100, 30);
        this.timeAssign.setBounds(10, 20, 110, 20);
        this.timeRest.setBounds(10, 50, 110, 20);
        this.textTimeAssign.setBounds(120, 20, 70, 20);
        this.textTimeRest.setBounds(120, 50, 70, 20);
        this.textSegTimeAssign.setBounds(190, 20, 30, 20);
        this.textSegTimeRest.setBounds(190, 50, 30, 20);
        add(btnStartUCP);
        add(btnFinishUCP);
        add(timeAssign);
        add(timeRest);
        add(textTimeAssign);
        add(textSegTimeAssign);
        add(textSegTimeRest);

        add(textSegTimeAssign);
        add(textTimeRest);
    }

    public void setTimeAssignUCP(int time) {
        this.textTimeAssign.setText(String.valueOf(time));
    }

    public void setTimeRestUCP(int time) {
        this.textTimeRest.setText(String.valueOf(time));
    }

    public int getTimeAssignUCP() {
        return Integer.parseInt(this.textTimeAssign.getText());
    }

    public int getTimeRestUCP() {
        return Integer.parseInt(this.textTimeRest.getText());
    }
}
