package CFCode;
import java.util.Random;
public class RandomAI implements CFPlayer
{
    public int nextMove(CFGame g)
    {
        Random randCol = new Random();
        
        int Col = randCol.nextInt(7) + 1;
        
        return Col;
    }

    public String getName()
    {return ("Random Player");}
}
