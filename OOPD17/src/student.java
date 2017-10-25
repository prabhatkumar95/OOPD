
public class student implements Comparable<student>
{
    public String first;
    public String last;
    public String[] courses;
    //public long pos;

    student(String a, String b, String c[])
    {
        first=a;
        last=b;
        courses=c.clone();

    }


    public boolean greater(student b)
    {
        int temp = this.first.compareTo(b.first);
        if(temp>0)
            return true;
        else if(temp==0)
        {
            if(this.last.compareTo(b.last)>0)
                return true;
            else
                return false;
        }
        else
            return false;
    }


    @Override
    public int compareTo(student o) {
        final int GREATER=1;
        final int SMALLER=-1;
        int temp = this.first.compareTo(o.first);
        if(temp>0)
            return GREATER;
        else if(temp==0)
        {
            if(this.last.compareTo((o.last))>0)
                return GREATER;
            else
                return SMALLER;
        }
        else
            return SMALLER;

    }
}