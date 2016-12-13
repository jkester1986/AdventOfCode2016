/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Floors {
    
    public ArrayList<String> floor1 = new ArrayList();
    public ArrayList<String> floor2 = new ArrayList();
    public ArrayList<String> floor3 = new ArrayList();
    public ArrayList<String> floor4 = new ArrayList();
    
    
    private int location;
    
    public Floors(){
        floor1.add("promm");
        floor1.add("promg");
        Collections.sort(floor1);
        
        floor2.add("cobag");
        floor2.add("curig");
        floor2.add("ruthg");
        floor2.add("plutg");
        Collections.sort(floor2);
        
        floor3.add("cobam");
        floor3.add("curim");
        floor3.add("ruthm");
        floor3.add("plutm");
        Collections.sort(floor3);
        
        location = 1;
    }
    
    public Floors(Floors another) {
        this.floor1 =  new ArrayList<String>(another.floor1); // you can access  
        this.floor2 = new ArrayList<String>(another.floor2);
        this.floor3 = new ArrayList<String>(another.floor3);
        this.floor4 = new ArrayList<String>(another.floor4);
        this.location = another.location;
    }
    
    public ArrayList<String> addToFloor(int floorNum, String gOrM){//should I make this boolean?
        ArrayList<String> floor = new ArrayList();
        
        switch(floorNum){
            case 1:
                if(floor1.contains(gOrM)){
                    System.out.println("already on this floor 1");
                }
                else{
                    floor1.add(gOrM);
                    Collections.sort(floor1);
                    floor = floor1;
                }
                break;
            case 2:
                if(floor2.contains(gOrM)){
                    System.out.println("already on this floor 2");
                }
                else{
                    floor2.add(gOrM);
                    Collections.sort(floor2);
                    floor = floor2;
                }
                break;
            case 3:
                if(floor3.contains(gOrM)){
                    System.out.println("already on floor 3");
                }
                else{
                    floor3.add(gOrM);
                    Collections.sort(floor3);
                    floor = floor3;
                }
                break;
            case 4:
                if(floor4.contains(gOrM)){
                    System.out.println("already on floor 4");
                }
                else{
                    floor4.add(gOrM);
                    Collections.sort(floor4);
                    floor = floor4;
                }
                break;
            default:
                System.out.println("there's a problem...");
                break;
        }
        
        return floor;
    }
    
    public ArrayList<String> removeFromFloor(int floorNum, String gOrM){
        ArrayList<String> floor = new ArrayList();
        
        switch(floorNum){
            case 1:
                if(!floor1.contains(gOrM)){
                    System.out.println(gOrM + " not on  1");
                }
                else{
                    floor1.remove(gOrM);
                    floor = floor1;
                    Collections.sort(floor1);
                }
                break;
            case 2:
                if(!floor2.contains(gOrM)){
                    System.out.println(gOrM + " not on  2");
                }
                else{
                    floor2.remove(gOrM);
                    floor = floor2;
                    Collections.sort(floor2);
                }
                break;
            case 3:
                if(!floor3.contains(gOrM)){
                    System.out.println(gOrM + " not on  3");
                }
                else{
                    floor3.remove(gOrM);
                    floor = floor3;
                    Collections.sort(floor3);
                }
                break;
            case 4:
                if(!floor4.contains(gOrM)){
                    System.out.println(gOrM + " not on  4");
                }
                else{
                    floor4.remove(gOrM);
                    floor = floor4;
                    Collections.sort(floor4);
                    
                }
                break;
            default:
                System.out.println("there's a problem...");
                break;
        }
        
        return floor;
    }
    
    public int getLocation(){
        return location;
    }
    
    public int changeLocation(int i){
        location = i;
        return location;
    }
    
    public boolean isFloorValid(int floor){
        switch(floor){
            case 1:
                return isFloorValid(floor1);
            case 2:
                return isFloorValid(floor2);
            case 3:
                return isFloorValid(floor3);
            case 4:
                return isFloorValid(floor4);
            default:
                return false;
        }
    }
    
    public boolean isFloorValid(ArrayList<String> onFloor){
        
        Pattern p = Pattern.compile("^\\w{4}(g)$");
        Pattern p2 = Pattern.compile("^(\\w{4})m$");
        
        int generators = 0;//start off with no generators
        
        for (String s: onFloor){//look through every string on the floor
            Matcher m = p.matcher(s);
            
            if(m.matches()){//if it matches pattern 1 (ends with g)
                generators++;//increase the generator count by 1
            }
        }
        
        for (String s : onFloor){//go through all the strings on the floor again
            Matcher m = p2.matcher(s);
            
            if(m.matches()){//this time match with the first pattern
                String pair = m.group(1) + "g";//create a new string from the letters in group 1, add g onto the end
                
                //if there is more than one generator on the floor
                //and none of the generators match with the microchip
                //then it's not a valid floor
                if(generators > 0 && !onFloor.contains(pair)) return false;
            }
        }
        
        //in all other cases, floor is valid
        return true;
    }
    
    public Floors getFinal(){
        for(String s: floor1){
            addToFloor(4, s);
        }
        floor1 = new ArrayList();
        for(String s: floor2){
            addToFloor(4, s);
        }
        floor2 = new ArrayList();
        for(String s: floor3){
            addToFloor(4, s);
        }
        floor3 = new ArrayList();
        
        changeLocation(4);
        return this;
    }
    
    public void printFloors(){
        System.out.println("on floor " + location);
        System.out.print("1:");
       for(String s: floor1){
            System.out.print(s + ", ");
       } 
       System.out.print("\n");
       System.out.print("2:");
       for(String s: floor2){
            System.out.print(s + ", ");
       }
       System.out.print("\n");
       System.out.print("3:");
       for(String s: floor3){
            System.out.print(s + ", ");
       }
       System.out.print("\n");
       System.out.print("4:");
       for(String s: floor4){
            System.out.print(s + ", ");
       }
       System.out.print("\n\n");
    }
    
    @Override
    public boolean equals(Object o){
        
        if (o == this) return true;
        if (!(o instanceof Floors)) {
            return false;
        }
        
        Floors f = (Floors) o;
        
        return (floor1.equals(f.floor1) && 
                floor2.equals(f.floor2) && 
                floor3.equals(f.floor3) &&
                floor4.equals(f.floor4) &&
                location == f.getLocation());
    }
    
    
    @Override
    public int hashCode(){
        return Objects.hash(floor1, floor2, floor3, floor4, location);
    }
}
