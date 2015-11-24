package solution;

import battleship.BattleShip;
import java.awt.Point;
import java.util.ArrayList;
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
  static final int NUMBEROFGAMES = 1;
   
  public static void mySolution()
  {
    long start = System.nanoTime();
    int totalShots = 0;
    boolean searchMode = true;
    boolean isFound = false;
    int currentSpot = 0 ;
    for (int game = 0; game < NUMBEROFGAMES; game++) {

      BattleShip battleShip = new BattleShip();
      Random r = new Random();
      ArrayList<Integer> board = new ArrayList<Integer>();
      
      for(int i =0; i<=99;i++)
      {
          board.add(i);
      }
      // Fire randomly - Does not consider repeat shots
      
      while (!battleShip.allSunk()) // You will always need a loop that looks like this
      {
          //System.out.println(battleShip.numberOfShipsSunk());
        Point shot;
        System.out.print(board);
        // All of your logic must occur here  
        if (searchMode){
            int newSpot=0;
            int index = 0;
            System.out.println(board.size());
            if(board.size()>=0)
                index = r.nextInt(board.size());
            newSpot = board.remove(index);
            shot = createPoint(newSpot);
            if(battleShip.shoot(shot)){
                searchMode = false;
                isFound = false;
                currentSpot = newSpot;
            }
        }
        else{
            int currentShipCount = battleShip.numberOfShipsSunk();
            int newSpot=0;
            boolean isEnd = true;
            
            System.out.println("hitmode");
            if(FindAbove(currentSpot) != -1 && board.contains((Integer)FindAbove(newSpot)) && battleShip.shoot(createPoint(FindAbove(currentSpot))))
            {
                newSpot = FindAbove(currentSpot);
                board.remove((Integer)newSpot);
                isEnd = false;
                while (!isEnd){
                    if(FindAbove(newSpot) != -1 && board.contains((Integer)FindAbove(newSpot))  && battleShip.shoot(createPoint(FindAbove(newSpot))))
                    {
                        newSpot= FindAbove(newSpot);
                        board.remove((Integer)newSpot);
                    }
                    else
                    {
                        isEnd = true;
                    }
                }
            }
            if( FindBelow(currentSpot) != -1 && board.contains((Integer)FindBelow(newSpot)) && battleShip.shoot(createPoint(FindBelow(currentSpot))))
            {
                newSpot = FindBelow(currentSpot);
                board.remove((Integer)newSpot);
                isEnd = false;
                while (!isEnd){
                    if( FindBelow(newSpot) != -1 && board.contains((Integer)FindBelow(newSpot))&& battleShip.shoot(createPoint(FindBelow(newSpot))))
                    {
                        newSpot= FindBelow(newSpot);
                        board.remove((Integer)newSpot);
                    }
                    else
                    {
                        isEnd = true;
                    }
                }
            }
            if(FindPrev(currentSpot) != -1 &&board.contains((Integer)FindPrev(newSpot)) && battleShip.shoot(createPoint(FindPrev(currentSpot))))
            {
                newSpot = FindPrev(currentSpot);
                board.remove((Integer)newSpot);
                isEnd = false;
                while (!isEnd){
                    if( FindPrev(newSpot) != -1&&board.contains((Integer)FindPrev(newSpot)) && battleShip.shoot(createPoint(FindPrev(newSpot))))
                    {
                        newSpot= FindPrev(newSpot);
                        board.remove((Integer)newSpot);
                    }
                    else
                    {
                        isEnd = true;
                    }
                }
            }
            if(FindNext(currentSpot) != -1 && board.contains((Integer)FindPrev(newSpot)) && battleShip.shoot(createPoint(FindNext(currentSpot))))
            {
                newSpot = FindNext(currentSpot);
                board.remove((Integer)newSpot);
                isEnd = false;
                while (!isEnd){
                    if( FindNext(newSpot) != -1&& board.contains((Integer)FindPrev(newSpot))  && battleShip.shoot(createPoint(FindNext(newSpot))))
                    {
                        newSpot = FindNext(newSpot);
                        board.remove((Integer)newSpot);
                    }
                    else
                    {
                        isEnd = true;
                    }
                }
            }
            searchMode = true;
        }
            
       
        // At the end of each decision on where to fire next you need to shoot
      }
      // When you are finished yoßu can ask the battleship API how many shots you have taken.
      int gameShots = battleShip.totalShotsTaken();
      totalShots += gameShots;
    }
    long timeRequired = (System.nanoTime() - start) / 1000000;
    // Report your results. 
    System.out.printf("Code Provided           - The Average = %.2f time = %8d ms\n", (double) totalShots / NUMBEROFGAMES, timeRequired);
  }

  public static Point createPoint(int spot){
      Point retPoint = new Point(spot/10, spot%10);
      System.out.println(retPoint);
      
      return retPoint;
    }
  public static int FindAbove(int shotSpot){
      if ((shotSpot-10)>=0)
        return shotSpot - 10;
      else
          return -1;
  }
  public static int FindBelow(int shotSpot){
      if ((shotSpot+10)<=99)
        return shotSpot + 10;
      else
          return -1;
  }
  public static int FindPrev(int shotSpot){
      if ((shotSpot-1)>=0)
        return shotSpot - 1;
      else
          return -1;
  }
  public static int FindNext(int shotSpot){
      if ((shotSpot+1)<=99)
        return shotSpot + 1;
      else
          return -1;
  }
  public static void main(String[] args)
  {
      System.out.println(BattleShip.version());  // Leave this here - Confirm Version # - Nov 26,2014
      
      // Call you solution  
      mySolution();
  }
  
}
