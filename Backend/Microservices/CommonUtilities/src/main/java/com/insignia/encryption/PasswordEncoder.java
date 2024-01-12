package com.insignia.encryption;

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

public class PasswordEncoder {

	public static String getEncryptedPassword(String password) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		SecretKeySpec secretKey = new SecretKeySpec(SecretKeyConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8),
				SecretKeyConstant.ALGORITHM);
		Cipher cipher = Cipher.getInstance(SecretKeyConstant.CIPHER_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedBytes);

	}
}
