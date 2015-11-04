package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldHost;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	boolean cancel = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogAccount dialog = new DialogAccount();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextField getTextFieldHost() {
		return textFieldHost;
	}

	public void setTextFieldHost(JTextField textFieldHost) {
		this.textFieldHost = textFieldHost;
	}

	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	/**
	 * Create the dialog.
	 */
	public DialogAccount() {
		cancel = false;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Account");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblHost = new JLabel("Host:");
			GridBagConstraints gbc_lblHost = new GridBagConstraints();
			gbc_lblHost.anchor = GridBagConstraints.EAST;
			gbc_lblHost.insets = new Insets(0, 0, 5, 5);
			gbc_lblHost.gridx = 0;
			gbc_lblHost.gridy = 0;
			contentPanel.add(lblHost, gbc_lblHost);
		}
		{
			textFieldHost = new JTextField();
			GridBagConstraints gbc_textFieldHost = new GridBagConstraints();
			gbc_textFieldHost.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldHost.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldHost.gridx = 1;
			gbc_textFieldHost.gridy = 0;
			contentPanel.add(textFieldHost, gbc_textFieldHost);
			textFieldHost.setColumns(10);
		}
		{
			JLabel lblUser = new JLabel("User:");
			GridBagConstraints gbc_lblUser = new GridBagConstraints();
			gbc_lblUser.anchor = GridBagConstraints.EAST;
			gbc_lblUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblUser.gridx = 0;
			gbc_lblUser.gridy = 1;
			contentPanel.add(lblUser, gbc_lblUser);
		}
		{
			textFieldUser = new JTextField();
			GridBagConstraints gbc_textFieldUser = new GridBagConstraints();
			gbc_textFieldUser.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldUser.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldUser.gridx = 1;
			gbc_textFieldUser.gridy = 1;
			contentPanel.add(textFieldUser, gbc_textFieldUser);
			textFieldUser.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Password:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 2;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 0);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 1;
			gbc_passwordField.gridy = 2;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DialogAccount.this.cancel = false;
						DialogAccount.this.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DialogAccount.this.cancel = true;
						DialogAccount.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
