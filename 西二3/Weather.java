package 西二3;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class Weather {
    private String province;
    private String city;
    private int adcode;
    private String weather;
    private int temperature;
    private String winddirection;
    private String windpower;
    private int humidity;
    private String reporttime;

    private String date[];
    private int week[];
    private String dayweather[];
    private String nightweather[];
    private int daytem[];
    private int nighttem[];
    private String daywind[];
    private String nightwind[];
    private String  daypower[];
    private String  nightpower[];
    public Weather ()
    {
        ;
    }
    public void setProvince (String Province)
    {
        province = Province;
    }
    public void setCity (String City)
    {
        city = City ;
    }
    public void setAdcode (int Adcode)
    {
        adcode = Adcode;
    }
    public void setWeather (String Weather)
    {
        weather = Weather ;
    }
    public void setTemperature (int Temperature)
    {
        temperature = Temperature ;
    }
    public void setWinddirection (String Winddirection)
    {
        winddirection = Winddirection ;
    }
    public void setWindpower (String Windpower)
    {
        windpower = Windpower ;
    }
    public void setHumidity (int Humidity)
    {
        humidity = Humidity ;
    }
    public void setReporttime (String Reporttime)
    {
        reporttime = Reporttime ;
    }


    public void setDate (String Date[])
    {
        date = new String[4];
        System .arraycopy(Date ,0,date ,0,4);
    }
    public void setWeek (int Week[])
    {
        week = new int[4];
        System .arraycopy(Week ,0,week ,0,4);
    }
    public void setDayweather (String Dayweather[])
    {
        dayweather = new String[4];
        System .arraycopy(Dayweather ,0,dayweather ,0,4);
    }
    public void setNightweather (String Nightweather[])
    {
        nightweather = new String[4];
        System .arraycopy(Nightweather  ,0,nightweather  ,0,4);
    }
    public void setDaytem (int Daytem[])
    {
        daytem = new int [4];
        System .arraycopy(Daytem ,0,daytem  ,0,4);
    }
    public void setNighttem (int Nighttem[])
    {
        nighttem = new int[4];
        System .arraycopy(Nighttem  ,0,nighttem ,0,4);
    }
    public void setDaywind (String Daywind[])
    {
        daywind = new String[4];
        System .arraycopy(Daywind ,0,daywind ,0,4);
    }
    public void setNightwind (String Nightwind[])
    {
        nightwind = new String[4];
        System .arraycopy(Nightwind ,0,nightwind ,0,4);
    }
    public void setDaypower (String  Daypower[])
    {
        daypower = new String[4];
        System .arraycopy(Daypower ,0,daypower ,0,4);
    }
    public void setNightpower (String  Nightpower[])
    {
        nightpower = new String[4];
        System .arraycopy(Nightpower ,0,nightpower ,0,4);
    }
    public String getProvince ()
    {
        return province ;
    }
    public String getCity ()
    {
        return city ;
    }
    public int getAdcode ()
    {
        return adcode ;
    }
    public String getWeather ()
    {
        return weather ;
    }
    public  int getTemperature ()
    {
        return temperature ;
    }
    public String getWinddirection ()
    {
        return winddirection ;
    }
    public int getHumidity ()
    {
        return humidity ;
    }
    public String getWindpower ()
    {
        return windpower ;
    }
    public String getReporttime ()
    {
        return reporttime ;
    }
    public String  getDate (int i)
    {
        return date[i] ;
    }
    public int getWeek (int i)
    {
        return week[i];
    }
    public String getDayweather (int i)
    {
        return dayweather[i] ;
    }
    public String getNightweather (int i)
    {
        return nightweather[i] ;
    }
    public int  getDaytem (int i)
    {
        return daytem[i] ;
    }
    public int getNighttem (int i)
    {
        return nighttem[i] ;
    }
    public String getDaywind (int i)
    {
        return daywind [i];
    }
    public String getNightwind (int i)
    {
        return nightwind[i] ;
    }
    public String  getDaypower (int i)
    {
        return daypower[i] ;
    }
    public String  getNightpower (int i)
    {
        return nightpower[i] ;
    }
    public void show ()
    {
        for (int i=0;i<4;i++)
        {
            System.out.println(nightpower [i]);
        }
    }
    public void show1 ()
    {
        System.out.println(reporttime);
    }
}
