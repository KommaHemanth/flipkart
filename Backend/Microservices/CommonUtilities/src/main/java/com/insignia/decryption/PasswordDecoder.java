package com.insignia.decryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.insignia.constants.SecretKeyConstant;

public class PasswordDecoder {

	public static String getDecryptedPassword(String encryptedPassword) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		SecretKeySpec secretKey = new SecretKeySpec(SecretKeyConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8),
				SecretKeyConstant.ALGORITHM);
		Cipher cipher = Cipher.getInstance(SecretKeyConstant.CIPHER_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword.trim()));
		return new String(decryptedBytes, StandardCharsets.UTF_8);

	}

}
