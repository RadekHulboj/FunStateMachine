import java.util.Collections;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {

        IStateMachine IStateMachine = Application::build;
        Optional<IStateMachine.States> state = IStateMachine.getState(IStateMachine.Events.EV_STOP);


    }

    private static IStateMachine.TransitionMap build() {
        IStateMachine.TransitionMap transitionMap = new IStateMachine.TransitionMap();
        transitionMap.possibleTransitions.put(IStateMachine.States.STOP, Collections.singletonList(IStateMachine.Events.EV_STOP));
        transitionMap.transitions.put(IStateMachine.Events.EV_STOP, IStateMachine.States.STOP);
        return transitionMap;
    }
}
