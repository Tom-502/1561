package milkshop;
import java.util.*;
public interface Shop
{
    public void add_milktea (Ingredient ingredient);
    public void sale (String teaname,String ingrename) throws SoldOutException,IngredientException;
}
