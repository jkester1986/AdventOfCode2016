/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg9;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day9 {

    /**
     * @param args the command line arguments
     */
    
    StringBuilder finalFile = new StringBuilder("");
    
    public void parseInput(Scanner sc){
        while(sc.hasNext()){
            String nextLine = sc.nextLine();
            int length = nextLine.length();
            if(nextLine.equals("exit")){
                break;
            }
            else{
                
                Pattern p = Pattern.compile("^\\((\\d+)x(\\d+)\\)([^$]+)$");
                
                for(int i=0; i < length; i++){
                    char c = nextLine.charAt(i);
                    
                    if(c == '('){
                        String sub =  nextLine.substring(i, length);
                        Matcher m = p.matcher(sub);
                        m.find();
                        
                        /*
                        System.out.println(m.group(1));
                        System.out.println(m.group(2));
                        System.out.println("Group three: " + m.group(3));
                                */
                                
                        int counter = Integer.parseInt(m.group(1));
                        int multiplier = Integer.parseInt(m.group(2));
                        String rest = m.group(3);
                                
                        
                        //System.out.println("The rest is " + rest);
                        StringBuilder duplicate = new StringBuilder("");
                        //System.out.println("counter is " + counter);
                        for(int j = 0; j < counter; j++){
                            duplicate.append(rest.charAt(j));
                        }
                        for(int j = 0; j < multiplier; j++){
                            finalFile.append(duplicate);
                        }
                        
                        i += 2 + m.group(1).length() + m.group(2).length() + counter;
                        //System.out.println("new value of i is: " + i);
                        
                        //break;
                    }
                    else finalFile.append(c);
                    //System.out.println(i);
                }
                       
                
                        
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day9 d9 = new Day9();
        Scanner sc = new Scanner(System.in);
        System.out.println("What's your input?");
        
        d9.parseInput(sc);
        System.out.println(d9.finalFile);
        System.out.println("The length of the file is " + d9.finalFile.length());
    }
    
}
