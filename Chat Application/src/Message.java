import java.util.ArrayList;

public class Message {
    int msgid;
    Entity sender;
    String msg;
    ArrayList<Message> reply;

    public void viewreply() {

        if (reply.size()!=0)
        {
            System.out.println("REPLIES FOR ORIGNAL MSG : ");
            for(int i =0;i<reply.size();i++)
            {
                reply.get(i).showmsg();
            }
        }
        else
            System.out.println("NO REPLIES FOR GIVEN MSG");

    }

    public void addreply(Message m) {
        this.reply.add(m);
    }

    public void showmsg() {
        System.out.println("ID: "+this.msgid+" SENDER: "+this.sender.username+" MESSAGE: "+this.msg);
    }


    Message(String m,Entity send,int mid)
    {
        msg= m;
        sender = send;
        msgid=mid;
        reply = new ArrayList<Message>();
    }


}
