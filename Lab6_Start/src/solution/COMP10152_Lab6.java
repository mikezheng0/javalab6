package solution;

import battleship.BattleShip;
import java.awt.Point;
import java.util.Random;


/**
 * A program that will run a battleship solution.  
 * 
 * It is suggested that you make a copy of the startingSolution method and build a class 
 * that can be used to solve the problem.
 * 
 * See lab text information for steps to building a solution.
 * 
 * @author mark.yendt
 */
public class COMP10152_Lab6
{
  static final int NUMBEROFGAMES = 10000;
   
  public static void startingSolution()
  {
    long start = System.nanoTime();
    int totalShots = 0;
    boolean searchMode = true;
    for (int game = 0; game < NUMBEROFGAMES; game++) {

      BattleShip battleShip = new BattleShip();
      Random r = new Random();

      // Fire randomly - Does not consider repeat shots
      
      while (!battleShip.allSunk()) // You will always need a loop that looks like this
      {
        Point shot = new Point(0,0);
        // All of your logic must occur here  
        if (searchMode){
            shot = new Point(battleShip.BOARDSIZE/2, battleShip.BOARDSIZE/2);
            if(battleShip.shoot(shot)){
                searchMode = false;
            }
        }
        else{
            
        }
        // At the end of each decision on where to fire next you need to shoot
      }
      
      // When you are finished you can ask the battleship API how many shots you have taken.
      int gameShots = battleShip.totalShotsTaken();
      totalShots += gameShots;
    }
    long timeRequired = (System.nanoTime() - start) / 1000000;
    // Report your results. 
    System.out.printf("Code Provided           - The Average = %.2f time = %8d ms\n", (double) totalShots / NUMBEROFGAMES, timeRequired);
  }
  
  public static void main(String[] args)
  {
      System.out.println(BattleShip.version());  // Leave this here - Confirm Version # - Nov 26,2014
      
      // Call you solution  
      startingSolution();
  }
}
