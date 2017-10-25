import javax.jws.soap.SOAPBinding;
import javax.rmi.CORBA.StubDelegate;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class main {


    public static int  SearchGroup(ArrayList<Group>a,String name )
    {
        for(int i =0;i<a.size();i++)
        {
            if(a.get(i).groupname.compareTo(name)==0) {
                return i;
            }
        }

        return -1;

    }
    public static int SearchAdmin(ArrayList<Admin>a,String name)
    {
        for(int i =0;i<a.size();i++)
        {
            if(a.get(i).username.compareTo(name)==0 )
                return i;
        }

        return -1;

    }

    public static int SearchUser(ArrayList<User>a,String name)
    {
        for(int i =0;i<a.size();i++)
        {
            if(a.get(i).username.compareTo(name)==0)
                return i;
        }

        return -1;

    }





    public static void main(String args[]) {
        int msgid =1;
        ArrayList<User> Userlist = new ArrayList<User>();
        ArrayList<Admin> Adminlist = new ArrayList<Admin>();
        ArrayList<Group> Grouplist = new ArrayList<Group>();
        String[] ch = null;

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int k = 0; k < lines.size(); k++) {
            ch = lines.get(k).toUpperCase().split(" ");
            switch (ch[0]) {
                case "ADDU": {
                    if(ch.length<4)
                        System.out.println("INVALID INPUT");
                    else {
                        if (SearchUser(Userlist, ch[1])==-1 && SearchAdmin(Adminlist,ch[1])==-1) {
                            String no = ch[2];
                            String username = ch[1];
                            String address = "";
                            for (int i=3;i<ch.length;i++ ) {
                                address = address + " " + ch[i];
                            }
                            User temp = new User(username,address,no);
                            Userlist.add(temp);
                            System.out.println("USER : "+temp.username+" ADDED TO THE SYSTEM");
                        }
                        else
                            System.out.println("ENTITY ALREADY REGISTERED IN THE SYSTEM ");
                    }
                    break;
                }




                case "ADDA": {
                    if(ch.length<4)
                        System.out.println("INVALID INPUT");
                    else {
                        if (SearchUser(Userlist, ch[1])==-1 && SearchAdmin(Adminlist,ch[1])==-1) {
                            String no = ch[2];
                            String username = ch[1];
                            String address = "";
                            for (int i=3;i<ch.length;i++ ) {
                                address = address + " " + ch[i];
                            }
                            Admin temp = new Admin(username,address,no);
                            Adminlist.add(temp);
                            System.out.println("ADMIN : "+temp.username+" ADDED TO THE SYSTEM");
                        }
                        else
                            System.out.println("ENTITY ALREADY REGISTERED IN THE SYSTEM ");
                    }
                    break;

                }

                case "ADDG": {
                    if(ch.length<3)
                        System.out.println("INVALID INPUT");
                    else
                    {
                        if(SearchAdmin(Adminlist,ch[2])==-1)
                        {
                            System.out.println("NO SUCH ADMIN IN SYSTEM");
                        }
                        else if(SearchGroup(Grouplist,ch[1])>-1)
                        {
                            System.out.println("GROUP WITH SAME NAME ALREADY EXISTS");
                        }

                        else
                        {
                            int admin = SearchAdmin(Adminlist,ch[2]);
                            Group temp = Adminlist.get(admin).creategrp(ch[1]);
                            Grouplist.add(temp);

                            System.out.println("GROUP : "+temp.groupname+" ADDED TO THE SYSTEM WITH ADMIN : "+temp.admin.username);
                        }
                    }
                break;
                }

                case "SNDMSG": {

                                if(ch.length<4)
                                {
                                    System.out.println("INVALID INPUT");
                                }
                                else
                                {
                                    if(SearchGroup(Grouplist,ch[1])==-1)
                                    {
                                        System.out.println("NO SUCH GROUP EXIST IN THE SYSTEM");
                                    }
                                    else if(SearchUser(Userlist,ch[2])!=-1)
                                    {
                                        int grp =SearchGroup(Grouplist,ch[1]);
                                        int usr = SearchUser(Userlist,ch[2]);
                                        String msg = "";
                                        for (int i = 3;i<ch.length;i++)
                                            msg+=(ch[i]+" ");

                                        Message temp = new Message(msg,(Entity)Userlist.get(usr),msgid);
                                        msgid++;
                                        Userlist.get(usr).sendmsg(temp,Grouplist.get(grp));
                                    }
                                    else if(SearchAdmin(Adminlist,ch[2])!=-1)
                                    {
                                        int grp =SearchGroup(Grouplist,ch[1]);
                                        int admin = SearchAdmin(Adminlist,ch[2]);
                                        String msg = "";
                                        for (int i = 3;i<ch.length;i++)
                                            msg+=(ch[i]+" ");

                                        Message temp = new Message(msg,(Entity)Adminlist.get(admin),msgid);
                                        msgid++;
                                        Adminlist.get(admin).sendmsg(temp,Grouplist.get(grp));

                                    }
                                    else
                                    {
                                        System.out.println("NO SUCH ENTITY EXIST IN THE SYSTEM");

                                    }
                                }
                                break;
                }

                case "REPLYMSG": {

                    if(ch.length<5)
                    {
                        System.out.println("INVALID INPUT");
                    }
                    else
                    {
                        if(SearchGroup(Grouplist,ch[1])==-1)
                        {
                            System.out.println("NO SUCH GROUP EXIST IN THE SYSTEM");
                        }
                        else if(SearchUser(Userlist,ch[2])!=-1)
                        {
                            int grp =SearchGroup(Grouplist,ch[1]);
                            int usr = SearchUser(Userlist,ch[2]);
                            String msg = "";
                            for (int i = 4;i<ch.length;i++)
                                msg+=(ch[i]+" ");

                            Message temp = new Message(msg,(Entity)Userlist.get(usr),msgid);
                            msgid++;

                            int i = Integer.parseInt(ch[3]);
                            Userlist.get(usr).replymsg(temp,Grouplist.get(grp),i);
                        }
                        else if(SearchAdmin(Adminlist,ch[2])!=-1)
                        {
                            int grp =SearchGroup(Grouplist,ch[1]);
                            int admin = SearchAdmin(Adminlist,ch[2]);
                            String msg = "";
                            for (int i = 4;i<ch.length;i++)
                                msg+=(ch[i]+" ");

                            Message temp = new Message(msg,(Entity)Adminlist.get(admin),msgid);
                            msgid++;
                            int i = Integer.parseInt(ch[3]);
                            Adminlist.get(admin).replymsg(temp,Grouplist.get(grp),i);

                        }
                        else
                        {
                            System.out.println("NO SUCH ENTITY EXIST IN THE SYSTEM");

                        }
                    }
                    break;


                }

                case "VIEWMSG": {
                                 if (SearchGroup(Grouplist, ch[1]) == -1) {
                                        System.out.println("NO SUCH GROUP EXIST IN THE SYSTEM");
                                 }
                                 else if (SearchUser(Userlist, ch[2]) != -1) {
                                     int grp =SearchGroup(Grouplist,ch[1]);
                                     int usr = SearchUser(Userlist,ch[2]);

                                     if(Grouplist.get(grp).isMember((Entity) Userlist.get(usr)))
                                     {
                                         Grouplist.get(grp).viewMessage();
                                     }
                                     else
                                     {
                                         System.out.println("GIVEN ENTITY : "+ Userlist.get(usr).username + " NOT A PART OF GROUP : "+ Grouplist.get(grp).groupname );
                                     }


                                 }
                                 else if(SearchAdmin(Adminlist,ch[2])!=-1)
                                 {
                                     int grp =SearchGroup(Grouplist,ch[1]);
                                     int admin = SearchAdmin(Adminlist,ch[2]);

                                     if(Grouplist.get(grp).isMember((Entity) Adminlist.get(admin)))
                                     {
                                         Grouplist.get(grp).viewMessage();
                                     }
                                     else
                                     {
                                         System.out.println("GIVEN ENTITY : "+ Adminlist.get(admin).username + " NOT A PART OF GROUP : "+ Grouplist.get(grp).groupname );
                                     }

                                 }
                    break;
                }

                case "JOIN": {
                                if(ch.length<3)
                                {
                                    System.out.println("INVALID INPUT");
                                }
                                else
                                {
                                    if(SearchGroup(Grouplist,ch[1])==-1)
                                    {
                                        System.out.println("NO SUCH GROUP EXISTS IN THE SYSTEM");
                                    }
                                    else if(SearchUser(Userlist,ch[2])!=-1)
                                    {
                                        int group = SearchGroup(Grouplist,ch[1]);
                                        int user = SearchUser(Userlist,ch[2]);
                                        System.out.println("REQUEST SENT BY USER : "+Userlist.get(user).username + " TO JOIN GROUP : "+Grouplist.get(group).groupname+ " WILL BE APPROVED BY ADMIN : "+Grouplist.get(group).admin.username);
                                        Userlist.get(user).join(Grouplist.get(group));

                                       }
                                    else if(SearchAdmin(Adminlist,ch[2])!=-1)
                                    {
                                        int group = SearchGroup(Grouplist,ch[1]);
                                        int admin = SearchAdmin(Adminlist,ch[2]);
                                        System.out.println("REQUEST SENT BY ADMIN: "+Adminlist.get(admin).username + " TO JOIN GROUP : "+Grouplist.get(group).groupname+ " WILL BE APPROVED BY ADMIN : "+Grouplist.get(group).admin.username);
                                        Adminlist.get(admin).join(Grouplist.get(group));

                                    }

                                    else
                                        System.out.println("NO SUCH ENTITY EXIST IN THE SYSTEM");
                                    }
                                break;
                                }


                case "LEAVE": {
                                if(ch.length<3)
                                {
                                    System.out.println("INVALID INPUT");

                                }
                                else
                                {
                                    if(SearchGroup(Grouplist,ch[1])==-1)
                                    {
                                        System.out.println("NO SUCH GROUP EXISTS IN THE SYSTEM");

                                    }
                                    else if (SearchUser(Userlist,ch[2])!=-1)
                                    {
                                        int group = SearchGroup(Grouplist,ch[1]);
                                        int user = SearchUser(Userlist,ch[2]);
                                        Userlist.get(user).leave(Grouplist.get(group));
                                    }
                                    else if(SearchAdmin(Adminlist,ch[2])!=-1)
                                    {
                                        int group = SearchGroup(Grouplist,ch[1]);
                                        int admin = SearchAdmin(Adminlist,ch[2]);
                                        Adminlist.get(admin).leave(Grouplist.get(group));

                                    }
                                    else
                                        System.out.println("NO SUCH ENTITY EXISTS IN THE SYSTEM");

                                }
                                break;
                }

                case "DELETEG": {
                                if(ch.length<3)
                                {
                                    System.out.println("INVALID INPUT");
                                }
                                else
                                {
                                    if(SearchGroup(Grouplist,ch[1])==-1)
                                    {
                                        System.out.println("NO SUCH GROUP EXISTS IN THE SYSTEM");
                                    }
                                    else if(SearchAdmin(Adminlist,ch[2])!=-1)
                                    {
                                        int grp = SearchGroup(Grouplist,ch[1]);
                                        int admin = SearchAdmin(Adminlist,ch[2]);

                                        Adminlist.get(admin).deletegrp(Grouplist.get(grp));
                                        System.out.println("GROUP : "+Grouplist.get(grp).groupname + " DELETED BY ADMIN : "+Adminlist.get(admin).username);
                                        Grouplist.remove(grp);
                                    }
                                    else
                                    {
                                        System.out.println("NO SUCH ADMIN IN SYSTEM");
                                    }
                                }
                                break;
                }

                case "SHAREMSG": {
                                    if(ch.length<5)
                                        System.out.println("INVALID INPUT");

                                    else
                                    {
                                        if(SearchGroup(Grouplist,ch[1])==-1 || SearchGroup(Grouplist,ch[2])==-1)
                                        {
                                            System.out.println("GROUP DOESNOT EXIST IN THE SYSTEM");
                                        }
                                        else if (SearchUser(Userlist,ch[3])!=-1)
                                        {
                                            int grp1 =SearchGroup(Grouplist,ch[1]);
                                            int grp2 = SearchGroup(Grouplist,ch[2]);
                                            int usr = SearchUser(Userlist,ch[3]);
                                            int id = Integer.parseInt(ch[4]);

                                            if(Grouplist.get(grp1).isMember((Entity)Userlist.get(usr))&&Grouplist.get(grp2).isMember((Entity)Userlist.get(usr)))
                                            {
                                                Userlist.get(usr).share(Grouplist.get(grp1),Grouplist.get(grp2),id,msgid);
                                                msgid++;
                                            }
                                            else
                                            {
                                                System.out.println("ENTITY NOT PART OF GROUP");
                                            }
                                        }
                                        else if(SearchAdmin(Adminlist,ch[3])!=-1)
                                        {
                                            int grp1 =SearchGroup(Grouplist,ch[1]);
                                            int grp2 = SearchGroup(Grouplist,ch[2]);
                                            int admin = SearchAdmin(Adminlist,ch[3]);
                                            int id = Integer.parseInt(ch[4]);

                                            if(Grouplist.get(grp1).isMember((Entity)Adminlist.get(admin))&&Grouplist.get(grp2).isMember((Entity)Adminlist.get(admin)))
                                            {
                                                Adminlist.get(admin).share(Grouplist.get(grp1),Grouplist.get(grp2),id,msgid);
                                                msgid++;
                                            }
                                            else
                                            {
                                                System.out.println("ENTITY NOT PART OF GROUP");
                                            }
                                        }
                                        else
                                            System.out.println("NO SUCH ENTITY EXIST IN THE SYSTEM");
                                    }
                                    break;

                }

                case "RESPOND" : {
                                if(ch.length<4)
                                    System.out.println("INVALID INPUT");
                                else
                                {
                                    if(SearchGroup(Grouplist,ch[2])==-1)
                                        System.out.println("GROUP DOESNOT EXIST IN THE SYSTEM");
                                    else
                                    {
                                        char c = ch[3].charAt(0);
                                        int grp = SearchGroup(Grouplist,ch[2]);
                                        if(SearchUser(Userlist,ch[1])!=-1)
                                        {

                                            int usr = SearchUser(Userlist,ch[1]);
                                            Grouplist.get(grp).admin.respond((Entity)Userlist.get(usr),Grouplist.get(grp),c);

                                        }

                                        else if(SearchAdmin(Adminlist,ch[1])!=-1)
                                        {

                                            int usr = SearchAdmin(Adminlist,ch[1]);
                                            Grouplist.get(grp).admin.respond((Entity)Adminlist.get(usr),Grouplist.get(grp),c);

                                        }
                                        else
                                            System.out.println("NO SUCH ENTITY IN SYSTEM");
                                    }
                                }
                                break;
                }

                case "VIEWGRP" : {
                                    if(ch.length<2)
                                    {
                                        System.out.println("INVALID INPUT");
                                    }
                                    else if (SearchAdmin(Adminlist,ch[1])!=-1)
                                    {
                                        Adminlist.get(SearchAdmin(Adminlist,ch[1])).viewGroups();
                                    }
                                    else if(SearchUser(Userlist,ch[1])!=-1)
                                    {
                                        Userlist.get(SearchUser(Userlist,ch[1])).viewGroups();
                                    }
                                    else
                                        System.out.println("ENTITY DOESNOT EXIST IN SYSTEM");
                                    break;

                }

                case "VIEWUSER" : {
                                    if(ch.length<2)
                                        System.out.println("INVALID INPUT");
                                    else if(SearchGroup(Grouplist,ch[1])!=-1)
                                    {
                                        Grouplist.get(SearchGroup(Grouplist,ch[1])).viewMembers();
                                    }
                                    else
                                        System.out.println("NO SUCH GROUP EXIST IN THE SYSTEM");

                                    break;
                }

                case "VIEWREPLY" : {
                                    if(ch.length<4)
                                    {
                                        System.out.println("INVALID INPUT");
                                    }
                                    else if(SearchGroup(Grouplist,ch[1])==-1)
                                        System.out.println("NO SUCH GROUP EXIST IN THE SYSTEM");
                                    else if(SearchUser(Userlist,ch[2])!=-1)
                                    {
                                        int grp = SearchGroup(Grouplist,ch[1]);
                                        int usr = SearchUser(Userlist,ch[2]);
                                        int id  = Integer.parseInt(ch[3]);
                                        if(Grouplist.get(grp).isMember((Entity)Userlist.get(usr)))
                                        {
                                            Grouplist.get(grp).viewMsgReply(id);
                                        }
                                        else
                                        {
                                            System.out.println("USER NOT A MEMBER OF GIVEN GROUP");
                                        }
                                    }
                                    else if(SearchAdmin(Adminlist,ch[2])!=-1)
                                    {
                                        int grp = SearchGroup(Grouplist,ch[1]);
                                        int admin = SearchAdmin(Adminlist,ch[2]);
                                        int id  = Integer.parseInt(ch[3]);
                                        if(Grouplist.get(grp).isMember((Entity)Adminlist.get(admin)))
                                        {
                                            Grouplist.get(grp).viewMsgReply(id);
                                        }
                                        else
                                        {
                                            System.out.println("USER NOT A MEMBER OF GIVEN GROUP");
                                        }
                                    }

                }
                break;
            }


            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
        }
    }
}
