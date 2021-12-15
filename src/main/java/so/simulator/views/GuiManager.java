package so.simulator.views;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import so.simulator.controllers.Commands;
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
    private JList<String> readyQueue;
    private JList<String> blockedList;
    private JLabel labelReadyQueue;
    private JLabel labelBlockedList;
    private MyJButton btnWakeProcess;

    public GuiManager(ActionListener listener) {
        super(Constants.tittle);
        FlatCyanLightIJTheme.setup();
        this.listener = listener;
        this.panelAdminUCP = new PanelAdminUCP(listener);
        this.panelCreateProcess = new PanelCreateProcess(listener);
        this.panelProcessExecution = new PanelProcessExecution(listener);
        this.readyQueue = new JList<String>();
        this.blockedList = new JList<String>();
        this.labelBlockedList = new JLabel("Procesos bloqueados: ");
        this.labelReadyQueue = new JLabel("Procesos listos: ");
        this.btnWakeProcess = new MyJButton(listener, Commands.BTN_WAKE_PROCESS, "Despertar");
        this.init();
    }

    private void init() {
        setSize(Constants.SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.btnWakeProcess.setEnabled(false);
        this.setEnableLists(false);
        this.fill();
        setVisible(true);
    }

    public void resetSpinnerPanelCreateProcess(){
        this.panelCreateProcess.resetSpinner();
    }

    public void setEnableBtnWakeProcess(boolean status){
        this.btnWakeProcess.setEnabled(status);
    }

    public void setEnablePanelProcessExecution(boolean status){
        this.panelProcessExecution.setEnableComponents(status);
    }

    public void clearList(){
        DefaultListModel<String> listModelEmpty = new DefaultListModel<>();
        this.readyQueue.setModel(listModelEmpty);
        this.blockedList.setModel(listModelEmpty);
    }

    public void setTimeRestProcess(int time) {
        this.panelProcessExecution.setTimeRest(time);
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
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String process : namesProcess) {
            System.out.println(process);
            listModel.addElement(process);
        }
        readyQueue.setModel(listModel);
        panelCreateProcess.addCount();
    }

    public String getSelectItem(){
        return this.blockedList.getSelectedValue();
    }

    public void setProcessActual(String nameProcess, int timeAssign, int timeRest) {
        this.panelProcessExecution.setProcessActual(nameProcess, timeAssign, timeRest);
    }

    public void updateBlockedList(ArrayList<String> namesProcess) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
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
        this.btnWakeProcess.setBounds(580, 550, 140, 30);
        add(panelAdminUCP);
        add(panelCreateProcess);
        add(labelReadyQueue);
        add(labelBlockedList);
        add(panelProcessExecution);
        add(btnWakeProcess);
    }

    public void setEnableLists(boolean status){
        this.readyQueue.setEnabled(status);
        this.blockedList.setEnabled(status);
    }

    public int getTimeAssignUCP() {
        return this.panelAdminUCP.getTimeAssignUCP();
    }

    public int getTimeRestUCP() {
        return this.panelAdminUCP.getTimeRestUCP();
    }

    public void clearLists(){
        DefaultListModel listModelEmpty = new DefaultListModel();
        this.readyQueue.setModel(listModelEmpty);
        this.blockedList.setModel(listModelEmpty);
        this.setEnableLists(false);
    }

    public int getFirstProcess(){
        return this.readyQueue.getFirstVisibleIndex();
    }


    public void resetSpinnerUCP() {
        this.panelAdminUCP.resetSpinnerUCP();
    }

    public void setEnablePanelAdminUCP(boolean status) {
        this.panelAdminUCP.setEnableComponents(status);
    }

    public void setEnablePanelCreateProcess(boolean status) {
        this.panelCreateProcess.setEnableComponents(status);
        this.panelCreateProcess.resetNameProcess();
    }
}
