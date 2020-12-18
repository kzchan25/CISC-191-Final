package RPG;

import java.awt.Rectangle;

//container class for rectangles and text, used for easy collision checking
public class MenuButton extends Rectangle {
	private static final long serialVersionUID = 1L;
	public String text;
	public MenuButton(String text) {
		this.text = text;
	}
	public MenuButton(int x, int y, int w, int h, String text) {
		this(text);
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
}
