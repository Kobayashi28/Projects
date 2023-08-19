package kobayashi.components;

import java.awt.Image;

import javax.swing.ImageIcon;

import kobayashi.main.Main;

public class LogoIcon {
	
	private int width, height;
	
	private String path;
	
	private ImageIcon img;
	
	public LogoIcon(int width, int height, String path) {
		
		this.path = path;
		this.width = width;
		this.height = height;

	}
	
	public ImageIcon setLogo() {
		img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(width, height , Image.SCALE_DEFAULT));
		return img;
	}
	
	
}
