import java.util.HashSet;
import java.util.Iterator;

public class User extends Entity {

    HashSet<Group>user_grp;

    @Override
    public void sendmsg(Message msg, Group g) {
        if(user_grp.contains(g))
        {
            g.messages.add(msg);
            System.out.println(this.username+" SENT MSG : '"+msg.msg+"' TO GROUP : "+g.groupname);
        }
        else
        {
            System.out.println("USER NOT PART OF GROUP");
        }

    }

    public void viewGroups()
    {
        System.out.println("USER OF : ");
        Iterator<Group> it = this.user_grp.iterator();
        int i =1;
        while(it.hasNext())
            System.out.println((i++)+" "+it.next().groupname);
    }

    @Override
    public void replymsg(Message msg,Group g ,int id) {
        if(g.isMsg(id)!=-1)
        {
                this.sendmsg(msg, g);
                g.messages.get(g.isMsg(id)).addreply(msg);

        }
        else
        {
            System.out.println("No such Msg in specified group");
        }


    }

    @Override
    public void join(Group g) {
        if(!this.user_grp.contains(g))
        {
            Requests r = new Requests(g,(Entity) this);
            for(int i =0;i<g.admin.request.size();i++)
            {
                if(g.admin.request.get(i).usr==(Entity)this&&g.admin.request.get(i).grp==g)
                {
                    System.out.println("REQUEST ALREADY EXISTS");
                    return;
                }
            }

            g.admin.request.add(r);
        }
        else
        {
            System.out.println("ENTITY ALREADY PART OF THE GROUP");
        }



    }

    @Override
    public void leave(Group g) {
        if(user_grp.contains(g))
        {
            g.members.remove(this);
            user_grp.remove(g);
            System.out.println("USER : "+this.username+" REMOVED FROM THE GROUP : "+g.groupname);
        }
        else
        {
            System.out.println("USER NOT A PART OF THIS GROUP");
        }
    }

    User(String user,String add, String number)
    {
        username = user;
        address=add;
        phone = number;
        user_grp=new HashSet<Group>();
    }

    public void share(Group g1,Group g2, int id,int msgid)
    {
        if(g1.isMember((Entity)this)&&g2.isMember((Entity)this))
        {
            if(g1.isMsg(id)!=-1)
            {
                Message temp = g1.messages.get(g1.isMsg(id));
                Message m = new Message(temp.msg,this,msgid);
                this.sendmsg(m,g2);
            }
            else
                System.out.println("NO SUCH MSG IN SOURCE GROUP");
        }
        else
            System.out.println("USER NOT PART OF GROUP");
    }
}
