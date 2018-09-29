package quick.pager.pay.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Http client 长连接请求工具类
 *
 * @author siguiyang
 */
public class HttpClient {

    private static CloseableHttpClient client = null;

    static {

        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(400);
        manager.setDefaultMaxPerRoute(50);
        RequestConfig globalConfig = RequestConfig.custom().setSocketTimeout(25000).setConnectTimeout(3000).setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        client = HttpClients.custom().setConnectionManager(manager).setDefaultRequestConfig(globalConfig).build();


    }

    /**
     * get 请求
     *
     * @param url    url 地址
     * @param params get请求参数
     */
    public static String doGet(String url, Map<String, String> params) {

        StringBuilder builder = new StringBuilder(url);
        if (!CollectionUtil.isEmpty(params)) {
            builder.append("?").append(Joiner.on("&").withKeyValueSeparator("=").join(params));
        }

        HttpGet get = new HttpGet(builder.toString());

        try {
            CloseableHttpResponse execute = client.execute(get);
            HttpEntity entity = execute.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * post 请求
     *
     * @param url    url地址
     * @param params post请求参数
     */
    public static String doPost(String url, Map<String, String> params) {

        try {
            HttpPost post = new HttpPost(url);
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            List<BasicNameValuePair> basicNameValuePairList = Lists.newArrayList();

            if (!CollectionUtil.isEmpty(params)) {
                params.forEach((k, v) -> {
                    basicNameValuePairList.add(new BasicNameValuePair(k, v));
                });

                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicNameValuePairList);

                post.setEntity(urlEncodedFormEntity);
            }

            CloseableHttpResponse execute = client.execute(post);
            return EntityUtils.toString(execute.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 发送xml post 请求
     *
     * @param url    url地址
     * @param params 参数
     */
    public static String doPostXml(String url, Map<String, String> params) {
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(XMLUtils.mapToXml(params), "UTF-8");
        post.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml;utf-8");
        post.setEntity(stringEntity);
        try {
            CloseableHttpResponse execute = client.execute(post);
            return EntityUtils.toString(execute.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
