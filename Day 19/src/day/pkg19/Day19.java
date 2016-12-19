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
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day19 d19 = new Day19();
        System.out.println("The winning elf is #" + d19.getWinningElf(3018458));
        
        for(int i = 0; i < 3018458; i++){
            d19.elves.add(i+1);
        }
        
        System.out.println("The winning elf for p2 is #" + d19.getWinningElf2());
    }
    
}
