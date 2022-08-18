package login;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class AutoGet {
    public static void main(String[] args) {
        //https://search.bilibili.com/all?keyword=java
        //打开浏览器，创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建uriBuilder对象
        URIBuilder uriBuilder = null;
        //创建请求对象
        HttpGet httpGet = null;
        try {
            //实例化uriBuilder对象，设置uri地址
            uriBuilder = new URIBuilder("https://search.bilibili.com/all");
            //设置uri参数
            uriBuilder.setParameter("keyword","java");
            //实例化请求对象，创建uri对象作为请求对象的参数
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //创建响应对象
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpclient.execute(httpGet);
            System.out.println(response.getVersion()); // HTTP/1.1
            System.out.println(response.getCode()); // 200
            System.out.println(response.getReasonPhrase()); // OK
            //获取响应信息
            HttpEntity entity = response.getEntity();
            try {
                //响应信息转为字符串
                String result = EntityUtils.toString(entity,"utf8");
                System.out.println(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 确保流被完全消费
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭响应
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //关闭浏览器
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


