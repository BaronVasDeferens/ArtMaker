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
public class MazeMaker extends ImageProducer {
    
    boolean drawFrontier = false;
    boolean drawStats = false;
    Maze maze;
    
    
    
    public MazeMaker(int wi, int hi) {
        super(wi,hi);
        
        maze = new Maze(200,100,5);
        
        Graphics g = image.createGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
       
    }
    
    @Override
    public void update() {
        
        Graphics g = image.createGraphics();

        //Graphics2D g = image.createGraphics();
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        maze.buildMaze();
        
        

        
        LinkedList<Room> openRooms = maze.getOpenRooms();
        for (Room room : openRooms) {
            if (room.isOpen == true) {
                g.setColor(Color.white);
                g.fillRect(room.row * maze.roomSize, room.col * maze.roomSize, maze.roomSize, maze.roomSize);
            }

        }
//        for (int i = 0; i < maze.mazeHeight; i++) {
//            for (int j = 0; j < maze.mazeWidth; j++) {
//                
//                if (maze.roomArray[i][j].isOpen == true) 
//                    g.setColor(Color.white);
//                else if (maze.allDone == false)
//                    g.setColor(Color.gray);
//                else
//                    g.setColor(Color.black);
//                
//                g.fillRect(i*maze.roomSize, j*maze.roomSize, maze.roomSize, maze.roomSize);
//            } 
//        }
        
        if (drawFrontier) {
            Iterator iter = maze.frontier.iterator();
            Room rm;
            g.setColor(Color.blue);
            while (iter.hasNext()) {
                rm = (Room)iter.next();
                if (rm != null)
                    g.drawRect(rm.row * maze.roomSize, rm.col * maze.roomSize, maze.roomSize, maze.roomSize);
            }  
        }
        
        if (drawStats) {
            g.setColor(Color.green);
            g.drawString("frontier: " + maze.frontier.size(), 10, 100);
            g.drawString("unreachable: " + maze.unreachable.size(), 10, 110);
            g.drawString("open: " + maze.openRooms.size(), 10, 120);
        }
            
        g.dispose();
    }

    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }
}
 
class Room {
    
    int row, col;
    Room up, down, left, right;
    boolean isOpen = false;
    boolean reachable = false;

    
    public Room(int row, int col) {
        this.row = row;
        this.col = col;
        
        up = null;
        down = null;
        right = null;
        left = null;
    }
    
    public int hasUnreachableNeightbor() {
           
        int unreachables = 0;
        
        if (up != null)
            if (up.reachable == false)
                unreachables++;
        if (down != null)
            if (down.reachable == false)
                unreachables++;
        if (left != null)
            if (left.reachable == false)
                unreachables++;
        if (right != null)
            if (right.reachable == false)
                unreachables++;
        
        return unreachables;
    }
         
} 




class Maze {
    
    int mazeWidth = 10;
    int mazeHeight = 10;
    int roomSize = 10;
    
    Random rando;
    
    Room roomArray[][];
    HashSet<Room> openRooms;
    HashSet<Room> theMaze;
    HashSet<Room> frontier;
    HashSet<Room> unreachable;
    HashSet<Room> trash;
    
    boolean allDone = false;
    
    public Maze(int height, int width, int rmsz) {
        
        mazeWidth = width;
        mazeHeight = height;
        roomSize = rmsz;
        
        roomArray = new Room[height][width];
        
        openRooms = new <Room>HashSet();
        openRooms.clear();
        
        theMaze = new <Room>HashSet();
        theMaze.clear();
        
        frontier = new <Room>HashSet();
        frontier.clear();
        
        unreachable = new <Room>HashSet();
        unreachable.clear();
        
        trash = new <Room>HashSet();
        trash.clear();
       
        rando = new Random();
        
        // Create the array
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                roomArray[i][j] = new Room(i,j);
                unreachable.add(roomArray[i][j]);
            }
        }
        
        // Construct the adjacency
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                
                Room rm = roomArray[i][j];
                
                if (i > 1)
                    rm.up = roomArray[i-1][j];
                    
                if  (i < mazeHeight - 2)
                    rm.down = roomArray[i+1][j];
                        
                if (j > 1)
                    rm.left = roomArray[i][j-1];
                        
                if (j < mazeWidth-2)
                    rm.right = roomArray[i][j+1];

            }
        }
        
       Room start = getRoom(rando.nextInt(mazeHeight-2) +1, rando.nextInt(mazeWidth-2) +1);
        //Room start = getRoom(1,1);
        start.isOpen = true;
        openRooms.add(start);
        start.reachable = true;
        theMaze.add(start);
        unreachable.remove(start);
        frontier.addAll(getNeighbors(start));
        
    }
    
    public Room getRoom(int row, int col) {
        
        // Bounds check:
        if (row < 0)
            return null;
        if (col < 0)
            return null;        
        if (row >= mazeHeight)
            return null;
        if (col >= mazeWidth)
            return null;
        
        return roomArray[row][col];        
        
    }
    
    
    public LinkedList getNeighbors (Room thsRm) {
        
        LinkedList<Room> returnList = new <Room>LinkedList();
        returnList.clear();
        
        if (thsRm.up != null) {
            returnList.add(thsRm.up);
        }
        
        if (thsRm.down != null) {
            returnList.add(thsRm.down);
        }
        
        if (thsRm.left != null) {
            returnList.add(thsRm.left);
        }
        
        if (thsRm.right != null) {
            returnList.add(thsRm.right);
        }
             
        return returnList;
    }

    
    private void addNeighborsToFrontier(Room thisRoom) {
        
        if (thisRoom == null)
            return;

        if ((thisRoom.row > 1) && (thisRoom.up != null)) {
            frontier.add(thisRoom.up);
            makeReachable(thisRoom.up);
        }
                
        if ((thisRoom.row < mazeHeight-2) && (thisRoom.down != null)) {
            frontier.add(thisRoom.down);
            makeReachable(thisRoom.down);
        }        
        if ((thisRoom.col > 1) && (thisRoom.left != null)) {
            frontier.add(thisRoom.left);
            makeReachable(thisRoom.left);
        }
                
        if ((thisRoom.col < mazeWidth-2) && (thisRoom.right != null)) {
            frontier.add(thisRoom.right);
            makeReachable(thisRoom.right);
        }
        
            
    }
    

    private void makeReachable(Room thisRoom) {
        
        if (thisRoom.reachable)
            return;
        
        thisRoom.reachable = true;
        unreachable.remove(thisRoom);
        theMaze.add(thisRoom);
    }
    
    private void makeReachable(LinkedList these) {
        Iterator iter = these.iterator();
        Room thisRoom;
        
        while (iter.hasNext()) {
            thisRoom = (Room)iter.next();
            makeReachable(thisRoom);
        }
    }
    
    public LinkedList<Room> getOpenRooms() { 
        LinkedList<Room> roomz = new LinkedList<Room>();
        Iterator iter = openRooms.iterator();
        Room r;
        while (iter.hasNext()) {
            r = (Room) iter.next();
            roomz.add(r);
        }
        
        openRooms.clear();
        
        return roomz;
    }
    
    private void connecto() {
        
    }
    


  
    
    
    public void buildMaze() {
                
        if ((unreachable.size() > ((mazeHeight * 2) + (mazeWidth * 2) - 4))) {
            
            Room thisRoom = null;
            Iterator fron = frontier.iterator();
            trash.clear();
            
            int roomValue;// = thisRoom.hasUnreachableNeightbor();
            
            while ((fron.hasNext())) {
                
                thisRoom = (Room)fron.next();
                roomValue = thisRoom.hasUnreachableNeightbor();
                                
//                if (roomValue == 4) {
//                    break;
//                }
                
                if (roomValue == 3) {
                    break;
                }
                
                else if (roomValue == 2) {
                    trash.add(thisRoom);
                    break;
                }
                
                else if (roomValue == 0) {
                    trash.add(thisRoom);   
                }
                    
            }
            
            if (thisRoom == null)
                return;
            
            
            thisRoom.isOpen = true;
            openRooms.add(thisRoom);
            
            makeReachable(thisRoom);
            makeReachable(getNeighbors(thisRoom));
            
            
            frontier.remove(thisRoom);
            frontier.addAll(getNeighbors(thisRoom));
            frontier.removeAll(openRooms);
            frontier.removeAll(trash);
            
        }
         
        else {
            frontier.clear();
            allDone = true;
            
        }
        
    }
    
    
}
