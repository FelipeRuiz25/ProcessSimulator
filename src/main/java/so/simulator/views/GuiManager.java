package so.simulator.views;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import so.simulator.controllers.Commands;
import so.simulator.views.components.ModifiedFlowLayout;
import so.simulator.views.components.MyJButton;
import so.simulator.views.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiManager extends JFrame {

    private ActionListener listener;
    private PanelSimulationInfo panelSimulationInfo;
    private PanelCreateProcess panelCreateProcess;
    private PanelProcessExecution panelProcessExecution;
    private PanelCreateSimulation panelCreateSimulation;

    private PanelProcessBlocked panelProcessBlocked;
    private JList<String> readyQueue;
    private JLabel labelReadyQueue;
    private JLabel labelBlockedList;
    private MyJButton btnOpenGraphs;

    public GuiManager(ActionListener listener) {
        super(Constants.TITTLE);
        setIconImage(new ImageIcon(Constants.ICON_PATH).getImage());
        //configurar tema de la aplicacion
        FlatCyanLightIJTheme.setup();
        this.listener = listener;
        this.panelSimulationInfo = new PanelSimulationInfo(listener);
        this.panelCreateProcess = new PanelCreateProcess(listener);
        this.panelProcessExecution = new PanelProcessExecution(listener);
        this.panelProcessBlocked = new PanelProcessBlocked(listener);
        this.panelCreateSimulation = new PanelCreateSimulation(listener);
        this.readyQueue = new JList<>();
        this.labelBlockedList = new JLabel("Procesos bloqueados: ");
        this.labelReadyQueue = new JLabel("Procesos listos: ");
        this.btnOpenGraphs = new MyJButton(listener, Commands.BTN_OPEN_GRAPHICS, "Abrir Graficas");
        this.init();
    }

    private void init() {
        setSize(Constants.SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setEnableLists(false);
        this.fill();
        addToolTips();
        setVisible(true);
    }

    public void setEnableBtnWakeProcess(boolean status){
        this.btnOpenGraphs.setEnabled(status);
    }

    public void setEnablePanelProcessExecution(boolean status){
        this.panelProcessExecution.setEnableComponents(status);
    }

    public void clearList() {
        DefaultListModel<String> listModelEmpty = new DefaultListModel<>();
        this.readyQueue.setModel(listModelEmpty);
    }

    public void setTimeRestProcess(int time) {
        this.panelProcessExecution.setTimeRest(time);
    }

    public void updateReadyQueue(ArrayList<String> namesProcess) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String process : namesProcess) {
            listModel.addElement(process);
        }
        readyQueue.setModel(listModel);
    }

    public void setProcessActual(String nameProcess, int timeAssign, int timeRest) {
        this.panelProcessExecution.setProcessActual(nameProcess, timeAssign, timeRest);
    }

    private void fill() {
        this.panelCreateSimulation.setBounds(30,20, 200,550);

        this.setLayout(null);
        int move = 220;
        this.panelSimulationInfo.setBounds(30+move, 20, 400, 120);
        this.panelCreateProcess.setBounds(480+move, 20, 300, 120);
        readyQueue.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        labelReadyQueue.setBounds(100+move, 150, 230, 20);
        readyQueue.setLayout(new ModifiedFlowLayout());
        JScrollPane jScrollPaneQueueProcess = new JScrollPane(readyQueue);
        jScrollPaneQueueProcess.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //ready Queue Bounds
        jScrollPaneQueueProcess.setBounds(30+move, 170, 230, 400);
        add(jScrollPaneQueueProcess);

        this.readyQueue.setFont(Constants.FONT_LIST);
        this.panelProcessExecution.setBounds(270+move, 160, 250, 200);
        this.panelProcessBlocked.setBounds(530+move, 160, 250,200);
        add(panelProcessExecution);
        this.btnOpenGraphs.setBounds(455+move, 380, 140, 30);
        add(panelSimulationInfo);
        add(panelCreateProcess);
        add(labelReadyQueue);
        add(labelBlockedList);
        add(panelProcessExecution);
        add(panelProcessBlocked);
        add(btnOpenGraphs);
        add(panelCreateSimulation);
    }

    public void resetComponentsPanelCurrentProcess(){
        this.panelProcessExecution.resetComponents();
    }

    private void addToolTips(){
        this.btnOpenGraphs.setToolTipText(Constants.TOOL_TIP_BTN_OPEN_GRAPHICS);
    }

    public void setEnableLists(boolean status) {
        this.readyQueue.setEnabled(status);
    }

    public void clearLists() {
        DefaultListModel<String> listModelEmpty = new DefaultListModel<>();
        this.readyQueue.setModel(listModelEmpty);
        this.setEnableLists(false);
    }

    public void resetSpinnerUCP() {
        this.panelSimulationInfo.resetComponentsUCP();
    }


    public void setEnablePanelCreateProcess(boolean status) {
        this.panelCreateProcess.resetNameProcess();
    }

    public PanelCreateSimulation getPanelCreateSimulation() {
        return panelCreateSimulation;
    }

    public PanelSimulationInfo getPanelSimulationInfo() {
        return panelSimulationInfo;
    }

    public PanelCreateProcess getPanelCreateProcess() {
        return panelCreateProcess;
    }

    public PanelProcessExecution getPanelProcessExecution() {
        return panelProcessExecution;
    }

    public PanelProcessBlocked getPanelProcessBlocked() {
        return panelProcessBlocked;
    }
}
