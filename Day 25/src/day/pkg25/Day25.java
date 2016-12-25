/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg25;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day25 {

    boolean toggle = true;
    
    int[] register = {0,0,0,0};
    
    ArrayList<String> dirs = new ArrayList();
    Pattern p = Pattern.compile("^(\\w+)\\s.*");
    
    //Pattern cpy = Pattern.compile("^.*\\s(\\w|\\d+)\\s(\\w)$");
    Pattern jnz = Pattern.compile("^.*\\s(\\-)?(\\w|\\d+)\\s(\\-)?(\\w|\\d+)$");
    Pattern incDec = Pattern.compile("(inc||dec||tgl).*(\\w)$");
    
    public void parseInput(Scanner sc){
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("exit")) break;
            else dirs.add(line);
        }
    }
    
    public void toggle(int location){
        
        String direction = dirs.get(location);
        //System.out.println("direction toggled from: " + direction);
        Matcher m = p.matcher(direction);
        m.find();
        
        Matcher twoArg1 = incDec.matcher(direction);
        Matcher twoArg2 = jnz.matcher(direction);
        boolean twoArgMatch = twoArg1.matches() || twoArg2.matches();
        
        
        switch(m.group(1)){
            case "tgl":
                direction = direction.replaceAll("tgl", "inc");
                dirs.set(location, direction);
                break;
            case "cpy":
                direction = direction.replaceAll("cpy", "jnz");
                
                dirs.set(location, direction);
                break;
            case "inc":
                direction = direction.replaceAll("inc", "dec");
                dirs.set(location, direction);
                break;
            case "dec":
                direction = direction.replaceAll("dec", "inc");
                dirs.set(location, direction);
                break;
            case "jnz":
                direction = direction.replaceAll("jnz", "cpy");
                
                dirs.set(location, direction);
                break;
        }
        //System.out.println("direction toggled to: " + direction);
        
    }
    
    public void followDirs(){
        int size = dirs.size();
        //System.out.println(size + "  is the size");
        
        loop:
        for(int i = 0; i < size; i++){
            //System.out.println("i: " + i);
            
            String direction = dirs.get(i);
            //System.out.println(direction);
            Matcher m = p.matcher(direction);
            m.find();
            
            
            //going to have to check 
            switch(m.group(1)){
                case "tgl":
                    Matcher tglM = incDec.matcher(direction);
                    if(tglM.matches()){
                        int tglIndex = register[getIndex(tglM.group(2))] + i;
                        //System.out.println("index to toggle is: " + tglIndex);
                        if(tglIndex >= 0 && tglIndex < dirs.size())
                            toggle(tglIndex);
                    }
                    break;
                case "cpy":
                    Matcher cpym = jnz.matcher(direction);
                    if(cpym.matches()){
                        int copiedVal;
                        if(cpym.group(1) != null){
                            copiedVal = Integer.parseInt(cpym.group(2))*(-1);
                        }
                        else{
                            if(cpym.group(2).matches("[abcd]")){
                                String abcd = cpym.group(2);
                                copiedVal = register[getIndex(abcd)];
                                //System.out.println("the value to be copied is: " + copiedVal);
                            }
                            else{
                                copiedVal = Integer.parseInt(cpym.group(2));
                            }
                        }
                        
                        
                        int where;
                        //System.out.println("copy group 4 = " + cpym.group(4));
                        if(cpym.group(3) != null){
                            where = getIndex(cpym.group(4));
                        }
                        else{
                            if(cpym.group(4).matches("[abcd]")){
                                String abcd = cpym.group(4);
                                where = getIndex(abcd);
                                //System.out.println("the location to be copied to is: " + where);
                            }
                            else{
                                where = Integer.parseInt(cpym.group(4));
                            }
                        }
                        //int where = getIndex(cpym.group(2));
                        //System.out.println("The location to copy to is: " + where);
                        if(where >= 0 && where < 4) 
                            register[where] = copiedVal;
                    }
                    break;
                case "inc":
                    Matcher inc = incDec.matcher(direction);
                    if(inc.matches()){
                        int index = getIndex(inc.group(2));
                        register[index] += 1;
                    }
                    break;
                case "dec":
                    Matcher dec = incDec.matcher(direction);
                    if(dec.matches()){
                        int decIndex = getIndex(dec.group(2));
                        register[decIndex] -= 1;
                    }
                    break;
                case "jnz":
                    Matcher jnzm = jnz.matcher(direction);
                    if(jnzm.matches()){
                        int jumpAmt;

                        if(jnzm.group(3) != null ){//if negative
                            jumpAmt = Integer.parseInt(jnzm.group(4))*(-1)-1;
                        }
                        //we need to find out if it's a letter or a number
                        else{
                            if(jnzm.group(4).matches("[abcd]")){
                                String abcd = jnzm.group(4);
                                jumpAmt = register[getIndex(abcd)] -1;

                            }
                            else{
                                jumpAmt = Integer.parseInt(jnzm.group(4)) -1;
                            }
                        }
                        int baseAmt;
                        if(jnzm.group(1) != null ){//if negative
                            baseAmt = Integer.parseInt(jnzm.group(2))*(-1);
                        }
                        else{
                            if(jnzm.group(2).matches("[abcd]")){
                                String abcd = jnzm.group(2);
                                baseAmt = register[getIndex(abcd)];

                            }
                            else{
                                baseAmt = Integer.parseInt(jnzm.group(2));
                            }
                        }
                        if(baseAmt != 0){
                            //System.out.println("amount to move: " + jumpAmt);
                            i += jumpAmt;
                            if(jumpAmt >= size) return;
                        }
                        else{
                            //System.out.println("val was 0, do nothing");
                        }
                    }
                    break;
                case "out":
                    String abcd = Character.toString(direction.charAt(direction.length()-1));
                    //System.out.println(abcd);
                    int output = register[getIndex(abcd)];
                    //System.out.println("output is " +  output);
                    
                    if(toggle && output == 0){
                        System.out.println(output);
                        toggle = false;
                    }
                    else if(!toggle && output == 1){
                        System.out.println(output);
                        toggle = true;
                    }
                    else break loop;
                    break;
                default: break;
            }
            /*
            for(int j: register){
                System.out.print(" " + j);
            }
            System.out.println("\n");
*/
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
        Day25 d25 = new Day25();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("what is your input?");
        
        d25.parseInput(sc);
        
        //d25.followDirs();
        
        
        int i = 0;
        while(true){
            i += 1;
            d25.register = new int[]{i,0,0,0};
            d25.toggle = true;
            System.out.println("VALUE OF THE REGISTER IS: " + d25.register[0]);
            d25.followDirs();
            
        }
    }
    
}
