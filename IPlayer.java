import java.util.List;
public interface IPlayer {
    ICharacter getCharacter();
    void setCharacter(ICharacter character);
    void doAction();
    void setEnnemy(List<ICharacter> ennemy);
    void setAlly(List<ICharacter> ally);
}