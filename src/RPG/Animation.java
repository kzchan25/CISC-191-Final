package RPG;

//Tram Huynh
//takes an array of images and displays them at a given x and y value. can also invert images, cycles > 1 indicates priority over other animations.

import processing.core.PApplet;
import processing.core.PImage;

public class Animation {
	private PApplet applet = ViewMenu.applet;
	private PImage[] images;
	private int frameCount = 0;
	private int totalFrames;
	private int cycles = 0;
	private final int FRAME_LENGTH = 4;
	//takes the path from the directory to fill images array
	public Animation(String imageName, int totalFrames) {
		images = new PImage[totalFrames];
		this.totalFrames = totalFrames;	
		for (int i = 0; i < totalFrames; i++) {
		      String filename = imageName + PApplet.nf(i, 3);
		      images[i] = applet.loadImage("resources/sprites" + filename + ".png");
		}
	}
	//draws images, uses rotation matrix to flip image, also decrements a cycle once an animation has finished
	//changes image in array once every 4 frames
	public void draw(int x, int y, boolean inverted) {
		frameCount++;
		if (inverted) {
			ViewMenu.applet.pushMatrix();
			ViewMenu.applet.scale(-1, 1);
			int width = images[frameCount / ((totalFrames * FRAME_LENGTH - 1) / totalFrames)].width * 3 / 4; //constants used here for scaling the image after the flip, rotation matrices are funky
			int height = images[frameCount / ((totalFrames * FRAME_LENGTH - 1) / totalFrames)].height * 3 / 4;
			ViewMenu.applet.image(images[frameCount / ((totalFrames * FRAME_LENGTH - 1) / totalFrames)] , -x, y , width, height);
			ViewMenu.applet.popMatrix();
		}
		else {
			applet.image(images[frameCount / ((totalFrames * FRAME_LENGTH - 1) / totalFrames)], x, y);
		}
		if (frameCount == totalFrames * 3 - 1) {
			frameCount = 0;
			cycles--;
		}
	}
	//will set priority to draw this object
	public void animate() {
		cycles = 1;
		frameCount = 0;
	}
	//used to determine which animation to use
	public int getCycles() {
		return cycles;
	}
}
