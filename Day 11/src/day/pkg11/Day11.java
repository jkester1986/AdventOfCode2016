/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.paukov.combinatorics.*;

/**
 *
 * @author jkester
 */
public class Day11 {

    /**
     * @param args the command line arguments
     */
    int lowestFinal = 0;
    int currentSteps = 1;
    
    Elevator elevator = new Elevator();
    Floors floors = new Floors();
    
    int[] totFloors = {1,2,3,4};
    
    Set<Floors> usedStates = new HashSet<Floors>();
    
    Floors endFloors = new Floors();
    
    
    public void takeSteps(int floor){//do I need to track old combo/location?
        
        
        System.out.println("Current floor state: ");
        floors.printFloors();
        if(endFloors.equals(floor)){
            //solution found!
            System.out.println("end floor match!");
            System.out.println("steps taken: " + currentSteps);
            lowestFinal = currentSteps;
        }
        
        if(lowestFinal != 0 && currentSteps > lowestFinal){
            //exit out if the current steps taken is greater than the lowest final steps (when lowestFinal != 0
            System.out.println("you've stepped through too many times");
            return;
        }
        if(usedStates.contains(floor)){
            //we don't want to visit states we've already been to
            System.out.println("you've already visited this state");
            return;
        }
        else{
            
            System.out.println("got through the beginning");
            Floors newFloor = new Floors(floors);
            usedStates.add(newFloor);
        
            int loc = floors.getLocation();

            //get all the combinations possible for the current floor
            ArrayList<ICombinatoricsVector<String>> combos = getCombos(loc);
            for(ICombinatoricsVector<String> combo: combos){
                System.out.println("current combo: " + combo);
                

                for(int i: totFloors){

                    if(loc != i){
                        for(String s: combo){
                            //move combo out of current floor
                            floors.removeFromFloor(floor, s);
                            //move combo into next floor
                            floors.addToFloor(i, s);
                        }
                        
                        //check if floor is valid
                        floors.changeLocation(i);
                        currentSteps++;

                        //if(currentSteps > lowestFinal) return;//if the current steps are going to far, don't bother

                        //TODO: check for floor, change floor, make sure we aren't moving to a state we've already been to
                        takeSteps(i);//do I need to return here??? or just run it?

                        //reverse all the things after stepping through:
                        currentSteps--;
                        floors.changeLocation(loc);
                        for(String s: combo){
                            //move combo back to current floor
                            floors.removeFromFloor(i, s);
                            //move combo out of next floor
                            floors.addToFloor(floor, s);
                        }
                    }

                }

                //be sure to subtract current steps out (maybe farther up?)

            }
            
            //make sure to remove the used state when going backward
            usedStates.remove(newFloor);
        }
        
        
        //return currentSteps;
        return;
    }
    
    public ArrayList<ICombinatoricsVector<String>> getCombos(int floorNum){Generator<String> gen;
        ArrayList<ICombinatoricsVector<String>> allCombos = new ArrayList();//hold all the combinations
    
        switch(floorNum){
           case 1:
               ICombinatoricsVector<String> comb = Factory.createVector(floors.floor1);
               Generator<String> gen1 = Factory.createSimpleCombinationGenerator(comb, 1);
               Generator<String> gen2 = Factory.createSimpleCombinationGenerator(comb, 2);
               allCombos = mergeCombos(gen1, gen2);
               return allCombos;
           case 2:
               ICombinatoricsVector<String> comb2 = Factory.createVector(floors.floor2);
               allCombos = mergeCombos(Factory.createSimpleCombinationGenerator(comb2, 1), 
                       Factory.createSimpleCombinationGenerator(comb2, 2));
               return allCombos;
           case 3:
               ICombinatoricsVector<String> comb3 = Factory.createVector(floors.floor3);
               allCombos = mergeCombos(Factory.createSimpleCombinationGenerator(comb3, 1), 
                       Factory.createSimpleCombinationGenerator(comb3, 2));
               return allCombos;
           case 4:
               ICombinatoricsVector<String> comb4 = Factory.createVector(floors.floor4);
               allCombos = mergeCombos(Factory.createSimpleCombinationGenerator(comb4, 1), 
                       Factory.createSimpleCombinationGenerator(comb4, 2));
               return allCombos;
           default:
               System.out.println("this is not a valid floor");
               break;
       }
         
       return allCombos;
    }
    
    public ArrayList<ICombinatoricsVector<String>> mergeCombos(Generator<String> gen1, Generator<String> gen2){
        ArrayList<ICombinatoricsVector<String>> fullCombos = new ArrayList();
        for(ICombinatoricsVector<String> sVec: gen1){
            fullCombos.add(sVec);
        }
        for(ICombinatoricsVector<String> sVec: gen2){
            fullCombos.add(sVec);
        }
        
        return fullCombos;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day11 d11 = new Day11();
        
        
        
        d11.endFloors.getFinal();
        
        
        d11.takeSteps(1);
        System.out.println("least num of steps is " + d11.lowestFinal);
    }
    
}
