package milkshop;
import java.util.*;
public class MilkTea
{
    private String name;
    private Ingredient ingredient;
    private Calendar date_time;
    public String get_mname()
    {
        return this.name;
    }
    public MilkTea (String Name,Ingredient ingre,Calendar time)
    {
        this.name = Name;
        this.ingredient = ingre;
        this.date_time=time;
    }
    public String toString ()
    {
        return "奶茶名:"+get_mname()+"   配料名:"+ingredient.name+"   "+"生产日期为:"+date_time.get(Calendar.YEAR)+"年"+(date_time.get(Calendar.MONTH) + 1)+"月"+date_time.get(Calendar.DAY_OF_MONTH)+"日"+date_time.get(Calendar.HOUR_OF_DAY)+"时"+date_time.get(Calendar.MINUTE)+"分"+date_time.get(Calendar.SECOND)+"秒"+"    "+"配料"+"生产日期:"+ingredient.date_time.get(Calendar.YEAR)+"年"+(ingredient.date_time.get(Calendar.MONTH) + 1)+"月"+ingredient.date_time.get(Calendar.DAY_OF_MONTH)+"日"+ingredient.date_time.get(Calendar.HOUR_OF_DAY)+"时"+ingredient.date_time.get(Calendar.MINUTE)+"分"+ingredient.date_time.get(Calendar.SECOND)+"秒"+"   "+"保质期:"+ingredient.quality_day+"天";
    }
}
