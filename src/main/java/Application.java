import java.util.Collections;
import java.util.Optional;

public class Application {

    enum Events {EV_WORK, EV_STOP, EV_REGENERATE, EV_HOLD, EV_ERROR}
    enum States {WORK, REGENERATE, STOP, HOLD, ERROR}

    public static void main(String[] args) {

        IStateMachine<States, Events> IStateMachine = Application::build;
        States state = IStateMachine.getState(Events.EV_STOP);
    }

    private static IStateMachine.TransitionMap build() {
        IStateMachine.TransitionMap transitionMap = new IStateMachine.TransitionMap(States.STOP);
        transitionMap.possibleTransitions.put(States.STOP, Optional.of(Collections.singletonList(Events.EV_STOP)));
        transitionMap.transitions.put(Events.EV_STOP, States.STOP);
        return transitionMap;
    }
}
