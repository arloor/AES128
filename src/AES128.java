import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AES128 {

    public static final Charset CHARSET=UTF_8;


    public static byte[] str2bytes(String str){
        return str.getBytes(CHARSET);
    }

    public static String bytes2str(byte[] bytes){
        return new String(bytes,CHARSET);
    }
    /**
     * 加密
     * @param source
     * @param keyStr 原始秘钥字符串，注意不是最终的秘钥
     * @return 加密后的字节数组
     * @throws KeyLengthException 如果秘钥长度不为16则抛出
     */
    static byte[] encrypt(byte[] source, String keyStr) throws KeyLengthException {
        byte[] key=getKey(keyStr);
        if(key.length!=16){
            throw new KeyLengthException();
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec keySpec=new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE,keySpec );
            return cipher.doFinal(source);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解密
     * @param encoded
     * @param keyStr 原始秘钥字符串，注意不是最终的秘钥
     * @return 解密后的字节数组
     * @throws KeyLengthException 如果秘钥长度不为16则抛出
     */
    static byte[] decrypt(byte[] encoded, String keyStr) throws  KeyLengthException {
        byte[] key=getKey(keyStr);
        if(key.length!=16){
            throw new KeyLengthException();
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            return cipher.doFinal(encoded);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 由keyStr经过SHA256再取128bit作为秘钥
     * 这里SHA-256也可以换成SHA-1
     * @param keyStr
     * @return
     */
    static byte[] getKey(String keyStr){
        byte[] raw=keyStr.getBytes(CHARSET);
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] key = sha.digest(raw);
        key = Arrays.copyOf(key, 16); // use only first 128 bit
        return key;
    }
    /**
     * 返回byte数组的16进制字符串
     * @param array
     * @return
     */
    static String byte2Hex(byte[] array){
        StringBuffer strHexString = new StringBuffer();
        for (int i = 0; i < array.length; i++)
        {
            String hex = Integer.toHexString(0xff & array[i]);
            if (hex.length() == 1)
            {
                strHexString.append('0');
            }
            strHexString.append(hex);
        }
        return strHexString.toString();
    }
    public static class KeyLengthException extends Exception{
    }
}
