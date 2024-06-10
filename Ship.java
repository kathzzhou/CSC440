import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Ship{

    // Initialize variables
    private static final Random random = new Random();
    private final String[][] grid;
    private final int size;

    public Ship(int size){
        this.size = size;
        this.grid = new String[size][size];
        createGrid();
        }

    // Creating a D x D grid of blocked cells 
    private void createGrid(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = "blocked";
            }
        }
    }

    // Retrieving neighbors of cell as adjacent cells in up/down/left/right direction
    public List<int[]> getNeighbors(int x, int y){
        List<int[]> neighbors = new ArrayList<>();
        if(x > 0){
            neighbors.add(new int[]{x-1,y});
        }
        if(x < size-1){
            neighbors.add(new int[]{x+1,y});
        }
        if(y > 0){
            neighbors.add(new int[]{x,y-1});
        }
        if(y < size-1){
            neighbors.add(new int[]{x, y+1});
        }
        return neighbors;
    }

    // Choose a square in the interior to 'open' at random
    private void openRandomInteriorCell(){
        int x = random.nextInt(size-2)+1;
        int y = random.nextInt(size-2)+1;
        grid[x][y] = "open";
    }

    // Boolean if cell has one open neighbor
    private boolean hasOneOpenNeighbor(int x, int y){
        List<int[]> neighbors = getNeighbors(x, y);
        int openNeighbors = 0;
        for(int[] neighbor : neighbors){
            if(grid[neighbor[0]][neighbor[1]].equals("open")){
                openNeighbors++;
            }
        }
        return openNeighbors == 1;
    }

    // Return list of blocked cells with one open neighbor
    private List<int[]> getBlockedCellsWOneOpenNeighbor(){
        List <int[]> blockedCells = new ArrayList<>();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(grid[x][y].equals("blocked") && hasOneOpenNeighbor(x, y)){
                    blockedCells.add(new int[]{x, y});
                }
            }
        }
        return blockedCells;
    }

    // To open a cell
    private void openCell(int x, int y){
        grid[x][y] = "open";
    }

    // Return all dead ends
    private List<int[]> identifyDeadEnds(){
        List<int[]> deadEnds = new ArrayList<>();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(grid[x][y].equals("open") && hasOneOpenNeighbor(x, y)){
                    deadEnds.add(new int[]{x,y});
                }
            }
        }
        return deadEnds;
    }

    // Open closed neighbor of a dead end at random
    private void openRandomNeighborOfDeadEnd(int x, int y){
        List<int[]> neighbors = getNeighbors(x, y);
        List<int[]> closedNeighbors = new ArrayList<>();
        for(int[] neighbor : neighbors){
            if(grid[neighbor[0]][neighbor[1]].equals("blocked")){
                closedNeighbors.add(neighbor);
            }
        }
        if(!closedNeighbors.isEmpty()){
            int[] chosenNeighbor = closedNeighbors.get(random.nextInt(closedNeighbors.size()));
            openCell(chosenNeighbor[0], chosenNeighbor[1]);
        }
    }

    /*
     * Generates Environment: 
     *    - Creates D x D Grid
     *    - Opens Random Interior Cell in the Grid
     *    - Iteratively: 
     *      - blockedCells: All currently blocked cells that have exactly one neighbor
     *      - Pick one at random
     *      - Open selected cell
     *      - Repeats until blockedCells.isEmpty
     *    - Identify all cells that are dead ends 
     *    - From approx. half of set, open one of their closed neighbors at random
     */
    public void startShip(){
        openRandomInteriorCell();

        // Iterates and opens all blocked cells that have exactly one neighbor until none left
        while(true){
            List<int[]> blockedCells = getBlockedCellsWOneOpenNeighbor();
            if(blockedCells.isEmpty()){
                break;
            }
            else{
                int[] chosenCell = blockedCells.get(random.nextInt(blockedCells.size()));
                openCell(chosenCell[0], chosenCell[1]);

                // Update blockedCells list
                List<int[]> newBlockedCells = getBlockedCellsWOneOpenNeighbor();
                blockedCells.removeAll(blockedCells);
                blockedCells.addAll(newBlockedCells);
            }
        }
        
        // Takes half of set of dead ends and opens one of closed neighbors at random
        List<int[]> deadEnds = identifyDeadEnds();
        Collections.shuffle(deadEnds);
        for(int i = 0; i< deadEnds.size() / 2; i++){
            int[] deadEnd = deadEnds.get(i);
            openRandomNeighborOfDeadEnd(deadEnd[0], deadEnd[1]);
        }
    }

    // Prints grid including bot, fire, and button
    public void printCompleteGrid(Bot bot, Fire fire, Button button) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (bot.getX() == x && bot.getY() == y) {
                    System.out.print("B ");
                }
                else if(fire.isBurning(x,y)){
                    System.out.print("F ");
                }
                else if((button.getXButtonLoc() == x) && (button.getYButtonLoc() == y)){
                    System.out.print("b ");
                }
                else {
                    System.out.print(grid[x][y].equals("open") ? "O " : "X ");
                }
            }
            System.out.println();
        }
    }

    // Checks if cell is open -- returns boolean
    public boolean isOpenCell(int x, int y){
        return grid[x][y].equals("open");
    }

    public int getSize(){
        return size;
    }

    public void setBurning(int x, int y) {
        grid[x][y] = "burning";
    }

    // Checks if cell is burning -- returns boolean
    // public boolean isBurning(int x, int y) {
    //     return grid[x][y].equals("burning");
    // }

    public List<int[]> getOpenCells(){
        List<int[]> openCells = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (isOpenCell(i, j)) {
                    openCells.add(new int[]{i, j});
                }
            }
        }
        return openCells;
    }

    public String[][] getGrid(){
        return grid;
    }

}