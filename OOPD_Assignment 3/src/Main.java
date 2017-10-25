import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {



    public static void main(String args[])
    {
        Team A = new Team();
        Team B = new Team();
        int a_ts=0;
        int b_ts=0;
        Random r = new Random();
        int st;
        st = r.nextInt(75)+25;
        A.stone.setStrength(st);
        System.out.println("A's Stone Strength : "+st);
        st = r.nextInt(75)+25;
        B.stone.setStrength(st);
        System.out.println("B's Stone Strength : "+st);
        st = r.nextInt(75)+25;
        A.paper.setStrength(st);
        System.out.println("A's Paper Strength : "+st);
        st = r.nextInt(75)+25;
        B.paper.setStrength(st);
        System.out.println("B's Paper Strength : "+st);
        st = r.nextInt(75)+25;
        A.scissor.setStrength(st);
        System.out.println("A's Scissor Strength : "+st);
        st = r.nextInt(75)+25;
        B.scissor.setStrength(st);
        System.out.println("B's Scissor Strength : "+st);

        for (int i =1;i<=20;i++)
        {
            int a_rs=0;
            int b_rs=0;

            Integer [] A_choice = {1,2,3};
            Integer [] B_choice = {1,2,3};


            System.out.println();
            System.out.println();
            System.out.println("\t\tRound : "+i+"\t\t");



            Collections.shuffle(Arrays.asList(A_choice));
            Collections.shuffle(Arrays.asList(B_choice));

            for(int j =0;j<3;j++)
            {
                int temp=0;
                Object a  = A.action(A_choice[j]);
                if(a instanceof Stone) {
                    System.out.print("Stone\t");
                    temp = ((Stone) a).battle(B.action(B_choice[j]));
                    }

                else if(a instanceof Paper) {
                System.out.print("Paper\t");
                temp = ((Paper) a).battle(B.action(B_choice[j]));
                }

                else {
                    System.out.print("Scissor\t");
                    temp = ((Scissor) a).battle(B.action(B_choice[j]));
                }
                if(temp>0)
                    a_rs++;
                if(temp<0)
                    b_rs++;

                if(a_rs>=2||b_rs>=2)
                    break;

            }

            System.out.println("Round Score A : "+ a_rs);
            System.out.println("Round Score B : "+b_rs);

            if(a_rs>b_rs)
                a_ts++;
            else if (a_rs<b_rs)
                b_ts++;


            System.out.println("Total Score A : "+ a_ts);
            System.out.println("Total Score B : "+b_ts);
            System.out.println("------------------------------------------------------------------------");

        }
    }
}
