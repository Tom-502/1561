package milkshop;
import java.util.*;
public class TeaShop implements Shop
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
