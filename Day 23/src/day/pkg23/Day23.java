/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg23;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day23 {

    int[] register = {7,0,0,0};
    
    ArrayList<String> dirs = new ArrayList();
    
    public void parseInput(Scanner sc){
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("exit")) break;
            else dirs.add(line);
        }
    }
    
    public void followDirs(){
        int size = dirs.size();
        
        Pattern p = Pattern.compile("^(\\w+)\\s.*");
        Pattern cpy = Pattern.compile("^.*\\s(\\w|\\d+)\\s(\\w)$");
        Pattern jnz = Pattern.compile("^.*\\s(\\w|\\d+)\\s(\\-)?(\\d)$");
        Pattern incDec = Pattern.compile(".*(\\w)$");
        
        for(int i = 0; i < size; i++){
            //System.out.println("i: " + i);
            
            String direction = dirs.get(i);
            //System.out.println(direction);
            Matcher m = p.matcher(direction);
            m.find();
            
            
            //going to have to check 
            switch(m.group(1)){
                case "cpy":
                    Matcher cpym = cpy.matcher(direction);
                    cpym.find();
                    int copiedVal;
                    if(cpym.group(1).matches("[abcd]")){
                        String abcd = cpym.group(1);
                        copiedVal = register[getIndex(abcd)];
                        //System.out.println("the value to be copied is: " + copiedVal);
                    }
                    else{
                        copiedVal = Integer.parseInt(cpym.group(1));
                    }
                    int where = getIndex(cpym.group(2));
                    //System.out.println("The location to copy to is: " + where);
                    register[where] = copiedVal;
                    break;
                case "inc":
                    Matcher inc = incDec.matcher(direction);
                    inc.find();
                    int index = getIndex(inc.group(1));
                    register[index] += 1;
                    break;
                case "dec":
                    Matcher dec = incDec.matcher(direction);
                    dec.find();
                    int decIndex = getIndex(dec.group(1));
                    register[decIndex] -= 1;
                    break;
                case "jnz":
                    Matcher jnzm = jnz.matcher(direction);
                    jnzm.find();
                    int jumpAmt;
                    
                    if(jnzm.group(2) == null ){
                        jumpAmt = Integer.parseInt(jnzm.group(3)) - 1;
                    }
                    else jumpAmt = (Integer.parseInt(jnzm.group(3))*(-1))-1;
                    int baseAmt;
                    if(jnzm.group(1).matches("[abcd]")){
                        String abcd = jnzm.group(1);
                        baseAmt = register[getIndex(abcd)];
                        
                    }
                    else{
                        baseAmt = Integer.parseInt(jnzm.group(1));
                    }
                    if(baseAmt != 0){
                        //System.out.println("amount to move: " + jumpAmt);
                        i += jumpAmt;
                        if(jumpAmt >= size) return;
                    }
                    else{
                        //System.out.println("val was 0, do nothing");
                    }
                            
                    break;
                default: break;
            }
            for(int j: register){
                //System.out.print(" " + j);
            }
            //System.out.println("");
        }
        
    }
    
    public int getIndex(String abcd){
        switch(abcd){
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            default:
                return 4;
        }
    }
    
    public static void main(String[] args) {
        Day23 d23 = new Day23();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("what is your input?");
        
        d23.parseInput(sc);
        
        d23.followDirs();
        
        System.out.println("Value in register A is: " + d23.register[0]);
    }
}
