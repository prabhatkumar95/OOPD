public class Paper {

    private int strength;
    public String name;

    public Paper()
    {
        name = "Paper";
        strength=0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int battle(Object o)
    {
        int temp;

        if(o instanceof Paper) {
            System.out.println("Paper");
            temp = this.getStrength() - ((Paper) o).getStrength();
        }
        else if(o instanceof Stone) {
            System.out.println("Stone");
            temp = 2 * this.getStrength() - (int) 0.5 * ((Stone) o).getStrength();
        }
        else {
            System.out.println("Scissor");
            temp = (int) 0.5 * this.getStrength() - 2 * ((Scissor) o).getStrength();
        }

        return Integer.compare(temp, 0);
    }

}
