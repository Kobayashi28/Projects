package kobayashi.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kobayashi.main.Main;

public class LandingPage implements MouseListener{
	public JPanel box;
	private JPanel leftbox, rightbox, overbox;
	private boolean login = false;
	private BufferedImage img;
	private ImageIcon email_icon, lock_icon, user_icon;
	
	public LandingPage() {
		box = new JPanel(null);
		leftbox = new JPanel();
		rightbox = new JPanel(null);
		overbox = new JPanel(null);
		
		email_icon = new ImageIcon(getClass().getResource("/email_icon.png"));
		lock_icon = new ImageIcon(getClass().getResource("/lock_icon.png"));
		user_icon = new ImageIcon(getClass().getResource("/user_icon.png"));
		
		try {
			img = ImageIO.read(new File("res/desktopcode.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		leftBox();
		rightBox();
		
		box.add(overbox);
		box.add(leftbox);
		box.add(rightbox);
		
	}
	
	public void rightBox() {
		rightbox.setBackground(new Color(29, 30, 34));
		rightbox.setBounds(Main.boxwidth, 0, Main.width-Main.boxwidth, Main.height);
		
		JTextField username = new JTextField();
		username.setBounds(70, Main.height/3, (Main.width-Main.boxwidth)-100, 40);
		username.setBackground(new Color(40, 40, 40));
		username.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		username.setForeground(new Color(180, 180, 180));
		username.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JTextField email = new JTextField();
		email.setBounds(70, Main.height/3+60, (Main.width-Main.boxwidth)-100, 40);
		email.setBackground(new Color(40, 40, 40));
		email.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		email.setForeground(new Color(180, 180, 180));
		email.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JPasswordField password = new JPasswordField();
		password.setBounds(70, Main.height/3+120, (Main.width-Main.boxwidth)-100, 40);
		password.setBackground(new Color(40, 40, 40));
		password.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		password.setForeground(new Color(180, 180, 180));
		password.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JLabel title = new JLabel("<html><body>Join over 25 million <br> learners from around the globe</body></html>");
		title.setBounds(30, 50, (Main.width-Main.boxwidth)-60 , 50);
		title.setForeground(Color.white);
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel subtitle = new JLabel("<html><body>Master the language of the web: HTML, CSS and JS. <br> This path will prepare you to build basic websites <br> and then build interactive web apps.</body></html>");
		subtitle.setBounds(30, 90, (Main.width-Main.boxwidth)-60 , 100);
		subtitle.setForeground(new Color(140, 140, 140));
		subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JCheckBox agree = new JCheckBox();
		agree.setBounds(30, Main.height/3+180, 20, 20);
		agree.setBackground(new Color(40, 40, 40));
		
		JLabel agreements = new JLabel("<html><body>I agree to all statements in <a href='' style='text-decoration: none'>Terms of Service</a>.</html></body>");
		agreements.setBounds(60, Main.height/3 + 165, 250, 50);
		agreements.setForeground(new Color(200, 200, 200));
		agreements.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JCheckBox rememberCB = new JCheckBox();
		rememberCB.setBounds(30, Main.height/3+120, 20, 20);
		rememberCB.setBackground(new Color(40, 40, 40));
		rememberCB.setVisible(false);
		
		JLabel rememberLB = new JLabel("<html><body>Keep me logged in.</html></body>");
		rememberLB.setBounds(60, Main.height/3 + 105, 250, 50);
		rememberLB.setForeground(new Color(200, 200, 200));
		rememberLB.setFont(new Font("Arial", Font.PLAIN, 12));
		rememberLB.setVisible(false);
		
		JButton next = new JButton();
		next.setBounds(30, Main.height/3+220, (Main.width-Main.boxwidth)-60, 50);
		next.setBackground(new Color(86, 92, 231));
		next.setForeground(Color.white);
		next.setFont(new Font("Arial", Font.PLAIN, 20));
		next.setText("Start coding now");
		next.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	    next.setFocusPainted(false);
		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(login) {
					//code to login
				}else {
					//code to register
				}
			}
		});
		
		JLabel user_i = new JLabel(user_icon); 
	    user_i.setBounds(28, Main.height/3, 32, 32);
	     
	    JLabel  email_i = new JLabel(email_icon); 
	    email_i.setBounds(29, Main.height/3+60, 32, 32);
	     
	    JLabel lock_i = new JLabel(lock_icon); 
	    lock_i.setBounds(28, Main.height/3+120, 32, 32);
	    
	    JLabel changeButton = new JLabel("<html><body>Already have an account? <a href='' style='text-decoration: none'>Login</a></html></body>");
	    changeButton.setBounds(70, Main.height/3+270, 250, 50);
	    changeButton.setForeground(new Color(200, 200, 200));
	    changeButton.setFont(new Font("Arial", Font.PLAIN, 14));
	    changeButton.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		login = !login;
	    		
	    		if(login) {
	    			user_i.setVisible(false);
	    			username.setVisible(false);
	    			agreements.setVisible(false);
	    			agree.setVisible(false);
	    			rememberCB.setVisible(true);
	    			rememberLB.setVisible(true);
	    			
	    			email_i.setBounds(29, Main.height/3, 32, 32);
	    			email.setBounds(70, Main.height/3, (Main.width-Main.boxwidth)-100, 40);

	    			lock_i.setBounds(28, Main.height/3+60, 32, 32);
	    			password.setBounds(70, Main.height/3+60, (Main.width-Main.boxwidth)-100, 40);
	    			
	    			next.setBounds(30, Main.height/3+160, (Main.width-Main.boxwidth)-60, 50);

	    		    changeButton.setBounds(70, Main.height/3+210, 250, 50);

	    			
	    			System.out.println("remove disgraça");
	    		}else {
	    			user_i.setVisible(true);
	    			username.setVisible(true);
	    			agreements.setVisible(true);
	    			agree.setVisible(true);
	    			rememberCB.setVisible(false);
	    			rememberLB.setVisible(false);
	    			
	    			email_i.setBounds(29, Main.height/3+60, 32, 32);
	    			email.setBounds(70, Main.height/3+60, (Main.width-Main.boxwidth)-100, 40);

	    			lock_i.setBounds(28, Main.height/3+120, 32, 32);
	    			password.setBounds(70, Main.height/3+120, (Main.width-Main.boxwidth)-100, 40);
	    			
	    			next.setBounds(30, Main.height/3+220, (Main.width-Main.boxwidth)-60, 50);
	    		    changeButton.setBounds(70, Main.height/3+260, 250, 50);
	    		}
	    	}
	    });
	    
	     
	    rightbox.add(rememberCB);
	    rightbox.add(rememberLB);
	    rightbox.add(changeButton);
		rightbox.add(email_i);
		rightbox.add(user_i);
		rightbox.add(lock_i);
		rightbox.add(username);
		rightbox.add(email);
		rightbox.add(password);
		rightbox.add(title);
		rightbox.add(subtitle);
		rightbox.add(agree);
		rightbox.add(agreements);
		rightbox.add(next);
		
	}
	
	public void leftBox() {

		leftbox.setBounds(0, 0, Main.boxwidth, Main.height);
		
		JLabel picLabel = new JLabel(new ImageIcon(img));
        leftbox.add(picLabel);
        
        overbox.setBackground(new Color(86, 92, 231, 200));
        overbox.setBounds(0, 0, Main.boxwidth, Main.height);
        
        String txts[] = {"Learn to code.","Interactively.","For free."};
        for(int i = 0; i < txts.length; i++) {
        	JLabel txt = new JLabel(txts[i]);
        	 txt.setForeground(Color.white);
             txt.setFont(new Font("Arial", Font.BOLD, 50));
             txt.setBounds(Main.boxwidth/7,Main.height/5+(i*70), 500, 50);
             overbox.add(txt);
        }
        
        JButton button = new JButton();
        button.setBounds(Main.boxwidth/7, Main.height/5+210, 200, 50);
        button.setBackground(new Color(28, 29, 33));
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setText("Watch demo");
        button.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        button.setFocusPainted(false);
        overbox.add(button);
        
        String list[] = {"Option 1","Option 2","Option 3","Option 4","Option 5"};
        
        for(int i = 0; i < list.length; i++) {
        	JLabel txt = new JLabel(list[i]);
        	 txt.setForeground(Color.white);
             txt.setFont(new Font("Arial", Font.PLAIN, 15));
             txt.setBounds(Main.boxwidth/7+(i*100),Main.height-100, 120, 50);
             overbox.add(txt);
        }
        
        JLabel marca = new JLabel("</> First Brackets");
        marca.setForeground(Color.white);
        marca.setFont(new Font("Arial", Font.BOLD, 15));
        marca.setBounds(Main.boxwidth/7,10, 300, 50);
        overbox.add(marca);
        
        
        
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("b");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
