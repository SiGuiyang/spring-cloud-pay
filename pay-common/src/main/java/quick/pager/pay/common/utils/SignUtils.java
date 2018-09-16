package quick.pager.pay.common.utils;

import cn.hutool.crypto.SecureUtil;
import com.google.common.base.Joiner;

import java.util.SortedMap;

/**
 * 签名工具类
 *
 * @author siguiyang
 */
public class SignUtils {

    /**
     * 获取签名
     *
     * @param signType 签名类型， md5
     * @param secret   密钥
     * @param data     需要签名的数据
     */
    public static String getSign(String signType, String secret, SortedMap<String, String> data) {
        String join = Joiner.on("&").withKeyValueSeparator("=").join(data);
        StringBuilder builder = new StringBuilder(join);

        String result = builder.append("&").append("key").append("=").append(secret).toString();

        if ("MD5".equals(signType)) {
            return SecureUtil.md5(result);
        } else {

            return MacUtils.hmacsha256(builder.toString(), secret);
        }

    }
}
