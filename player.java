import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class player implements IPlayer
{
    private ICharacter character;
    private List<ICharacter> ennemy;
    private List<ICharacter> ally;

    public ICharacter getCharacter(){
        return this.character;
    }
    public void setCharacter(ICharacter character)
    {
        this.character = character;
    }
    
    public List<ICharacter> getEnnemy(){
        return this.ennemy;
    }

    public void setEnnemy(List<ICharacter> ennemy){
        this.ennemy = ennemy;
    }

    public List<ICharacter> getAlly(){
        return this.ally;
    }

    public void setAlly(List<ICharacter> ally){
        this.ally = ally;
    }

    

    public void doAction()
    {
        String input;
        int number = 0;
        character caract = new character();
        for (int i = 0; i < ally.size(); i++)
        System.out.println(ally.get(i).getName() + " vient d'entrer dans la pièce !");
        System.out.println(ennemy.size() + " ennemis lui bloque le chemin");
        while (ally.size() >= 1 && ennemy.size() >= 1) {
            while (number < 1 || number > ennemy.size())
                try {
                    System.out.println("qui voulez vous attaquez ?!");
                    for(int i = 0; i < ennemy.size(); i++)
                        System.out.println((i + 1)  + ") " + ennemy.get(i).getName());
                    System.out.println((ennemy.size() + 1) + ") pour voir les stats d'un personnage");
                    System.out.println((ennemy.size() + 2) + ") pour Utiliser vos PN!!!");
                    BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
                    input = test.readLine();
                    number = Integer.parseInt(input);
                    cleaner.clean();
                    if (number == ennemy.size() + 2)
                        chose_pn();
                    if (number > ennemy.size() + 2){
                        System.out.println("le monstre demander n'existe pas !!!");
                    }
                    if(number == ennemy.size() + 1)
                        stats();
                } catch (NumberFormatException ex) {
                    System.out.println("Vous n'avez pas mis un nombre !");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            for(int i = 0; i < ally.size(); i++)
                if(ennemy.size() >= 1)
                    caract.attack(getAlly().get(i), ennemy.get(number - 1), ennemy, number - 1);        
            for(int i = 0;i < ennemy.size();i++)
                if(ally.size() >= 1){
                    caract.attack(ennemy.get(i), ally.get((int)(Math.random() * ((ally.size() - 1) + 1))), ally, 0 );
                }
            number = 0;
        }
    }

    public void chose_pn()
    {
        String input = "";
        int number = 0;
        boolean continu = true;
        while (continu) {
            while(number < 1 || number > ally.size() + ennemy.size())
                try {
                System.out.println("De qui voulez vous augmentez les caracteristique?");
                for(int i = 0;i < ally.size();i++)
                    System.out.println((i + 1) + ") " + ally.get(i).getName());
                System.out.println((ally.size() + 1) + ") pour quitter ce menu");
                BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
                input = test.readLine();
                number = Integer.parseInt(input);
                if (number == ally.size() + 1){
                    cleaner.clean();
                    return;
                }
                if(number <= ally.size()){
                    print_stats(number - 1);
                    change_stat(number - 1);
                }
                } catch (NumberFormatException ex) {
                    System.out.println("Vous n'avez pas mis un nombre !");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("pour stopper les augmentations des stats mettez non sinon appuyer sur entrée");
                    BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
                    input = test.readLine();
                    cleaner.clean();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(input.contains("no"))
                continu = false;
            number = 0;
        }

    }
    
    public void print_stats(int index)
    {
        System.out.println("1) HP max : " + ally.get(index).getHp_max());
        System.out.println("2) MP max : " + ally.get(index).getMp_max());
        System.out.println("3) Force : " + ally.get(index).getStrength());
        System.out.println("4) Agiliter : " + ally.get(index).getAgility());
        System.out.println("5) Magie : " + ally.get(index).getMagic());
    }

    public void change_stat(int index){
        String input = "";
        int number = 0;
        int up = 0;
        while(number < 1 || number > 5)
            try {
            System.out.println("quel stats voulez vous augmentez?");
            BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
            input = test.readLine();
            number = Integer.parseInt(input);
            } catch (IOException e) {
                    e.printStackTrace();
            }
            while(up < 1 || up > ally.get(index).getPN())
                try {
                System.out.println("de combien?");
                BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
                input = test.readLine();
                up = Integer.parseInt(input);
                if (up > ally.get(index).getPN())
                    System.out.println("vous n'avez pas assez de points pour ça !!!");
                } catch (IOException e) {
                    e.printStackTrace();
            }
        if (number == 1) {
            ally.get(index).setHp_max(ally.get(index).getHp_max() + up);
            ally.get(index).setHp(ally.get(index).getHp_max());
        }

        else if (number == 2) {
            ally.get(index).setMp_max(ally.get(index).getMp_max() + up);
            ally.get(index).setMp(ally.get(index).getMp_max());
        }

        else if (number == 3) {
            ally.get(index).setStrength(ally.get(index).getStrength() + up);
            
        }

        else if (number == 4) {
            ally.get(index).setAgility(ally.get(index).getAgility() + up);
        }

        else if (number == 5) {
            ally.get(index).setMagic(ally.get(index).getMagic() + up);
        }
    }

    public void stats()
    {
        int number = 0;
        String input = "";
        boolean continu = true;
        while (continu) {
            while(number < 1 || number > ally.size() + ennemy.size())
                try {
                System.out.println("De qui voulez vous voire les caractéristiques?");
                for(int i = 0;i < ally.size();i++)
                    System.out.println((i + 1) + ") " + ally.get(i).getName());
                for(int i = 0;i < ennemy.size();i++)
                    System.out.println((i + ally.size() + 1) + ") " +ennemy.get(i).getName());
                System.out.println((ennemy.size() + ally.size() + 1) + ") pour quitter ce menu");
                BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
                input = test.readLine();
                number = Integer.parseInt(input);
                if (number == ennemy.size() + ally.size() + 1){
                    cleaner.clean();
                    return;
                }
                if(number <= ally.size() + ennemy.size())
                    print_stat(number);
                } catch (NumberFormatException ex) {
                    System.out.println("Vous n'avez pas mis un nombre !");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("pour stopper la visualisation des stats mettez non sinon appuyer sur entrée");
                    BufferedReader test = new BufferedReader(new InputStreamReader(System.in));
                    input = test.readLine();
                    cleaner.clean();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(input.contains("no"))
                continu = false;
            number = 0;
        }
    }

    public void print_stat(int number)
    {
        cleaner.clean();
        if(number <= ally.size())
            print_ally(number - 1);
        else
            print_ennemy(number - 1 - ally.size());
    }

    public void print_ally(int index)
    {
        System.out.println("Name : " + ally.get(index).getName());
        System.out.println("Experience : " + ally.get(index).getExp());
        System.out.println("Level : " + ally.get(index).getLvl());
        System.out.println("HP : " + ally.get(index).getHp());
        System.out.println("HP max : " + ally.get(index).getHp_max());
        System.out.println("MP : " + ally.get(index).getMp());
        System.out.println("MP max : " + ally.get(index).getMp_max());
        System.out.println("Force : " + ally.get(index).getStrength());
        System.out.println("Agiliter : " + ally.get(index).getAgility());
        System.out.println("Magie : " + ally.get(index).getMagic());
        System.out.println("Armur : " + ally.get(index).getArmor());
    }

    public void print_ennemy(int index)
    {
        System.out.println("Name : " + ennemy.get(index).getName());
        System.out.println("HP : " + ennemy.get(index).getHp());
        System.out.println("HP max : " + ennemy.get(index).getHp_max());
        System.out.println("MP : " + ennemy.get(index).getMp());
        System.out.println("MP max : " + ennemy.get(index).getMp_max());
        System.out.println("Force : " + ennemy.get(index).getStrength());
        System.out.println("Agiliter : " + ennemy.get(index).getAgility());
        System.out.println("Magie : " + ennemy.get(index).getMagic());
        System.out.println("Armur : " + ennemy.get(index).getArmor());
    }
}