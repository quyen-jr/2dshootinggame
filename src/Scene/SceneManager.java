package Scene;

import main.GamePanel;

public class SceneManager {

	public Scene currentScene;
	public void initialize(Scene _startScene) {
		currentScene=_startScene;
		currentScene.enter();
	}
	public void changeScene(Scene _newScene) {
		currentScene.exit();
		currentScene=_newScene;
		currentScene.enter();
	}
}
