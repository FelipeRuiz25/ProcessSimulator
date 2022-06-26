package so.simulator.views;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import so.simulator.views.panels.PanelGraphic;

import javax.swing.*;
import java.awt.*;

public class ViewGraphics extends JFrame {

    private JLabel titleFrame;
    private JPanel mainPanel;
    private PanelGraphic graphicTimeOfLife;
    private PanelGraphic graphicTimeOfReady;
    private PanelGraphic graphicTimeOfWaiting;

    public ViewGraphics(){
        super(Constants.TITTLE);
        setIconImage(new ImageIcon(Constants.ICON_PATH).getImage());
        //configurar tema de la aplicacion
        FlatCyanLightIJTheme.setup();
        this.titleFrame = new JLabel(Constants.TITTLE_FRAME_GRAPHICS);
        this.mainPanel = new JPanel();
        this.setBackground(Color.WHITE);
        this.mainPanel.setBackground(Color.WHITE);
        this.graphicTimeOfLife  = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_LIFE);
        this.graphicTimeOfReady = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_READY);
        this.graphicTimeOfWaiting = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_WAITING);
        this.graphicTimeOfLife.setBackground(Color.WHITE);
        this.graphicTimeOfReady.setBackground(Color.WHITE);
        this.graphicTimeOfWaiting.setBackground(Color.WHITE);
        this.init();
    }

    private void init(){
        setSize(Constants.SIZE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.fill();
        setVisible(true);
    }

    private void fill(){
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        this.titleFrame.setFont(Constants.FONT_LIST);
        mainPanel.add(titleFrame);
        mainPanel.add(graphicTimeOfLife);
        mainPanel.add(graphicTimeOfReady);
        mainPanel.add(graphicTimeOfWaiting);
        this.add(scroll,BorderLayout.CENTER);
    }
}