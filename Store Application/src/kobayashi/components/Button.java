package kobayashi.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import kobayashi.main.LandingPage;
import kobayashi.main.Navigation;

public class Button {
	private int buttonId;
	private JButton optBtn;
	
	public JButton newButton( int buttonId, String path) {
		
		this.buttonId = buttonId;
		
		optBtn = new JButton();
		
		optBtn.setBorder(BorderFactory.createEmptyBorder());
		optBtn.setBackground(null);
		
		if(buttonId == 3) {
			optBtn.setIcon(new LogoIcon(35,35,path).setLogo());
			optBtn.setBounds(40, 75+(105*(buttonId+1)), 35, 35);

		}else {
			optBtn.setIcon(new LogoIcon(40,40,path).setLogo());	
			optBtn.setBounds(40, 20+(100*(buttonId+1)), 40, 40);
			
		}
		
		optBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LandingPage.home.setVisibility(false);
				LandingPage.promotions.setVisibility(false);
				LandingPage.history.setVisibility(false);
				LandingPage.admin.setVisibility(false);
				switch(buttonId) {
				case 0:
					LandingPage.home.setVisibility(true);
					break;
				case 1:
					LandingPage.promotions.setVisibility(true);
					break;
				case 2:
					LandingPage.history.setVisibility(true);
					break;
				case 3:
					LandingPage.admin.setVisibility(true);
					break;
				}
			}
			
		});
		return optBtn;
	}
	
}
