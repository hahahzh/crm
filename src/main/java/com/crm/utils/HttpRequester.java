/*
 * 鏂? 浠? 鍚?: HttpRequester.java
 * 鐗? 鏉?: Huawei Technologies Co., Ltd. Copyright YYYY-YYYY, All rights reserved
 * 鎻? 杩?: <鎻忚堪>
 * 淇? 鏀? 浜?: w00171845
 * 淇敼鏃堕棿: 2012-8-7
 * 璺熻釜鍗曞彿: <璺熻釜鍗曞彿>
 * 淇敼鍗曞彿: <淇敼鍗曞彿>
 * 淇敼鍐呭: <淇敼鍐呭>
 */
package com.crm.utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 澶勭悊Http璇锋眰鐩稿叧鐨勭被
 * 
 * @author w00171845
 * @version [鐗堟湰鍙?, 2012-8-7]
 * @see [鐩稿叧绫?/鏂规硶]
 * @since [浜у搧/妯″潡鐗堟湰]
 */
public class HttpRequester
{
    
    
    public final static int HTTP_OK = 200;
    
    public final static int HTTP_TIMEOUT = 408;
    
    private final static String METHOD_POST = "POST";
    
    private final static String METHOD_GET = "GET";
    
    private String defaultContentEncoding = "UTF-8";
    
    private static SSLSocketFactory ssf;
    
    /**
     * 蹇借璇佷功HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier()
    {
        @Override
        public boolean verify(String s, SSLSession sslsession)
        {
            return true;
        }
    };
    
    /**
     * 蹇借璇佷功 Certification
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager()
    {
        private X509Certificate[] certificates;
        
        @Override
        public void checkClientTrusted(X509Certificate[] certificates, String authType)
            throws CertificateException
        {
            if (this.certificates == null)
            {
                this.certificates = certificates;
            }
            
        }
        
        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate, String s)
            throws CertificateException
        {
            if (this.certificates == null)
            {
                this.certificates = ax509certificate;
            }
        }
        
        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    
    static
    {
        /*
         * use ignore host name verifier
         */
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        
        // Prepare SSL Context
        try
        {
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            
            // 浠庝笂杩癝SLContext瀵硅薄涓緱鍒癝SLSocketFactory瀵硅薄
            ssf = sslContext.getSocketFactory();
        }
        catch (Exception e)
        {
        }
    }
    
    public HttpRequester()
    {
    }
    
    /**
     * 鍙戦?丟ET璇锋眰
     * 
     * @param urlString 鐩爣鍦板潃url
     * @param properties head灞炴??
     * @param parameters get鎼哄甫鐨勫弬鏁板??
     * @return
     * @throws IOException [鍙傛暟璇存槑]
     * 
     * @return HttpResponse 寰楀埌鐨剅esponse鍝嶅簲瀵硅薄
     * @exception throws [杩濅緥绫诲瀷] [杩濅緥璇存槑]
     * @see [绫汇?佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public HttpResponse get(String urlString, Map<String, String> properties, String parameters)
        throws IOException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(urlString);
        if (parameters != null)
        {
            if (urlString.indexOf('?') > 0)
            {
                sb.append('&');
                sb.append(parameters);
                // urlString += '&' + parameters;
            }
            else
            {
                sb.append('?');
                sb.append(parameters);
                // urlString += '?' + parameters;
            }
        }
        
        urlString = sb.toString();
       
        
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        
        if (urlString.indexOf("https://") >= 0)
        {
            ((HttpsURLConnection)urlConnection).setSSLSocketFactory(ssf);
        }
        
        urlConnection.setRequestMethod(METHOD_GET);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        
        if (properties != null)
        {
            // CHECKSTYLE:OFF 璇ラ棶棰樹负闈為棶棰? -h00193325
            for (Entry<String, String> value : properties.entrySet())
            // CHECKSTYLE:ON
            {
                urlConnection.addRequestProperty(value.getKey(), value.getValue());
            }
        }
        
        return this.makeContent(urlConnection);
    }
    
    /**
     * 鍙戦?丳OST璇锋眰
     * 
     * @param urlString
     * @param properties
     * @param parameters
     * @return
     * @throws IOException [鍙傛暟璇存槑]
     * 
     * @return HttpResponse [杩斿洖绫诲瀷璇存槑]
     * @exception throws [杩濅緥绫诲瀷] [杩濅緥璇存槑]
     * @see [绫汇?佺被#鏂规硶銆佺被#鎴愬憳]
     */
    public HttpResponse post(String urlString, Map<String, String> properties, String parameters)
        throws IOException
    {
        
        OutputStream out = null;
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            
            if (urlString.indexOf("https://") >= 0)
            {
                ((HttpsURLConnection)urlConnection).setSSLSocketFactory(ssf);
            }
            
            urlConnection.setRequestMethod(METHOD_POST);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            
            if (properties != null)
            {
                // CHECKSTYLE:OFF 璇ラ棶棰樹负闈為棶棰? -h00193325
                for (Entry<String, String> value : properties.entrySet())
                // CHECKSTYLE:ON
                {
                    urlConnection.addRequestProperty(value.getKey(), value.getValue());
                }
            }
            
            out = urlConnection.getOutputStream();
            if (parameters != null)
            {
                out.write(parameters.getBytes(defaultContentEncoding));
            }
        }
        catch (Exception e)
        {
        }
        finally
        {
            try
            {
                if (null != out)
                {
                    out.flush();
                    out.close();
                }
            }
            catch (Exception e2)
            {
            }
            
            try
            {
                if (null != urlConnection)
                {
                    urlConnection.disconnect();
                }
            }
            catch (Exception e2)
            {
            }
        }
        if (null == urlConnection)
        {
            return null;
        }
        
        return this.makeContent(urlConnection);
    }
    
    /**
     * 寰楀埌鍝嶅簲瀵硅薄
     */
    private HttpResponse makeContent(HttpURLConnection urlConnection)
    {
        HttpResponse httpResponser = new HttpResponse();
        InputStream in = null;
        BufferedReader bufferedReader = null;
        
        try
        {
            in = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder temp = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null)
            {
                temp.append(line).append('\n');
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            
            String encoding = urlConnection.getContentEncoding();
            if (encoding == null)
            {
                encoding = this.defaultContentEncoding;
            }
            
            URL url = urlConnection.getURL();
            
            httpResponser.defaultPort = url.getDefaultPort();
            httpResponser.file = url.getFile();
            httpResponser.host = url.getHost();
            httpResponser.path = url.getPath();
            httpResponser.port = url.getPort();
            httpResponser.protocol = url.getProtocol();
            httpResponser.query = url.getQuery();
            httpResponser.ref = url.getRef();
            httpResponser.userInfo = url.getUserInfo();
            
            httpResponser.content = new String(temp.toString().getBytes(), encoding);
            httpResponser.contentEncoding = encoding;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();
        }
        catch (Exception e)
        {
        }
        finally
        {
            closeBufferReader(bufferedReader);
            try
            {
                if (null != in)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
            }
        }
        
        return httpResponser;
    }
    
    private void closeBufferReader(BufferedReader bufferedReader)
    {
        try
        {
            if (null != bufferedReader)
            {
                bufferedReader.close();
            }
        }
        catch (Exception e2)
        {
        }
    }
    
    /**
     * 榛樿鐨勫搷搴斿瓧绗﹂泦
     */
    public String getDefaultContentEncoding()
    {
        return this.defaultContentEncoding;
    }
    
    /**
     * 璁剧疆榛樿鐨勫搷搴斿瓧绗﹂泦
     */
    public void setDefaultContentEncoding(String defaultContentEncoding)
    {
        this.defaultContentEncoding = defaultContentEncoding;
    }
    
}
