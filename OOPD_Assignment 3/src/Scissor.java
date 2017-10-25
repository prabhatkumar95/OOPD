public class Scissor {
    private int strength;
    public String name;

    public Scissor()
    {
        name= "Scissor";
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

        if(o instanceof Scissor) {
            System.out.println("Scissor");
            temp = this.getStrength() - ((Scissor) o).getStrength();
        }
        else if(o instanceof Paper) {
            System.out.println("Paper");
            temp = 2 * this.getStrength() - (int) 0.5 * ((Paper) o).getStrength();
        }
        else {
            System.out.println("Stone");
            temp = (int) 0.5 * this.getStrength() - 2 * ((Stone) o).getStrength();
        }

        return Integer.compare(temp, 0);

    }
}
