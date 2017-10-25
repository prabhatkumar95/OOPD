public class Team {

    Paper paper;
    Stone stone;
    Scissor scissor;

    Team ()
    {
        paper= new Paper();
        stone = new Stone();
        scissor= new Scissor();
    }


    public Object action(int i)
    {
        if(i==1)
            return this.stone;
        else if(i==2)
            return this.scissor;
        else
            return this.paper;
    }
}
