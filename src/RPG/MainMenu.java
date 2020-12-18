package RPG;

public class MainMenu implements Menu{
	
	private static String[] options = {"new game", "load game", "exit"};
	private static MenuButton[] buttons;
	private static final int NUM_OPTIONS = options.length;
	public static boolean active = true;
	
	public MainMenu() {
		int headerHeight = ViewMenu.applet.height * 1 / 10;
		buttons = new MenuButton[NUM_OPTIONS];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new MenuButton(0, (i * (ViewMenu.applet.height - headerHeight) / NUM_OPTIONS) + headerHeight, ViewMenu.applet.width / 5, ((ViewMenu.applet.height - headerHeight) / NUM_OPTIONS ), options[i]);
		}
	}
	
	public static void mouseClicked(int x, int y) {
		for (MenuButton button : buttons) {
			if (button.contains(x, y)) {
				buttonPressed(button.text);
				break;
			}
		}
	}
	
	public static boolean enterPressed() {
		return true;
	}

	public static MenuButton[] getButtons() {
		return buttons;
	}
	
	public static void buttonPressed(String pressed) {
		switch (pressed) {
		case "new game":
			Main.newGame();
			active = false;
			BattleMenu.active = true;
			break;
		case "load game":
			Main.loadGame();
			active = false;
			BattleMenu.active = true;
			break;
		case "exit":
			System.exit(0);
		}
	}
}
