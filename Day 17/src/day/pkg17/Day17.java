/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg17;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.io.*;

/**
 *
 * @author jkester
 */
public class Day17 {

    /**
     * @param args the command line arguments
     */
    Set<Room> allRoomStates = new HashSet<Room>();
    ArrayList<Room> currentRooms = new ArrayList();
    ArrayList<Room> futureRooms = new ArrayList();
    
    int highest = 0;
    boolean first = false;
    public void move()throws NoSuchAlgorithmException{
        int steps = 0; 
        
        while(!currentRooms.isEmpty()){
            steps++;
            for(Room r: currentRooms){
                String hash = getHash(r.getPasscode());
                
                String up = hash.substring(0,1);
                String down = hash.substring(1,2);
                String left = hash.substring(2,3);
                String right = hash.substring(3,4);
                
                int x = r.getCoordinates()[0];
                int y = r.getCoordinates()[1];
                
                if(x != 0 && left.matches("[bcdef]")){//if we aren't all the way to the left
                    String newPass = r.getPasscode() + "L";
                    //System.out.println("new pass left: " + newPass);
                    Room newRoom = new Room(x-1, y, newPass);
                    if(!checkRoom(newRoom))
                        futureRooms.add(newRoom);
                    else if(steps > highest)
                        highest = steps;
                }
                if(x != 3 && right.matches("[bcdef]")){
                    String newPass = r.getPasscode() + "R";
                    //System.out.println("new pass right: " + newPass);
                    Room newRoom = new Room(x+1, y, newPass);
                    if(!checkRoom(newRoom))
                        futureRooms.add(newRoom);
                    else if(steps > highest)
                        highest = steps;
                }
                if(y != 0 && down.matches("[bcdef]")){
                    String newPass = r.getPasscode() + "D";
                    //System.out.println("new pass down: " + newPass);
                    Room newRoom = new Room(x, y-1, newPass);
                    if(!checkRoom(newRoom))
                        futureRooms.add(newRoom);
                    else if(steps > highest)
                        highest = steps;
                }
                if(y != 3 && up.matches("[bcdef]")){
                    String newPass = r.getPasscode() + "U";
                    //System.out.println("new pass up: " + newPass);
                    Room newRoom = new Room(x, y+1, newPass);
                    if(!checkRoom(newRoom))
                        futureRooms.add(newRoom);
                    else if(steps > highest)
                        highest = steps;
                }
            }
            
            currentRooms = new ArrayList<Room>(futureRooms);
            futureRooms = new ArrayList<Room>();
        }
        System.out.println("total steps til maxed out: " + highest);
    }
    
    public boolean checkRoom(Room room){
        int x = room.getCoordinates()[0];
        int y = room.getCoordinates() [1];
        if(x == 3 && y == 0){
            if(!first){
                System.out.println("the shortest path uses passcode " + room.getPasscode());
                first = true;
            }
            return true;
        }
        else return false;
    }
    
    public String getHash(String s)throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes(),0,s.length());
            byte[] mdbytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 4; i++) {//only need the first 4 chars of the hash
              sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //System.out.println("the hash is: " + sb.toString());
            return sb.toString();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        // TODO code application logic here
        Day17 d17 = new Day17();
        
        Room start = new Room(0,3, "pgflpeqp");
        d17.currentRooms.add(start);
        
        d17.move();
    }
    
}
