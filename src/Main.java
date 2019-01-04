import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String keyStr="/logistics/user/getloginverifycode-d4af719008dd7f88";
        byte[] source=AES128.str2bytes("{\"from\":\"8\",\"telephone\":\"17625955421\"}");
        try {
            byte[] encode=AES128.encrypt(source,keyStr);
            System.out.println("加密后的16进制："+AES128.byte2Hex(encode));
            byte[] confusioned=Transform.confusion(encode);
            System.out.println("confusion结果： "+new String(confusioned,AES128.CHARSET));
            byte[] decode=AES128.decrypt(encode,keyStr);
            System.out.println(AES128.byte2Hex(decode));
            System.out.println(AES128.bytes2str(decode));
        } catch (AES128.KeyLengthException e) {
            e.printStackTrace();
        }
    }
}
