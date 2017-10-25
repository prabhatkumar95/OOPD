import java.util.ArrayList;
import java.util.HashSet;

public class Student {
    public String getRollNo() {
        return RollNo;
    }

    private String RollNo;
    private String Name;
    private String Degree;
    private int Year;
    HashSet<Course> c_list;

    public Student(String n,String roll,int yr,String degree)
    {
        this.Name=n;
        this.RollNo=roll;
        this.Year=yr;
        this.Degree=degree;
        c_list=new HashSet<Course>();
    }


    public void Enroll(Course c) {
       if(c_list.contains(c))
           System.out.println("STUDENT ALREADY REGISTERED IN THE SYSTEM");
       else
       {
           if(c.getPre_req()==null && c.getMax_student()>c.getEnrolled_Students().size())
           {
               c_list.add(c);
               c.addtocourse(this);
               System.out.println(this.Name+ " SUCCESSFULLY ENROLLED IN THE COURSE : "+c.getName());
           }
           else
           {
               PreRequsite temp=c.getPre_req();
               if((this.Year==temp.getYear()) && (this.Degree.compareTo(this.getDegree())==0) && this.c_list.contains(temp.getReq()) && c.getMax_student()-c.getEnrolled_Students().size()>0)
               {
                   c_list.add(c);
                   c.addtocourse(this);
                   System.out.println(this.Name+ " SUCCESSFULLY ENROLLED IN THE COURSE : "+c.getName());
               }
               else
                   System.out.println("STUDENT DOESN'T MEET THE MINIMUM CRITERIA FOR THIS COURSE");
           }
       }
    }

    public void UnEnroll(Course c) {
        if (!c_list.contains(c))
            System.out.println("STUDENT HAS NOT ENROLLED IN THIS COURSE");
        else
        {

            for(Course d:c_list)
            {
                if(d.getPre_req()!=null)
                if(d.getPre_req().getReq().getName().compareTo(c.getName())==0)
                {System.out.println("THIS COURSE IS PRE REQUSITE OF ANOTHER COURSE ENROLLED BY THE STUDENT");
                    return;}
            }

            c_list.remove(c);
            c.removefromcourse(this);
            System.out.println(this.Name+" UNENROLLED FROM THE COURSE : "+c.getName());
        }
    }

    public void ShowStudent()
    {
        System.out.println("NAME : "+this.Name);
        System.out.println("DEGREE : "+this.Degree);
        System.out.println("YEAR : "+this.Year);
        System.out.print("COURSES ENROLLED : ");
        for (Course c : this.c_list)
        {
            System.out.print(c.getName()+" ");
        }
        System.out.println();System.out.println();
    }


    public String getName() {
        return Name;
    }

    public String getDegree() {
        return Degree;
    }

    public int getYear() {
        return Year;
    }

    public HashSet<Course> getC_list() {
        return c_list;
    }

}
