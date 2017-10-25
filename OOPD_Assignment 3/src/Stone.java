public class Stone {
    private int strength;
    public String name;

    public Stone()
    {
        name = "Stone";
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

        if(o instanceof Stone) {
            System.out.println("Stone");
            temp = this.getStrength() - ((Stone) o).getStrength();
        }
        else if(o instanceof Scissor) {
            System.out.println("Scissor");
            temp = 2 * this.getStrength() - (int) 0.5 * ((Scissor) o).getStrength();
        }
        else {
            System.out.println("Paper");
            temp = (int) 0.5 * this.getStrength() - 2 * ((Paper) o).getStrength();
        }

        return Integer.compare(temp, 0);

    }
}
