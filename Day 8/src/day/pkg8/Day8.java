/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg8;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */

public class Day8 {
    
    boolean[][] pixels = new boolean[50][6];

    public void parseInput(Scanner sc){
        
        Pattern rect = Pattern.compile("^.*\\s(\\d*)x(\\d*)$");
        Pattern rot = Pattern.compile("^\\w+\\s(\\w+).+\\=(\\d+)\\sby\\s(\\d+)$");
        
        int x = 0;
        int y = 0;
        
        while(sc.hasNext()){
            String nextLine = sc.nextLine();
            if(nextLine.equals("exit")) break;
            else{
                Matcher rectMatch = rect.matcher(nextLine);
                Matcher rotMatch = rot.matcher(nextLine);
                
                if(rectMatch.matches()){//fill rect
                    x = Integer.parseInt(rectMatch.group(1));
                    y = Integer.parseInt(rectMatch.group(2));
                    
                    for(int i = 0; i < y; i++){
                        for(int j = 0; j < x; j++){
                            pixels[j][i] = true;
                        }
                    }
                    
                }
                else{//otherwise we do a rotation
                    rotMatch.find();
                    x = Integer.parseInt(rotMatch.group(2));
                    y = Integer.parseInt(rotMatch.group(3));
                    
                    rotate(rotMatch.group(1), x, y);
                }
            }
        }
    }
    
    public void rotate(String direction, int index, int moveAmt){
        
        switch(direction){
            case "row":
                boolean oldrowVals[] = new boolean[50];
                for(int i = 0; i < 50; i++){
                    oldrowVals[i] = pixels[i][index];
                }
                for(int i = 0; i < 50; i++){
                    pixels[(moveAmt+i)%50][index] = oldrowVals[i];
                }   
                break;
                
            case "column":
                boolean oldcolVals[] = new boolean[6];
                for(int i = 0; i < 6; i++){
                    oldcolVals[i] = pixels[index][i];
                }
                for(int i = 0; i < 6; i++){
                    pixels[index][(i+moveAmt)%6] = oldcolVals[i];
                }
                break;
                
            default:
                break;
        }
        
        
    }
    
    public int countLights(){
        int count = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 50; j++){
                if(pixels[j][i]) count++;
            }
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 50; j++){
               if(pixels[j][i]) System.out.print("#");
               else System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        return count;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day8 d8 = new Day8();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        
        d8.parseInput(sc);
        
        System.out.println("there are " + d8.countLights() + " lights on");
    }
    
}
