import java.util.*;
abstract class Ingredient
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
class Bubble extends Ingredient
{
    public Bubble (Calendar time,String Name)
    {
        super(7,time,Name);
    }
    public Calendar get_time ()
    {
        return this.date_time;
    }

    public String toString ()
    {
        return "珍珠"+"  " + "生产日期:"+get_time().get(Calendar.YEAR)+"年"+(get_time().get(Calendar.MONTH) + 1)+"月"+get_time().get(Calendar.DAY_OF_MONTH)+"日"+get_time().get(Calendar.HOUR_OF_DAY)+"时"+get_time().get(Calendar.MINUTE)+"分"+get_time().get(Calendar.SECOND)+"秒"+"   "+"保质期:"+get_day()+"天";
    }
    public String get_name ()
    {
        return this.name;
    }
    public int get_day ()
    {
        return 7;
    }
}
class Cocount extends Ingredient
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
class MilkTea
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
class TeaShop implements Shop
{
    private ArrayList<Ingredient> bu;
    private ArrayList<Ingredient> co;
    public TeaShop ()
    {
        bu= new ArrayList <Ingredient>();
        co=new ArrayList <Ingredient>();
    }
    public void add_milktea (Ingredient ingredient)
    {
        if (ingredient instanceof Bubble)
        {
            bu.add(ingredient);
        }
        if (ingredient instanceof Cocount)
        {
            co.add(ingredient);
        }
    }
    public void sale (String teaname,String ingrename) throws  SoldOutException,IngredientException
    {
        Ingredient in;
        Calendar time=Calendar.getInstance();
        if (!ingrename.equals("珍珠")&&!ingrename.equals("椰果"))
        {
            throw new IngredientException("抱歉，没有这种奶茶.");
        }
        if (ingrename.equals("珍珠"))
        {
            if (bu.isEmpty())
            {
                throw new SoldOutException("珍珠奶茶已售完");
            }
            in=bu.get(0);
            long t=time.getTimeInMillis()-in.get_time ().getTimeInMillis();
            if (t>=604800000)
            {
                bu.remove (0);
            }
            else if (!bu.isEmpty())
            {
                MilkTea milktea = new MilkTea (teaname,in,time);
                System.out.println("成功售出一杯名为"+teaname+"的珍珠奶茶\n");
                System.out.println(milktea.toString());
            }
        }
        if (ingrename.equals("椰果"))
        {
            if (co.isEmpty())
            {
                throw new SoldOutException("椰果奶茶已售完");
            }
            in=co.get(0);
            long t=time.getTimeInMillis()-in.get_time ().getTimeInMillis();
            if (t>=432000000)
            {
                co.remove(0);
            }
            else if (!co.isEmpty())
            {
                MilkTea milktea = new MilkTea (teaname,in,time);
                System.out.println("成功售出一杯名为"+teaname+"的椰果奶茶\n");
                System.out.println(milktea.toString());
            }
        }
    }
}
interface Shop
{
    public void add_milktea (Ingredient ingredient);
    public void sale (String teaname,String ingrename) throws SoldOutException,IngredientException;
}
class IngredientException extends Exception
{
    public IngredientException (String Message)
    {
        super(Message);
    }
}
class SoldOutException extends Exception
{
    public SoldOutException (String Message)
    {
        super(Message);
    }
}
public class test
{
    public static void main(String[] args)
    {
        Calendar time=Calendar.getInstance();
        Bubble bubble =new Bubble(time,"qqq");
        Cocount cocount =new Cocount (time,"yyy");
        TeaShop teashop;
        teashop=new TeaShop();
        teashop.add_milktea(bubble);
        teashop.add_milktea(cocount);
        System.out.println(bubble.toString());
        System.out.println(cocount.toString());
        Scanner in =new Scanner(System.in);
        System.out.println("欢迎来到西二奶茶店\n");
        System.out.println("请问您要什么奶茶呢？我们有椰果奶茶和珍珠奶茶.\n");
        String na=in.next();
        try
        {
            teashop.sale("sss",na);
        }
        catch (SoldOutException |IngredientException s)
        {
            System.out.println(s.getMessage());
        }
    }
}



/*Calendar time=Calendar.getInstance();
time.set(year,month+1,day,hour,minute,second)


time.getTime();
time.get(Calendar.DAY_OF_MONTH)
time.get(Calendar.HOUR_OF_DAY)*/
