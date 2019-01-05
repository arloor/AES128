
import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {
    public static void main(String[] args) throws AES128.KeyLengthException {
        String source="刘港欢";
        System.out.println("加密前字符串为："+source);
        System.out.println("===================================");
        //集成SHA256、AES128和confusion
        String key="我的密钥";
        byte[] afterAESEncrypt=AES128.encrypt(source.getBytes(UTF_8),key);
        byte[] afterConfusion=AES128.base64encode(afterAESEncrypt);
        String encodeStr=new String(afterConfusion,UTF_8);
        System.out.println("加密最终结果; "+encodeStr);
        byte[] afterDisConfusion= AES128.base64decode(afterConfusion);
        byte[] afterAESDescrypt=AES128.decrypt(afterDisConfusion,key);
        String decodeStr=new String(afterAESDescrypt,UTF_8);
        System.out.println("解密之后："+decodeStr);
        System.out.println("与加密前结果相同？"+decodeStr.equals(source));
        System.out.println("===================================");
        //只使用confusion
        byte[] afterConfusion1=AES128.base64encode(source.getBytes(UTF_8));
        String encodeStr1=new String(afterConfusion1,UTF_8);
        System.out.println("base64混淆最终结果: "+encodeStr1);
        byte[] afterDisConfusion1= AES128.base64decode(afterConfusion1);
        String decodeStr1=new String(afterDisConfusion1,UTF_8);
        System.out.println("解base64混淆后："+decodeStr1);
        System.out.println("与base64混淆前结果相同？"+decodeStr1.equals(source));
    }
}