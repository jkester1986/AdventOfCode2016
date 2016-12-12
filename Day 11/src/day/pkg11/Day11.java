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
    
    Floors floors = new Floors();
    
    int[] totFloors = {1,2,3,4};
    
    ArrayList<Floors> states = new ArrayList<Floors>();
    
    Floors endFloors = new Floors();
    
    
    public int takeSteps(){//do I need to track old combo/location?
        int steps = 0;
        ArrayList<Floors> toRemove = new ArrayList();
        
        while(states.size() != 0){
            steps ++;
            int end = states.size();
            
            for(int i = 0; i < end; i++){//go through all states at current step level
                int floorLevel = states.get(i).getLocation();
                states.get(i).printFloors();
                ArrayList<ICombinatoricsVector<String>> allCombos = getCombos(floorLevel, states.get(i));
                
                System.out.println("The combos are:");
                for(ICombinatoricsVector<String> vector: allCombos){
                    for(String s: vector){
                        System.out.print(s + ",");
                    }
                    System.out.print("\n");
                }
                System.out.println("");
                
                for (ICombinatoricsVector<String> vector: allCombos){
                    System.out.println("The state we are examining is:");
                    states.get(i).printFloors();
                    Floors original = new Floors(states.get(i));
                    System.out.println("original:");
                    original.printFloors();
                    Floors current = new Floors(states.get(i));
                    current.printFloors();
                    for(String s: vector){
                        current.removeFromFloor(floorLevel, s);
                    }
                    System.out.println("state after removal:");
                    current.printFloors();
                    if(current.isFloorValid(floorLevel)){
                        //add them to the new floor, trying all floor combos
                        for(int f = 1; f < 5; f++){
                            if(f  != floorLevel){
                                current.changeLocation(f);
                                Floors newStatePartial = new Floors(current);
                                for(String s: vector){
                                    newStatePartial.addToFloor(f, s);
                                }
                                //check to see if new floor is also valid
                                if(newStatePartial.isFloorValid(f)){
                                    states.add(newStatePartial);//add newest state to the queue
                                    if(newStatePartial.equals(endFloors)){
                                        System.out.println("took " + steps + "to complete");
                                    }
                                }
                                else{
                                    System.out.println("can't leave those on floor " + f);
                                }
                                current.changeLocation(floorLevel);
                            }
                        }
                    }
                    else{
                        System.out.println("The floor is not valid");
                    }//do nothing if the floor isn't valid
                }
                toRemove.add(states.get(i));
                return steps;
            }
            
            for(Floors f: toRemove){
                states.remove(f);
            }
            
        }
        
        //return currentSteps;
        return steps;
    }
    
    public ArrayList<ICombinatoricsVector<String>> getCombos(int floorNum, Floors floor){Generator<String> gen;
        ArrayList<ICombinatoricsVector<String>> allCombos = new ArrayList();//hold all the combinations
    
        switch(floorNum){
           case 1:
               ICombinatoricsVector<String> comb = Factory.createVector(floor.floor1);
               Generator<String> gen1 = Factory.createSimpleCombinationGenerator(comb, 1);
               Generator<String> gen2 = Factory.createSimpleCombinationGenerator(comb, 2);
               allCombos = mergeCombos(gen1, gen2);
               return allCombos;
           case 2:
               ICombinatoricsVector<String> comb2 = Factory.createVector(floor.floor2);
               allCombos = mergeCombos(Factory.createSimpleCombinationGenerator(comb2, 1), 
                       Factory.createSimpleCombinationGenerator(comb2, 2));
               return allCombos;
           case 3:
               ICombinatoricsVector<String> comb3 = Factory.createVector(floor.floor3);
               allCombos = mergeCombos(Factory.createSimpleCombinationGenerator(comb3, 1), 
                       Factory.createSimpleCombinationGenerator(comb3, 2));
               return allCombos;
           case 4:
               ICombinatoricsVector<String> comb4 = Factory.createVector(floor.floor4);
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
        
        Floors initialState = new Floors(d11.floors);
        
        d11.states.add(initialState);
        
        System.out.println("steps taken: " + d11.takeSteps());
    }
    
}
