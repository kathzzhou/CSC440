import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot{

    private static final Random random = new Random();

    private int x; 
    private int y;
    private final Ship ship;

    public Bot(Ship ship){
        this.ship = ship;
    }

    public void placeBot(){
        List<int[]> openCells = new ArrayList<>();
        for(int i = 0; i < ship.getSize(); i++){
            for(int j = 0; j < ship.getSize(); j++){
                if(ship.isOpenCell(i,j)){
                    openCells.add(new int[]{i,j});
                }
            }
        }
        int[] initialPosition = openCells.get(random.nextInt(openCells.size()));
        this.x = initialPosition[0];
        this.y = initialPosition[1];
    }

    public void moveBot(){
        List<int[]> neighbors = ship.getNeighbors(x, y);
        List<int[]> openNeighbors = new ArrayList<>();

        for(int[] neighbor : neighbors){
            if(ship.isOpenCell(neighbor[0], neighbor[1])){
                openNeighbors.add(neighbor);
            }
        }
        if(!openNeighbors.isEmpty()){
            int[] newPosition = openNeighbors.get(random.nextInt(openNeighbors.size()));
            this.x = newPosition[0];
            this.y = newPosition[1];
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
