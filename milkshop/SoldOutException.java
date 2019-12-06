package milkshop;
import java.util.*;
public class SoldOutException extends Exception
{
    public SoldOutException (String Message)
    {
        super(Message);
    }
}
