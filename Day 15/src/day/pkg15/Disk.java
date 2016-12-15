/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg15;

/**
 *
 * @author jkester
 */
public class Disk {
    
    private int positions;
    private int starting;
    //private int remainder;
    
    public Disk(int numPositions, int startingPosition){
        positions = numPositions;
        starting = startingPosition;
    }
    
    public int positions(){
        return positions;
    }
    
    public int starting(){
        return starting;
    }
}
