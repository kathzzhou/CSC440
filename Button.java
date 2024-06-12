
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Button{

    private static final Random random = new Random();
    private final Ship ship;
    public boolean button;
    private int xButtonLoc, yButtonLoc;

    public Button(Ship ship){
        this.ship = ship;
    }

    public void placeButton(){
        List<Cell> openCells = ship.getOpenCells();
        Cell buttonLocation = openCells.get(random.nextInt(openCells.size()));
        xButtonLoc = buttonLocation.getX();
        yButtonLoc = buttonLocation.getY();
    }

    public int getXButtonLoc(){
        return xButtonLoc;
    }

    public int getYButtonLoc(){
        return yButtonLoc;
    }

}
