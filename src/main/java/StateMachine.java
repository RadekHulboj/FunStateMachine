import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
public interface StateMachine {

    TransitionMap apply();

    class TransitionMap {
        public Map<Events, States> _transitions = new ConcurrentHashMap<>();
        public Map<States, List<Events>> _possibleTransitions = new ConcurrentHashMap<>();
    }

    List<States> STATES = new ArrayList<>();
    enum States {WORK, REGENERATE, STOP, HOLD, ERROR}
    enum Events {EV_WORK, EV_STOP, EV_REGENERATE, EV_HOLD, EV_ERROR}

    default Optional<States> getState(Events event) {
        TransitionMap transitionMap = apply();
        int stateSlot = 0;
        if (transitionMap._possibleTransitions.get(STATES.get(stateSlot)).contains(event)) {
            States state = transitionMap._transitions.get(event);
            StateMachine.STATES.add(stateSlot, state);
            return Optional.of(state);
        }
        return Optional.empty();
    }
}
