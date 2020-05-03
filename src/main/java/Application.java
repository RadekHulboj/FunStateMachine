import java.util.Collections;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {

        StateMachine stateMachine = Application::build;
        Optional<StateMachine.States> state = stateMachine.getState(StateMachine.Events.EV_STOP);


    }

    private static StateMachine.TransitionMap build() {
        StateMachine.TransitionMap transitionMap = new StateMachine.TransitionMap();
        transitionMap._possibleTransitions.put(StateMachine.States.STOP, Collections.singletonList(StateMachine.Events.EV_STOP));
        transitionMap._transitions.put(StateMachine.Events.EV_STOP, StateMachine.States.STOP);
        return transitionMap;
    }
}
