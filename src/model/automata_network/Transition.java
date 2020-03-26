package model.automata_network;

import java.util.List;

public class Transition {
    LocalState origin, destination;
    List<LocalState> conditions;

    public Transition(LocalState origin, LocalState destination, List<LocalState> conditions) {
        this.origin = origin;
        this.destination = destination;
        this.conditions = conditions;
    }

    public LocalState getOrigin() {
        return origin;
    }

    public LocalState getDestination() {
        return destination;
    }

    public List<LocalState> getConditions() {
        return conditions;
    }
}
