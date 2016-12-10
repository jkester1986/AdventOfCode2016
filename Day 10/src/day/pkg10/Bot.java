/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg10;

/**
 *
 * @author jkester
 */
public class Bot {
    public int id;
    public int chip1;
    public int chip2;
    private int holding;
    
    public Bot(int botId, int valChip1, int valChip2){
        id = botId;
        chip1 = valChip1;
        chip2 = valChip2;
        
        
        if(chip1 != 0){
            holding++;
        }
        if(chip2 != 0){
            holding++;
        }
        
    }
    
    public int getHolding(){
        return holding;
    }
    
    /*
    //no reason to incHolding when addChip already handles
    public void incHolding(){
        holding++;
    }
*/
    
    public void addChip(int chip){
        if(chip1 == 0){
            chip1 = chip;
            holding ++;
        }
        else if(chip2 == 0){
            chip2 = chip;
            holding ++;
        }
        else{
            //do nothing, both chips are full
        }
    }
    
    public int highest(){
        int highest = chip1;
        int lowest = chip2;
        if(highest < lowest){
            int temp = highest;
            highest = lowest;
            lowest = temp;
        }    
        
        return highest;
    }
    
    public int lowest(){
        int highest = chip1;
        int lowest = chip2;
        if(highest < lowest){
            int temp = highest;
            highest = lowest;
            lowest = temp;
        }    
        
        return lowest;
    }
    
    public boolean holds(int val1, int val2){
        int count = 0;
        if(val1 == chip1 || val1 == chip2) count++;
        if(val2 == chip1 || val2 == chip2) count++;
        return count == 2;
    }
    
}
