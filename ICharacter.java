import java.util.List;
public interface ICharacter {

	// getter
	String getName();
	int getExp();
	int getLvl();
	int getHp();
	int getHp_max();
	int getMp();
	int getMp_max();
	int getArmor();
	int getPN();
	int getStrength();
	int getMagic();
	int getAgility();
	Boolean getAlly();

	IWeapon getWeapon();

	// setter
    void setPN(int PN);
    void setName(String name);
    void setHp(int hp);
    void setMp(int mp);
    void setArmor(int armor);
    void equip(IWeapon weapon);
	void setExp(int exp);
	void setLvl(int lvl);
    void attack(ICharacter attacker , ICharacter target, List<ICharacter> ennemy, int index);
	int defend(ICharacter target);
	void setHp_max(int hp_max) ;
	void setStrength(int strength);
	void setAgility(int aglility);
	void setMagic(int magic);


    void setMp_max(int mp_max);

	ICharacter caract(String name,int exp, int lvl, int hp, int hp_max, int mp, int mp_max, int strength, int aglility , int magic, int armor, Boolean ally,int PN, IWeapon weapon);
}