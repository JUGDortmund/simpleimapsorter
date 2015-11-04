package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {

	private static final long serialVersionUID = 7441052136085841110L;

	private JPanel contentPane;
	private JTable tableAccounts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setTitle("Simple IMAP Sorter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelAccounts = new JPanel();
		tabbedPane.addTab("Accounts", null, panelAccounts, null);
		panelAccounts.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAccounts.add(scrollPane);

		tableAccounts = new JTable();
		scrollPane.setViewportView(tableAccounts);

		JPopupMenu popupMenuTableAccounts = new JPopupMenu();
		scrollPane.add(popupMenuTableAccounts);

		JMenuItem itemAccountNew = new JMenuItem("Add Account");
		itemAccountNew.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JOptionPane.showMessageDialog(Gui.this, "Menu Item selected");
			}
		});
		popupMenuTableAccounts.add(itemAccountNew);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenuTableAccounts.show(e.getComponent(), e.getX(),
							e.getY());
				}
			}
		});
		tableAccounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		JPanel panelKeywords = new JPanel();
		tabbedPane.addTab("Keywords", null, panelKeywords, null);

		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								Gui.this,
								"Add email account with popup menu.\n"
										+ "Open Email Account with popup menu.\n"
										+ "Enter keywords for folders and then sort inbox with button.");
			}
		});
		mnNewMenu.add(mntmHelp);

		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Gui.this, "Version 0.1");
			}
		});
		mnNewMenu.add(mntmInfo);

		JMenuItem mntmEnd = new JMenuItem("End");
		mntmEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmEnd);
	}

}
