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
        List<int[]> openCells = ship.getOpenCells();
        int[] buttonLocation = openCells.get(random.nextInt(openCells.size()));
        xButtonLoc = buttonLocation[0];
        yButtonLoc = buttonLocation[1];
    }

    public int getXButtonLoc(){
        return xButtonLoc;
    }

    public int getYButtonLoc(){
        return yButtonLoc;
    }

}
