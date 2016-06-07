package org.workspace7.encryptor.provider;

import org.jasypt.encryption.StringEncryptor;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * 
 */
@Component(name="org.workspace7.encryptor",service=StringEncryptor.class,immediate=true)
public class EncryptorImpl implements StringEncryptor {

	
	@Activate
	public void activated(BundleContext bundleContext){
		System.out.println("EncryptorImpl.activated()");
	}
	
	//TODO:FOR now just return the same string, later we can add the properties via config admin
	@Override
	public String decrypt(String encryptedString) {
		return encryptedString;
	}

	@Override
	public String encrypt(String unenryptedString) {
		return unenryptedString;
	}

}