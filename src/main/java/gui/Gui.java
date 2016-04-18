package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class Gui extends JFrame {

	private static final long serialVersionUID = 7441052136085841110L;

	private JPanel contentPane;
	private JTable tableAccounts;
	private List<Account> accounts = new ArrayList();
	private JTable tableKeywords;

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

	public static class Account {
		private String host;
		private String user;
		private String password;
		private boolean marked;
		private List<MyFolder> folders;
		private Session session;
		private Store store;

		public Account(String host, String user, String password) {
			this.host = host;
			this.user = user;
			this.password = password;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isMarked() {
			return marked;
		}

		public void setMarked(boolean marked) {
			this.marked = marked;
		}

		public List<MyFolder> getFolders() {
			return folders;
		}

		public void setFolders(List<MyFolder> folders) {
			this.folders = folders;
		}

		public Session getSession() {
			return session;
		}

		public void setSession(Session session) {
			this.session = session;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		@Override
		public String toString() {
			return host + " " + user + " XXX";
		}
	}

	public static class MyFolder {
		private String name;
		private String keywords;

		public MyFolder(String name) {
			this.name = name;
			this.keywords = "";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKeywords() {
			return keywords;
		}

		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
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
		panelAccounts.add(scrollPane, BorderLayout.NORTH);

		tableAccounts = new JTable();
		tableAccounts
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(tableAccounts);

		JPopupMenu popupMenuTableAccounts = new JPopupMenu();
		scrollPane.add(popupMenuTableAccounts);

		JMenuItem itemAccountNew = new JMenuItem("Add Account");
		itemAccountNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogAccount dialog = new DialogAccount();
				dialog.getTextFieldHost().setText("");
				dialog.getTextFieldUser().setText("");
				dialog.getPasswordField().setText("");
				dialog.setLocation(itemAccountNew.getLocation());
				dialog.setVisible(true);
				if (!dialog.isCancel()) {
					accounts.add(new Account(dialog.getTextFieldHost()
							.getText(), dialog.getTextFieldUser().getText(),
							new String(dialog.getPasswordField().getPassword())));
					updateTableAccounts();
				}
			}

		});
		itemAccountNew.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JOptionPane.showMessageDialog(Gui.this, "Menu Item selected");
			}
		});
		popupMenuTableAccounts.add(itemAccountNew);

		JMenuItem itemDeleteAccount = new JMenuItem("Delete Account(s)");
		itemDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rows = tableAccounts.getSelectedRows();
				List<Account> tmp = new ArrayList();
				for (Account i : accounts)
					i.setMarked(false);
				for (int i : rows)
					accounts.get(i).setMarked(true);
				for (Account i : accounts)
					if (!i.isMarked())
						tmp.add(i);
				accounts = tmp;
				updateTableAccounts();
			}
		});
		popupMenuTableAccounts.add(itemDeleteAccount);
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
		panelKeywords.setLayout(new BorderLayout(0, 0));

		JButton btnSort = new JButton("Sort");
		panelKeywords.add(btnSort, BorderLayout.SOUTH);

		tableKeywords = new JTable();
		panelKeywords.add(tableKeywords, BorderLayout.CENTER);

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
										+ "Open Email Account with double click.\n"
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

	private void updateTableAccounts() {
		tableAccounts.setModel(new DefaultTableModel(
				new Object[] { "Acccounts" }, accounts.size()));
		for (int i = 0; i < accounts.size(); i++)
			tableAccounts.getModel().setValueAt(accounts.get(i), i, 0);
	}

}
