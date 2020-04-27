package com.jeff.test.gaode;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GaoDPOi {

    public static void main(String[] args) throws Exception {
        String location = "116.473168,39.993015";
        String keywords = "美食";
        generateData(location, keywords);
    }

    public static void  generateData(String location, String keywords) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String radius = "500";
        String url = "https://restapi.amap.com/v3/place/around?key=eea76fff9ed991bb945380e7ba40dfe5&location="+location+"&keywords="+
                keywords+"&radius=" + radius;
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String objString = EntityUtils.toString(entity, "utf-8");
        ShopInfo shopInfo = JSON.parseObject(objString, ShopInfo.class);
        System.out.println(objString);
        //关闭httpclient
        System.out.println(shopInfo);
        response.close();
        httpClient.close();
    }
}
