package quick.pager.pay.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import quick.pager.pay.common.client.IRequest;
import quick.pager.pay.common.client.response.WeiXinBaseResponse;


public class XMLUtils {

    private static final XStream stream = new XStream(new DomDriver());

    /**
     * 转化为xml格式
     *
     * @param request 请求入参
     */
    public static String toXML(IRequest request) {
        stream.processAnnotations(request.getClass());
        return stream.toXML(request);
    }

    /**
     * xml 字符串转对象
     *
     * @param xml xml字符串
     */
    public static WeiXinBaseResponse parseXML(String xml, WeiXinBaseResponse response) {

        return null;
    }
}

