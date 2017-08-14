package com.ricelink.interfaceService.ipad.utils;

import com.ricelink.utils.http.Utf8ResponseHandler;
import com.ricelink.utils.http.impl.HttpDefaultUtils;
import net.sf.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/31.
 */
public class HttpUtil extends HttpDefaultUtils{
    private static HttpUtil httpUtil = null;
    private CloseableHttpClient httpClient = null;

    /**
     * 获取sessionID
     * @param url
     * @param queryParams
     * @return
     * @throws IOException
     */
    public String getSessionId(String url, String queryParams) throws IOException{
        if(queryParams != null) {
            if(url.indexOf(63) == -1) {
                url = url + '?';
            }

            url = url + (url.endsWith("?")?queryParams:'&' + queryParams);
        }

        HttpGet httpGet = new HttpGet(url);

        String var7 = "error";
        try {
            HttpClient h = new DefaultHttpClient();
            CloseableHttpResponse response = (CloseableHttpResponse)h.execute(httpGet);
            Throwable var5 = null;

            try {
                String responseContent = (String) Utf8ResponseHandler.INSTANCE.handleResponse(response);
                int codeIndex = responseContent.indexOf("retCode") + 9;
                String retCode = responseContent.substring(codeIndex, codeIndex + 1);
                if ("1".equals(retCode)){
                    List<Cookie> cookies = ((AbstractHttpClient) h).getCookieStore().getCookies();
                if (!cookies.isEmpty()) {
                    for (int i = 0; i < cookies.size(); i++) {
                        if ("JSESSIONID".equalsIgnoreCase(((Cookie) cookies.get(i)).getName())) {
                            //System.out.println("JSESSIONID=" + ((Cookie) cookies.get(i)).getValue());
                            var7 = ((Cookie) cookies.get(i)).getValue();
                        }
                    }
                }
            }
                //var7 = responseContent;
            } catch (Throwable var23) {
                var5 = var23;
                throw var23;
            } finally {
                if(response != null) {
                    if(var5 != null) {
                        try {
                            response.close();
                        } catch (Throwable var22) {
                            var5.addSuppressed(var22);
                        }
                    } else {
                        response.close();
                    }
                }

            }
        } finally {
            httpGet.releaseConnection();
        }

        return var7;
    }

    /**
     * 带有sessionId的请求
     * @param url
     * @param postData
     * @param sessionId
     * @return
     * @throws IOException
     */
    public String postWithSession(String url, Map<String, String> postData, String sessionId) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if(postData != null) {
            List<NameValuePair> params = new ArrayList();
            postData.forEach((key, value) -> {
                NameValuePair param = new BasicNameValuePair(key, value);
                params.add(param);
            });
            httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
        /****************************************************************/
        //设置sessionID
        Map<String,String> header = new HashMap<>();
        header.put("Cookie", "JSESSIONID=" + sessionId);
        //header.put("Content-Type", "application/x-www-form-urlencoded");
        //设置 header
        Header headerss[] = buildHeader(header);
        if(headerss != null && headerss.length > 0){
            httpPost.setHeaders(headerss);
        }
        /****************************************************************/
        Object var6;
        try {
            CloseableHttpResponse response = this.getHttpClient().execute(httpPost);
            Throwable var5 = null;

            try {
                var6 = (String)Utf8ResponseHandler.INSTANCE.handleResponse(response);
            } catch (Throwable var22) {
                var6 = var22;
                var5 = var22;
                throw var22;
            } finally {
                if(response != null) {
                    if(var5 != null) {
                        try {
                            response.close();
                        } catch (Throwable var21) {
                            var5.addSuppressed(var21);
                        }
                    } else {
                        response.close();
                    }
                }

            }
        } finally {
            httpPost.releaseConnection();
        }

        return (String)var6;
    }

    /**
     * 下载文件
     * @param url
     * @param postData
     * @param sessionId
     * @param response
     */
    public Boolean fileDown(String url, Map<String, String> postData, HttpServletResponse response, String sessionId) throws IOException{
        HttpPost httpPost = new HttpPost(url);
        //设置请求参数
        if(postData != null) {
            List<NameValuePair> params = new ArrayList();
            postData.forEach((key, value) -> {
                NameValuePair param = new BasicNameValuePair(key, value);
                params.add(param);
            });
            httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
        /****************************************************************/
        //设置sessionID
        Map<String,String> header = new HashMap<>();
        header.put("Cookie", "JSESSIONID=" + sessionId);
        //header.put("Content-Type", "application/x-www-form-urlencoded");
        //设置 header
        Header headerss[] = buildHeader(header);
        if(headerss != null && headerss.length > 0){
            httpPost.setHeaders(headerss);
        }

        InputStream is = null;
        OutputStream os = null;
        try{
            HttpResponse resp = this.getHttpClient().execute(httpPost);
            Header[] httpHeaders = resp.getAllHeaders();

            //设置返回头
            String dispositio = null;
            if (httpHeaders != null) {
                for (int i = 0; i < httpHeaders.length; i++) {
                    if ("Content-Disposition".equalsIgnoreCase((httpHeaders[i].getName()))) {
                        //System.out.println(httpHeaders[i].getValue());
                        dispositio = httpHeaders[i].getValue();
                    }
                }
            }
            if(dispositio == null){
                return false;
            }else{
                response.setContentType("application/x-msdownload;charset=utf-8");
                response.setHeader("Content-Disposition", dispositio);
            }

            //通过输出流返回数据
            is = resp.getEntity().getContent();
            os = new BufferedOutputStream(response.getOutputStream());
            int read = 0;
            byte[] temp = new byte[1024*1024];

            while((read=is.read(temp))>0){
            byte[] bytes = new byte[read];
            System.arraycopy(temp, 0, bytes, 0, read);
            os.write(bytes);
         }
            os.flush();
            return true;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * 上传文件
     * @param url
     * @param postData
     * @param sessionId
     * @throws IOException
     */
    public String fileUpload(String url, Map<String, Object> postData, String sessionId) throws IOException{

        HttpPost httpPost = new HttpPost(url);
        //httpPost.setHeader("User-Agent","SOHUWapRebot");
        httpPost.setHeader("Accept-Language","zh-cn,zh;q=0.8");

        MultipartEntity mutiEntity = new MultipartEntity();
        if(postData != null) {
            postData.forEach((key, value) -> {
                // BasicNameValuePair param;
                if(value instanceof String) {
                    mutiEntity.addPart(key,new StringBody((String) value, ContentType.create(
                            "text/plain", Consts.UTF_8)));
                }

                if(value instanceof File) {
                    mutiEntity.addPart(key, new FileBody((File) value));
                }
            });
        }
        httpPost.setEntity(mutiEntity);

        //设置sessionID
        Map<String,String> header = new HashMap<>();
        header.put("Cookie", "JSESSIONID=" + sessionId);
        //header.put("Content-Type", "application/x-www-form-urlencoded");
        //设置 header
        Header headerss[] = buildHeader(header);
        if(headerss != null && headerss.length > 0){
            httpPost.setHeaders(headerss);
        }

        CloseableHttpResponse response = null;
        try {
            response = this.getHttpClient().execute(httpPost);

            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            String resContent = null;
            if (resEntity != null) {
                // 打印响应长度
                //System.out.println("Response content length: " + resEntity.getContentLength());
                // 打印响应内容
                resContent = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                //System.out.println(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));

            }
            // 销毁
            EntityUtils.consume(resEntity);
            return resContent;
        }finally {
            try {
                if(response != null){
                    response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    /**
     * 带有sessionId的json请求
     * @param url
     * @param jsonParam
     * @param sessionId
     * @return
     * @throws IOException
     */
    public  String postWithJSON(String url,JSONObject jsonParam, String sessionId) throws IOException{

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = this.getHttpClient();
        String respContent = null;

         //json方式
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        //设置sessionID
        Map<String,String> header = new HashMap<>();
        header.put("Cookie", "JSESSIONID=" + sessionId);
        //设置 header
        Header headerss[] = buildHeader(header);
        if(headerss != null && headerss.length > 0){
            httpPost.setHeaders(headerss);
        }

        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }

    /**
     * 组装请求头
     * @param params
     * @return
     */
    public static Header[] buildHeader(Map<String,String> params){
        Header[] headers = null;
        if(params != null && params.size() > 0){
            headers = new BasicHeader[params.size()];
            int i  = 0;
            for (Map.Entry<String, String> entry:params.entrySet()) {
                headers[i] = new BasicHeader(entry.getKey(),entry.getValue());
                i++;
            }
        }
        return headers;
    }


    private CloseableHttpClient getHttpClient() {
        if(this.httpClient == null) {
            BasicCookieStore cookieStore = new BasicCookieStore();
            this.httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        }

        return this.httpClient;
    }

    public static HttpUtil getInstance() {
        if(httpUtil == null) {
            httpUtil = new HttpUtil();
        }

        return httpUtil;
    }
}
