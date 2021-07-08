package crypto.cryptoutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyPair;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class AsymmetricEncryptionUtilsTest {

	@Test
	public void testGenerateKeyPair() throws Exception{
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		assertNotNull(keyPair);
		System.out.println("Private Key="+DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
		System.out.println("Public Key= "+DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
	}
	
	@Test
	public void testEncryptionDecryption() throws Exception {
		String plainText = "Nikhil Mathur";
		KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
		assertNotNull(keyPair);
		byte[] encryptedValue = AsymmetricEncryptionUtils.performRSAEncryption(plainText, keyPair.getPrivate());
		assertNotNull(encryptedValue);
		System.out.println("encrypted Value ="+DatatypeConverter.printHexBinary(encryptedValue));
		String decryptedValue = AsymmetricEncryptionUtils.performRSADecryption(encryptedValue, keyPair.getPublic());
		System.out.println("decrypted Value"+decryptedValue);
		assertNotNull(decryptedValue);
		assertEquals(decryptedValue, plainText);
		
		
	}
}
