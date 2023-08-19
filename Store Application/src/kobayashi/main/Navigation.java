package kobayashi.main;

import kobayashi.pages.Home;
import kobayashi.pages.Login;

public class Navigation {
	
	private static void reset() {
		
		if(Main.lp != null) {
			Main.frame.remove(Main.lp.container);
			Main.lp = null;
		}
		if(Main.login != null) {
			Main.frame.remove(Main.login.container);
			Main.login = null;
		}
	}
	
	public static void setLogin() {
		reset();
		Main.frame.setSize(Main.loginW, Main.loginH);
        Main.frame.setLocationRelativeTo(null);
        Main.login = new Login();
        Main.frame.add(Main.login.container);
		Main.login.container.setVisible(true);
	}
	
	public static void setLandingPage() {
		reset();
		Main.lp = new LandingPage();
		Main.frame.add(Main.lp.container);
		Main.frame.setSize(Main.width, Main.height);
        Main.frame.setLocationRelativeTo(null);
		Main.lp.container.setVisible(true);
	}
}
