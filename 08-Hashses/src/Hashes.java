import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(pw.getBytes());
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            int iterations = 10000;
            int keyLength = 256;
            char[] chars = pw.toCharArray();
            byte[] saltBytes = salt.getBytes();

            PBEKeySpec spec = new PBEKeySpec(chars, saltBytes, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            HexFormat hex = HexFormat.of();
            return hex.formatHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        npass = 0;
        String charset = "abcdefABCDEF1234567890!";
        char[] currentPass = new char[6];

        for (int l1 = 0; l1 < charset.length(); l1++) {
            currentPass[0] = charset.charAt(l1);
            if (provarPassword(alg, hash, salt, currentPass, 1)) return new String(currentPass, 0, 1);

            for (int l2 = 0; l2 < charset.length(); l2++) {
                currentPass[1] = charset.charAt(l2);
                if (provarPassword(alg, hash, salt, currentPass, 2)) return new String(currentPass, 0, 2);

                for (int l3 = 0; l3 < charset.length(); l3++) {
                    currentPass[2] = charset.charAt(l3);
                    if (provarPassword(alg, hash, salt, currentPass, 3)) return new String(currentPass, 0, 3);

                    for (int l4 = 0; l4 < charset.length(); l4++) {
                        currentPass[3] = charset.charAt(l4);
                        if (provarPassword(alg, hash, salt, currentPass, 4)) return new String(currentPass, 0, 4);

                        for (int l5 = 0; l5 < charset.length(); l5++) {
                            currentPass[4] = charset.charAt(l5);
                            if (provarPassword(alg, hash, salt, currentPass, 5)) return new String(currentPass, 0, 5);

                            for (int l6 = 0; l6 < charset.length(); l6++) {
                                currentPass[5] = charset.charAt(l6);
                                if (provarPassword(alg, hash, salt, currentPass, 6)) return new String(currentPass, 0, 6);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean provarPassword(String alg, String targetHash, String salt, char[] passChars, int length) {
        String candidate = new String(passChars, 0, length);
        npass++;
        String computedHash = "";

        if (alg.equals("SHA-512")) {
            computedHash = getSHA512AmbSalt(candidate, salt);
        } else if (alg.equals("PBKDF2")) {
            computedHash = getPBKDF2AmbSalt(candidate, salt);
        }

        return computedHash != null && computedHash.equals(targetHash);
    }

    public String getInterval(long t1, long t2){
        long diff = t2 - t1;
        long dies = diff / (1000 * 60 * 60 * 24);
        long hores = (diff / (1000 * 60 * 60)) % 24;
        long minuts = (diff / (1000 * 60)) % 60;
        long segons = (diff / 1000) % 60;
        long millis = diff % 1000;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args){
        String salt = "qpoweiruañslkdfjz"; 
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashses = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };
            
        String pwTrobat = null;
        String[] algorisme = {"SHA-512", "PBKDF2"};

        for(int i = 0; i < aHashses.length; i++){
            System.out.printf("=============================\n");
            System.out.printf("Algorisme: %s\n", algorisme[i]);
            System.out.printf("Hash: %s\n", aHashses[i]);
            System.out.printf("---------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorisme[i], aHashses[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %s\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("------------------------\n\n");
        }
    }
}