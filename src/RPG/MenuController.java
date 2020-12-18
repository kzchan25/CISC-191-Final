package RPG;
public class MenuController {
	
	//initializes menus, sets main menu to active
	public static void onStart() {
		MainMenu.active = true;
		new PauseMenu();
		new MainMenu();
		new UpgradeMenu();
		new BattleMenu();
	}

	//main controller, shows the active menu, and routes code to it
	public static void display() {
		if (PauseMenu.active) {
			ViewMenu.viewMenu(PauseMenu.getButtons(), "Game Paused");
		}
		else if (MainMenu.active) {
			ViewMenu.viewMenu(MainMenu.getButtons(), "Main Menu");
		}
		else if (UpgradeMenu.active) {
			ViewMenu.viewMenu(UpgradeMenu.getButtons(), "Upgrade a Move");
			BattleView.showBasicInfo();
			BattleView.animate();
		}
		else if (BattleMenu.active) {
			ViewMenu.viewMenu(BattleMenu.getButtons(), "Make a Move");
			BattleView.showBasicInfo();
			BattleView.animate();
		}
	}
	
	//routes the input to the active class
	public static void mouseClicked() {
		if (PauseMenu.active) {
			PauseMenu.mouseClicked(ViewMenu.applet.mouseX, ViewMenu.applet.mouseY);
		}
		else if (MainMenu.active) {
			MainMenu.mouseClicked(ViewMenu.applet.mouseX, ViewMenu.applet.mouseY);
		}
		else if (UpgradeMenu.active) {
			UpgradeMenu.mouseClicked(ViewMenu.applet.mouseX, ViewMenu.applet.mouseY);
		}
		else if (BattleMenu.active) {
			BattleMenu.mouseClicked(ViewMenu.applet.mouseX, ViewMenu.applet.mouseY);
		}
	}
}
