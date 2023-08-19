package kobayashi.main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.JFrame;

import kobayashi.components.ErrorMsg;
import kobayashi.main.LandingPage;
import kobayashi.pages.Login;

public class Main {
	
	public static JFrame frame;
	public static LandingPage lp;
	public static Login login;
	
	public static int userId;
	public static String username;
	public static boolean isAdmin;
	
	public static final int barcodeLength = 13;
	
	public static int width = 1000,  height = 600, loginW = 350, loginH = 500;
	
	public Main() {
		frame = new JFrame();
		login = new Login();
	}
	
	public static void main(String[] args) {
		Main main  = new Main();
		main.Gui();
	}
	
	public void Gui() {

		frame.setTitle("Fantástica sorvetes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(loginW, loginH);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        URL url = this.getClass().getResource("/logo.jpeg");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        frame.setIconImage(imagemTitulo);
        frame.setVisible(true);
        
        
        frame.add(login.container);
	}
	
}
