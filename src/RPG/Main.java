package RPG;

//Kai Chan, Ryan Cervantes, Tram Huynh
//group E, CISC 191

import processing.core.PApplet;
import processing.core.PFont;

public class Main extends PApplet{

	private static final long serialVersionUID = -2335511677622606735L;
	
	//applet and font globals
	public PApplet applet = this;
	public final PFont regular = createFont("resources/font/SFUIDisplay-Regular.otf", 32);
	public final PFont bold = createFont("resources/font/SFUIDisplay-Bold.ttf", 32);
	//sets up processing 
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", Main.class.getName() }); 
	}
	//runs once at the beginning of the program, commented in a common resolution
	public void setup() {
		size(1920, 1080);
		//size(2560, 1440);
		
		stroke(ViewMenu.light.r, ViewMenu.light.g, ViewMenu.light.b);
		strokeWeight(4);
		fill(0);
		textFont(regular, 16);
		ViewMenu.applet = this;
		BattleView.onStart();
		MenuController.onStart();
	}
	//runs 60 times every second
	public void draw() {
		background(ViewMenu.light.r, ViewMenu.light.g, ViewMenu.light.b);
		MenuController.display();
	}
	//creates a new game, and brings the player to the main menu
	public static void gameOver() {
		newGame();
		MainMenu.active = true;
	}
	//saves game when game is paused and left to main menu
	public static void saveGame() {
		DataIO.saveMoveList();
		DataIO.savePlayer();
		DataIO.saveEnemy();
		DataIO.saveScene();
	}
	//loads game from json file called from main menu
	public static void loadGame() {
		DataIO.loadMoveList();
		DataIO.loadPlayer();
		DataIO.loadEnemy();
		DataIO.loadScene();
	}
	//creates new game
	public static void newGame() {
		new MoveList();
		new BattleController();
	}
	//using the listener from processing
	public void mouseClicked() {
		MenuController.mouseClicked();
	}
	
}
