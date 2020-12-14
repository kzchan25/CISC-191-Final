package RPG;

//Kai Chan
//Main Gameplay loop, houses player and enemy objects and manipulates them. Coordinates with the battle view as well

public class BattleController {
	public static Character player;
	public static EnemyCharacter currentEnemy;
	private static int turnCount;
	private static int enemiesDefeated;
	//constructors
	//new constructor
	public BattleController() {
		player = new Character("Player", 100);
		populatePlayerMoves();
		turnCount = 0;
		enemiesDefeated = 0;
		generateEnemy();
	}
	//load constructor
	public BattleController(int turnCount, int enemiesDefeated) {
		populatePlayerMoves();
		BattleController.turnCount = turnCount;
		BattleController.enemiesDefeated = enemiesDefeated;
	}
	//main gameplay loop
	public static void iterateTurn(String input) {
		turnCount++;
		BattleView.showBasicInfo();
		player.endOfTurnStatusUpdate();
		currentEnemy.endOfTurnStatusUpdate();
		playerTurn(input);
		if (currentEnemy.getHealth() > 0) {
			currentEnemy.makeMove();
		}
		else {
			BattleView.enemyDied();
			enemiesDefeated++;
			generateEnemy();
			upgradeEnemyMoves();
			UpgradeMenu.active = true;
		}
		gameIsOverCheck();
	}
	//checks if player health is less or equal to 0 
	private static void gameIsOverCheck() {
		if (player.getHealth() <= 0) {
			Main.gameOver();
		}
	}
	//adds moves to player 
	public static void populatePlayerMoves() {
		player.addMove("attack");
		player.addMove("poison");
		player.addMove("heal");
		player.addMove("shield");
	}
	//tests for pause menu, also controls the view and the model for player turn
	private static void playerTurn(String input) {
		if (input.equals("pause")) {
			PauseMenu.active = true;
			return;
		}
		else {
			BattleView.addMessage(input, ViewMenu.applet.width  * 1 / 4, ViewMenu.applet.height * 1 / 3);
			BattleView.playerMove();
			player.makeMove(input);
		}
	}
	//tells the view to show the enemy making a move 
	public static void enemyMoveMade(String move) {
		BattleView.enemyMoveMade(move);
	}
	
	//called once enemy dies, generation algorithm
	private static void generateEnemy() {
		currentEnemy = (new EnemyCharacter("enemy" + enemiesDefeated + 1, generateEnemyHealth()));
	}
	
	//helper to generateEmemy(), used to see algorithm clearly for balancing purposes 
	private static int generateEnemyHealth() {
		return player.getAverageModifier() + (enemiesDefeated * 3) + (int)(Math.random() * 5);
	}
	
	//randomly upgrades 1 move from the enemies move pool
	private static void upgradeEnemyMoves() {
		int randomGenerated = (int)(Math.random() * 3);
		switch (randomGenerated) {
		case 0:
			MoveList.upgradeMove("enemyattack");
			break;
		case 1:
			MoveList.upgradeMove("enemyheal");
			break;
		case 2:
			MoveList.upgradeMove("enemypoison");
			break;
		}
	}
	//getters / setters
	public static int getTurnsPassed() {
		return turnCount;
	}
	
	public static int getEnemiesDefeated() {
		return enemiesDefeated;
	}
	
	public static void setTurnsPassed(int turns) {
		turnCount = turns;
	}
	
	public static void setEnemiesDefeated(int enemiesNumber) {
		enemiesDefeated = enemiesNumber;
	}
}
