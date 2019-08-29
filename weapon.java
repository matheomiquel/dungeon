public class weapon implements IWeapon
{
    private String name;
	private int dmg;
    private String type;
	private int strength;
	private int magic;
    private int agility;

    public String getName() {
        return this.name;
    }

    public int getDmg() {
        return this.dmg;
    }

    public String getType() {
        return this.type;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getMagic() {
        return this.magic;
    }

    public int getAgility() {
        return this.agility;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public void setDmg(int dmg) { 
        this.dmg = dmg;
    }

    public void setType(String type) { 
        this.type = type;
    }

    public void setStrength(int strength) { 
        this.strength = strength;
    }

    public void setMagic(int magic) { 
        this.magic = magic;
    }


    public void setAgility(int agility) { 
        this.agility = agility;
    }

    public IWeapon weapon(String name, int dmg, String type, int strength, int magic, int agility){
        this.name = name;
        this.dmg = dmg;
        this.type = type;
        this.strength = strength;
        this.magic = magic;
        this.agility = agility;
        return this;
    }
}