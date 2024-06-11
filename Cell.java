public class Cell {

    int x, y;
    
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
	}

    public int getY() {
        return this.y;
    }

    public boolean equals(Cell cell){
        if((cell.getX() == this.x) && (cell.getY() == this.y)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isOpenCell(Ship ship){
        return ship.getGrid()[x][y].equals("open");
    }
    
    @Override
    public String toString(){
        return "Cell: (" + x + ", " + y + ") ";
    }

}
