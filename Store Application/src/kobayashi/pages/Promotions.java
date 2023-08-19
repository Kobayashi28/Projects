package kobayashi.pages;

import java.awt.Color;

import javax.swing.JPanel;

import kobayashi.main.Global;
import kobayashi.main.LandingPage;
import kobayashi.main.Main;

public class Promotions {
	
	public JPanel container;
	public boolean visibility = false;
	
	public JPanel setPromo() {
		
		container = new JPanel(null);
		
		container.setBackground(Global.generalBg);
		container.setBounds(LandingPage.menuWidth, 0, LandingPage.contentWidth, Main.height);
		container.setVisible(false);
		
		return container;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
		container.setVisible(visibility);
	}
}
