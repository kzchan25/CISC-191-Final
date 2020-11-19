import java.util.LinkedList;
import java.util.Queue;
public class BattleController {
	private static Character player;
	private static Queue<EnemyCharacter> enemies;
	private static EnemyCharacter currentEnemy;
	private static int turnCount;
	public BattleController() {
		player = new Character("Player", 100);
		enemies = new LinkedList<EnemyCharacter>();
		generateEnemies();
		populatePlayerMoves();
		turnCount = 0;
	}
	
	public static void turnIteration() {
		turnCount++;
		if (currentEnemy == null && enemies.size() != 0) {
			currentEnemy = enemies.remove();
		}
		BattleView.viewTurnCount(turnCount);
		BattleView.viewEnemyHealth(currentEnemy.getHealth());
		BattleView.viewPlayerHealth(player.getHealth());
		BattleView.printMoves(player.getMoves());
		playerTurn();
		if (currentEnemy.getHealth() > 0) {
			currentEnemy.makeMove();
		}
		else {
			currentEnemy = null;
		}
		gameIsOverCheck();
	}
	
	private static void gameIsOverCheck() {
		if ((currentEnemy == null && enemies.size() == 0) || player.getHealth() == 0) {
			Main.gameOver();
		}
	}
	
	public static void generateEnemies() {
		for (int i = 0; i < 2; i++) {
			EnemyCharacter temp = new EnemyCharacter("Enemy " + i, 20);
			enemies.add(temp);
		}
	}
	
	public static EnemyCharacter getCurrentEnemy() {
		return currentEnemy;
	}
	
	public static void populatePlayerMoves() {
		player.addMove("attack1");
		player.addMove("poison1");
		player.addMove("heal1");
		player.addMove("debug1shot");
	}
	
	private static void playerTurn() {
		if (!player.makeMove(BattleView.getUserInput())) {
			System.out.println("Please input valid move");
			playerTurn();
		}
	}
	
}
