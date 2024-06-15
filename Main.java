import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random random = new Random();
    Scanner myObj = new Scanner(System.in);

    public static void main(String[] args) {
        int D = 5; 
        String menuOption = "1";
        // String [][] fixedGrid = {
        //     {"blocked", "open", "open", "blocked", "open"},
        //     {"blocked", "open", "blocked", "open", "open"},
        //     {"blocked", "open", "open", "open", "blocked"},
        //     {"open", "blocked", "blocked", "open", "open"},
        //     {"open", "open", "open", "open", "blocked"}
        // };
        // Cell button = new Cell(1,0);
        // Cell bot = new Cell(1,3);

        //menuOption = printMenu();

        while(true){
            if(menuOption.equals("1")){ // Bot 1
                // Initialize Ship
                Ship ship = new Ship(D);
                //Ship ship = new Ship(fixedGrid, bot, button);
                //ship.startShip();
                Bot_1 bot_1 = new Bot_1(ship);
                Cell botLocation = bot_1.placeBot();
                ship.setCellOnFire(botLocation);
                //Fire fire = new Fire(ship); <- old line where Fire class was in use
                ship.setBot(botLocation.getX(), botLocation.getY());
                ship.printCompleteGrid();
                        
                
                List<Cell> shortestPath = (bot_1.breadthFirstSearch(ship));
                
                // System.out.println(shortestPath.size());
                // for(int i = 0; i < shortestPath.size(); i++){
                //     Cell cell = shortestPath.get(i);
                // System.out.println(cell.toString());
                // }

                // //fire.startFire(ship);

                // for(int i = 0; i < 10; i++){

                //     //bot_1.breadthFirstSearch(botCell, buttonCell, fireCell);
                //     bot.moveBot();
                //     System.out.println("Bot position after move " + (i + 1) + ": (" + bot.getX() + ", " + bot.getY() + ")");
                //     ship.printCompleteGrid(botCell, fire);
                //     if((bot.getX() == ship.getButton().getX()) && (bot.getY() == ship.getButton().getY())){
                //         //fire.endFire();
                //         System.out.println("The bot has successfully found the button.\nThe fire has been extinguished.");
                //         break;
                //     }
                // }  
                break;
            }
            

            if(menuOption.equals(String.valueOf(2))){ //Bot 2
                Ship ship = new Ship(D);
                Bot_2 bot_2 = new Bot_2(ship);
                Cell botLocation = bot_2.placeBot();
                ship.setCellOnFire(botLocation);
                ship.setBot(botLocation.getX(), botLocation.getY());
                ship.printCompleteGrid();
                        
                
                List<Cell> shortestPath = (bot_2.breadthFirstSearch(ship));
            }

            // if(menuOption.equals(String.valueOf(4))){
            //     Ship ship = new Ship(D);
            //     Bot_4 bot_4 = new Bot_4(ship);
            //     Bot bot = new Bot(ship);
            //     Fire fire = new Fire(ship);
            //     Cell botCell = bot.placeBot();
            //     ship.printCompleteGrid(botCell, fire);

            //     bot_4.aStarSearch(ship, botCell, ship.getButton());
            // }
            //menuOption = printMenu();
        }        
    }

    private static String printMenu(){
        String menuOption = null;
        try{
            System.out.print("Would you like to use Bot 1 or Bot 2? (Enter the #): ");
            Scanner myObj = new Scanner(System.in);
            menuOption = myObj.nextLine();
            //System.out.println("You entered : " + menuOption + " (" + menuOption.length() + ")");
            if(menuOption.length() == 1){
                if(menuOption.equals("1") || menuOption.equals("2")){
                    return menuOption;
                }
                else {
                    throw new Exception("Invalid input, please try again--\n");
                }
            }
            else{
                throw new Exception("Invalid input, please try again==\n");
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + "\n");
        }
        return menuOption;
    }
}
