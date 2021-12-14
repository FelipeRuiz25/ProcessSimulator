package so.simulator.views;

import so.simulator.views.components.ModifiedFlowLayout;
import so.simulator.views.components.MyJButton;
import so.simulator.views.panels.PanelAdminUCP;
import so.simulator.views.panels.PanelCreateProcess;
import so.simulator.views.panels.PanelProcessExecution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiManager extends JFrame {

    private ActionListener listener;
    private PanelAdminUCP panelAdminUCP;
    private PanelCreateProcess panelCreateProcess;
    private PanelProcessExecution panelProcessExecution;
    private JList readyQueue;
    private JList blockedList;
    private JLabel labelReadyQueue;
    private JLabel labelBlockedList;
    private MyJButton btnWakeProcess;

    public GuiManager(ActionListener listener) {
        super(Constants.tittle);
        this.listener = listener;
        this.panelAdminUCP = new PanelAdminUCP(listener);
        this.panelCreateProcess = new PanelCreateProcess(listener);
        this.panelProcessExecution = new PanelProcessExecution(listener);
        this.readyQueue = new JList();
        this.blockedList = new JList();
        this.labelBlockedList = new JLabel("Procesos bloqueados: ");
        this.labelReadyQueue = new JLabel("Procesos listos: ");
        this.btnWakeProcess = new MyJButton(listener, "btnWakeProcess", "Despertar");
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

    public void setTimeAssignUCP(int time) {
//        this.panelAdminUCP.setTimeAssignUCP(time);
    }

    public void setTimeRestUCP(int time) {
        this.panelAdminUCP.setTimeRestUCP(time);
    }

    public int getNameNewProcess() {
        return this.panelCreateProcess.getNameProcess();
    }

    public int getTimeNewProcess() {
        return this.panelCreateProcess.getTimeProcess();
    }

    public void updateReadyQueue(ArrayList<String> namesProcess) {
        DefaultListModel listModel = new DefaultListModel();
        for (String process : namesProcess) {
            System.out.println(process);
            listModel.addElement(process);
        }
        readyQueue.setModel(listModel);
    }

    public void setProcessActual(String nameProcess, int timeAssign, int timeRest) {
        this.panelProcessExecution.setProcessActual(nameProcess, timeAssign, timeRest);
    }

    public void updateBlockedList(ArrayList<String> namesProcess) {
        DefaultListModel listModel = new DefaultListModel();
        for (String process : namesProcess) {
            System.out.println(process);
            listModel.addElement(process);
        }
        blockedList.setModel(listModel);
    }

    private void fill() {
        this.setLayout(null);
        this.panelAdminUCP.setBounds(20, 20, 360, 100);
        this.panelCreateProcess.setBounds(400, 20, 360, 100);
        this.readyQueue.setBounds(30, 160, 230, 380);
        readyQueue.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        this.blockedList.setBounds(530, 160, 230, 380);
        blockedList.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        labelReadyQueue.setBounds(30, 130, 230, 20);
        labelBlockedList.setBounds(530, 130, 230, 20);
        readyQueue.setLayout(new ModifiedFlowLayout());
        JScrollPane jScrollPaneQueueProcess = new JScrollPane(readyQueue);
        jScrollPaneQueueProcess.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneQueueProcess.setBounds(30, 160, 230, 380);
        add(jScrollPaneQueueProcess);

        blockedList.setLayout(new ModifiedFlowLayout());
        JScrollPane jScrollPaneBlockedList = new JScrollPane(blockedList);
        jScrollPaneBlockedList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneBlockedList.setBounds(530, 160, 230, 380);
        add(jScrollPaneBlockedList);

        this.panelProcessExecution.setBounds(270, 150, 250, 270);
        add(panelProcessExecution);
        this.btnWakeProcess.setBounds(580,550, 140, 30);
        add(panelAdminUCP);
        add(panelCreateProcess);
        //add(blockedList);
        add(labelReadyQueue);
        add(labelBlockedList);
        add(btnWakeProcess);
        this.repaint();
    }

    public int getTimeAssignUCP() {
        return this.panelAdminUCP.getTimeAssignUCP();
    }

    public int getTimeRestUCP() {
        return this.panelAdminUCP.getTimeRestUCP();
    }
}
