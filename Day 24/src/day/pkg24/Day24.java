/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg24;

import java.util.SortedSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author jkester
 */
public class Day24 {

    char[][] airDuct = new char[185][41];
    int startx;
    int starty;
    
    /*
    Map<Integer[], SortedSet<Integer>> current = new HashMap<Integer[], SortedSet<Integer>>();
    Map<Integer[], SortedSet<Integer>> future = new HashMap<Integer[], SortedSet<Integer>>();
*/
    Set<Location> current = new HashSet();
    Set<Location> future = new HashSet();
    Set<Location> all = new HashSet();
    
    public void parseInput(Scanner sc){
        for(int y = 0; y < 41; y++){
            String line = sc.nextLine();
            for(int x = 0; x < 185; x++){
                airDuct[x][y] = line.charAt(x);
                if(line.charAt(x) == '0'){
                    startx = x;
                    starty = y;
                }
            }
        }
        System.out.println("starting point is at " + startx + ", " + starty);
    }
    
    public int takeSteps(){
        int steps = 0;
        
        while(!current.isEmpty()){
            //move
            steps++;
            
            for(Location location: current){
                //check to see if new location contains a #, then add it if it is and doesn't already exist in the list
                if(location.collected.size() == 8){
                    System.out.println("You found the shortest route!");
                    return steps;
                }
                
                else{
                    //addLocations(location);//p1
                    
                    //if/else below only for p2
                    if(location.collected.size() != 7)
                        addLocation(location, false);
                    else
                        addLocation(location, true);
                }
            }
            //move future to current, empty future
            current = new HashSet(future);
            future = new HashSet();
        }
        
        
        return steps;
    }
    
    public void addLocations(Location location){
        int x = location.x;
        int y = location.y;
        
        SortedSet<Integer> collectedNums = new TreeSet(location.collected);
        
        if(y-1 > 0){
           if(airDuct[x][y-1] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x][y-1]).matches("\\d")){//if the location contains a number
                   Integer theNum = Character.getNumericValue(airDuct[x][y-1]);//find out what the number is
                   if(!collectedNums.contains(theNum)){//if the number is NOT already in the list of collected numbers
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newList.add(theNum);
                       newLoc = new Location(x, y-1, newList);
                   }
                   else{//number is already in list of collected nums
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x, y-1, newList);
                   }
               }
               else{//just an open space "."
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x, y-1, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        if(y+1 < 40){
            if(airDuct[x][y+1] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x][y+1]).matches("\\d")){
                   Integer theNum = Character.getNumericValue(airDuct[x][y+1]);
                   if(!collectedNums.contains(theNum)){
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newList.add(theNum);
                       newLoc = new Location(x, y+1, newList);
                   }
                   else{//num not in the arraylist yet
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x, y+1, newList);
                   }
               }
               else{
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x, y+1, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        if(x+1 < 185){
            if(airDuct[x+1][y] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x+1][y]).matches("\\d")){
                   Integer theNum = Character.getNumericValue(airDuct[x+1][y]);
                   if(!collectedNums.contains(theNum)){
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newList.add(theNum);
                       newLoc = new Location(x+1, y, newList);
                   }
                   else{//num not in the arraylist yet
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x+1, y, newList);
                   }
               }
               else{
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x+1, y, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        if(x-1 > 0){
            if(airDuct[x-1][y] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x-1][y]).matches("\\d")){
                   Integer theNum = Character.getNumericValue(airDuct[x-1][y]);
                   if(!collectedNums.contains(theNum)){
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newList.add(theNum);
                       newLoc = new Location(x-1, y, newList);
                   }
                   else{//num not in the arraylist yet
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x-1, y, newList);
                   }
               }
               else{
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x-1, y, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        
    }
    
    public void addLocation(Location location, boolean returnToStart){
        int x = location.x;
        int y = location.y;
        
        SortedSet<Integer> collectedNums = new TreeSet(location.collected);
        
        if(y-1 > 0){
           if(airDuct[x][y-1] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x][y-1]).matches("\\d")){//if the location contains a number
                   Integer theNum = Character.getNumericValue(airDuct[x][y-1]);//find out what the number is
                   if(!collectedNums.contains(theNum)){//if the number is NOT already in the list of collected numbers
                       if(theNum == 0 && !returnToStart){
                            SortedSet<Integer> newList = new TreeSet(location.collected);
                            newLoc = new Location(x, y-1, newList);
                       }
                       else{
                        SortedSet<Integer> newList = new TreeSet(location.collected);
                        newList.add(theNum);
                        newLoc = new Location(x, y-1, newList);
                       }
                   }
                   else{//number is already in list of collected nums
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x, y-1, newList);
                   }
               }
               else{//just an open space "."
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x, y-1, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        if(y+1 < 40){
            if(airDuct[x][y+1] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x][y+1]).matches("\\d")){
                   Integer theNum = Character.getNumericValue(airDuct[x][y+1]);
                   if(!collectedNums.contains(theNum)){
                       if(theNum == 0 && !returnToStart){
                           SortedSet<Integer> newList = new TreeSet(location.collected);
                            newLoc = new Location(x, y+1, newList);
                       }
                       else{
                            SortedSet<Integer> newList = new TreeSet(location.collected);
                            newList.add(theNum);
                            newLoc = new Location(x, y+1, newList);
                       }
                       
                   }
                   else{//num not in the arraylist yet
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x, y+1, newList);
                   }
               }
               else{
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x, y+1, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        if(x+1 < 185){
            if(airDuct[x+1][y] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x+1][y]).matches("\\d")){
                   Integer theNum = Character.getNumericValue(airDuct[x+1][y]);
                   if(!collectedNums.contains(theNum)){
                       if(theNum == 0 && !returnToStart){
                           SortedSet<Integer> newList = new TreeSet(location.collected);
                            newLoc = new Location(x+1, y, newList);
                       }
                       else{
                            SortedSet<Integer> newList = new TreeSet(location.collected);
                            newList.add(theNum);
                            newLoc = new Location(x+1, y, newList);
                       }
                   }
                   else{//num not in the arraylist yet
                       SortedSet<Integer> newList = new TreeSet(location.collected);
                       newLoc = new Location(x+1, y, newList);
                   }
               }
               else{
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x+1, y, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
        if(x-1 > 0){
            if(airDuct[x-1][y] != '#'){
               Location newLoc;
               if(Character.toString(airDuct[x-1][y]).matches("\\d")){
                   Integer theNum = Character.getNumericValue(airDuct[x-1][y]);
                   if(!collectedNums.contains(theNum)){
                        if(theNum == 0 && !returnToStart){
                            SortedSet<Integer> newList = new TreeSet(location.collected);
                            newLoc = new Location(x-1, y, newList);
                        }
                        else{
                            SortedSet<Integer> newList = new TreeSet(location.collected);
                            newList.add(theNum);
                            newLoc = new Location(x-1, y, newList);
                        }
                        
                   }
                   else{//num not in the arraylist yet
                        SortedSet<Integer> newList = new TreeSet(location.collected);
                        newLoc = new Location(x-1, y, newList);
                   }
               }
               else{
                   SortedSet<Integer> newList = new TreeSet(location.collected);
                   newLoc = new Location(x-1, y, newList);
               }
                   if(!all.contains(newLoc)){
                       all.add(newLoc);
                       future.add(newLoc);
                   }
           }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day24 d24 = new Day24();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What's your input?");
        d24.parseInput(sc);
        
        SortedSet<Integer> startNum = new TreeSet();
        startNum.add(0);
        
        //Location start = new Location(d24.startx, d24.starty, startNum);//p1
        Location start = new Location(d24.startx, d24.starty);//p2
        
        d24.addLocations(start);
        d24.current = new HashSet(d24.future);
        d24.future = new HashSet();
        System.out.println(d24.takeSteps() + " steps");
        //d24.all.put(start, startNum);
    }
    
}
