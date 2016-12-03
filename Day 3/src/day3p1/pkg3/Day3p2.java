/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day3p1.pkg3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author jkester
 */
public class Day3p2 {

    /**
     * @param args the command line arguments
     */
    int totTriangles = 0;
    
    ArrayList<int[]> theSides = new ArrayList<>();
    
    public void scan(Scanner sc){
        
        while(sc.hasNext()){
            String nextLine = sc.nextLine();
            if(nextLine.equals("exit")){
                break;
            }
            else{
                parseLine(nextLine);
            }
        }
        
        while(!theSides.isEmpty()){
                for(int i = 0; i < 3; i++){
                    int firstSide = theSides.get(0)[i];
                    int secondSide = theSides.get(1)[i];
                    int thirdSide = theSides.get(2)[i];
                    
                    System.out.println("Sides are: " + firstSide + " " + secondSide + " " + thirdSide);
                    if(isTriangle(firstSide, secondSide, thirdSide)) totTriangles++;
                    
                }
                theSides.remove(0);
                theSides.remove(0);
                theSides.remove(0);
            }
        
        //shoot to isTriangle, then increment count;
    }
    
    
    public void parseLine(String line){
        Pattern numPattern = Pattern.compile("\\s*(\\d*)\\s*(\\d*)\\s*(\\d*)");
        
        Matcher m = numPattern.matcher(line);
        
        if(m.matches()){
            //System.out.println("There's a match!");
            int firstSide = Integer.parseInt(m.group(1));
            int secondSide = Integer.parseInt(m.group(2));
            int thirdSide = Integer.parseInt(m.group(3));
            
            int[] sides = {firstSide, secondSide, thirdSide};
            theSides.add(sides);
            
            
        }
        
    }
    
    public boolean isTriangle(int firstSide, int secondSide, int thirdSide){
        
        int max = Math.max(Math.max(firstSide, secondSide), thirdSide);
        
        System.out.println("max is " + max);
        if(max == firstSide){
            return (thirdSide+secondSide) > firstSide;
        }
        else if (max == secondSide){
            return (firstSide+thirdSide) > secondSide;
        }
        else if (max == thirdSide){
            return (firstSide+secondSide) > thirdSide;

        }
        else return false;
    }
    public int getTotTriangles(){
        return totTriangles;
    }
     
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day3p2 d3 = new Day3p2();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What is your input?");
        
        d3.scan(sc);
        
        System.out.println(d3.getTotTriangles());
    }
    
}
