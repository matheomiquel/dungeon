import java.lang.Math;

public class DiceRoller {

    static public int Dice2(int number) 
    {
        int dice = 0;
        for (int i = 0;i <= number;i++) {
            dice += (int)(Math.random() * ((2 - 1) + 1));
        }
		return (dice);
	}
}