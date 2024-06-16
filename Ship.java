import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Ship{

    // Initialize variables
    private static final Random random = new Random();
    private String[][] grid;
    private int size = 5;
    private Cell button = new Cell();
    private Cell bot = new Cell();
    public double q = 0.9;

    public Ship(int size){
        this.size = size;
        this.grid = new String[size][size];
        // Create a D x D grid of blocked cells
        createGrid();
        // Setup Open and Blocked cells per instructions
        startShip();
        // Setup location for button
        button = createButton();
    }

    public Cell createButton(){
        // Initialize Button
        while(true){
            int x = random.nextInt(getSize() - 1);
            int y = random.nextInt(getSize() - 1);

            if(isOpenCell(x,y) && (!isBurning(x,y))){
                this.button.setX(x);
                this.button.setY(y);
                break;
            }
        }
        System.out.println("button random location is " + button.toString());
        return button;
    }

    // Button Getter
    public Cell getButton(){
        return button;
    }

    // Button Setter
    public void setButton(int x, int y) {
        this.button.setX(x);
        this.button.setY(y);
    }

    // Bot Getter
    public Cell getBot(){
        return bot;
    }

    // Bot Setter
    public void setBot(int x, int y) {
        this.bot.setX(x);
        this.bot.setY(y);
    }

    // Create a D x D grid of blocked cells 
    private void createGrid(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = "blocked";
            }
        }
    }

    private void createFixedGrid(){
        String [][] fixedGrid = {
            {"blocked", "open", "open", "blocked", "open"},
            {"blocked", "open", "blocked", "open", "open"},
            {"blocked", "open", "open", "open", "blocked"},
            {"open", "blocked", "blocked", "open", "open"},
            {"open", "open", "open", "open", "blocked"}
        };
    }



    // Retrieving neighbors of cell as adjacent cells in up/down/left/right direction
    public List<Cell> getNeighbors(Cell cell){
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        //Neighbors in order
        if(y > 0){
            neighbors.add(new Cell(x,y-1));
        }
        if(y < size-1){
            neighbors.add(new Cell(x, y+1));
        }
        if(x > 0){
            neighbors.add(new Cell(x-1,y));
        }
        if(x < size-1){
            neighbors.add(new Cell(x+1,y));
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
    private boolean hasOneOpenNeighbor(Cell cell){
        List<Cell> neighbors = getNeighbors(cell);
        int openNeighbors = 0;
        for(Cell neighbor : neighbors){
            if(grid[neighbor.getX()][neighbor.getY()].equals("open")){
                openNeighbors++;
            }
        }
        return openNeighbors == 1;
    }

    // Return list of blocked cells with one open neighbor
    private List<Cell> getBlockedCellsWOneOpenNeighbor(){
        List <Cell> blockedCells = new ArrayList<>();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                Cell cell = new Cell(x,y);
                if(grid[x][y].equals("blocked") && hasOneOpenNeighbor(cell)){
                    blockedCells.add(new Cell(x, y));
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
    private List<Cell> identifyDeadEnds(){
        List<Cell> deadEnds = new ArrayList<>();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                Cell cell = new Cell(x,y);
                if(grid[x][y].equals("open") && hasOneOpenNeighbor(cell)){
                    deadEnds.add(new Cell(x,y));
                }
            }
        }
        return deadEnds;
    }

    // Open closed neighbor of a dead end at random
    private void openRandomNeighborOfDeadEnd(Cell cell){
        List<Cell> neighbors = getNeighbors(cell);
        List<Cell> closedNeighbors = new ArrayList<>();
        for(Cell neighbor : neighbors){
            if(grid[cell.getX()][cell.getY()].equals("blocked")){
                closedNeighbors.add(neighbor);
            }
        }
        if(!closedNeighbors.isEmpty()){
            Cell chosenNeighbor = closedNeighbors.get(random.nextInt(closedNeighbors.size()));
            openCell(chosenNeighbor.getX(), chosenNeighbor.getY());
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
            List<Cell> blockedCells = getBlockedCellsWOneOpenNeighbor();
            if(blockedCells.isEmpty()){
                break;
            }
            else{
                Cell chosenCell = blockedCells.get(random.nextInt(blockedCells.size()));
                openCell(chosenCell.getX(), chosenCell.getY());

                // Update blockedCells list
                List<Cell> newBlockedCells = getBlockedCellsWOneOpenNeighbor();
                blockedCells.removeAll(blockedCells);
                blockedCells.addAll(newBlockedCells);
            }
        }
        
        // Takes half of set of dead ends and opens one of closed neighbors at random
        List<Cell> deadEnds = identifyDeadEnds();
        Collections.shuffle(deadEnds);
        for(int i = 0; i< deadEnds.size() / 2; i++){
            Cell deadEnd = deadEnds.get(i);
            openRandomNeighborOfDeadEnd(deadEnd);
        }
    }

    // Prints grid including bot, fire, and button
    public void printCompleteGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {   
                if (bot.getX() == x && bot.getY() == y) {
                    System.out.print("B ");
                }
                else if(isBurning(x,y)){
                    System.out.print("F ");
                }
                else if((button.getX() == x) && (button.getY() == y)){
                    System.out.print("s ");
                }
                else {
                    System.out.print(grid[x][y].equals("open") ? ". " : "x ");
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

    public List<Cell> getOpenCells(){
        List<Cell> openCells = new ArrayList<>();

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (isOpenCell(i, j)) {
                    openCells.add(new Cell(i, j));
                }
            }
        }
        return openCells;
    }

    public String[][] getGrid(){
        return grid;
    }

    public String getGridCellString(int x, int y) {
        return grid[x][y];
    }

    // Returns a List of all openNeighbors given a position in the grid
    public List<Cell> getOpenNeighbors(Cell cell){
        List<Cell> openNeighbors = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if(x > 0 && isOpenCell(x-1,y)){
            openNeighbors.add(new Cell(x-1,y));
        }
        if(x < getSize()-1 && isOpenCell(x+1,y)){
            openNeighbors.add(new Cell(x+1,y));
        }
        if(y > 0 && isOpenCell(x,y-1)){
            openNeighbors.add(new Cell(x,y-1));
        }
        if(y < getSize()-1 && isOpenCell(x,y+1)){
            openNeighbors.add(new Cell(x,y+1));
        }

        return openNeighbors;
    }

    public boolean isBurning(int x, int y){
        return getGrid()[x][y].equals("burning");
    }

    // Returns the number of burningCells neighboring a given cell
    public int numOfBurningCells(Cell cell){
        int x = cell.getX();
        int y = cell.getY();
        
        int burningCount = 0;
        if(isBurning(x-1,y) || isBurning(x+1,y) || 
            isBurning(x,y-1) || isBurning(x,y+1)){
            burningCount++;
        }
        return burningCount;
    }

    public List<Cell> getBurningCells(){
        List<Cell> burningCells = new ArrayList<>();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if(isBurning(x,y)){
                    burningCells.add(new Cell(x,y));
                }
            }
        }
        return burningCells;
    }

    public List<Cell> getCurrentlyBurningNeighbors(Cell cell){
        List<Cell> currentlyBurningNeighbors = new ArrayList<>();
        List<Cell> allNeighbors = getNeighbors(cell);
        for(Cell neighbor : allNeighbors){
            if(isBurning(neighbor.getX(), neighbor.getY())){
                currentlyBurningNeighbors.add(neighbor);
            }
        }
        return currentlyBurningNeighbors;
    }

    public void setFirstCellOnFire(Cell cell){
        while(true){
            int x = random.nextInt(getSize()-1);
            int y = random.nextInt(getSize()-1);
            if(isOpenCell(x,y) && (x != button.getX() && y != button.getY()) && (cell.getX() != x) && (cell.getY() != y)){
                grid[x][y] = "burning";
                System.out.println("Starting Fire Cell: "+ x + ", " + y);
                break;
            }
        }
    }

    public void spreadFire(){
        for (Cell cell : getBurningCells()){
            for (Cell neighbor : getOpenNeighbors(cell)){
                double probability = Math.random();
                int K = getCurrentlyBurningNeighbors(neighbor).size();

                if(probability < (1 - (Math.pow(1 - q, K)))){
                    setBurning(neighbor);
                }
            }
        }
    }

    public void setBurning(Cell cell){
        while(true){
            if(isOpenCell(cell.getX(), cell.getY())){
                grid[cell.getX()][cell.getY()] = "burning";
                break;
            }
            else {
                return;
            }
        }
    }

    public List<Cell> getBurningAndNeighbors(){
        List<Cell> burningCellsAndNeighbors = new ArrayList<>();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if(isBurning(x,y)){
                    burningCellsAndNeighbors.add(new Cell(x,y)); 
                    // Add neighbors 
                    if(x < getSize()-1){
                        burningCellsAndNeighbors.add(new Cell(x+1, y));
                    }
                    if(x > 0){
                        burningCellsAndNeighbors.add(new Cell(x-1, y));
                    }
                    if(y < getSize()-1){
                        burningCellsAndNeighbors.add(new Cell(x, y+1));
                    }
                    if(y > 0){
                        burningCellsAndNeighbors.add(new Cell(x, y-1));
                    }   
                }
            }
        }
        return burningCellsAndNeighbors;
    }

    public boolean isBurningOrAdj(int x, int y){
        boolean cellFound = false;
        for(Cell cell : getBurningAndNeighbors()){
            if(cell.getX() == x && cell.getY() == y){
                cellFound = true;
                break;
            }
        }
        return cellFound;
    }

    public boolean isBurningOrWillBurn(int x, int y){
        boolean cellFound = false;
        for(Cell cell : getBurningAndNeighbors()){
            if((cell.getX() == x && cell.getY() == y) && (1-Math.pow((1-q), getOpenNeighbors(cell).size())) > .6 ){
                cellFound = true;
                break;
            }
        }
        return cellFound;
    }

    public double getP(Cell cell, double q){
        int K = getOpenNeighbors(cell).size();
        double P = 1 - Math.pow((1-q), K);
        return P;
    }

}
