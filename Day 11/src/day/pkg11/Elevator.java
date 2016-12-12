/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg11;

import java.util.ArrayList;

/**
 *
 * @author jkester
 */



public class Elevator {
    ArrayList<String> carrying = new ArrayList();
    
    public Elevator(){
        
    }
    
    public void add(String s){
        if(!carrying.contains(s)){
            carrying.add(s);
        }
    }
    
    public void remove(String s){
        if(carrying.contains(s)){
            carrying.remove(s);
        }
    }
    
    
}
