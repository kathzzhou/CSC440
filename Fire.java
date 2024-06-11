import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fire {

    // Initializing Variables 
    private static final Random random = new Random();

    //private final double q; //Flammability 
    //private final List<int[]> burningCells; 

    // Constructor 
    public Fire(Ship ship) {
        // Find a random cell and put fire in it
        ship.setCellOnFire();

    }

    //Finds all the open cells, starts the fire at random open cell
    public Cell startFire(Ship ship) {
        Cell fireStartCell = new Cell (random.nextInt(ship.getSize()-1), random.nextInt(ship.getSize()-1));
        //ship.setCellOnFire(fireStartCell.getX(), fireStartCell.getY());
        System.out.println("Starting Fire Cell: "+ fireStartCell.getX() + ", " + fireStartCell.getY());
        return fireStartCell;
    }

    /*
     * Every time step, the fire has the ability to spread to adjacent open cells
     * A non-burning cell catches on fire with the probability 1-(1-q)^k
     *  q = [0,1] defining flammability
     *  K = # of currently burning neighbors of the cell
     */
    // public void spreadFire(Ship ship){
    //     List<int[]> newBurningCells = new ArrayList<>();

    //     for(int[] cell : burningCells){
    //         int x = cell[0];
    //         int y = cell[1];
    //         List<int[]> neighbors = ship.getOpenNeighbors(x, y);

    //         for(int[] neighbor : neighbors){
    //             int xneighbor = neighbor[0];
    //             int yneighbor = neighbor[1];
    //             if(!ship.isBurning(xneighbor, yneighbor)){
    //                 int k = ship.numOfBurningCells(xneighbor, yneighbor);
    //                 if(random.nextDouble() < 1 - Math.pow(1 - q, k)){
    //                     newBurningCells.add(new int[]{xneighbor, yneighbor});
    //                 }
    //             }
    //         }
    //     }

    //     burningCells.addAll(newBurningCells);
    // }

    // // Ends the fire -- only when button pressed
    // public void endFire(){
    //     burningCells.clear();
    // }

}
