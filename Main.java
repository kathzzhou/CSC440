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
        int D = 7; 
        double q = 0.3; // Fire Spread
        String menuOption;

        menuOption = printMenu();

        while(true){
            if(menuOption.equals(String.valueOf(1))){ // Bot 1
                // Initialize Ship
                Ship ship = new Ship(D);
                //ship.startShip();
                Bot bot = new Bot(ship);
                Fire fire = new Fire(ship);
                Cell botCell = bot.placeBot();
                ship.printCompleteGrid(botCell, fire);
                Bot_1 bot_1 = new Bot_1(ship);

                List<Cell> shortestPath = (bot_1.breadthFirstSearch(botCell,ship.getButton(), fire));
                System.out.println(shortestPath.size());
                for(int i = 0; i < shortestPath.size(); i++){
                    Cell cell = shortestPath.get(i);
                System.out.println(cell.toString());
                }

                //fire.startFire(ship);

                for(int i = 0; i < 10; i++){

                    //bot_1.breadthFirstSearch(botCell, buttonCell, fireCell);
                    bot.moveBot();
                    System.out.println("Bot position after move " + (i + 1) + ": (" + bot.getX() + ", " + bot.getY() + ")");
                    ship.printCompleteGrid(botCell, fire);
                    if((bot.getX() == ship.getButton().getX()) && (bot.getY() == ship.getButton().getY())){
                        //fire.endFire();
                        System.out.println("The bot has successfully found the button.\nThe fire has been extinguished.");
                        break;
                    }
                }  
            }

            if(menuOption.equals(String.valueOf(2))){ //Bot 2
                // Initialize Ship
                Ship ship = new Ship(D);
                //ship.startShip();
                Bot bot = new Bot(ship);
                Fire fire = new Fire(ship);
                Cell botCell = bot.placeBot();
                ship.printCompleteGrid(botCell, fire);
                Bot_2 bot_2 = new Bot_2(ship);

                List<Cell> shortestPath = (bot_2.breadthFirstSearch(botCell,ship.getButton(), fire));
                System.out.println(shortestPath.size());
                for(int i = 0; i < shortestPath.size(); i++){
                    Cell cell = shortestPath.get(i);
                System.out.println(cell.toString());
                }

                fire.startFire(ship);

                for(int i = 0; i < 10; i++){

                    //bot_1.breadthFirstSearch(botCell, buttonCell, fireCell);
                    bot.moveBot();
                    System.out.println("Bot position after move " + (i + 1) + ": (" + bot.getX() + ", " + bot.getY() + ")");
                    ship.printCompleteGrid(botCell, fire);
                    if((bot.getX() == ship.getButton().getX()) && (bot.getY() == ship.getButton().getY())){
                        //fire.endFire();
                        System.out.println("The bot has successfully found the button.\nThe fire has been extinguished.");
                        break;
                    }
                }
            }
            menuOption = printMenu();
        }

             
        
        
        // Initialize Bot
        // Bot bot = new Bot(ship);
        // bot.placeBot();

        //Initialize Bot 1
        //Bot_1 bot_1 = new Bot_1(ship);
        // bot.placeBot();

        // Initialize Fire

        // Fire fire = new Fire(ship);
        

        // ship.printCompleteGrid(bot, fire, button);
        // System.out.println();

        //Cell cell = new Cell(bot.getX(), bot.getY());
        //bot.moveBot(cell);

        //bot_1.breadthFirstSearch(bot.getX(), bot.getY(), button.getXButtonLoc(), button.getYButtonLoc(), fireX, fireY);

        // ship.printCompleteGrid(bot, fire, button);
        // System.out.println();

        //bot.moveBot();

        // ship.printCompleteGrid(bot, fire);
            
        // //Cell buttonCell = new Cell(button.getXButtonLoc(), button.getYButtonLoc());
        // Cell fireCell = new Cell(fire.startFire(ship).getX(), fire.startFire(ship).getY());


        
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
