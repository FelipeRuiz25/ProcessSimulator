package so.util.observer;

import so.simulator.models.SimulationStatus;

public interface Observer{

    void update(SimulationStatus status);
}
