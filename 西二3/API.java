package 西二3;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
public class API
{
    private static String requestUrl ="https://restapi.amap.com/v3/weather/weatherInfo?parameters";
    private static String key ="e3c5eedd1a954dc6b5b9d17a92e0992e";
    private static String json;
    public API ()
    {
        json=null;
    }
    public void set_json (String JSON)
    {
        json=JSON;
    }
    public String get_json ()
    {
        return json;
    }
    public static String result(String city,String extensions)
    {
        Map params = new HashMap();
        params.put("key",key);
        params.put("city",city);
        params.put ("extensions",extensions);
        params.put("output",json);
        String st = httpRequest(requestUrl,params);
        return st;
    }
    private static String httpRequest(String requestUrl,Map params)
    {
        //buffer用于接受返回的字符
        StringBuffer buffer = new StringBuffer();
        try {
            //建立URL，把请求地址给补全，其中urlencode（）方法用于把params里的参数给取出来
            URL url = new URL(requestUrl+"?"+urlencode(params));
            //开http连接
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            //获得输入
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //将bufferReader的值给放到buffer里
            String str = null;
            while ((str = bufferedReader.readLine()) != null)
            {
                buffer.append(str);
            }
            //关闭bufferReader和输入流
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            //断开连接
            httpUrlConn.disconnect();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //返回字符串
        return buffer.toString();
    }
    public static String urlencode(Map<String,Object>data)
    {
        //将map里的参数变成像 showapi_appid=###&showapi_sign=###&的样子
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet())
        {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public static void main(String[] args)
    {
        API api = new API();
        api.set_json(result("110101","all" + "")) ;
        System.out.println(api.get_json());

    }

}