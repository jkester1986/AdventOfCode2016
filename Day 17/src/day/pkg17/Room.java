/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg17;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author jkester
 */
public class Room {
    
    private int[] coordinates = new int[2];
    private String passcode = "";
    
    public Room(){
       coordinates[0] = 0;
       coordinates[1] = 0;
       passcode = "pgflpeqp";
    }
    
    public Room(int x, int y, String passCode){
       coordinates[0] = x;
       coordinates[1] = y;
       passcode = passCode;
    }
    
    public Room(Room r){
        coordinates[0] = r.getCoordinates()[0];
        coordinates[1] = r.getCoordinates()[1];
        passcode = new String(r.getPasscode());
    }
    
    public String getPasscode(){
        return passcode;
    }
    
    public int[] getCoordinates(){
        return coordinates;
    }
    
}
