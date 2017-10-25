import java.util.HashSet;

public class Professor {

    private String Name;
    private String AOE;
    private HashSet<Course> c_list;

    public Professor(String name,String aoe) {
        Name = name;
        AOE=aoe;
        c_list=new HashSet<Course>();
    }
public void addcourse(Course c)
{
    this.c_list.add(c);
}
public void removecourse(Course c)
{
    this.c_list.remove(c);
}

    public void ShowProf()
{
    System.out.println("NAME : "+this.Name);
    System.out.println("DEGREE : "+this.AOE);
    System.out.print("COURSES ASSIGNED : ");
    if(c_list.size()>0)
    {
    for (Course c : this.c_list)
    {
        System.out.print(c.getName()+" ");
    }}
    System.out.println();System.out.println();
}

    public String getName() {
        return Name;
    }

    public String getAOE() {
        return AOE;
    }

    public HashSet<Course> getC_list() {
        return c_list;
    }

}
