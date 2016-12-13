/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    Floors floors = new Floors();
    
    int[] totFloors = {1,2,3,4};
    
    Set<Floors> allStates = new HashSet<Floors>();
    ArrayList<Floors> currentLevelStates = new ArrayList();
    ArrayList<Floors> futureLevelStates = new ArrayList();
    
    Floors endFloors = new Floors();
    
    
    public int takeSteps(){//do I need to track old combo/location?
        int steps = 0;
        boolean found = false;
        
        while(!found){
            steps++;
            /*
            System.out.println("You are at stepcount " + steps);
            System.out.println("Here are all the current states: ");
            for(Floors fState: currentLevelStates){
                fState.printFloors();
            }
            */
            
            for(Floors fState: currentLevelStates){//for every state at this level
                ArrayList<ICombinatoricsVector<String>> allCombos = getCombos(fState.getLocation(), fState);
                /*
                for(ICombinatoricsVector<String> vector: allCombos){
                    System.out.println(vector);
                }
                System.out.println("");
*/
                
                for(ICombinatoricsVector<String> vector: allCombos){//for every combination we COULD move on the current floor
                    
                    for(int i = 1; i < 5; i++){//look at all floors we could move to
                        
                        Floors currentState = new Floors(fState);//create a new state each time we look at a differen combination
                        int currentFloor = currentState.getLocation();
                        
                        if(i != currentFloor){//as long as we aren't already on this floor
                            
                            //if(i == 3)System.out.println("hit 3");
                            for(String s: vector){//for each string in the combo
                                currentState.removeFromFloor(currentFloor, s);//remove the string from the floor we're on
                            }
                            
                            if(currentState.isFloorValid(currentFloor)){//as long as we are leaving a floor valid
                                currentState.changeLocation(i);//make sure we move to the floor we want to be on
                                for(String s: vector){
                                    currentState.addToFloor(i, s);
                                }
                                /*
                                if(i == 3){
                                    currentState.printFloors();
                                }
*/
                                if(currentState.isFloorValid(i)){//check new floor to make sure we aren't bringing anything dangerous
                                    /*
                                    System.out.println("this is a valid state: " );
                                    currentState.printFloors();
*/
                                    if(!allStates.contains(currentState)){//if the state doesn't exist in our set of states yet
                                        if(endFloors.equals(currentState)){
                                            //this is our goal state
                                            System.out.println("Congratulations, puzzle solved! It took only " + steps + " steps! Here's your final state:");
                                            currentState.printFloors();
                                            found = true;
                                            return steps;
                                        }
                                        else{
                                            //currentState.printFloors();
                                            allStates.add(currentState);//add it to the list of sets
                                            futureLevelStates.add(currentState);//also add it to the next set of states to look at
                                        }
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //System.out.println("THERE ARE " + futureLevelStates.size() + " STATES TO LOOK AT NEXT");
            //System.out.println("and " + allStates.size() + " states in the set of all states");
            
            currentLevelStates = new ArrayList<Floors>(futureLevelStates);
            futureLevelStates = new ArrayList<Floors>();
            
            if(currentLevelStates.size() == 0){
                System.out.println("reached the end, didn't find anything");
                return steps;
            }
        }
        
        return steps;
    }
    
    public ArrayList<ICombinatoricsVector<String>> getCombos(int floorNum, Floors floor){Generator<String> gen;
        ArrayList<ICombinatoricsVector<String>> allCombos = new ArrayList();//hold all the combinations
    
        switch(floorNum){
           case 1:
               ICombinatoricsVector<String> comb = Factory.createVector(floor.floor1);
               Generator<String> gen1 = Factory.createSimpleCombinationGenerator(comb, 1);
               Generator<String> gen2 = Factory.createSimpleCombinationGenerator(comb, 2);
               allCombos = mergeAndCheck(gen1, gen2);
               return allCombos;
           case 2:
               ICombinatoricsVector<String> comb2 = Factory.createVector(floor.floor2);
               allCombos = mergeAndCheck(Factory.createSimpleCombinationGenerator(comb2, 1), 
                       Factory.createSimpleCombinationGenerator(comb2, 2));
               return allCombos;
           case 3:
               ICombinatoricsVector<String> comb3 = Factory.createVector(floor.floor3);
               allCombos = mergeAndCheck(Factory.createSimpleCombinationGenerator(comb3, 1), 
                       Factory.createSimpleCombinationGenerator(comb3, 2));
               return allCombos;
           case 4:
               ICombinatoricsVector<String> comb4 = Factory.createVector(floor.floor4);
               allCombos = mergeAndCheck(Factory.createSimpleCombinationGenerator(comb4, 1), 
                       Factory.createSimpleCombinationGenerator(comb4, 2));
               return allCombos;
           default:
               System.out.println("this is not a valid floor");
               break;
       }
         
       return allCombos;
    }
    
    public ArrayList<ICombinatoricsVector<String>> mergeAndCheck(Generator<String> gen1, Generator<String> gen2){
        ArrayList<ICombinatoricsVector<String>> fullCombos = new ArrayList();
        for(ICombinatoricsVector<String> sVec: gen1){
            fullCombos.add(sVec);
        }
        for(ICombinatoricsVector<String> sVec: gen2){
            Pattern p = Pattern.compile("^\\w{4}(g)$");
            Pattern p2 = Pattern.compile("^(\\w{4})m$");

            int generators = 0;//start off with no generators

            for (String s: sVec){//look through every string on the floor
                Matcher m = p.matcher(s);

                if(m.matches()){//if it matches pattern 1 (ends with g)
                    generators++;//increase the generator count by 1
                }
            }
            
            if(generators == 0) fullCombos.add(sVec);
            else{
                for (String s : sVec){//go through all the strings on the floor again
                    Matcher m = p2.matcher(s);

                    if(m.matches()){//this time match with the first pattern
                        String pair = m.group(1) + "g";//create a new string from the letters in group 1, add g onto the end

                        //if there is more than one generator on the elevator
                        //and none of the generators match with the microchip
                        //then it's not a valid floor
                        if(generators > 0 && !sVec.contains(pair));
                        else{
                            fullCombos.add(sVec);
                        }
                    }
                }
            }
        }
        
        return fullCombos;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day11 d11 = new Day11();
        d11.endFloors.getFinal();
        System.out.println("end state to find:");
        d11.endFloors.printFloors();
        
        Floors initialState = new Floors(d11.floors);
        
        d11.allStates.add(initialState);
        d11.currentLevelStates.add(initialState);
        
        System.out.println("steps taken: " + d11.takeSteps());
    }
    
}
