import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@FunctionalInterface
public interface IStateMachine<S, E> {

    TransitionMap<S, E> apply();

    class TransitionMap<S, E> {
        private S state;
        public final Map<E, S> transitions = new ConcurrentHashMap<>();
        public final Map<S, List<E>> possibleTransitions = new ConcurrentHashMap<>();
        public final Map<E, Consumer<Void>> events = new ConcurrentHashMap<>();

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
        Optional<List<E>> list = Optional.of(transitionMap.possibleTransitions.get(transitionMap.getState()));
        list.ifPresent(listEvents -> {
            if(listEvents.contains(event)) {
                Optional<Consumer<Void>> voidFunctionOpt = Optional.of(transitionMap.events.get(event));
                if(Objects.nonNull(voidFunctionOpt)) {
                    voidFunctionOpt.ifPresent(voidFunction -> voidFunction.accept(null));
                }
                S state = transitionMap.transitions.get(event);
                transitionMap.setState(state);
            }
        });
        return transitionMap.getState();
    }
}
