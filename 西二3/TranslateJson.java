package 西二3;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class TranslateJson {
    public Weather  translate (String st,Weather weather )
    {
        try {
            JSONObject json = new JSONObject(st);
            int status = json.getInt("status");
            int count = json.getInt("count");
            String info = json.getString("info");
            int infocode = json.getInt("infocode");
            System.out.println("结果返回状态:"+status);
            System.out.println("查询次数:"+count );
            System.out.println("查询结果是否为info:"+info );
            System.out.println("info代码为:"+infocode);
            System.out.println("————————————————————————————————");
            JSONArray forecasts = json.getJSONArray("forecasts");
            System.out.println("查询结果如下:");
                JSONObject fore = forecasts.getJSONObject(0);
                String fore_city = fore.getString("city");
                weather .setCity(fore_city );
                int fore_adcode = fore.getInt("adcode");
                weather .setAdcode(fore_adcode );
                String fore_province = fore.getString("province");
                weather .setProvince(fore_province ) ;
                String fore_reporttime = fore.getString("reporttime");
                weather .setReporttime(fore_reporttime );
                System.out.println("您查询的地区为:"+fore_province+"  "+fore_city+" "+"编码为:"+fore_adcode+" "+"查询时间为:"+fore_reporttime );
                System.out.println("————————————————————————————————");
                JSONArray casts = fore.getJSONArray("casts");
                System.out.println("近四天天气状况如下:");
                String [] ca_date= new String[4];
                int [] ca_week = new int[4];
                String [] ca_dayweather= new String[4];
                String [] ca_nightweather= new String[4];
                int [] ca_daytemp = new int[4];
                int [] ca_nighttemp = new int[4];
                String [] ca_daywind= new String[4];
                String [] ca_nightwind= new String[4];
                String [] ca_daypower= new String[4];
                String [] ca_nightpower= new String[4];
                for (int j = 0; j < casts.length(); j++)
                {
                    JSONObject ca = casts.getJSONObject(j);
                    ca_date[j] = ca.getString("date");
                    ca_week[j] = ca.getInt("week");
                    ca_dayweather[j] = ca.getString("dayweather");
                    ca_nightweather[j] = ca.getString("nightweather");
                    ca_daytemp[j] = ca.getInt("daytemp");
                    ca_nighttemp[j] = ca.getInt("nighttemp");
                    ca_daywind[j] = ca.getString("daywind");
                    ca_nightwind[j] = ca.getString("nightwind");
                    ca_daypower[j] = ca.getString("daypower");
                    ca_nightpower[j] = ca.getString("nightpower");
                    System.out.println("日期:"+ca_date[j]+"  "+"星期"+ca_week[j]+"  "+"白天天气:"+ca_dayweather[j]+"  "+"晚间天气:"+ca_nightweather[j]+"  "+"白天温度:"+ca_daytemp[j]+"℃"+"  "+"晚间温度:"+ca_nighttemp[j]+"℃"+"  "+"白天风向:"+ca_daywind[j]+"  "+"晚间风向:"+ca_nightwind[j]+"  "+"白天风力:"+ca_daypower[j]+"级" +"  "+"晚间风力:"+ca_nightpower[j]+"级" );
                }
            weather .setDate(ca_date );
            weather .setWeek(ca_week );
            weather .setDayweather(ca_dayweather );
            weather .setNightweather(ca_nightweather );
            weather .setDaytem(ca_daytemp );
            weather .setNighttem(ca_nighttemp );
            weather .setDaywind(ca_daywind );
            weather .setNightwind(ca_nightwind );
            weather .setDaypower(ca_daypower );
            weather .setNightpower(ca_nightpower );
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return weather ;
    }
    public static void main(String[] args)
    {
        Weather weather = new Weather() ;
        TranslateJson trans = new TranslateJson();
        API api = new API();
        trans.translate(api.result("110101","all"),weather ) ;
        weather .show() ;
    }
}
