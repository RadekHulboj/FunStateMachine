public class Application {



    public static void main(String[] args) {
        StateMachineBuilder stateMachineBuilder = new StateMachineBuilder();
        IStateMachine<StateMachineBuilder.States, StateMachineBuilder.Events> IStateMachine = stateMachineBuilder::build;
        StateMachineBuilder.States state = IStateMachine.getState(StateMachineBuilder.Events.EV_WORK);
    }


}
