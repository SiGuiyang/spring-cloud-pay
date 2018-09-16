package quick.pager.pay.common.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

public class MacUtils {

    /**
     * HmacSHA256 加密
     *
     * @param data       加密数据
     * @param privateKey 私钥
     */
    public static String hmacsha256(String data, String privateKey) {
        StringBuilder builder = new StringBuilder();
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(privateKey.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(data.getBytes(Charset.forName("UTF-8")));
            for (byte b : bytes) {
                builder.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
