import java.util.*;
import java.lang.Math;

public class Bot_4 extends Bot{

    private List<int[]> path;
    private int pathIndex;

    public Bot_4(Ship ship){
        super(ship);
    }

    public double q;

    public List<Cell> breadthFirstSearch(Cell bot, Cell button, Fire fire){
        int row = ship.getSize();
        int col = ship.getSize();

        PriorityQueue <Cell> queue = new PriorityQueue<>();
        Queue <Cell> visited = new LinkedList<>();
        Map <Cell, Cell> parent = new HashMap<>(); // Stores child, parent
        Map <Cell, Integer> dist = new HashMap<>(); // Stores distance from node to goal node

        //Starting Node
        queue.add(bot);
        visited.add(bot);
        parent.put(bot,null);
        dist.put(bot, 0); // dist[start] = 0 from pseudocode

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
                            (neighbor.isOpenCell(ship)) && (ship.getP(neighbor, q) < .70)){  // for every valid child. including the P probability hard boundary:
                        // calculate cost between current and the child
                        // ?????????? im confused on how to calculate this cost
                        
                        // calculate heuristic h(child) for the child using manhattan distance
                        int hDist = Math.abs(neighbor.getX() - button.getX()) + Math.abs(neighbor.getY() - button.getY());
                        Integer estTotalDist = dist.get(current) + hDist; // + cost(current, child) 


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
