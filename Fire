import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fire {

    // Initializing Variables 
    private static final Random random = new Random();

    private final Ship ship;
    private final double q; //Flammability 
    private final List<int[]> burningCells; 

    // Constructor 
    public Fire(Ship ship, double q) {
        this.ship = ship;
        this.q = q;
        this.burningCells = new ArrayList<>();
    }

    // Finds all the open cells, starts the fire at random open cell
    public void startFire() {
        List<int[]> openCells = ship.getOpenCells();
        int[] startCell = openCells.get(random.nextInt(openCells.size()));
        burningCells.add(startCell);
    }

    // Returns a List of all openNeighbors given a position in the grid
    public List<int[]> getOpenNeighbors(int x, int y){
        List<int[]> openNeighbors = new ArrayList<>();

        if(ship.isOpenCell(x-1,y)){
            openNeighbors.add(new int[]{x-1,y});
        }
        if(ship.isOpenCell(x+1,y)){
            openNeighbors.add(new int[]{x+1,y});
        }
        if(ship.isOpenCell(x,y-1)){
            openNeighbors.add(new int[]{x,y-1});
        }
        if(ship.isOpenCell(x,y+1)){
            openNeighbors.add(new int[]{x,y+1});
        }

        return openNeighbors;
    }

    // Returns the number of burningCells neighboring a given cell
    public int numOfBurningCells(int x, int y){
        int burningCount = 0;
        if(isBurning(x-1,y) || isBurning(x+1,y) || 
            isBurning(x,y-1) || isBurning(x,y+1)){
            burningCount++;
        }
        return burningCount;
    }

    /*
     * Every time step, the fire has the ability to spread to adjacent open cells
     * A non-burning cell catches on fire with the probability 1-(1-q)^k
     *  q = [0,1] defining flammability
     *  K = # of currently burning neighbors of the cell
     */
    public void spreadFire(){
        List<int[]> newBurningCells = new ArrayList<>();

        for(int[] cell : burningCells){
            int x = cell[0];
            int y = cell[1];
            List<int[]> neighbors = getOpenNeighbors(x, y);

            for(int[] neighbor : neighbors){
                int xneighbor = neighbor[0];
                int yneighbor = neighbor[1];
                if(!isBurning(xneighbor, yneighbor)){
                    int k = numOfBurningCells(xneighbor, yneighbor);
                    if(random.nextDouble() < 1 - Math.pow(1 - q, k)){
                        newBurningCells.add(new int[]{xneighbor, yneighbor});
                    }
                }
            }
        }

        burningCells.addAll(newBurningCells);
    }

    public boolean isBurning(int x, int y){
        return ship.getGrid()[x][y].equals("burning");
    }

    // Ends the fire -- only when button pressed
    public void endFire(){
        burningCells.clear();
    }

}
