import java.util.ArrayList;
public class main 
{
    public static void main(String args[]) 
    {
        ArrayList<ICharacter> ally = new ArrayList<ICharacter>();
        ArrayList<ICharacter> ennemy = new ArrayList<ICharacter>();
        weapon armes = new weapon();
        
        for(int i = 0;i < 5;i++){
            character aventurier = new character();
            aventurier.caract("Mathéo", 0 ,1, 100, 100, 100, 100, 100, 100, 100, 100, true,20, armes.weapon("tueur de dieu", 50, "strength", 10, 10 ,10));

            ally.add(aventurier);
        }
        for(int i = 0;i < 5;i++){
            character aventurier = new character();
            aventurier.caract("lucile", 0 ,1, 100, 100, 100, 100, 100, 100, 100, 100, false,0, null);
            ennemy.add(aventurier);
        }
        player play = new player();
        play.setAlly(ally);
        play.setEnnemy(ennemy);
        play.doAction();
    }
}