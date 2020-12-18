package RPG;

public class PauseMenu implements Menu {
	public static boolean active = false;
	private static String[] options = {"resume", "main menu"};
	private static MenuButton[] buttons;
	private static final int NUM_OPTIONS = options.length;
	
	public PauseMenu() {
		int headerHeight = ViewMenu.applet.height * 1 / 10;
		buttons = new MenuButton[NUM_OPTIONS];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new MenuButton(0, (i * (ViewMenu.applet.height - headerHeight) / NUM_OPTIONS) + headerHeight, ViewMenu.applet.width / 5, ((ViewMenu.applet.height - headerHeight) / NUM_OPTIONS ), options[i]);
		}
	}
	//check to see if anything was pressed
	public static void mouseClicked(int x, int y) {
		for (MenuButton button : buttons) {
			if (button.contains(x, y)) {
				buttonPressed(button.text);
				break;
			}
		}
	}
	//determines what was pressed
	private static void buttonPressed(String pressed) {
		if (pressed.equals("resume")) {
			active = false;
		}
		else if (pressed.equals("main menu")) {
			active = false;
			Main.saveGame();
			MainMenu.active = true;
		}
	}
	
	public static MenuButton[] getButtons() {
		return buttons;
	}
}
