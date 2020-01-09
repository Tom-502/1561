package 西二3;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class TranslationJson2 {
    public Weather translate2 (String st,Weather weather)
    {
        try {
            JSONObject json = new JSONObject(st);
            int status = json.getInt("status");
            int count = json.getInt("count");
            String info = json.getString("info");
            int infocode = json.getInt("infocode");
            System.out.println("结果返回状态:" + status);
            System.out.println("查询次数:" + count);
            System.out.println("查询结果是否为info:" + info);
            System.out.println("info代码为:" + infocode);
            System.out.println("————————————————————————————————");
            JSONArray lives = json.getJSONArray("lives");
            System.out.println ("当天天气查询结果如下:");
            for (int i=0;i<lives.length();i++)
            {
                JSONObject li = lives.getJSONObject(i);
                String li_province = li.getString("province");
                weather.setProvince(li_province);
                String li_city = li.getString("city");
                weather .setCity(li_city) ;
                int li_adcode = li.getInt("adcode");
                weather .setAdcode(li_adcode) ;
                String li_weather = li.getString("weather");
                weather .setWeather(li_weather );
                int li_temperature = li.getInt("temperature");
                weather .setTemperature(li_temperature );
                String li_winddirection = li.getString("winddirection");
                weather .setWinddirection(li_winddirection);
                String li_windpower = li.getString("windpower");
                weather .setWindpower(li_windpower );
                int li_humidity = li.getInt("humidity");
                weather .setHumidity(li_humidity );
                String li_reporttime = li.getString("reporttime");
                weather .setReporttime(li_reporttime );
                System.out.println("您查询的地区为:"+li_province+"  "+li_city+" "+"编码为:"+li_adcode+" "+"查询时间为:"+li_reporttime+"  "+"天气为:"+li_weather+"  "+"温度为:"+li_temperature+"℃"+"  "+"风向为:"+li_winddirection +"  "+"风力为:"+li_windpower+"级" +"  "+"空气湿度为:"+li_humidity+"  ");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return weather ;
    }
    public static void main(String[] args)
    {
        Weather weather = new Weather() ;
        TranslationJson2 trans = new TranslationJson2();
        API api = new API();
        trans.translate2(api.result("110101","base"),weather) ;
        weather .show1() ;
    }
}
