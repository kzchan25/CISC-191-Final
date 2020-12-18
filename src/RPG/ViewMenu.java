package RPG;

public class ViewMenu {
	
	//static applet for easy access, also contains colors for the menus
	public static Main applet;
	public static Color light = new Color(255, 166, 193);
	public static Color dark = new Color(0, 0, 0);
	public static Color highlightlight = new Color(255, 204, 219);
	public static Color highlightdark = new Color(23, 23, 23);
	public static Color white = new Color(255, 237, 241);
	
	//takes a list of buttons, and header. each button individually run through draw button, header put at top
	public static void viewMenu(MenuButton[] buttons, String header) {
		applet.background(ViewMenu.light.r, ViewMenu.light.g, ViewMenu.light.b);
		for (MenuButton button : buttons) {
			drawButton(button);
		}
		applet.textFont(applet.bold, 42);
		applet.fill(dark.r, dark.g, dark.b);
		applet.text(header, 30 , buttons[0].y / 2);
	}
	
	//if the button is highlighted, uses highlighted theme, if not uses normal theme
	private static void drawButton(MenuButton button) {
		if (!button.contains(applet.mouseX, applet.mouseY)) {
			applet.textFont(applet.regular, 16);
			applet.fill(dark.r, dark.g, dark.b);
			applet.rect(button.x, button.y, button.width, button.height);
			applet.fill(light.r, light.g, light.b);
			applet.text(button.text, button.x + 30, button.y + (button.height / 2) + 10);
		}
		else {
			applet.textFont(applet.regular, 16);
			applet.fill(highlightdark.r, highlightdark.g, highlightdark.b);
			applet.rect(button.x, button.y, button.width, button.height);
			applet.fill(highlightlight.r, highlightlight.g, highlightlight.b);
			applet.text(button.text, button.x + 30, button.y + (button.height / 2) + 10);
		}
	}
}

