import java.util.Arrays;
import java.util.Scanner;

public class BattleView {
	private static Scanner reader = new Scanner(System.in);
	
	public BattleView() {
		
	}
	public static void printMoves(String[] moves) {
		System.out.println("\nYour Current Moves Are:");
		Arrays.asList(moves).forEach(move -> System.out.println(move));
	}
	
	public static void viewEnemyHealth(int health) {
		System.out.println("Enemy Health is: " + health);
	}
	
	public static void viewPlayerHealth(int health) {
		System.out.println("Your Health is: " + health);
	}
	
	public static void viewTurnCount(int count) {
		System.out.println("TURN " + count + "\n");
	}
	
	public static String getUserInput() {
		return reader.nextLine();
	}
}
