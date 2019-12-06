package milkshop;
import java.util.*;
public class Cocount extends Ingredient
{
    public Cocount (Calendar time,String Name)
    {
        super(5,time,Name);
    }
    public Calendar get_time ()
    {
        return this.date_time;
    }

    public String toString ()
    {
        return "椰果"+"  " + "生产日期:"+get_time().get(Calendar.YEAR)+"年"+(get_time().get(Calendar.MONTH) + 1)+"月"+get_time().get(Calendar.DAY_OF_MONTH)+"日"+get_time().get(Calendar.HOUR_OF_DAY)+"时"+get_time().get(Calendar.MINUTE)+"分"+get_time().get(Calendar.SECOND)+"秒"+"   "+"保质期:"+get_day()+"天";
    }
    public String get_name ()
    {
        return this.name;
    }
    public int get_day ()
    {
        return 5;
    }
}
