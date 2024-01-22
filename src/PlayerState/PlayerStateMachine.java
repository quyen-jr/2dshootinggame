package PlayerState;

public class PlayerStateMachine {
	protected PlayerState currentState;
	protected void initialize(PlayerState _startState) {
		currentState=_startState;
		currentState.enter();
	}
	protected void changeState(PlayerState _newState) {
		currentState.exit();
		currentState=_newState;
		currentState.enter();
	}
}
