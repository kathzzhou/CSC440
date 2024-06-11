import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Bot_2 extends Bot{

    private List<int[]> path;
    private int pathIndex;

    public Bot_2(Ship ship){
        super(ship);
    }

    public List<Cell> breadthFirstSearch(Cell bot, Cell button, Fire fire){
        int row = ship.getSize();
        int col = ship.getSize();

        Queue <Cell> queue = new LinkedList<>();
        Queue <Cell> visited = new LinkedList<>();
        Map <Cell, Cell> parent = new HashMap<>(); // Stores child, parent

        //Starting Node
        queue.add(bot);
        visited.add(bot);
        parent.put(bot,null);

        while(!queue.isEmpty()){        

            // Assign current node & Moves to next cell in queue
            Cell current = queue.poll();
            System.out.println("Current: " + current.toString());

            // Check if bot = button
            if(current.equals(button)){
                List<Cell> path = new ArrayList<>();
                Cell node = button;
                while(node != null){
                    path.add(node);
                    System.out.println("CurrentNODE: " + node);
                    node = parent.get(node);
                }

                // for(Cell currentNode = button; currentNode != null; currentNode = parent.get(currentNode)){
                //     System.out.println("currentNode: " + current.toString());
                //     System.out.println("currentNode: " + parent.get(currentNode).toString());
                //     path.add(currentNode);
                // }  

                Collections.reverse(path);
                return path;
            }
            
            //Add children of bot to queue
            else{
                for(Cell neighbor : getBotNeighbors(current)){
                    //System.out.println(neighbor);
                    if((!(ship.isBurning(neighbor.getX(),neighbor.getY()))) && 
                    (neighbor.isOpenCell(ship))){
                        queue.add(neighbor);
                        visited.add(neighbor);
                        System.out.println("parent: " + current.toString() + " " + neighbor.toString());
                        parent.put(current, neighbor);
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    
}
