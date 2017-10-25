public abstract class Entity {

    String username;
    String address;
    String phone;

    public abstract void sendmsg(Message msg,Group g);
    public abstract void replymsg(Message msg, Group g,int id);
    public abstract void join(Group g);
    public abstract void leave(Group g);
    public abstract void share(Group g1,Group g2,int id,int msgid);
    public abstract void viewGroups();



}
