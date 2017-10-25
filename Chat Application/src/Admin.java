import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Admin extends Entity {


    HashSet<Group> admin_grp;
    HashSet<Group> user_grp;
    ArrayList<Requests> request;

    @Override
    public void sendmsg(Message msg,Group g) {
        if(admin_grp.contains(g)||user_grp.contains(g))
        {
            g.messages.add(msg);
            System.out.println(this.username+" SENT MSG : '"+msg.msg+"' TO GROUP : "+g.groupname);
        }
        else
        {
            System.out.println("ENTITY NOT A PART OF THE GROUP");
        }

    }

    public void respond(Entity username, Group groupname, char c)
    {
        for (int i =0;i<request.size();i++)
        {
            if(request.get(i).grp==groupname&&request.get(i).usr==username)
            {
                if(c=='1') {
                        if (username instanceof User) {
                            ((User) username).user_grp.add(groupname);
                            groupname.members.add(username);
                            System.out.println("ENTITY : "+username.username+" REQUEST TO JOIN GROUP : "+ groupname.groupname+" ACCEPTED");
                        } else {
                            ((Admin) username).user_grp.add(groupname);
                            groupname.members.add(username);
                            System.out.println("ENTITY : "+username.username+" REQUEST TO JOIN GROUP : "+ groupname.groupname+" ACCEPTED");
                        }
                        request.remove(i);
                        return;
                        }
                else
                    {
                        System.out.println("ENTITY : "+username.username+" REQUEST TO JOIN GROUP : "+ groupname.groupname+" DECLINED");
                        request.remove(i);
                        return;
                    }

            }

        else
        {
            System.out.println("NO SUCH REQUEST FOUND");
        }
    }
    }



    @Override
    public void replymsg(Message msg,Group g,int id) {
        if(g.isMsg(id)!=-1)
        {
            this.sendmsg(msg,g);
            g.messages.get(g.isMsg(id)).addreply(msg);

        }
        else
        {
            System.out.println("NO SUCH MSG IN SPECIFIED GROUP");
        }

    }

    @Override
    public void join(Group g) {
        if(!user_grp.contains(g)&& !admin_grp.contains(g))
        {
            Requests r = new Requests(g,(Entity) this);
            for(int i =0;i<g.admin.request.size();i++)
            {
                if(g.admin.request.get(i).usr==(Entity)this&&g.admin.request.get(i).grp==g)
                {
                    System.out.println("Request Already Exists");
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
            }
            else if(admin_grp.contains(g))
            {
                System.out.println("ADMIN CANNOT LEAVE THE GROUP");
            }
            else
            {
                System.out.println("USER NOT PART OF THIS GROUP");
            }
    }

    public Group creategrp(String gname)
    {
        Group g = new Group(gname,this);
        this.admin_grp.add(g);
        return g;
    }

    public void viewGroups()
    {

        System.out.println("ADMIN OF : ");
        Iterator<Group> it = this.admin_grp.iterator();
        int i =1;
        while(it.hasNext())
            System.out.println((i++)+" "+it.next().groupname);

        System.out.println("USER OF : ");
        it = this.user_grp.iterator();
        i =1;
        while(it.hasNext())
            System.out.println((i++)+" "+it.next().groupname);
    }

    public void deletegrp(Group group)
    {
        if(this.admin_grp.contains(group)) {
            Iterator<Entity> it = group.members.iterator();
            while(it.hasNext())
                ((User)it.next()).leave(group);

            for(int i =0;i<this.request.size();i++)
            {
                if(this.request.get(i).grp.groupname==group.groupname)
                    request.remove(i);
                    i--;
            }

            this.admin_grp.remove(group);
        }
        else
        {
            System.out.println("ADMIN : "+ this.username+ " DOESN'T MANAGE THE GROUP : "+group.groupname);
        }
    }

    Admin(String user, String add, String number)
    {
        username=user;
        address = add;
        phone = number;
        admin_grp = new HashSet<Group>();
        user_grp = new HashSet<Group>();
        request = new ArrayList<Requests>();
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
