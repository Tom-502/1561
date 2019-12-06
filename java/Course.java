import java.util.*;
class Course 
{
	private String name;
	private double credit;
	private	int score;
	private	double gradePoint;
	private	boolean schoolElect;
	public String get_name ()
	{
		return name;
	}
	public double get_credit()
	{
		return credit;
	}
	public int get_score()
	{
		return score;
	}
	public double get_gradepoint ()
	{
		return gradePoint;
	}
	public boolean get_schoolElect ()
	{
		return schoolElect;
	}
	public void set_name (String Name)
	{
		name=Name;
	}
	public void set_credit (double Credit)
	{
		credit=Credit;
	}
	public void set_score (int Score)
	{
		score=Score;
	}
	public void set_gradePoint (double GradePoint)
	{
		gradePoint=GradePoint;
	}
	public void set_schoolElect (boolean SchoolElect)
	{
		schoolElect=SchoolElect;
	}
	public void caculate_gradePoint(String name,double credit,int score,boolean schoolElect)
	{
		set_name(name);
        set_credit (credit);
		set_score(score);
		set_schoolElect (schoolElect);
        if (schoolElect==true)
        {
			set_gradePoint( 0.0);
        }
		else 
		{
			if (score>=90&&score<=100)
			{
				set_gradePoint( 4.0);
			}
			else if (score>=85&&score<=89)
			{
				set_gradePoint( 3.7);
			}
			else if (score>=82&&score<=84)
			{
				set_gradePoint( 3.3);
			}
				else if (score>=78&&score<=81)
			{
				set_gradePoint( 3.0);
			}
				else if (score>=75&&score<=77)
			{
				set_gradePoint( 2.7);
			}
				else if (score>=71&&score<=74)
			{
				set_gradePoint( 2.3);
			}
				else if (score>=66&&score<=70)
			{
				set_gradePoint( 2.0);
			}
				else if (score>=62&&score<=65)
			{
				set_gradePoint( 1.7);
			}
				else if (score>=60&&score<=61)
			{
				set_gradePoint( 1.0);
			}
			else if (score<60)
			{
				set_gradePoint( 0.0);
			}
		}
	    if (score>100||score<0)
	    {
			System.out.println("³É¼¨ÊäÈë´íÎó¡£");
            set_gradePoint( 0.0);
	    }
	}
	public static double getGpa (Course[] course)
	{
		int length=course.length;
        int i,total_credit_and_gradepoint=0,total_credit=0;
		for (i=0;i<length ;i++ )
		{
            total_credit_and_gradepoint+=course[i].get_credit()*course[i].get_gradepoint();
            total_credit+=course[i].get_credit();
            System.out.println(total_credit_and_gradepoint);
            System.out.println(total_credit);
		}
		double result = (double)total_credit_and_gradepoint/(double)total_credit;
        System.out.println(String.format("%.2f",result));
		 return total_credit_and_gradepoint/total_credit;
	}
	public static void main(String[] args) 
	{
        Scanner in =new Scanner(System.in);
		int a=in.nextInt();
		Course [] cou;
		cou = new Course [a];
		for (int i=0;i<a ;i++ )
		{
			cou[i] = new Course () ;
			String na=in.next();
            double cre = in.nextDouble();
			int sco = in.nextInt();
			boolean sch = in.nextBoolean();
			cou[i].caculate_gradePoint(na,cre,sco,sch);
		}
		double result = getGpa (cou);
		/*for (int i=0;i<a;i++ )
		{
             System.out.println(cou[i].get_name());
             System.out.println(cou[i].get_credit());
			 System.out.println(cou[i].get_score());
			 System.out.println(cou[i].get_gradepoint());
			 System.out.println(cou[i].get_schoolElect());
		}*/
	}
}
