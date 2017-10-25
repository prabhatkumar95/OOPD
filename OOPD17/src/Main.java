import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        array_heap heap = new array_heap();
        String [] ch=null;

        List<String>lines = null;
        try {
            lines = Files.readAllLines(Paths.get("input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int k=0;k<lines.size();k++) {
            ch = lines.get(k).toUpperCase().split(" ");
            switch (ch[0]) {
                case "INSERT": {
                    String n1, n2;
                    if(ch.length<3)
                    {System.out.println("INVALID INPUT");break;}
                    System.out.print("INSERT");
                    n1 = ch[1];
                    n2 = ch[2];
                    System.out.println(" "+n1+" "+n2);
                    String[] inp = new String[ch.length - 3];
                    for (int i = 0; i < inp.length; i++) {
                        inp[i] = ch[3 + i];
                    }
                    heap.insertion(n1, n2, inp);
                    break;
                }

                case "MAXIMUM": {
                    System.out.println("MAXIMUM");

                    student temp;
                    temp = heap.find_max();
                    if (temp != null) {
                        System.out.println(temp.first + " " + temp.last);
                        System.out.print("COURSES : ");
                        for(int m=0;m<temp.courses.length;m++)
                            System.out.print(temp.courses[m]+"  ");

                    }
                    else
                        System.out.print("-1");
                    System.out.println();
                    break;
                }

                case "EXTRACT-MAX": {
                    System.out.println("EXTRACT-MAX");

                    student temp;
                    temp = heap.extract_max();
                    if (temp != null)
                    { System.out.println(temp.first + " " + temp.last);
                        System.out.print("COURSES : ");
                        for(int m=0;m<temp.courses.length;m++)
                            System.out.print(temp.courses[m]+"  ");
                        }
                    else
                        System.out.print("-1");
                    System.out.println();
                    break;
                }

                case "DELETE": {
                    System.out.println("DELETE");

                    String n1, n2;
                    if(ch.length<3)
                    {
                        System.out.println("INVALID INPUT");break;
                    }
                    n1 = ch[1];
                    n2 = ch[2];
                    student temp2;
                    temp2 = heap.delete(n1, n2);
                    if (temp2 == null)
                    {System.out.print("-1");
                        System.out.println();}
                    break;
                }
                case "SHOW": {
                    System.out.println("SHOW");

                    heap.print_heap();
                    break;
                }
            }
        System.out.println("-------------------------------------------------------------------------------------------");

        }

    }
}
