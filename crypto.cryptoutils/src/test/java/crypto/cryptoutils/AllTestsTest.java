package crypto.cryptoutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class AllTestsTest {

	@Test
	public void testAESKey() throws Exception {
		SecretKey key = SymmetricEncryptionUtils.createAESKey();
		assertNotNull(key);
		System.out.println(DatatypeConverter.printHexBinary(key.getEncoded()));
	}

	@Test
	public void testEncryptionDecryption() throws Exception {
		String plainText = "Nikhil Mathur";
		SecretKey key = SymmetricEncryptionUtils.createAESKey();
		byte[] initializationVector = SymmetricEncryptionUtils.createInitializationVector();
		byte[] cipherText = SymmetricEncryptionUtils.performAESEncryption(plainText, key, initializationVector);
		assertNotNull(cipherText);
		System.out.println(DatatypeConverter.printHexBinary(cipherText));
		String decryptedText = SymmetricEncryptionUtils.performAESDecryption(cipherText, key, initializationVector);
		assertEquals(plainText, decryptedText);
	}

}
