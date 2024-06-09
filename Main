import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        int D = 5; 
        double q = 0.3; // Fire Spread

        // Initialize Ship
        Ship ship = new Ship(D);
        ship.startShip();
        //ship.printCompleteGrid();

        // Initialize Bot
        Bot bot = new Bot(ship);
        bot.placeBot();

        // Initialize Fire
        Fire fire = new Fire(ship, q);
        fire.startFire();

        // Initialize Button
        Button button = new Button(ship);
        button.placeButton();

        for(int i = 0; i < 10; i++){
            bot.moveBot();
            System.out.println("Bot position after move " + (i + 1) + ": (" + bot.getX() + ", " + bot.getY() + ")");
            ship.printCompleteGrid(bot, fire, button);
            if((bot.getX() == button.getXButtonLoc()) && (bot.getY() == button.getYButtonLoc())){
                fire.endFire();
            }
        }
    }
}
