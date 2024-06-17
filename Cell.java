
public class Cell {

    int x, y, dist;
    Cell parentCell;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, int dist, Cell parentCell){
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.parentCell = parentCell;
    }

    //Bot 4 Constructor
    int parent_i, parent_j;
    double f, g, h;

    Cell(){
        this.parent_i = 0;
        this.parent_j = 0;
        this.f = 0; // parameter = g + h
        this.g = 0; // movement cost to move from the starting point to a given square on grid
        this.h = 0; // estimated movment cost to move from given square on grid to final destination
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDist() {
        return this.dist;
    }

    public void setDist(int x) {
        this.dist = x;
    }

    public Cell getParentCell() {
        return this.parentCell;
    }

    public void setParentCell(Cell x) {
        this.parentCell = x;
    }

    public boolean isOpenCell(Ship ship){
        return ship.getGrid()[x][y].equals("open");
    }

    @Override
    public String toString(){
        return "Cell: (" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object cell){

        if (cell == this) {
            return true;
        }

        if (!(cell instanceof Cell)) {
            return false;
        }

        Cell newCell = (Cell) cell;
        if((newCell.getX() == this.x) && (newCell.getY() == this.y)){
            return true;
        }
        else{
            return false;
        }
    }

}
