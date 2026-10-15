package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class XifradorAES implements Xifrador {

    private static final String ALGORISME_XIFRAT = "AES/CBC/PKCS5Padding";
    private static final String ALGORISME_CLAU = "PBKDF2WithHmacSHA256";
    private static final int KEY_SIZE = 256;
    private static final int ITERACIONS = 65536;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);

            KeySpec spec = new PBEKeySpec(clau.toCharArray(), salt, ITERACIONS, KEY_SIZE);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORISME_CLAU);
            byte[] keyBytes = factory.generateSecret(spec).getEncoded();
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance(ALGORISME_XIFRAT);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            byte[] encrypted = cipher.doFinal(msg.getBytes("UTF-8"));

            byte[] combined = new byte[salt.length + iv.length + encrypted.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(iv, 0, combined, salt.length, iv.length);
            System.arraycopy(encrypted, 0, combined, salt.length + iv.length, encrypted.length);

            return new TextXifrat(combined);
        } catch (Exception e) {
            System.err.println("Error en xifrar AES: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (xifrat == null) return null;
        try {
            byte[] combined = xifrat.getBytes();

            byte[] salt = new byte[16];
            byte[] iv = new byte[16];
            byte[] encrypted = new byte[combined.length - 32];

            System.arraycopy(combined, 0, salt, 0, 16);
            System.arraycopy(combined, 16, iv, 0, 16);
            System.arraycopy(combined, 32, encrypted, 0, encrypted.length);

            KeySpec spec = new PBEKeySpec(clau.toCharArray(), salt, ITERACIONS, KEY_SIZE);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORISME_CLAU);
            byte[] keyBytes = factory.generateSecret(spec).getEncoded();
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance(ALGORISME_XIFRAT);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            System.err.println("Error en desxifrar AES: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
}