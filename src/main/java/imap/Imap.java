package imap;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import gui.Gui.Account;
import gui.Gui.MyFolder;

public class Imap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}
	
	public boolean open(Account account) {
	    Properties props = System.getProperties();
	    props.setProperty("mail.store.protocol", "imaps");
	    Session session = Session.getDefaultInstance(props, null);
	    try {
	        Store store = session.getStore("imaps");
	        store.connect(account.getHost(), account.getUser(), account.getPassword());
	        account.setSession(session);
	        account.setStore(store);
	        return true;
	    } catch (MessagingException e) {
	    	this.close(account);
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public void close(Account account) {
		
	}
 	
	public void readFolders(Account account) {
		try {
			Map<String,MyFolder> myFolderMap= new HashMap<>();
			for (MyFolder i: account.getFolders())
				myFolderMap.put(i.getName().toLowerCase(), i);
			Folder[] folders= account.getStore().getDefaultFolder().list();
			for (Folder i: folders)
				if (!myFolderMap.containsValue(i.getFullName().toLowerCase()))
					account.getFolders().add(new MyFolder(i.getFullName()));			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void sort(Account account) {
		
	}
	
}
