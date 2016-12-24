/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author jkester
 */
public class Location {
    
    int x;
    int y;
    
    SortedSet<Integer> collected;
    
    
    public Location(int xLoc, int yLoc){
        x = xLoc;
        y = yLoc;
        collected = new TreeSet<Integer>();
    }
    
    public Location(int xLoc, int yLoc, SortedSet<Integer> hasThese){
        x = xLoc;
        y = yLoc;
        collected = new TreeSet<Integer>(hasThese);
    }
    
    public Location(Location copy){
        x = copy.x;
        y = copy.y;
        
        collected = new TreeSet<Integer>(copy.collected);
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Location)){
            return false;
        }
        
        Location l = (Location) o;
        
        return(x == l.x &&
                y == l.y &&
                collected.equals(l.collected));
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(x, y, collected);
    }
}
