/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1p1;

import java.io.*;
import java.util.*;


/**
 *
 * @author jkester
 */
public class Day1P1 {

    /**
     * @param args the command line arguments
     * 
     * 
     */
    
    
    int north = 0;
    int south = 0;
    int east = 0;
    int west = 0;
    
    String dir = "north";
    
    public void readString(Scanner input){
        
        ArrayList<int[]> locations = new ArrayList<int[]>();
        
        breakLoop:
        while(input.hasNext()){
            String next = input.next();
            //System.out.println(next);
            if(next.equals("exit")){
                break;
            }
            else{
                String[] turn = next.split(",");
                //System.out.println(turn[0]);
                              
                changeDir(dir, turn[0]);
                
                int vert = north - south;
                int horiz = east - west;
                
                /*
                System.out.println("Vert: " + vert);
                System.out.println("Horiz: " + horiz);
                */
                
                
                int[] coordinate = {horiz, vert};
                System.out.println("coordinates to add: " + Arrays.toString(coordinate));
                
                for(int[] oneCoord: locations){
                    //System.out.println("running");
                    System.out.println(Arrays.toString(oneCoord));
                    if (Arrays.equals(oneCoord, coordinate)){
                        System.out.println("The new coordinates are: " + Arrays.toString(coordinate));
                        System.out.println("REAL Easter Bunny HQ is " + (Math.abs(vert) + Math.abs(horiz)) + " blocks away");
                        //break breakLoop;
                    }
                }
                
                /*
                if(locations.contains(coordinate)){
                    System.out.println("The new coordinates are: " + Arrays.toString(coordinate));
                }
                */
                locations.add(coordinate);

                System.out.println("Easter Bunny HQ is " + (Math.abs(vert) + Math.abs(horiz)) + " blocks away");
            }
        }
        
        /*
        System.out.println("North: " + north);
        System.out.println("South: " + south);
        System.out.println("East: " + east);
        System.out.println("West: " + west);
                */
        
       
    }
    
    public String changeDir(String currentDir, String turn){
        System.out.println("Current Direction: " + currentDir);
        System.out.println("Turning: " + turn);
        
        switch(currentDir){
            case "north":
                if(turn.charAt(0) == 'L'){
                    dir = "west";
                    System.out.println("West is : " + west);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    west += moved;
                    System.out.println("West is :" + west);
                }
                else {
                    dir = "east";
                     System.out.println("East is :" + east);
                     String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    east += moved;
                     System.out.println("East is :" + east);
                }
                
                break;
            case "south":
                if(turn.charAt(0) == 'L'){
                    dir = "east";
                    System.out.println("East is :" + east);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    east += moved;
                    System.out.println("East is :" + east);
                }
                else {
                    dir = "west";
                    System.out.println("West is :" + west);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    west += moved;
                    System.out.println("West is :" + west);
                }
                break;
            case "east":
                if(turn.charAt(0) == 'L'){
                    dir = "north";
                    System.out.println("North is :" + north);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    north += moved;
                    System.out.println("North is :" + north);
                }
                else {
                    dir = "south";
                    System.out.println("South is :" + south);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    south += moved;
                    System.out.println("South is :" + south);
                }
                break;
            case "west":
                if(turn.charAt(0) == 'L'){
                    dir = "south";
                    System.out.println("South is :" + south);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    south += moved;
                    System.out.println("South is :" + south);
                }
                else {
                    dir = "north";
                    System.out.println("North is :" + north);
                    String[] splitDirs = turn.split("[LR]");
                    //System.out.println("splitDirs = " + splitDirs[1]);
                    int moved = Integer.parseInt(splitDirs[1]);
                    north += moved;
                    System.out.println("North is :" + north);
                }
                break;
            default:
                break;
        }
        
        return currentDir;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day1P1 d1 = new Day1P1();
        Scanner user_input = new Scanner( System.in );
        System.out.println("What is the string you'd like to input?");
        
        d1.readString(user_input);
        
    }
    
}
