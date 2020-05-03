import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@FunctionalInterface
public interface IStateMachine<S, E> {

    TransitionMap<S, E> apply();

    class TransitionMap<S, E> {
        private S state;
        public final Map<E, S> transitions = new ConcurrentHashMap<>();
        public final Map<S, Optional<List<E>>> possibleTransitions = new ConcurrentHashMap<>();
        public final Map<E, Optional<Function<Void, Void>>> events = new ConcurrentHashMap<>();

        TransitionMap(S state) {
            this.state = state;
        }
        private void setState(S state) {
            this.state = state;
        }
        public S getState() {
            return this.state;
        }
    }

    default S getState(E event) {
        TransitionMap<S, E> transitionMap = apply();
        Optional<List<E>> list = transitionMap.possibleTransitions.get(transitionMap.getState());
        list.ifPresent(listEvents -> {
            if(listEvents.contains(event)) {
                Optional<Function<Void, Void>> voidFunctionOpt = transitionMap.events.get(event);
                if(Objects.nonNull(voidFunctionOpt)) {
                    voidFunctionOpt.ifPresent(voidFunction -> voidFunction.apply(null));
                }
                S state = transitionMap.transitions.get(event);
                transitionMap.setState(state);
            }
        });
        return transitionMap.getState();
    }
}
