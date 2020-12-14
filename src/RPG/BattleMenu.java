package RPG;

//Ryan Cervantes
//Draws the Battle Menu, takes the input from the mouse, decides which button was pressed, and passes that onto the Battle Controller

public class BattleMenu implements Menu {
	
	private static MenuButton[] buttons;
	public static boolean active = false;
	private static String[] options = {"attack", "poison", "shield", "heal", "pause"};
	private static final int NUM_OPTIONS = 4;
	
	//makes 5 buttons, the ones in options[] and pause(done seperately because its in another corner)
	public BattleMenu() {
		int headerHeight = ViewMenu.applet.height * 1 / 10;
		int interfaceHeight = ViewMenu.applet.height - headerHeight;
		buttons = new MenuButton[5];
		for (int i = 0; i < NUM_OPTIONS; i++) {
			buttons[i] = new MenuButton(0, (i * (interfaceHeight) / NUM_OPTIONS) + headerHeight, ViewMenu.applet.width / 5, ((interfaceHeight) / NUM_OPTIONS ), options[i]);
		}
		buttons[4] = new MenuButton(ViewMenu.applet.width - 150, 0, 150, 70, options[4]);
	}
	
	//checks for mouse click on button
	public static void mouseClicked(int x, int y) {
		for (MenuButton button : buttons) {
			if (button.contains(x, y)) {
				BattleController.iterateTurn(button.text);
				break;
			}
		}
	}
	
	public static MenuButton[] getButtons() {
		return buttons;
	}
}
