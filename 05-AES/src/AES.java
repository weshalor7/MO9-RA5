import java.util.*;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String Format_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU ="WeshAlor";

    public static byte[] xifraAES(String msg, String clau)throws Exception{
        SecureRandom aleatori = new SecureRandom();
        aleatori.nextBytes(iv);

        MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] bytesClau = hash.digest(clau.getBytes("UTF-8"));
        SecretKeySpec claveAES = new SecretKeySpec(bytesClau, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(Format_AES);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, claveAES, ivSpec);
        byte[] bytesXifrats = cipher.doFinal(msg.getBytes("UTF-8"));
        byte[] total = new byte[MIDA_IV + bytesXifrats.length];

        for(int i = 0; i< MIDA_IV;i++){
            total[i] = iv[i];
        }

        for(int i = 0; i < bytesXifrats.length;i++){
                total[MIDA_IV + i] = bytesXifrats[i];
        }
        return total;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau)throws Exception{
        byte[] ivGuardat = new byte[MIDA_IV];
        for(int i = 0; i < MIDA_IV; i++){
            ivGuardat[i] = bIvIMsgXifrat[i];
        }

        byte[] bytesXifrats = new byte[bIvIMsgXifrat.length - MIDA_IV];
        for(int i = 0; i < bytesXifrats.length; i ++){
            bytesXifrats[i] = bIvIMsgXifrat[MIDA_IV + i];
        }


        MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] bytesClau = hash.digest(clau.getBytes("UTF-8"));
        SecretKeySpec claveAES = new SecretKeySpec(bytesClau, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(Format_AES);
        IvParameterSpec ivSpec = new IvParameterSpec(ivGuardat);
        cipher.init(Cipher.DECRYPT_MODE, claveAES, ivSpec);
        byte[] bytesDesxifrats = cipher.doFinal(bytesXifrats);
        
        return new String(bytesDesxifrats, "UTF-8");
     }
    

    public static void main(String[] args){
        String msgs[] = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo esta tu cuñado",
            "Àgora ïlla Ôtto"
        };

        for(int i = 0; i < msgs.length; i++){
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            }catch(Exception e){
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("----------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
