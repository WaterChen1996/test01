package login;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoPost {
    public static void main(String[] args) {
        //打开浏览器，创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建post对象
        HttpPost httpPost = new HttpPost("");
        //创建表单参数集合对象
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        // POST 请求参数
        paramsList.add(new BasicNameValuePair("", ""));
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList));

        //创建response对象
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpclient.execute(httpPost);
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
