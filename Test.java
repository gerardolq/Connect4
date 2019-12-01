import java.util.Scanner;
import CFCode.CFPlayer;
import CFCode.RandomAI;
import CFCode.GerardoAI;
import CFCode.ConsoleCF;
import CFCode.GUICF;


public class Test {
  public static void main(String[] args) {
    Scanner reader = new Scanner (System.in);
    int gameMode = reader.nextInt();
    
    if (gameMode==1) {
     new GUICF(new GerardoAI());
      //  new GUICF(new RandomAI());
    } else if (gameMode==2) {
      new GUICF();
      //new GUICF PvP
      }
    else {
      ConsoleCF game = new ConsoleCF(new GerardoAI());
        //ConsoleCF game = new ConsoleCF(new RandomAI());
      game.playOut();
      System.out.println(game.getWinner() + " has won.");
    } 
  }
}
