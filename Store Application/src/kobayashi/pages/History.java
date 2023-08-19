package kobayashi.pages;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import kobayashi.components.ErrorMsg;
import kobayashi.components.IndividualItemSold;
import kobayashi.components.LogoIcon;
import kobayashi.components.SalesInfo;
import kobayashi.main.DatabaseQueries;
import kobayashi.main.Global;
import kobayashi.main.LandingPage;
import kobayashi.main.Main;

public class History {
	
	public JPanel container, salesList, individualItemList = new JPanel(new GridLayout(0,1,3,3));
	public boolean visibility = false, getByAllUsers = false;
	public ArrayList<SalesInfo> salesInfo = null;
	public JComboBox users = new JComboBox();
	public JFormattedTextField dateInput ;
	public JDialog dialog, Edialog;

	
	
	
	public JPanel setHistory() {
		
		container = new JPanel(null);
		
		container.setBackground(Global.generalBg);
		container.setBounds(LandingPage.menuWidth, 0, LandingPage.contentWidth, Main.height);
		container.setVisible(visibility);
		
		setDialogBox();
		
		container.add(setHeader());
		container.add(setHeaderLabels());
		container.add(setContent());
		return container;
	}
	
	public JPanel setHeader() {
		JPanel header = new JPanel(null);
		header.setBounds(0, 0, LandingPage.contentWidth, 50);
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		
		JLabel title = new JLabel("Histórico do dia: ");
		title.setForeground(Color.black);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		title.setBounds(10, 10, 160, 30);
		MaskFormatter mask = null;
		
		try {
			mask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	    dateInput =  new JFormattedTextField(mask);
		dateInput.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		dateInput.setBounds(180, 10, 100, 30);
		dateInput.setHorizontalAlignment(JTextField.CENTER);
		dateInput.setFont(new Font("Arial", Font.BOLD, 15));
		dateInput.setForeground(Color.black);
		
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");		
	    dateInput.setText(formatter.format(date));
	    
	    if(Main.isAdmin) {
	    	
	    	ArrayList<String> userList = DatabaseQueries.getUserNames();
	    	users.setBounds(290, 10, 200, 30);
	    	users.setBackground(Global.generalBg);
	    	users.setFont(new Font("Arial", Font.PLAIN, 15));
	    	users.setForeground(Color.black);
	    	users.setBorder(BorderFactory.createEmptyBorder());
	    	users.setToolTipText("Selecione um usuário");
	    	
	    	if(Main.isAdmin)
	    		users.addItem("Todos os usuários");
	    	
	    	for(int i = 0; i < userList.size(); i++) {
	    		users.addItem(userList.get(i));
	    		if(Main.username.equals(userList.get(i))) {
	    			users.setSelectedItem(Main.username);
	    		}
	    	}
	    	
	    	
	 			header.add(users);	
	    	
	    }

		JButton searchButton = new JButton(new LogoIcon(40, 40, "/lupa.png").setLogo());
		searchButton.setBounds(LandingPage.contentWidth - 50, 5, 40, 40);
		searchButton.setBackground(Global.generalBg);
		searchButton.setFocusPainted(false);
		searchButton.setBorder(BorderFactory.createEmptyBorder());
		searchButton.setToolTipText("Buscar");
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setHistoryList();
			}
			
		});
		
		header.add(title);
		header.add(searchButton);
		header.add(dateInput);
		
		
		return header;
	}
	
	public JPanel setHeaderLabels() {
		
		JPanel headerLabels = new JPanel(new FlowLayout(FlowLayout.LEFT));
		headerLabels.setBounds(0, 50, LandingPage.contentWidth, 35);
		headerLabels.setBackground(Global.generalBg);
		headerLabels.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));
		
		Font font = new Font("Arial", Font.BOLD, 12);
		
		headerLabels.add(setLabel("ID da venda", 60,font));
		
		headerLabels.add(setLabel("Nome do vendedor", 140,font));

		headerLabels.add(setLabel("Valor total", 60,font));

		headerLabels.add(setLabel("Valor recebido", 60,font));

		headerLabels.add(setLabel("Troco", 60,font));
		
		headerLabels.add(setLabel("Método de pagamento", 130,font));


		

		return headerLabels;
	}
	
	public JPanel setContent() {
		JPanel content  = new JPanel();
		content.setBounds(0, 80, LandingPage.contentWidth, Main.height - 50);
		
		salesList = new JPanel(new GridLayout(0,1,3,3));
		
		JScrollPane scrollPane = new JScrollPane(salesList,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.setPreferredSize(new Dimension(LandingPage.contentWidth, Main.height - 110));
					
		setHistoryList();
	
		content.add(scrollPane);
		
		return content;
	}
	
	public void updateInfo() {
		if(Main.isAdmin) {
			if(users.getSelectedIndex() == 0) {
				 salesInfo = DatabaseQueries.getAllSalesInfoOnTheDay(dateInput.getText());
				 getByAllUsers = true;
			}else {
				 salesInfo = DatabaseQueries.getSalesBySellerId(DatabaseQueries.getIdByUsername((String) users.getSelectedItem()),dateInput.getText());
			}
		}else {
			 salesInfo = DatabaseQueries.getSalesBySellerId(Main.userId, dateInput.getText());
		}
		
	}
	
	public void resetHistoryList() {
		int salesListSize = salesList.getComponentCount();
		for(int i = 0; i <salesListSize; i++) {
			salesList.remove(salesList.getComponent(0));
			salesInfo.remove(0);
		}
	}
	
	public void updateContent() {
		container.revalidate();
		container.repaint();	
	}
	
	public void setHistoryList() {
		resetHistoryList();
		updateInfo();
		if(visibility) {
			if(salesInfo.size() == 0) {
				if(Main.isAdmin) {
					if(getByAllUsers) {
						ErrorMsg.setErrorDialog("Nenhuma venda realizada no dia " + dateInput.getText()+".");
						getByAllUsers = false;
					}else {
						ErrorMsg.setErrorDialog("Nenhuma venda realizada no dia " + dateInput.getText() +" pelo usuário '" + users.getSelectedItem()+"'.");
					}
				}else {
					ErrorMsg.setErrorDialog("Nenhuma venda realizada no dia " + dateInput.getText() + ".");
				}
			}
		}
		
		
		for(int i = 0; i < salesInfo.size(); i++) {			
			
			JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER));
			row.setPreferredSize(new Dimension(LandingPage.contentWidth, 50));
			row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			Font font = new Font("Arial", Font.PLAIN, 10);
			row.add(setLabel("" + salesInfo.get(i).saleId, 20, font ));
						
			row.add(setLabel("" + salesInfo.get(i).sellername, 160, font));
			
			row.add(setLabel("R$ " + salesInfo.get(i).totalValue, 60, font));
			
			row.add(setLabel("R$ " + salesInfo.get(i).valueReceived, 60, font));
			
			row.add(setLabel("R$ " + salesInfo.get(i).changeGiven, 60, font));
			
			row.add(setLabel("" + salesInfo.get(i).paymentMethod, 140, font));
			
			JButton moreInfo = new JButton(new LogoIcon(30,30, "/infoicon.png").setLogo());
			moreInfo.setPreferredSize(new Dimension(30, 30));
			moreInfo.setName(salesInfo.get(i).saleId + "");
			moreInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			moreInfo.setBorder(BorderFactory.createEmptyBorder());
			moreInfo.setBackground(Global.generalBg);
			moreInfo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					setDialogInfo(Integer.parseInt(moreInfo.getName()));
					dialog.setVisible(true);
				}
				
			});
			
			row.add(moreInfo);

			salesList.add(row);
			
		}
		updateContent();
	}
	
	public void setDialogBox() {
		dialog = new JDialog(Main.frame, "Lista dos produtos vendidos",  true);
		dialog.setLayout(new FlowLayout());				
		dialog.setSize(new Dimension(720, 500));
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						
		JPanel dialogHeader = new JPanel();
		dialogHeader.setPreferredSize(new Dimension(720, 50));
		dialogHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
		
		Font font = new Font("Arial", Font.BOLD, 15);
		
		dialogHeader.add(setLabel("Código de barras",  140, font));
		dialogHeader.add(setLabel("Quantidade", 100, font));
		dialogHeader.add(setLabel("Nome do produto", 160, font));
		dialogHeader.add(setLabel("Categoria", 140, font));
		dialogHeader.add(setLabel("Preço", 100, font));
		
		JScrollPane scrollPane = new JScrollPane(individualItemList,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.setPreferredSize(new Dimension(720, 450));
		
		dialog.add(dialogHeader);
		dialog.add(scrollPane);
		
	}
	
	public void resetDialogInfo() {
		int compCount = individualItemList.getComponentCount();
		for(int i  =0; i < compCount; i++) {
			individualItemList.remove(0);
		}
	}
	
	public void setDialogInfo(int mainSaleId) {
		resetDialogInfo();
		ArrayList<IndividualItemSold> info = DatabaseQueries.getIndividualItensSold(mainSaleId);
		Font font = new Font("Arial", Font.PLAIN, 15);
		for(int i = 0; i < info.size(); i++) {
				
			JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER));
			row.setPreferredSize(new Dimension(720, 40));
			row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			
			row.add(setLabel("" + info.get(i).productBarcode, 140,font));
			row.add(setLabel(info.get(i).quantitySold + "x", 100,font));
			row.add(setLabel("" + info.get(i).productName, 160,font));
			row.add(setLabel("" + info.get(i).categoryName, 140,font));
			row.add(setLabel("R$ " + info.get(i).productPrice, 100,font));
			
			individualItemList.add(row);
			
		}
			
		updateContent();
	}
	
	public JLabel setLabel(String info, int width, Font font) {
		
		JLabel lbl = new JLabel(info, SwingConstants.CENTER);
		lbl.setPreferredSize(new Dimension(width, 35));
		lbl.setToolTipText(info);
		lbl.setForeground(Color.black);
		lbl.setFont(font);
		return lbl;
	}
	
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
		container.setVisible(visibility);
	}
}
