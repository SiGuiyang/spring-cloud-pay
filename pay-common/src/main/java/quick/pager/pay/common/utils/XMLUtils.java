package quick.pager.pay.common.utils;

import cn.hutool.core.util.XmlUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Map;


public class XMLUtils {

    private static final XStream stream = new XStream(new DomDriver());


    /**
     * 将map转成xml
     */
    public static String mapToXml(Map<String, String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("<xml>").append("\n");
        data.forEach((k, v) -> {
            builder.append("<").append(k).append(">").append(v).append("</").append(k).append(">").append("\n");
        });

        builder.append("</xml>");

        return builder.toString();

    }

    public static void main(String[] args) {

//        MpPayService service = new MpPayService();
//        WeChatPayDTO dto = new WeChatPayDTO();
//        dto.setSpbillCreateIp("3223232");
//        dto.setBody("e323mkds");
//        dto.setNotifyUrl("23fsdfsdfsdf");
//        dto.setBody("234fdsfdfs");
//        dto.setMchId("90kjdd");
//        dto.setSignType("MD5");
//        dto.setOpenid("3232fsdfsdfsdfs32");
//        dto.setTotalFee("2323");
//        dto.setOutTradeNo("323e2fdsfsdf");
//        dto.setSecret("edfwfdsfsdfsdfsdf");
//        dto.setAppid("323dsfsdfsd");
//
//
//        service.doService(dto);

        String params = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>";
        Map<String, Object> toMap = XmlUtil.xmlToMap(params);

        System.out.println(toMap);

    }
}

