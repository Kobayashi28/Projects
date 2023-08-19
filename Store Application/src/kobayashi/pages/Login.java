package kobayashi.pages;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kobayashi.components.ErrorMsg;
import kobayashi.main.DatabaseQueries;
import kobayashi.main.Global;
import kobayashi.main.Main;
import kobayashi.main.Navigation;

public class Login {
	public JPanel container;
	private JTextField username;
	private JPasswordField password;
	private JLabel userLabel, pwLabel, title, subtitle;
	private JButton reqButton;
	private JPanel invalidInputs = ErrorMsg.newErrorMsg(40, Main.loginH/3+220, Main.loginW-80, 40,70, "Senha ou usu�rio inv�lido!");
	private JPanel nullInputs = ErrorMsg.newErrorMsg(40, Main.loginH/3+220, Main.loginW-80, 40,70, "Preencha todos os campos!");
	
	public Login() {
		container = new JPanel(null);
		username = new JTextField();
		password = new JPasswordField();
		userLabel = new JLabel("Nome de usuário");
		pwLabel = new JLabel("Senha de usuário");
		title = new JLabel("Bem vindo de volta!");
		subtitle = new JLabel("<html><body>Insira o nome de usuário e sua senha para acessar o sistema.</body></html>");
		reqButton = new JButton("Entrar");
		invalidInputs.setVisible(false);
		nullInputs.setVisible(false);
		
		setContainer();
	}
	
	private void setLabels() {
		userLabel.setBounds(40, Main.loginH/3-18, Main.loginW-80, 20);
		userLabel.setForeground(Color.black);
		userLabel.setFont(new Font("Arial", Font.ITALIC, 17));
		
		pwLabel.setBounds(40, Main.loginH/3+72, Main.loginW-80, 20);
		pwLabel.setForeground(Color.black);
		pwLabel.setFont(new Font("Arial", Font.ITALIC, 17));
		
		title.setBounds(40, 50, Main.loginW-80, 40);
		title.setForeground(Color.black);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		
		subtitle.setBounds(40, 90, Main.loginW-80, 40);
		subtitle.setForeground(Color.black);
		subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
	}
	
	private void resetErrors() {
		invalidInputs.setVisible(false);
		nullInputs.setVisible(false);
	}
	
	private void setFields() {
		
		username.setBounds(40, Main.loginH/3, Main.loginW-80, 40);
		username.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Global.tertiaryBg));
		username.setBackground(Global.generalBg);
		username.setForeground(new Color(100,100,100));
		username.setFont(new Font("Arial", Font.PLAIN, 15));
		username.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				resetErrors();
			}
			public void focusLost(FocusEvent e) {}
			
		});
		username.addKeyListener(new KeyListener() {

			
			public void keyPressed(KeyEvent e) {
				
			}

			
			public void keyReleased(KeyEvent e) {
				
			}

			
			public void keyTyped(KeyEvent e) {
				char c  = e.getKeyChar();
				if( !Character.isAlphabetic(c) && !Character.isDigit(c)) {
					e.consume();
				}
			}
			
		});
		
		password.setBounds(40, Main.loginH/3+90, Main.loginW-80, 40);
		password.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Global.tertiaryBg));
		password.setBackground(Global.generalBg);
		password.setForeground(new Color(100,100,100));
		password.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				resetErrors();
			}
			public void focusLost(FocusEvent e) {}
			
		});
		password.addKeyListener(new KeyListener() {

			
			public void keyPressed(KeyEvent e) {
				
			}

			
			public void keyReleased(KeyEvent e) {
				
			}

			
			public void keyTyped(KeyEvent e) {
				char c  = e.getKeyChar();
				if( !Character.isAlphabetic(c) && !Character.isDigit(c)) {
					e.consume();
				}
			}
			
		});
		
	}
	
	public void setReqButton() {
		reqButton.setBounds(40, Main.loginH/3+160, Main.loginW-80, 40);
		reqButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		reqButton.setBackground(Global.secondaryBg);
		reqButton.setForeground(Color.black);
		reqButton.setFont(new Font("Arial", Font.BOLD, 16));
		reqButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		reqButton.setFocusPainted(false);
		
		reqButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String pw = String.valueOf(password.getPassword());
				if(!username.getText().equals("") && !pw.equals("")){
					System.out.println("?");
					if(DatabaseQueries.verifyUser(username.getText(), pw)) {
						Navigation.setLandingPage();
					}else {
						invalidInputs.setVisible(true);
					}
				}else {
					nullInputs.setVisible(true);
				}
			}
			
		});
	}
	
	public void setContainer() {
		container.setBounds(0,0,Main.loginW, Main.loginH);
		container.setBackground(Global.generalBg);
		
		
		setLabels();
		setFields();
		setReqButton();
		
		container.add(nullInputs);
		container.add(invalidInputs);
		container.add(title);
		container.add(subtitle);
		container.add(pwLabel);
		container.add(userLabel);
		container.add(password);
		container.add(username);
		container.add(reqButton);
	}
	
}
