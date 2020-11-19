
public class Main {
	private static boolean gameOver = false;
	
	public static void main(String[] args) {
		MoveList list = new MoveList();
		BattleController model = new BattleController();
		while(!gameOver) {
			model.turnIteration();
		}
		System.out.println("GameOver");
	}
	
	
	public void loop() {
		
	}
	public static void gameOver() {
		gameOver = true;
	}
}
