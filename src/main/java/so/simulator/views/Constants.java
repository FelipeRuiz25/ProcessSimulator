package so.simulator.views;

import java.awt.*;

public interface Constants {

    String ICON_PATH = "src/main/resources/SimulatorIcon.png";
    String TITTLE = "Simulador: Transiciones de procesos";
    Dimension SIZE = new Dimension(1030, 600);

    String TITTLE_PANEL_SIMULATION = " Simulacion ";
    String TITTLE_PANEL_CREATION_PROCESS = " Creacion de proceso ";
    String TITTLE_FRAME_GRAPHICS = "Graficas de los procesos";
    String TITTLE_PANEL_GRAPHIC_TIME_LIFE = " Tabla del tiempo de vida total de los procesos ";
    String TITTLE_PANEL_GRAPHIC_TIME_READY = " Tabla del tiempo en estado listo ";
    String TITTLE_PANEL_GRAPHIC_TIME_WAITING = " Tabla del tiempo en estado bloqueado ";
    String TEXT_LABEL_TIME_ASSIGN = "Tiempo asignado: ";
    String TEXT_LABEl_CLOCK = "Tiempo simulacion: ";
    String TEXT_LABEL_QUANTUM = "Quantum";

    String TEXT_LABEL_MAX_TIME_IO = "Tiempo max Entrada/Salida:";

    String TEXT_LABEL_MAX_TIME_PROCESS_LIFE = "Tiempo max vida:";

    String TIME_TO_NEW_PROCESS = "Siguiente Proceso en:";
    String TEXT_BTN_FORWARD_SIMULATION = "Adelantar";
    String TEXT_BTN_FINISH_SIMULATION = "Finalizar";
    String NO_HAY_MAS_PROCESOS_POR_EJECUTAR = "No hay mas procesos por ejecutar";
    Font FONT_LIST = new Font("Arial", Font.PLAIN, 15);
    String TEXT_BTN_CREATE_PROCESS = "Crear";
    String TEXT_LABEL_NAME_PROCESS = "Nombre proceso:";
    String TEXT_LABEL_TIME_PROCESS = "Tiempo: ";
    String PRESIONE_PARA_CREAR_UN_PROCESO_CON_LOS_DATOS_INGRESADOS = "Presione para crear un proceso con los datos ingresados";
    String INGRESE_EL_TIEMPO_DE_EJECUCION_DEL_PROCESO = "Ingrese el tiempo de ejecucion del proceso";
    String TOOL_TIP_BTN_WAKE_PROCESS = "Presione para despertar el proceso selecionado de la lista de procesos bloqueados";
    String TOOL_TIP_BTN_OPEN_GRAPHICS = "Presione para abrir las graficas sobre el tiempo de los procesos";
    String TOOL_TIP_BTN_STOP_PROCESS = "Presione si desea detener el proceso en ejecucion";
    String VALUE_Y_GRAPHIC = "Tiempo ";
    String VALUE_X_GRAPHIC = "Procesos";
    String TEXT_LABEL_SEG = "Seg";
}

