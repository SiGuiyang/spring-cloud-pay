package quick.pager.pay.common.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import quick.pager.pay.common.client.response.WeiXinBaseResponse;
import quick.pager.pay.common.utils.XMLUtils;


import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 抽象支付Client
 * @author siguiyang
 */
public abstract class BasePayClient implements IClient {

    private static final CloseableHttpClient client = HttpClientBuilder.create().build();

    /**
     * 请求第三方微信
     *
     * @param request 请求入参对象
     * @param url     请求地址
     * @return 返回结果
     */
    @Override
    public WeiXinBaseResponse execute(IRequest request, String url, WeiXinBaseResponse response) {

        if (null == request || null == response) {
            return null;
        }

        String params = XMLUtils.toXML(request);
        StringEntity entity = new StringEntity(params, Charset.defaultCharset());
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        try {
            CloseableHttpResponse closeableHttpResponse = client.execute(post);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                return XMLUtils.parseXML(EntityUtils.toString(closeableHttpResponse.getEntity()), response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
