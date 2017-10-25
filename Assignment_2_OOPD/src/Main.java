import com.sun.javafx.image.BytePixelSetter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int  SearchStudent(ArrayList<Student>a,String name )
    {
        for(int i =0;i<a.size();i++)
        {
            if(a.get(i).getName().compareTo(name)==0) {
                return i;
            }
        }

        return -1;

    }
    public static int SearchProf(ArrayList<Professor>a,String name)
    {
        for(int i =0;i<a.size();i++)
        {
            if(a.get(i).getName().compareTo(name)==0 )
                return i;
        }

        return -1;

    }

    public static int SearchCourse(ArrayList<Course>a,String name)
    {
        for(int i =0;i<a.size();i++)
        {
            if(a.get(i).getName().compareTo(name)==0)
                return i;
        }

        return -1;

    }


    public static void main(String args[])
    {
        ArrayList<Course> Courselist= new ArrayList<Course>();
        ArrayList<Student> Studentlist=new ArrayList<Student>();
        ArrayList<Professor> Proflist=new ArrayList<Professor>();
        String [] ch=null;

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int k=0;k<lines.size();k++) {
            ch = lines.get(k).toUpperCase().split(" ");
            switch (ch[0]) {
                case "ADDS": {

                    if(ch.length<5)
                        System.out.println("INVALID INPUT");
                    else {
                        if (SearchStudent(Studentlist, ch[1])==-1) {
                            int yr = Integer.parseInt(ch[4]);
                            Student temp = new Student(ch[1],ch[2],yr,ch[3]);
                            Studentlist.add(temp);
                            System.out.println("STUDENT : "+temp.getName()+" ADDED TO THE SYSTEM");
                        }
                        else
                            System.out.println("STUDENT ALREADY REGISTERED IN THE SYSTEM ");
                    }
                    break;
                    }

                case "ADDP": {
                    if(ch.length<3)
                        System.out.println("INVALID INPUT");
                    else {
                        if (SearchProf(Proflist, ch[1])==-1){
                            Professor temp = new Professor(ch[1],ch[2]);
                            Proflist.add(temp);
                            System.out.println("PROFESSOR : "+temp.getName()+" ADDED TO THE SYSTEM");
                        }
                        else
                            System.out.println("PROFESSOR ALREADY REGISTERED IN THE SYSTEM ");
                    }

                    break;
                }

                case "ADDC": {
                    if(ch.length!=4 && ch.length!=7)
                    {
                        System.out.println("INVALID INPUT");
                    }
                    else
                    {
                        if(SearchCourse(Courselist,ch[1])==-1)
                        {

                            if(SearchProf(Proflist,ch[3])==-1)
                                System.out.println("PROFESSOR ASSIGNED TO COURSE DOESN'T EXIST");
                            else
                            {
                                int i=SearchProf(Proflist,ch[3]);
                                if(ch.length==4)
                                {
                                    Course temp = new Course(ch[1],Integer.parseInt(ch[2]),Proflist.get(i));
                                    Courselist.add(temp);
                                    System.out.println("COURSE ADDED : "+temp.getName());
                                }
                                else
                                {
                                    if(SearchCourse(Courselist,ch[6])==-1)
                                    {
                                        System.out.println("PREREQUISITE ERROR");
                                    }
                                    else
                                    {
                                        int j=SearchCourse(Courselist,ch[6]);
                                        PreRequsite temp1 = new PreRequsite(Courselist.get(j),Integer.parseInt(ch[5]),ch[4]);
                                        Course temp = new Course(ch[1],Integer.parseInt(ch[2]),Proflist.get(i),temp1);
                                        Courselist.add(temp);
                                        System.out.println("COURSE ADDED : "+temp.getName());
                                    }
                                }
                            }

                        }
                        else
                            System.out.println("COURSE DOESN'T EXIST");
                    }
                    break;
                }

                case "ENROLL": {
                    if(ch.length!=3)
                        System.out.println("INVALID INPUT");
                    else
                    {
                        if(SearchStudent(Studentlist,ch[2])!=-1 && SearchCourse(Courselist,ch[1])!=-1)
                        {   int i,j;
                            i=SearchStudent(Studentlist,ch[2]);
                            j=SearchCourse(Courselist,ch[1]);
                            Studentlist.get(i).Enroll(Courselist.get(j));

                        }
                        else
                            System.out.println("Either the Student or the Coursename Info is incorrect");
                    }


                    break;
                }
                case "UNENROLL": {
                    if(ch.length!=3)
                        System.out.println("INVALID INPUT");
                    else
                    {
                        if(SearchStudent(Studentlist,ch[2])!=-1 && SearchCourse(Courselist,ch[1])!=-1)
                        {   int i,j;
                            i=SearchStudent(Studentlist,ch[2]);
                            j=SearchCourse(Courselist,ch[1]);
                            Studentlist.get(i).UnEnroll(Courselist.get(j));
                        }
                        else
                            System.out.println("EITHER STUDENT OR COURSE INFO IS INCORRECT");
                    }


                    break;
                }
                case "MODIFY": {
                    if(ch.length!=4)
                    {
                        System.out.println("INVALID INPUT");
                    }
                    else
                    {
                        if(SearchCourse(Courselist,ch[1])==-1)
                        {
                            System.out.println("COURSE DOESN'T EXIST");
                        }
                        else
                        {
                            if(SearchProf(Proflist,ch[3])==-1)
                            {
                                System.out.println("PROFESSOR DOESNOT EXIST");
                            }
                            else
                            {
                                int i=SearchProf(Proflist,ch[3]);
                                int j =SearchCourse(Courselist,ch[1]);
                                Courselist.get(j).modify(Integer.parseInt(ch[2]),Proflist.get(i));

                            }
                        }
                    }

                    break;
                }
                case "SHOWS": {

                    if(ch.length!=2)
                    {
                        System.out.println("INVALID INPUT");
                    }
                    else
                    if(SearchStudent(Studentlist,ch[1])!=-1)
                    {
                        Studentlist.get(SearchStudent(Studentlist,ch[1])).ShowStudent();
                    }
                    break;
                }
                case "SHOWP": {
                    if(ch.length!=2)
                    {
                        System.out.println("INVALID INPUT");
                    }
                    else
                    if(SearchProf(Proflist,ch[1])!=-1)
                    {
                        Proflist.get(SearchProf(Proflist,ch[1])).ShowProf();
                    }
                    else
                        System.out.println("PROFESSOR DOESN'T EXIST");

                    break;
                }
                case "SHOWC": {
                    if(ch.length!=2)
                    {
                        System.out.println("INVALID INPUT");
                    }
                    else
                    if(SearchCourse(Courselist,ch[1])!=-1)
                    {
                        Courselist.get(SearchCourse(Courselist,ch[1])).ShowCourse();
                    }
                    else
                        System.out.println("PROFESSOR DOESN'T EXIST");


                    break;
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------");

        }



    }
}
