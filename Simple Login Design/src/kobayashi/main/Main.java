
package kobayashi.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import kobayashi.components.LandingPage;

public class Main {
    
	public static int width = 1000, height = 600;
	
	public static int boxwidth = (width/2)+(width/7);
		
	private JFrame frame; 
	
	public LandingPage lp;
	
	public Connection connection;
	
	public Main() {
		frame = new JFrame();  
		lp = new LandingPage();
		connection = new Connection();
		try {
			connection.Conn();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void Gui() {
		frame.setTitle("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        frame.add(lp.box);  
	}
	
    public static void main(String[] args) {    
    	Main main =  new Main();
    	main.Gui();
    }

}