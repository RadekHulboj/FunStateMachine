public class Application {
    public static void main(String[] args) {
        IStateMachine<StateMachineBuilder.States, StateMachineBuilder.Events> IStateMachine = StateMachineBuilder::build;
        StateMachineBuilder.States state1 = IStateMachine.getState(StateMachineBuilder.Events.EV_WORK, "Ala");
        StateMachineBuilder.States state2 = IStateMachine.getState(StateMachineBuilder.Events.EV_WORK, 2);
        StateMachineBuilder.States state3 = IStateMachine.getState(StateMachineBuilder.Events.EV_WORK);
    }
}
