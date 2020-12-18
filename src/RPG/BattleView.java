package RPG;
import java.util.ArrayList;

public class BattleView {
	
	private static Animation enemyIdle;
	private static Animation enemyDead;
	private static Animation enemyAttack;
	private static Animation playerIdle;
	private static Animation playerAttack;
	private static Animation playerDead;
	private static ArrayList<FadeText> messages = new ArrayList<FadeText>();
	 
	//initializes animations, passes file path and size of animations in frames
	public static void onStart() {
		enemyIdle = new Animation("/enemy/idle/Idle__", 17);
		enemyDead = new Animation("/enemy/dead/die__", 10);
		enemyAttack = new Animation("/enemy/attack/attack__", 8);
		playerIdle = new Animation("/player/idle/Idle__", 10);
		playerDead = new Animation("/player/dead/Dead__", 10);
		playerAttack = new Animation("/player/attack/Attack__", 10);
	}
	//adds text that fades over time
	public static void addMessage(String text, int x, int y) {
		messages.add(new FadeText(text, x , y));
	}
	//displays health, turn, enemies defeated
	public static void showBasicInfo() {
		ViewMenu.applet.strokeWeight(0);
		ViewMenu.applet.fill(ViewMenu.white.r, ViewMenu.white.g, ViewMenu.white.b);
		ViewMenu.applet.rect(ViewMenu.applet.width / 5, ViewMenu.applet.height * 1 / 10, ViewMenu.applet.width * 13 / 20, ViewMenu.applet.height * 1 / 10);
		ViewMenu.applet.fill(ViewMenu.dark.r, ViewMenu.dark.g, ViewMenu.dark.b);
		ViewMenu.applet.text("Health", ViewMenu.applet.width / 5 + 5, ViewMenu.applet.height * 1 / 10 * 15 / 12);
		ViewMenu.applet.text("TURN: " + BattleController.getTurnsPassed(), ViewMenu.applet.width / 2, ViewMenu.applet.height * 9 / 10);
		ViewMenu.applet.text("ENEMIES DEFEATED: " + BattleController.getEnemiesDefeated(), ViewMenu.applet.width * 7 / 10, ViewMenu.applet.height * 9 / 10);
		ViewMenu.applet.text("Health", ViewMenu.applet.width * 4 / 5, ViewMenu.applet.height * 1 / 10 * 15 / 12);
		ViewMenu.applet.text(BattleController.player.getHealth(), ViewMenu.applet.width / 5 + 5, ViewMenu.applet.height * 2 / 10);
		ViewMenu.applet.text(BattleController.currentEnemy.getHealth(), ViewMenu.applet.width * 4 / 5, ViewMenu.applet.height * 2 / 10);
		ViewMenu.applet.strokeWeight(4);
	}

	//decides which animation will be called based on if the animation has cycles
	//also shows Fade Text, when one reaches 0 opacity, gets deleted
	public static void animate() {
		if (enemyAttack.getCycles() != 0) {
			enemyAttack.draw(ViewMenu.applet.width  * 3 / 4, ViewMenu.applet.height / 2, true);
		}
		else if (enemyDead.getCycles() != 0) {
			enemyDead.draw(ViewMenu.applet.width  * 3 / 4, ViewMenu.applet.height / 2, true);
		}
		else {
			enemyIdle.draw(ViewMenu.applet.width  * 3 / 4, ViewMenu.applet.height / 2, true);
		}
		if (playerAttack.getCycles() != 0) {
			playerAttack.draw(ViewMenu.applet.width  * 1 / 4, ViewMenu.applet.height / 2, false);
		}
		else if (playerDead.getCycles() != 0) {
			playerDead.draw(ViewMenu.applet.width  * 1 / 4, ViewMenu.applet.height / 2, false);
		}
		else {
			playerIdle.draw(ViewMenu.applet.width * 1 / 4, ViewMenu.applet.height / 2, false);
		}
		messages.forEach(message -> message.draw());
		messages.stream().filter(message -> message.getOpacity() > 0);
	}
	
	//displays text for an enemy dying, also animates it
	public static void enemyDied() {
		addMessage("An Enemy Has Died", ViewMenu.applet.width / 2, ViewMenu.applet.height / 2);
		enemyDead.animate();
	}
	
	//displays what move the enemy made, also plays attack animation
	public static void enemyMoveMade(String move) {
		BattleView.addMessage(move, ViewMenu.applet.width  * 3 / 4, ViewMenu.applet.height * 1 / 3);
		enemyAttack.animate();
	}

	//plays the attack animation
	public static void playerMove() {
		playerAttack.animate();
	}
}
