import java.util.Collections;
import java.util.Optional;

public class Application {

    enum Events {EV_WORK, EV_STOP, EV_REGENERATE, EV_HOLD, EV_ERROR}
    public static void main(String[] args) {

        IStateMachine<Events> IStateMachine = Application::build;
        Optional<IStateMachine.States> state = IStateMachine.getState(Events.EV_STOP);
    }

    private static IStateMachine.TransitionMap build() {
        IStateMachine.TransitionMap transitionMap = new IStateMachine.TransitionMap();
        transitionMap.possibleTransitions.put(IStateMachine.States.STOP, Collections.singletonList(Events.EV_STOP));
        transitionMap.transitions.put(Events.EV_STOP, IStateMachine.States.STOP);
        return transitionMap;
    }
}
