public interface IWeapon {

	// getter
	String getName();
	int getDmg();
	String getType();

	int getStrength();
	int getMagic();
	int getAgility();

	void setName(String name);
    void setDmg(int hp);
	void setType(String type);
	void setStrength(int strength);
	void setMagic(int magic);
	void setAgility(int agility);
	IWeapon weapon(String name, int dmg, String type, int strength, int magic, int agility);
}