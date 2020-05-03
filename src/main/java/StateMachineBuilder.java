import java.util.Collections;

public class StateMachineBuilder {
    enum Events {EV_WORK, EV_STOP, EV_REGENERATE, EV_HOLD, EV_ERROR}
    enum States {WORK, REGENERATE, STOP, HOLD, ERROR}

    public IStateMachine.TransitionMap build() {
        IStateMachine.TransitionMap<States, Events> transitionMap = new IStateMachine.TransitionMap(States.STOP);
        transitionMap.possibleTransitions.put(States.STOP, Collections.singletonList(Events.EV_WORK));
        transitionMap.transitions.put(Events.EV_WORK, States.WORK);
        transitionMap.events.put(Events.EV_WORK, StateMachineBuilder::executeEvWork);
        return transitionMap;
    }

    private static void executeEvWork(Void aVoid) {
        System.out.println("executeEvWork");
    }
}
