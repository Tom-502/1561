package milkshop;
import java.util.*;
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
