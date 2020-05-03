import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
public interface IStateMachine<E> {

    TransitionMap<E> apply();

    class TransitionMap<E> {
        private States state = States.STOP;
        public final Map<E, States> transitions = new ConcurrentHashMap<>();
        public final Map<States, List<E>> possibleTransitions = new ConcurrentHashMap<>();
        private void setState(States state) {
            this.state = state;
        }
        public States getState() {
            return this.state;
        }
    }

    enum States {WORK, REGENERATE, STOP, HOLD, ERROR}


    default Optional<States> getState(E event) {
        TransitionMap<E> transitionMap = apply();
        if (transitionMap.possibleTransitions.get(transitionMap.getState()).contains(event)) {
            States state = transitionMap.transitions.get(event);
            transitionMap.setState(state);
            return Optional.of(state);
        }
        return Optional.empty();
    }
}
