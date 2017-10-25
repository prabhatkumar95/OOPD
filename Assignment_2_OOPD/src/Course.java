import java.util.HashSet;

public class Course {

    public String getName() {
        return Name;
    }

    public int getMax_student() {
        return max_student;
    }

    public HashSet<Student> getEnrolled_Students() {
        return enrolled_Students;
    }

    public Professor getAssign_prof() {
        return assign_prof;
    }

    public PreRequsite getPre_req() {
        return pre_req;
    }



    public void setMax_student(int max_student) {
        this.max_student = max_student;
    }

    public void ShowCourse()
    {
        System.out.println("NAME : "+this.Name);
        System.out.println("MAX STUDENTS : "+this.max_student);
        System.out.println("NO OF ENROLLED STUDENTS : "+this.enrolled_Students.size());
        System.out.println("PROFESSOR ASSIGNED : "+this.getAssign_prof().getName());
        if(this.pre_req!=null)
        {
            System.out.println("PREREQUISITE DEGREE : "+this.pre_req.getDegree());
            System.out.println("PREREQUISITE YEAR : "+this.pre_req.getYear());
            System.out.print("PREREQUISITE COURSE : "+this.pre_req.getReq().getName());
        }

         System.out.println();System.out.println();
    }

    public void addtocourse(Student s)
    {
        enrolled_Students.add(s);
    }

    public void removefromcourse(Student s)
    {
        enrolled_Students.remove(s);
    }

    public Course(String name,int max,Professor prof)
    {
        Name=name;
        max_student=max;
        assign_prof=prof;
        prof.getC_list().add(this);
        enrolled_Students=new HashSet<Student>();
        pre_req=null;
    }

    public Course(String name, int max, Professor prof, PreRequsite p)
    {
        Name=name;
        max_student=max;
        assign_prof=prof;
        prof.getC_list().add(this);
        pre_req=p;
        enrolled_Students=new HashSet<Student>();
    }

    public void modify(int max,Professor p)
    {

        if(this.enrolled_Students.size()<=max)
            this.max_student=max;
        else
        {System.out.println("MAX NO OF STUDENTS CANT BE LESS THAN CURRENTLY ENROLLED");return;}

        if(this.assign_prof.getName().compareTo(p.getName())!=0)
        {
            this.assign_prof.removecourse(this);
            this.assign_prof=p;
            this.assign_prof.addcourse(this);
        }
    }


    private String Name;
    private int max_student;
    private HashSet<Student> enrolled_Students;
    private Professor assign_prof;
    private PreRequsite pre_req;

}
