import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
public interface IStateMachine {

    TransitionMap apply();

    class TransitionMap {
        private States state = States.STOP;
        public final Map<Events, States> transitions = new ConcurrentHashMap<>();
        public final Map<States, List<Events>> possibleTransitions = new ConcurrentHashMap<>();
        private void setState(States state) {
            this.state = state;
        }
        public States getState() {
            return this.state;
        }
    }

    enum States {WORK, REGENERATE, STOP, HOLD, ERROR}
    enum Events {EV_WORK, EV_STOP, EV_REGENERATE, EV_HOLD, EV_ERROR}

    default Optional<States> getState(Events event) {
        TransitionMap transitionMap = apply();
        if (transitionMap.possibleTransitions.get(transitionMap.getState()).contains(event)) {
            States state = transitionMap.transitions.get(event);
            transitionMap.setState(state);
            return Optional.of(state);
        }
        return Optional.empty();
    }
}
