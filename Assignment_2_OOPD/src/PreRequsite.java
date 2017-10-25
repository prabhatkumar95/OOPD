public class PreRequsite {
     private String Degree;
    private int  Year;
    Course req;

    public PreRequsite(Course r,int yr, String Degree)
    {
        req=r;
        Year=yr;
        this.Degree=Degree;

    }

    public String getDegree() {
        return Degree;
    }

    public int  getYear() {
        return Year;
    }

    public Course getReq() {
        return req;
    }
 }



