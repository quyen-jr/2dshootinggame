package EnemyState;

public class EnemyStateMachine {
	public EnemyState currentState;
	public void initialize(EnemyState _startState) {
		currentState=_startState;
		currentState.enter();	
	}
	public void changeState(EnemyState _newState) {
		currentState.exit();
		currentState=_newState;
		currentState.enter();
	}
}
