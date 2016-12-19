/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg19;

import java.util.ArrayList;

/**
 *
 * @author jkester
 */
public class Day19 {

    ArrayList<Integer> elves = new ArrayList();
    
    public int getWinningElf(int elfCount){
        int winningElf = 1;
        
        for(int i = 1; i <= elfCount; i++){
            //System.out.println(i + ") winning elf: " + winningElf);
            if(i == elfCount){
                break;
            }
            if(i == winningElf){
                winningElf = 1;
            }
            else winningElf+= 2;
        }
        
        return winningElf;
    }
    
    /*
    //Original, SLOW solution
    public int getWinningElf2(){
        Integer index = 0;
        
        while(elves.size() > 1){
            if(index >= elves.size()) index = 0;
            int numToRemove = ((int)Math.floor(elves.size()/2)+index)%elves.size();
            //System.out.println("index to remove: " + numToRemove);
            elves.remove(numToRemove);
            if(numToRemove < index){
                
            }
            else index++;
        }
    
        return elves.get(0);
    }
*/
    /*New solution, someone tipped me off about the pattern
    //pattern: 
    //If the # of elves is a power of 3, that elf gets the presents
    //Ainning elf increases by 1 after each power of three, up to last last power of 3 doubled
    //After reaching the power of 3 doubled, the winning elf increase by 2 for every elf added
    */
    public int getWinningElf2(int elfCount){
        int currentElf = elfCount;
        
        while(!isPower(3, currentElf)){
            currentElf--;
        }
        
        int goal = currentElf*2;
        int winningElf = 0;
        
        for(int i = currentElf+1; i <= goal; i++){
            winningElf++;
            if(i == elfCount){
                return winningElf;
            }
        }
        
        for(int i = goal; i <= elfCount; i++){
            winningElf +=2;
        }
        
        return winningElf;
    }
    
    public static boolean isPower(int x, int y)
    {
        // The only power of 1 is 1 itself
        if (x == 1)
           return (y == 1);
 
        // Repeatedly compute power of x
        int pow = 1;
        while(pow < y)
           pow = pow * x;
 
        // Check if power of x becomes y
        return (pow == y);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day19 d19 = new Day19();
        System.out.println("The winning elf is #" + d19.getWinningElf(3018458));
        
        for(int i = 0; i < 3018458; i++){
            d19.elves.add(i+1);
        }
        
        System.out.println("The winning elf for p2 is #" + d19.getWinningElf2(3018458));
    }
    
}
