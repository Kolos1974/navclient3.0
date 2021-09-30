package utils;

import exception.AES128Exception;
import exception.SHA512Exception;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.CRC32;

public class Algos {

    private static final String TAG = "Algos";
    //make sure not to instantiate this class
    private Algos() {
        throw new AssertionError();
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String generateSha512From(String m) throws SHA512Exception {
        utils.Logger.logMessage(TAG, "Generate SHA512 from: " + m);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(m.getBytes());
            char[] hexChars = new char[digest.length * 2];
            for ( int j = 0; j < digest.length; j++ ) {
                int v = digest[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        } catch (NoSuchAlgorithmException e) {
            throw new SHA512Exception("Error while generating SHA512");
        }
    }

    public static String generateSha3512From(String m) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(m.getBytes());
        return Hex.toHexString(digest);
    }

    public static String decryptAES128(byte[] cipherText, String key) throws AES128Exception {
        try {
            String result;
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            result = new String(cipher.doFinal(cipherText), "UTF-8");
            return result;
        } catch (Exception e) {
            throw new AES128Exception("Error while decrypting AES128");
        }
    }

    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeBase64(byte[] input) {
        return new String(Base64.getDecoder().decode(input));
    }

    public static String getCRC3CheckSum(String input){
        CRC32 crc = new CRC32();
        crc.update(input.getBytes());
        return Long.toString(crc.getValue());
    }
}
