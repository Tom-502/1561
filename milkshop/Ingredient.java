package milkshop;
import java.util.*;
public abstract class Ingredient
{
    protected String name;
    protected Calendar date_time;
    protected int quality_day;
    public Ingredient(int day,Calendar time,String Name)
    {
        this.quality_day = day;
        this. date_time = time;
        this.name=Name;
    }
    public abstract String toString ();
    public abstract String get_name ();
    public abstract int get_day ();
    public abstract Calendar get_time ();
}
