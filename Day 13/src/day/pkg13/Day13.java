/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

/**
 *
 * @author jkester
 */
public class Day13 {

    ArrayList<Integer> start = new ArrayList();
    
    Set<ArrayList<Integer>> allStates = new HashSet();
    Set<ArrayList<Integer>> allRejections = new HashSet();
    
    ArrayList<ArrayList<Integer>> currentSet = new ArrayList();
    ArrayList<ArrayList<Integer>> nextSet = new ArrayList();
    
    public int takeSteps(){
        int steps = 0;
        int x;
        int y;
        
        while(currentSet.size() != 0){
            steps ++;
            for(ArrayList<Integer> coordinates: currentSet){
                x = coordinates.get(0);
                y = coordinates.get(1);
                
                ArrayList<Integer> up = new ArrayList<>(Arrays.asList(x, y+1));
                ArrayList<Integer> down = new ArrayList<>(Arrays.asList(x, y-1));
                ArrayList<Integer> left = new ArrayList<>(Arrays.asList(x-1, y));
                ArrayList<Integer>  right = new ArrayList<>(Arrays.asList(x+1, y));
                
                ArrayList<ArrayList<Integer>> directions = new ArrayList();
                directions.add(up);
                directions.add(down);
                directions.add(left);
                directions.add(right);
                
                for(ArrayList<Integer> direction: directions){
                    x = direction.get(0);
                    y = direction.get(1);
                    if(x > -1 && y > -1){
                        if(x == 31 && y == 39){//found the ending location
                        //if(x == 7 && y == 4){
                            System.out.println("You found (31, 39)! It took you " + steps + " steps");
                            return steps;
                        }
                        else if(!allStates.contains(direction) && !allRejections.contains(direction)){
                            int poly = x*x + 3*x + 2*x*y + y + y*y;
                            poly += 1358;
                            //poly += 10;
                            
                            String binary = Integer.toBinaryString(poly);
                            
                            int count = 0;
                            int length = binary.length();
                            
                            for(int i = 0; i < length; i++){
                                if(binary.charAt(i) == '1'){
                                    count ++;
                                }
                            }
                            
                            if(count%2 == 0){//num of 1's in binary are even
                                nextSet.add(direction);
                                allStates.add(direction);
                            }
                            else{//num of 1's in binary are odd
                                allRejections.add(direction);
                            }
                        }
                    }
                }
            }
            //System.out.println(nextSet.size() + " coordinates were added this time");
            
            currentSet = new ArrayList<ArrayList<Integer>>(nextSet);
            nextSet = new ArrayList<ArrayList<Integer>>();
            
            if(steps == 50){
                System.out.println("in 50 steps or less, you can reach " + allStates.size() + " locations");
            }
        }
        return steps;
    }
    
    public void printMaze(){
        for(int y = 1; y <= 100; y++){
            for(int x = 1; x <= 100; x++){
                int poly = x*x + 3*x + 2*x*y + y + y*y;
                    poly += 1358;

                    String binary = Integer.toBinaryString(poly);

                    int count = 0;
                    int length = binary.length();

                    for(int i = 0; i < length; i++){
                        if(binary.charAt(i) == '1'){
                            count ++;
                        }
                    }

                    if(count%2 == 0){//num of 1's in binary are even
                        System.out.print(" ");
                    }
                    else{//num of 1's in binary are odd
                        System.out.print("#");
                    }
            }
            System.out.print(y + "\n");
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day13 d13 = new Day13();
        
        d13.start.add(1);
        d13.start.add(1);
        
        d13.allStates.add(d13.start);
        d13.currentSet.add(d13.start);
        
        d13.takeSteps();
        d13.printMaze();
    }
    
}
