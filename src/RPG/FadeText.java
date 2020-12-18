package RPG;

public class FadeText implements Menu {
	private String text;
	private int opacity = 255;
	private int x;
	private int y;

	public FadeText(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y ;
	}
	
	//reduces opacity by 4 every frame
	public void draw() {
		opacity -= 4;
		ViewMenu.applet.fill(ViewMenu.dark.r, ViewMenu.dark.g, ViewMenu.dark.b, opacity);
		ViewMenu.applet.text(text, x, y);
	}

	public int getOpacity() {
		return opacity;
	}
}
