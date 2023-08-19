package graphicalUserInterface;

import criptografia.Encrypt;
import criptografia.Decrypt;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Content {
	
	public JPanel container;
	public JTextField textarea;
	public JTextField output;

	
	public Content() {
		container = new JPanel(null);
		this.setContainer();
	}
	
	public void setContainer() {
		container.setBounds(0, 0, Main.Wwidth, Main.Wheight);
		container.setBackground(Color.white);
		container.setVisible(true);
		
		this.setTextbox();
		this.setOutput();
		this.setEncryptButton();
		this.setDecryptButton();
		this.setLabels();
		this.setCopyButton();
		
	}
	
	public void setCopyButton() {
		JButton copy = new JButton("Copiar resultado");
		copy.setBounds(50, 210, 180, 60);
		copy.setFont(new Font("Arial", Font.BOLD, 18));
		copy.setBackground(Color.LIGHT_GRAY);
		copy.setForeground(Color.black);

		copy.setBorder(BorderFactory.createEmptyBorder());
		
		copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StringSelection selection = new StringSelection(output.getText());
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				cb.setContents(selection, selection);
			}
			
		});
		
		container.add(copy);
	}

	
	public void setLabels() {
		JLabel tte = new JLabel("Insira o texto a ser modificado: ");
		tte.setBounds(50, 25, 300, 20);
		tte.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel te = new JLabel("Resultado: ");
		te.setBounds(50, 120, 200, 20);
		te.setFont(new Font("Arial", Font.BOLD, 18));

		container.add(te);
		container.add(tte);
	}
	
	public void setTextbox() {
		textarea = new JTextField();
		textarea.setBounds(50, 50, (Main.Wwidth - 100), 40);
		textarea.setVisible(true);
		textarea.setBackground(Color.white);
		textarea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		container.add(textarea);
	}
	
	public void setOutput() {
		output = new JTextField();
		output.setBounds(50, 140, (Main.Wwidth - 100), 40);
		output.setEditable(false);
		output.setVisible(true);
		output.setBackground(Color.LIGHT_GRAY);
		output.setBorder(BorderFactory.createLineBorder(Color.black));
		
		container.add(output);
	}
	
	public void setEncryptButton() {
		JButton btn = new JButton("Criptografar");
		btn.setBounds(260, 210, 140, 60);
		btn.setFont(new Font("Arial", Font.BOLD, 18));
		btn.setBackground(Color.LIGHT_GRAY);
		btn.setForeground(Color.black);
		btn.setBorder(BorderFactory.createEmptyBorder());
		
		btn.setVisible(true);
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				output.setText(Encrypt.encryptMethod1(textarea.getText()));
				
			}
			
		});
		container.add(btn);
	}
	
	public void setDecryptButton() {
		JButton btn = new JButton("Decriptar");
		btn.setBounds(430, 210, 120, 60);
		btn.setFont(new Font("Arial", Font.BOLD, 18));
		btn.setBackground(Color.LIGHT_GRAY);
		btn.setForeground(Color.black);
		btn.setBorder(BorderFactory.createEmptyBorder());
		
		btn.setVisible(true);
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String result = Decrypt.decryptMethod3(textarea.getText());
				output.setText(result);
			}
			
		});
		container.add(btn);
	}
	
}
