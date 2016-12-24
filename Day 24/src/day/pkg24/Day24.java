/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author jkester
 */
public class Day24 {

    char[][] airDuct = new char[185][41];
    int startx;
    int starty;
    
    
    Map<Integer[], ArrayList<Integer>> current = new HashMap<Integer[], ArrayList<Integer>>();
    Map<Integer[], ArrayList<Integer>> future = new HashMap<Integer[], ArrayList<Integer>>();
    Map<Integer[], ArrayList<Integer>> all = new HashMap<Integer[], ArrayList<Integer>>();
    
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
        
        
        
        return steps;
    }
    
    public ArrayList<Integer[]> getLocations(Integer[] currentLoc){
        int x = currentLoc[0];
        int y = currentLoc[1];
        
        ArrayList<Integer[]> locations = new ArrayList();
        
        if(y-1 != 0){
           Integer[] up = {x, y-1};
           //if not a wall locations.add(up);
        }
        if(y+1 != 0){
            Integer[] down = {x, y+1};
        }
        
        return locations;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day24 d24 = new Day24();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What's your input?");
        d24.parseInput(sc);
        
        Integer[] start = {d24.startx, d24.starty};
        ArrayList<Integer> startNum = new ArrayList<Integer>();
        d24.current.put(start, startNum);
        d24.all.put(start, startNum);
    }
    
}
