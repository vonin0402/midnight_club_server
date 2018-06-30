package com.club.midnight.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public abstract class CipherUtils {
    private static final String CIPHER_SPEC = "AES/ECB/PKCS5Padding";
    private static final String KEY_SPEC = "AES";

    private static final Cipher encryptor = createCipher(Cipher.ENCRYPT_MODE, "+5FE3F17A24j5AB!".getBytes(StandardCharsets.UTF_8));
    private static final Cipher decryptor = createCipher(Cipher.DECRYPT_MODE, "+5FE3F17A24j5AB!".getBytes(StandardCharsets.UTF_8));

    private static Cipher createCipher(int cipherMode, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_SPEC);
            cipher.init(cipherMode, new SecretKeySpec(key, KEY_SPEC));
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String value) {
        try {
            return Base64.encodeBase64URLSafeString(encryptor.doFinal(value.getBytes()));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String decrypt(String encryptedValue) {
        try {
            return new String(decryptor.doFinal(Base64.decodeBase64(encryptedValue.getBytes())));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
