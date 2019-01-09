
import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) throws AES128.KeyLengthException {
        String source="刘港欢";
        System.out.println("加密前字符串为："+source);
        System.out.println("===================================");
        //集成SHA256、AES128和confusion
        String key="我的密钥";
        byte[] afterAESEncrypt=AES128.encrypt(source.getBytes(UTF_8),key);
        String encodeStr=new String(afterAESEncrypt,UTF_8);
        System.out.println("加密最终结果; "+encodeStr);
        byte[] afterAESDescrypt=AES128.decrypt(afterAESEncrypt,key);
        String decodeStr=new String(afterAESDescrypt,UTF_8);
        System.out.println("解密之后："+decodeStr);
        System.out.println("与加密前结果相同？"+decodeStr.equals(source));
        System.out.println("===================================");
    }
}