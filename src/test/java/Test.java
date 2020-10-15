import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encodeStr = URLEncoder.encode("中国", "utf-8");
        System.out.println("处理后:" + encodeStr);


        String decodeStr = URLDecoder.decode(encodeStr, "utf-8");
        System.out.println("处理后："+ decodeStr);
    }
}
