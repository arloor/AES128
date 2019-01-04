import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, AES128.KeyLengthException {
        testEncode();
        testDecode();
    }

    private static void testEncode(){
        String keyStr="/logistics/user/getloginverifycode-d4af719008dd7f88";
        byte[] source=AES128.str2bytes("{\"from\":\"8\",\"telephone\":\"17625955421\"}");
        try {

            byte[] encode=AES128.encrypt(source,keyStr);
            System.out.println(encode.length);
            System.out.println("加密后的16进制："+AES128.byte2Hex(encode));
            byte[] confusioned=Transform.confusion(encode);
            System.out.println(confusioned.length);
            System.out.println("confusion结果： "+new String(confusioned,AES128.CHARSET));
        } catch (AES128.KeyLengthException e) {
            e.printStackTrace();
        }
    }

    public static  void testDecode() throws AES128.KeyLengthException {
        String keyStr="/logistics/user/getloginverifycode-d4af719008dd7f88";
        byte[] result=Transform.disConfusion("KaV(z,igE(HV@0#MCuXKmtg-7TT2t}%{sSx97Idj]7H]#B[lc+hi<dw,H--4dTqCYqCGsG9<Fqss9^CH!,74T@m[u[x0F!TS$q6Iz][{T<hHsqHsu>wA`AFuSjuFR(Hzj#D0tD}t#d~x98D70awMdM]woIx~KC,G`w,!~IsA!j7l(qh9uwAEIcF<6:~>FSa-:@A~S%X}ThIIK[alj7hIl]qDiaia<KmAFQxdqqxTY.$J");
        byte[] decode=AES128.decrypt(result,keyStr);
        System.out.println(new String(decode));
    }
}
