package frames;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Event;

public class InvoicePanel extends JPanel {
	private JButton jbtnExport;
	private JButton jbtnImport;
	private JButton jbtnReturn;
	private JPanel Mid_MidPanel;

	/**
	 * Create the panel.
	 */
	public InvoicePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel Headerpanel = new JPanel();
		Headerpanel.setBackground(new Color(102, 204, 204));
		Headerpanel.setFont(new Font("Stencil Std", Font.BOLD, 14));
		FlowLayout fl_Headerpanel = (FlowLayout) Headerpanel.getLayout();
		fl_Headerpanel.setAlignment(FlowLayout.LEFT);
		Headerpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(Headerpanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setIcon(new ImageIcon(InvoicePanel.class.getResource("/resources/Modify48.png")));
		Headerpanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("INVOICE");
		lblNewLabel_1.setFont(new Font("Stencil", Font.ITALIC, 20));
		Headerpanel.add(lblNewLabel_1);
		
		JPanel BotPanel = new JPanel();
		BotPanel.setBackground(new Color(153, 153, 102));
		FlowLayout flowLayout = (FlowLayout) BotPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(BotPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.setIcon(new ImageIcon(InvoicePanel.class.getResource("/resources/Exit.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotPanel.add(btnNewButton);
		
		JPanel MidPanel = new JPanel();
		add(MidPanel, BorderLayout.CENTER);
		MidPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel Mid_TopPanel = new JPanel();
		Mid_TopPanel.setBackground(new Color(102, 204, 204));
		Mid_TopPanel.setForeground(new Color(255, 255, 255));
		MidPanel.add(Mid_TopPanel, BorderLayout.NORTH);
		
		jbtnExport = new JButton("EXPORT");
		jbtnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnExport_actionPerformed(e);
			}
		});
		jbtnExport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnExport.setBorderPainted(false);
		Mid_TopPanel.add(jbtnExport);
		
		jbtnImport = new JButton("IMPORT");
		jbtnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnImport_actionPerformed(e);
			}
		});
		jbtnImport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnImport.setBorderPainted(false);
		Mid_TopPanel.add(jbtnImport);
		
		jbtnReturn = new JButton("RETURN");
		jbtnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbtnReturn.setBorderPainted(false);
		jbtnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnReturn_actionPerformed(e);
			}
		});
		Mid_TopPanel.add(jbtnReturn);
		
		JPanel Mid_BotPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) Mid_BotPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		MidPanel.add(Mid_BotPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.setIcon(new ImageIcon(InvoicePanel.class.getResource("/resources/Back.png")));
		Mid_BotPanel.add(btnNewButton_1);
		
		Mid_MidPanel = new JPanel();
		MidPanel.add(Mid_MidPanel, BorderLayout.CENTER);
		Mid_MidPanel.setLayout(new BorderLayout(0, 0));

	}@Override
	public boolean action(Event evt, Object what) {
		// TODO Auto-generated method stub
		return super.action(evt, what);
	}
	public void jbtnExport_actionPerformed(ActionEvent e) {
		Mid_MidPanel.removeAll();
		Mid_MidPanel.revalidate();
		ExportPanel exportPanel = new ExportPanel();
		Mid_MidPanel.add(exportPanel);
		exportPanel.setVisible(true);
	}
	public void jbtnImport_actionPerformed(ActionEvent e) {
		Mid_MidPanel.removeAll();
		Mid_MidPanel.revalidate();
		ImportPanel importPanel = new ImportPanel();
		Mid_MidPanel.add(importPanel);	
		importPanel.setVisible(true);
		
	}
	public void jbtnReturn_actionPerformed(ActionEvent e) {
		Mid_MidPanel.removeAll();
		Mid_MidPanel.revalidate();
		ReturnPanel returnPanel = new ReturnPanel();
		Mid_MidPanel.add(returnPanel);
		returnPanel.setVisible(true);
		
	}

}
