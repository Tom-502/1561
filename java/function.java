public class function
{
	public static int Fibo (int n)
	{
		if (n==1||n==2)
		{
			return 1;
		}
	    else
		{
			return Fibo(n-1)+Fibo(n-2);
		}
	}
public static void main (String[] args)
{
    for (int i=1;i<=10 ;i++ )
    {
		System.out.print(Fibo(i)+"     ");
    }
}
}
