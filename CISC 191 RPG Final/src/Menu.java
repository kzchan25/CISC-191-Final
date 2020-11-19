
public abstract class Menu {
	private static int menuIDTracker = 0;
	private int menuID;
	Menu() {
		assignMenuID();
	}
	
	private  void assignMenuID() {
		this.menuID = assignMenuIDHelper();
	}
	
	private static int assignMenuIDHelper() {
		menuIDTracker++;
		return menuIDTracker;
	}
	
	public abstract void onClick();
	
	public abstract void keyPressed();
}
