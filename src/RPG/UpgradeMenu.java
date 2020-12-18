package RPG;

public class UpgradeMenu implements Menu {
	
	public static boolean active = false;
	private static String[] options = {"attack", "poison", "shield", "heal"};
	private static MenuButton[] buttons;
	private static final int NUM_OPTIONS = options.length;
	
	
	public UpgradeMenu() {
		int headerHeight = ViewMenu.applet.height * 1 / 10;
		buttons = new MenuButton[NUM_OPTIONS];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new MenuButton(0, (i * (ViewMenu.applet.height - headerHeight) / NUM_OPTIONS) + headerHeight, ViewMenu.applet.width / 5, ((ViewMenu.applet.height - headerHeight) / NUM_OPTIONS ), options[i]);
		}
	}
	//check for button pressed
	public static void mouseClicked(int x, int y) {
		for (MenuButton button : buttons) {
			if (button.contains(x, y)) {
				MoveList.upgradeMove(button.text);
				active = false;
				break;
			}
		}
	}
	
	public static MenuButton[] getButtons() {
		return buttons;
	}
}
