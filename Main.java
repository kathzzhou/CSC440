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
        int D = 30; 
        String menuOption;
        
        menuOption = printMenu();

        while(true){
            if(menuOption.equals("1")){ // Bot 1
                
                Ship ship = new Ship(D);
                Bot_1 bot_1 = new Bot_1(ship);
                Cell botLocation = bot_1.placeBot();
                ship.setFirstCellOnFire(botLocation);
                ship.setBot(botLocation.getX(), botLocation.getY());
                //ship.printCompleteGrid();
                                        
                while(true){
                    System.out.println("======================================================");
                    
                    System.out.println("Bot Location: " + botLocation.toString());
                    ship.setBot(botLocation.getX(), botLocation.getY());
                    ship.printCompleteGrid();
                    List<Cell> shortestPath = (bot_1.breadthFirstSearch(ship));
                    if (shortestPath.size() > 0) {
                        System.out.print("ShortestPath(size:" + shortestPath.size() +  "): ");
                        for (Cell cell : shortestPath) {
                            System.out.print(cell.toString() + " -> ");
                        }
                        System.out.println();

                        botLocation.setLocation(shortestPath.get(0).getX(), 
                                                shortestPath.get(0).getY());
                    }
                    else if (shortestPath.size() == 0){
                        System.out.println("There is no path from the bot to the button");
                        break;                    
                    }
                    else {
                        System.out.println("Bot reached the button (1).");
                        break;                    
                    }
                    if(botLocation.getX() == ship.getButton().getX() && botLocation.getY() == ship.getButton().getY()){
                        System.out.println("Bot reached the button (2).");
                        break;
                    }
                    // Check if bot is on a cell with Fire
                    if (ship.isBurning(botLocation.getX(), botLocation.getY())) {
                        System.out.println("Bot is on the same cell as fire. Program Ends.");
                        break;      
                    }
                }
                break;
            }
            
            if(menuOption.equals("2")){ //Bot 2
                Ship ship = new Ship(D);
                Bot_2 bot_2 = new Bot_2(ship);
                Cell botLocation = bot_2.placeBot();
                ship.setFirstCellOnFire(botLocation);
                ship.setBot(botLocation.getX(), botLocation.getY());

                while((!ship.isBurning(botLocation.getX(), botLocation.getY()))){
                    System.out.println("======================================================");
                    
                    System.out.println("Bot Location: " + botLocation.toString());
                    ship.setBot(botLocation.getX(), botLocation.getY());
                    ship.printCompleteGrid();
                    List<Cell> shortestPath = (bot_2.breadthFirstSearch(ship));
                    if (shortestPath.size() > 0) {
                        System.out.print("ShortestPath(size:" + shortestPath.size() +  "): ");
                        for (Cell cell : shortestPath) {
                            System.out.print(cell.toString() + " -> ");
                        }
                        System.out.println();

                        botLocation.setLocation(shortestPath.get(0).getX(), 
                                                shortestPath.get(0).getY());
                    }
                    else if (shortestPath.size() == 0){
                        System.out.println("There is no path from the bot to the button");
                        break;                    
                    }
                    else {
                        System.out.println("Bot reached the button (1).");
                        break;                    
                    }
                    if(botLocation.getX() == ship.getButton().getX() && botLocation.getY() == ship.getButton().getY()){
                        System.out.println("Bot reached the button (2).");
                        break;
                    }
                    else{
                        ship.spreadFire();
                    }
                    // Check if bot is on a cell with Fire
                    if (ship.isBurning(botLocation.getX(), botLocation.getY())) {
                        System.out.println("Bot is on the same cell as fire. Program Ends.");
                        break;      
                    }
                }
                break;
            }

            if(menuOption.equals("3")){ //Bot 3
                Ship ship = new Ship(D);
                Bot_3 bot_3 = new Bot_3(ship);
                Cell botLocation = bot_3.placeBot();
                ship.setFirstCellOnFire(botLocation);
                ship.setBot(botLocation.getX(), botLocation.getY());

                while((!ship.isBurning(botLocation.getX(), botLocation.getY()))){
                    System.out.println("======================================================");
                    
                    System.out.println("Bot Location: " + botLocation.toString());
                    ship.setBot(botLocation.getX(), botLocation.getY());
                    ship.printCompleteGrid();
                    List<Cell> shortestPath = (bot_3.breadthFirstSearch(ship));
                    if (shortestPath.size() > 0) {
                        System.out.print("ShortestPath(size:" + shortestPath.size() +  "): ");
                        for (Cell cell : shortestPath) {
                            System.out.print(cell.toString() + " -> ");
                        }
                        System.out.println();

                        botLocation.setLocation(shortestPath.get(0).getX(), 
                                                shortestPath.get(0).getY());
                    }
                    else if (shortestPath.size() == 0){
                        System.out.println("There is no path from the bot to the button");
                        break;                    
                    }
                    else {
                        System.out.println("Bot reached the button (1).");
                        break;                    
                    }
                    if(botLocation.getX() == ship.getButton().getX() && botLocation.getY() == ship.getButton().getY()){
                        System.out.println("Bot reached the button (2).");
                        break;
                    }
                    else{
                        ship.spreadFire();
                    }
                    // Check if bot is on a cell with Fire
                    if (ship.isBurning(botLocation.getX(), botLocation.getY())) {
                        System.out.println("Bot is on the same cell as fire. Program Ends.");
                        break;      
                    }
                }
                break;
            }

            if(menuOption.equals("4")){
                Ship ship = new Ship(D);
                Bot_4 bot_4 = new Bot_4(ship);
                Cell botLocation = bot_4.placeBot();
                ship.setFirstCellOnFire(botLocation);
                ship.setBot(botLocation.getX(), botLocation.getY());

                while((!ship.isBurning(botLocation.getX(), botLocation.getY()))){
                    System.out.println("======================================================");
                    
                    System.out.println("Bot Location: " + botLocation.toString());
                    ship.setBot(botLocation.getX(), botLocation.getY());
                    ship.printCompleteGrid();
                    List<Cell> shortestPath = (bot_4.breadthFirstSearch(ship));
                    if (shortestPath.size() > 0) {
                        System.out.print("ShortestPath(size:" + shortestPath.size() +  "): ");
                        for (Cell cell : shortestPath) {
                            System.out.print(cell.toString() + " -> ");
                        }
                        System.out.println();

                        botLocation.setLocation(shortestPath.get(0).getX(), 
                                                shortestPath.get(0).getY());
                    }
                    else if (shortestPath.size() == 0){
                        System.out.println("There is no path from the bot to the button");
                        break;                    
                    }
                    else {
                        System.out.println("Bot reached the button (1).");
                        break;                    
                    }
                    if(botLocation.getX() == ship.getButton().getX() && botLocation.getY() == ship.getButton().getY()){
                        System.out.println("Bot reached the button (2).");
                        break;
                    }
                    else{
                        ship.spreadFire();
                    }
                    // Check if bot is on a cell with Fire
                    if (ship.isBurning(botLocation.getX(), botLocation.getY())) {
                        System.out.println("Bot is on the same cell as fire. Program Ends.");
                        break;      
                    }
                }
                break;
            }
            menuOption = printMenu();
        }        
    }

    private static String printMenu(){
        String menuOption = null;
        try{
            System.out.print("Would you like to use Bot 1, Bot 2, Bot 3, or Bot 4? (Enter only the #): ");
            Scanner myObj = new Scanner(System.in);
            menuOption = myObj.nextLine();
            if(menuOption.length() == 1){
                if(menuOption.equals("1") || menuOption.equals("2") || menuOption.equals("3") || menuOption.equals("4")){
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
