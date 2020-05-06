import java.util.Collections;
import java.util.Optional;

public class StateMachineBuilder {
    enum Events {EV_WORK, EV_STOP, EV_REGENERATE, EV_HOLD, EV_ERROR}
    enum States {WORK, REGENERATE, STOP, HOLD, ERROR}

    public static IStateMachine.TransitionMap build() {
        IStateMachine.TransitionMap<States, Events> transitionMap = new IStateMachine.TransitionMap(States.STOP);
        transitionMap.possibleTransitions.put(States.STOP, Collections.singletonList(Events.EV_WORK));
        transitionMap.transitions.put(Events.EV_WORK, States.WORK);
        transitionMap.events.put(Events.EV_WORK, StateMachineBuilder::executeEvWork);
        return transitionMap;
    }

    private static void executeEvWork(Optional<?> aVoid) {
        aVoid.ifPresent(o -> System.out.println("executeEvWork " + o.getClass() ));
    }
}
