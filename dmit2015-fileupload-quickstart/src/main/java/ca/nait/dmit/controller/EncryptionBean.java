package ca.nait.dmit.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.jasypt.util.text.StrongTextEncryptor;

import util.JSFUtil;

@Named
@RequestScoped
public class EncryptionBean {
	private static final Logger logger = Logger.getLogger(
			EncryptionBean.class.getName());
	
	public void encrypt() {
		logger.log(Level.INFO, "cipherText = {0}",cipherText);
		logger.log(Level.INFO, "password = {0}",password);
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(password);
		String encryptedText = textEncryptor.encrypt(cipherText);
		logger.log(Level.INFO, "encrypted text = {0}", encryptedText);
		JSFUtil.addInfoMessage("Encrypted text: " + encryptedText);
	}
	
	public void decrypt() {
		logger.log(Level.INFO, "cipherText = {0}, password = {1}",new Object[] {cipherText, password});
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(password);
		String plainText = textEncryptor.decrypt(cipherText);
		logger.log(Level.INFO, "Decrypted text: " + plainText);
		JSFUtil.addInfoMessage("Decrypted text: " + plainText);
	}
	
	private String cipherText;
	private String password;
	
	public String getCipherText() {
		return cipherText;
	}
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
