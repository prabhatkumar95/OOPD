import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Group {

    String groupname;
    HashSet<Entity> members;
    ArrayList<Message> messages;
    Admin admin;

    Group(String gname,Admin ad)
    {
        admin = ad;
        groupname = gname;
        members = new HashSet<Entity>();
        messages = new ArrayList<Message>();
    }

    public void viewMessage()
    {
        for(int i = 0; i < messages.size();i++)
        {
            messages.get(i).showmsg();
        }
    }

    public void viewMembers()
    {
        System.out.println("ADMIN : "+admin.username);
        Iterator<Entity> it = this.members.iterator();
        int i =1;
        System.out.println("USERS : ");
        while(it.hasNext())
            System.out.println((i++)+" "+it.next().username);
    }

    public boolean isMember(Entity u )
    {
        return (members.contains(u)||u.username==this.admin.username) ;
    }


    public int isMsg(int id)
    {
        for(int i =0 ;i<messages.size();i++)
            if(id==messages.get(i).msgid)
            {
                return i;
            }
        return -1;

    }

    public void viewMsgReply(int id)
    {
        if(this.isMsg(id)!=-1)
        {
            System.out.println("ORIGINAL MSG : ");
            messages.get(this.isMsg(id)).showmsg();
            messages.get(this.isMsg(id)).viewreply();
        }
    }





}
