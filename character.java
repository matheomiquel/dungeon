import java.util.List;

public class character implements ICharacter
{
    private String name;
    private int lvl;
    private int exp;
    private int hp;
    private int hp_max;
    private int mp;
    private int mp_max;
    private int armor; 
    private int strength;
    private int aglility;
    private int magic;
    private boolean ally;
    private int PN;
    private IWeapon weapon;
    {

    }

    //getter
    public int getPN(){
        return this.PN;
    }
    public String getName() {
        return this.name;
    }

    public int getExp() {
        return this.exp;
    }

    public int getLvl()
    {
        return this.lvl;
    }

    public int getHp() {
        return this.hp;
    }
    public int getHp_max() {
        return this.hp_max;
    }
    public int getMp() {
        return this.mp;
    }
    public int getMp_max() {
        return this.mp_max;
    }
    public int getArmor() {
        return this.armor;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAgility() {
        return this.aglility;
    }

    public int getMagic() {
        return this.magic;
    }

    public IWeapon getWeapon() {
        return this.weapon;
    }

    public Boolean getAlly(){
        return this.ally;
    }

    //setter
    public void setPN(int PN){
        this.PN = PN;
    }
    public void setName(String name) { 
        this.name = name;
    }

    public void setExp(int exp) { 
        this.exp = exp;
    }

    public void setLvl(int lvl) 
    {
        this.lvl = lvl;
    }

    public void setHp(int hp) { 
        this.hp = hp;
    }
    public void setHp_max(int hp_max) { 
        this.hp_max = hp_max;
    }

    public void setMp(int mp) { 
        this.mp = mp;
    }

    public void setMp_max(int mp_max) { 
        this.mp_max = mp_max;
    }

    public void setArmor(int armor) { 
        this.armor = armor;
    }

    public void setStrength(int strength) { 
        this.strength = strength;
    }

    public void setAgility(int aglility) { 
        this.aglility = aglility;
    }

    public void setMagic(int magic) { 
        this.magic = magic;
    }

    public void equip(IWeapon weapon) { 
        this.weapon = weapon;
    }


    public ICharacter caract(String name,int exp, int lvl, int hp, int hp_max, int mp, int mp_max, int strength, int aglility , int magic, int armor, Boolean ally,int PN, IWeapon weapon){
        this.name = name;
        this.exp = exp;
        this.lvl = lvl;
        this.hp = hp;
        this.hp_max = hp_max;
        this.mp = mp;
        this.mp_max = mp;
        this.strength = strength;
        this.aglility = aglility;
        this.magic = magic;
        this.armor = armor;
        this.ally = ally;
        this.PN = PN;
        this.weapon = weapon;
        return this;
    }


    public void attack(ICharacter attacker , ICharacter target, List<ICharacter> méchant, int index)
    {
        DiceRoller dice = new DiceRoller();
        weapon armes = new weapon();
        int succes;
        int dammage;
        int dodge;
        boolean magic_weapon; 
        System.out.println(attacker.getName() + " attaque " + target.getName());
        
        if (attacker.getWeapon() == null)
        attacker.equip(armes.weapon("hand", 0, "strength", 0,0,0));
        magic_weapon = attacker.getWeapon().getType() == "magic" ? true : false;
        if (attacker.getWeapon().getType() == "magic")
            if ( attacker.getMp() <  attacker.getWeapon().getDmg() ) {
                System.out.println("vous n'avez pas assez de mana pour lancer votre attaque, attendez le tour suivant");
                return;
            }
        
        if (magic_weapon) {
            attacker.setMp(attacker.getMp() - attacker.getWeapon().getDmg());
        }
        succes = dice.Dice2(attacker.getStrength());
        if (succes == 0) {
            System.out.println("l'attaque à échouer !!!");
            return;
        }
        dodge = defend(target);
        dammage = succes + attacker.getWeapon().getDmg();
        calcule_dammage(target, dammage, dodge, magic_weapon);
        regen_mana(attacker);
        if (target.getHp() <= 0) {
            System.out.println(target.getName() + " est mort");
            experience(attacker, target);
            méchant.remove(index);
        }

    }

    public void experience(ICharacter attack, ICharacter target) 
    {
        int xp;
        if (!attack.getAlly())
            return;
        xp = target.getHp_max() < target.getMp_max() ? target.getMp_max() : target.getHp_max();
        if (target.getAgility() < target.getMagic())
            xp += target.getMagic() < target.getStrength() ? target.getStrength() : target.getMagic();
        else
            xp += target.getAgility() < target.getStrength() ? target.getStrength() : target.getAgility();
        for (int i = 0;i < xp;i++) {
            attack.setExp(attack.getExp() + 1);
            if (attack.getExp() == (attack.getLvl() * (attack.getLvl() + 3))) {
                attack.setLvl(attack.getLvl() + 1);
                attack.setPN(attack.getPN() + 5);
                attack.setExp(0);
                System.out.println("bravo vous avez lvl up!!!");
            }
        }
    }

    public int defend(ICharacter target)
    {
        DiceRoller dice = new DiceRoller();
        return dice.Dice2(target.getAgility());
    }

    public void calcule_dammage(ICharacter target, int dammage, int dodge, boolean magic_weapon)
    {
        int dammage_deal;
        if (dammage < dodge) {
            System.out.println(target.getName() + " a réussi a esquiver!!!");
            return;
        }
        else if (dammage == dodge) {
            if (magic_weapon)
                dammage_deal = (dammage / 2) - target.getArmor();
            else          
                dammage_deal = (dammage / 2);
        } else {
            if (magic_weapon)
                dammage_deal = dammage - target.getArmor();
            else          
                dammage_deal = dammage;
        }
        System.out.println("l'attaque a fait : " + dammage_deal  + " dégats !!!");
        target.setHp(target.getHp() - dammage_deal);
    }

    public void regen_mana(ICharacter attacker)
    {
        for(int i = 0; i < attacker.getMagic() / 2; i++)
            if(attacker.getMp_max() > attacker.getMp())
                attacker.setMp(attacker.getMp() + 1);
    }
}