/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
/**
 *
 * @author skot
 */
public class MazeMaker2 extends ImageProducer {
    

    Maaze maze;
    
    
    
    public MazeMaker2(int wi, int hi) {
        super(wi,hi);
        
        maze = new Maaze(30,50,22);
        
        
        
    }
    
    @Override
    public void update() {

        Graphics g = image.createGraphics();

        //Graphics2D g = image.createGraphics();
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        maze.buildMaaze();

        boolean done = maze.allDone;
        for (int i = 0; i < maze.mazeHeight; i++) {
            for (int j = 0; j < maze.mazeWidth; j++) {

                if (maze.roomArray[i][j].isOpen == true)
                    g.setColor(Color.white);
                else if (done == false)
                    g.setColor(Color.gray);
                else
                    g.setColor(Color.black);

                //g.fillRect(i*maze.roomSize, j*maze.roomSize, maze.roomSize, maze.roomSize);
                g.fillRect(j*maze.roomSize, i*maze.roomSize, maze.roomSize, maze.roomSize);

                if (!done) {
                    g.setColor(Color.green);
                    g.drawString("frontier: " + maze.frontier.size(), 10, 100);
                    g.drawString("unreachable: " + maze.unreachable.size(), 10, 110);
                    g.drawString("open: " + maze.openCells.size(), 10, 120);
                }

//                Iterator iter = maze.frontier.iterator();
//                Cell rm;
//
//                g.setColor(Color.blue);
//                while (iter.hasNext()) {
//                    rm = (Cell)iter.next();
//                    if (rm != null)
//                        g.drawRect(rm.col * maze.roomSize, rm.row * maze.roomSize, maze.roomSize, maze.roomSize);
//                }



            }
        }

        g.setColor(Color.red);
        g.fillRect(maze.start.col * maze.roomSize, maze.start.row * maze.roomSize, maze.roomSize, maze.roomSize);

    }

    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }

}
 
class Cell {
    
    int row, col;
    boolean up, down, left, right;
    boolean isOpen = false;
    boolean explored = false;
    boolean inMaze = false;

    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        
        up = false;
        down = false;
        right = false;
        left = false;
        
        inMaze = false;
    }
    
    public int sharesWalls() {
        int retVal = 0;
        
        if (up)
            retVal++;
        if(down)
            retVal++;
        if (left)
            retVal++;
        if (right)
            retVal++;
        
        return retVal;
    }
    
      
} 




class Maaze {
    
    int mazeWidth = 10;
    int mazeHeight = 10;
    int roomSize = 10;
    
    Random rando;
    
    Cell roomArray[][];
    HashSet<Cell> openCells;
    HashSet<Cell> maaze;
    LinkedList<Cell> frontier;
    LinkedList<Cell> neighbors;
    HashSet<Cell> unreachable;
    HashSet<Cell> trash;
    
    Cell start = null;
    
    boolean allDone = false;
    
    public Maaze(int height, int width, int rmsz) {
        
        mazeWidth = width;
        mazeHeight = height;
        roomSize = rmsz;
        
        roomArray = new Cell[height][width];
        
        openCells = new <Cell>HashSet();
        openCells.clear();
        
        maaze = new <Cell>HashSet();
        maaze.clear();
        
        frontier = new <Cell>LinkedList();
        frontier.clear();
        
        unreachable = new <Cell>HashSet();
        unreachable.clear();
        
        trash = new <Cell>HashSet();
        trash.clear();
        
        neighbors = new<Cell>LinkedList();
        neighbors.clear();
       
        rando = new Random();
        
        // Create the array
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                roomArray[i][j] = new Cell(i,j);
                frontier.add(roomArray[i][j]);
            }
        }
        
        
        int WTF = rando.nextInt(mazeHeight-2) + 1;
        int GTFO = rando.nextInt(mazeWidth-2) + 1;
        start = getCell(WTF, GTFO);
        
//        start.isOpen = true;
//        openCells.add(start);
//        start.explored = true;
//        maaze.add(start);
//        unreachable.remove(start);
//        addNeighborsToFrontier(start);
        
        //addToMaze(start);
        //frontier.addAll(getNeighbors(start));
        
    }
    
    public Cell getCell(int row, int col) {
        
        // Bounds check:
        if (!(row >= 1))
            return null;
        if (!(col >= 1))
            return null;
        if (row >= mazeHeight-1)
            return null;
        if (col >= mazeWidth-1)
            return null;
    
        
        return roomArray[row][col];        
        
    }
    
    public LinkedList getNeighbors (Cell thsRm) {
        
        LinkedList<Cell> returnList = new <Cell>LinkedList();
        returnList.clear();
        
        Cell temp = null;
        
        // up
        temp = getCell(thsRm.row-1, thsRm.col);
        if (temp != null) 
            returnList.add(temp);
        
        // down
        temp = getCell(thsRm.row+1, thsRm.col);
        if (temp != null)
            returnList.add(temp);
        
        // left
        temp = getCell(thsRm.row, thsRm.col-1);
        if (temp != null) 
            returnList.add(temp);
        
        // right
        temp = getCell(thsRm.row, thsRm.col+1);
        if (temp != null)
            returnList.add(temp);
        
             
        return returnList;
    }
    
    public Cell getLeft(Cell thisCell) {
        if (thisCell == null)
            return null;
        else return getCell(thisCell.row,thisCell.col-1);   
    }
    
    public Cell getRight(Cell thisCell) {
        if (thisCell == null)
            return null;
        else return getCell(thisCell.row,thisCell.col+1);   
    }
    
    public Cell getUp(Cell thisCell) {
        if (thisCell == null)
            return null;
        else return getCell(thisCell.row-1,thisCell.col);   
    }
    
    public Cell getDown(Cell thisCell) {
        if (thisCell == null)
            return null;
        else return getCell(thisCell.row+1,thisCell.col);   
    }
    
    private void addNeighborsToFrontier(Cell thisCell) {
        
        if (thisCell == null)
            return;
        
        Cell temp = null;
        
        if (thisCell.row > 1) {
            temp = getCell(thisCell.row-1, thisCell.col);
            
            if (temp != null) { 
                temp.down = true;
                if ((!frontier.contains(temp)) && (temp.sharesWalls() < 2))
                    frontier.add(temp);
            }
        }
        
        if (thisCell.row < mazeHeight-2) {
            temp = getCell(thisCell.row+1, thisCell.col);
            
            if (temp != null) {
                temp.up = true;
                if ((!frontier.contains(temp)) && (temp.sharesWalls() < 2))
                    frontier.add(temp);
            }
        }
        
        if (thisCell.col > 1) {
            temp = getCell(thisCell.row, thisCell.col-1);
            
            if (temp != null) {
                temp.left = true;
                if ((!frontier.contains(temp)) && (temp.sharesWalls() < 2))
                    frontier.add(temp);
            }
        }
        
        if (thisCell.col < mazeWidth-2) {
            temp = getCell(thisCell.row, thisCell.col+1);
            
            if (temp != null) {
                temp.right = true;
                if ((!frontier.contains(temp)) && (temp.sharesWalls() < 2))
                    frontier.add(temp);
            }
        }
        
       
    }
    
    private void addToMaze(Cell cell) {
        
        if (cell == null) return;
        
        if (cell.inMaze == true) return;
        
        cell.inMaze = true;
        maaze.add(cell);
    }
    
    private void addToMaze(LinkedList<Cell> list) {
        
        Iterator iter = list.iterator();
        Cell temp = null;
        
        while(iter.hasNext()) {
            temp = (Cell)iter.next();
            addToMaze(temp);
        }
    }
    

    public void buildMaaze() {

        if (frontier.isEmpty() == false) {
           
            // The frontier represents those cells which are IN THE MAZE but are the tips upon
            // which the maze expands.
            
            // Choose one and find its neighbors...
            
            //Cell thisCell = frontier.get(rando.nextInt(frontier.size()));
            Cell thisCell = frontier.poll();
            thisCell.isOpen = true;
            //frontier.remove(thisCell);
            
//            Cell neighbor;
//            Cell target;
//            LinkedList candidates [] = new LinkedList [4];
//            int targetIndex = 0;
//            
//            int index = -1;
//            
//            // Find the cell two clicks LEFT of the frontier cell
//            neighbor = getLeft(thisCell);
//            if (neighbor != null) {
//                target = getLeft(neighbor);
//                if (target != null) {
//                    if ((target.inMaze == false) && (neighbor.inMaze == false)) {
//                        index++;
//                        candidates[index] = new <Cell>LinkedList();
//                        candidates[index].addFirst(target);
//                        candidates[index].addLast(neighbor);
//                    }
//                }
//            }
//            
//            // Find the cell two clicks RIGHT of the frontier cell
//            neighbor = getRight(thisCell);
//            if (neighbor != null) {
//                target = getRight(neighbor);
//                if (target != null) {
//                    if ((target.inMaze == false) && (neighbor.inMaze == false)) {
//                        index++;
//                        candidates[index] = new <Cell>LinkedList();
//                        candidates[index].addFirst(target);
//                        candidates[index].addLast(neighbor);
//                    }
//                }
//            }
//            
//            
//            // Find the cell two clicks UP of the frontier cell
//            neighbor = getUp(thisCell);
//            if (neighbor != null) {
//                target = getUp(neighbor);
//                if (target != null) {
//                    if ((target.inMaze == false) && (neighbor.inMaze == false)) {
//                        index++;
//                        candidates[index] = new <Cell>LinkedList();
//                        candidates[index].addFirst(target);
//                        candidates[index].addLast(neighbor);
//                    }
//                }
//            }
//            
//            // Find the cell two clicks DOWN of the frontier cell
//            neighbor = getDown(thisCell);
//            if (neighbor != null) {
//                target = getDown(neighbor);
//                if (target != null) {
//                    if((target.inMaze == false) && (neighbor.inMaze == false)) {
//                        index++;
//                        candidates[index] = new <Cell>LinkedList();
//                        candidates[index].addFirst(target);
//                        candidates[index].addLast(neighbor);
//                    }
//                }
//            }
//            
//            //let's choose a random pair of cells from candidates and open them up, add them to the maze
//            // and update the system
//            if (index < 0) {
//                
//                return;
//            }
//            
//            else if (index == 0) {
//                targetIndex = 0;
//            }
//            
//            else {
//                targetIndex = rando.nextInt(index);
//                System.out.println(targetIndex);
//            }
//
//            target = (Cell)candidates[targetIndex].pollFirst();
//            neighbor = (Cell)candidates[targetIndex].pollLast();
//
//            thisCell.isOpen = true;
//            thisCell.inMaze = true;
//
//            neighbor.isOpen = true;
//            neighbor.inMaze = true;
//
//            frontier.add(target);
//            frontier.remove(thisCell);
                
        }
           
        else {
            allDone = true;
            System.exit(0);
        }
   
    
//        if (frontier.isEmpty() == false) {
//           
//            Cell thisCell = frontier.get(rando.nextInt(frontier.size()));
//            Cell temp = null;
//            
//            // analyze where this cell: where is connected to the maze...
//            if (thisCell.isOpen) {
//                frontier.remove(thisCell);
//                return;
//            }
//            
//            if (thisCell.down) 
//               temp = getCell(thisCell.row-1,thisCell.col);
//            else if (thisCell.up) 
//                temp = getCell(thisCell.row+1,thisCell.col);
//            else if (thisCell.left) 
//                temp = getCell(thisCell.row,thisCell.col-1);
//            else if (thisCell.right)
//                temp = getCell(thisCell.row,thisCell.col+1);
//            else {
//                trash.add(temp);
//                temp = null;
//            }
//                
//            
//            if (temp != null) {
//                if (temp.explored == false) {
//                   thisCell.isOpen = true;
//                   temp.isOpen = true;
//                   temp.explored = true;
////                       maaze.add(thisCell);
////                       maaze.add(temp);
//                   openCells.add(temp);
//                   openCells.add(thisCell);
//                   frontier.remove(getNeighbors(thisCell));
////                       frontier.remove(temp);
//                   
//                   addNeighborsToFrontier(temp);
//
//                   frontier.removeAll(openCells);
//                }
//            }
//            
//            //clean the frontier
//            Iterator iter = frontier.iterator();
//            while (iter.hasNext()) {
//                temp = (Cell)iter.next();
//                if (temp.sharesWalls() >= 2)
//                    trash.add(temp);
//                else if (temp.row <= 1)
//                    trash.add(temp);
//                else if (temp.row >= mazeHeight-2)
//                    trash.add(temp);
//                else if (temp.col <= 1)
//                    trash.add(temp);
//                else if (temp.col >= mazeWidth-2)
//                    trash.add(temp);
//            }
//            
//            frontier.removeAll(trash);
//            trash.clear();
//            
//        }
//        
//        else {
//            allDone = true;
//        }
//   
//    }
    }
}
