/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg7;

import java.util.Scanner;

/**
 *
 * @author jkester
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Day7 {

    /**
     * @param args the command line arguments
     */
    int goodIPs = 0;
    int sslSupportCount = 0;
    
    public void parseInput(Scanner sc){
        Pattern goal = Pattern.compile(".*(\\w)((?!\\1)\\w)\\2\\1.*");
        Pattern exclude = Pattern.compile(".*\\[\\w*(\\w)((?!\\1)\\w)\\2\\1\\w*\\].*");
        
        Pattern abaPat = Pattern.compile("\\w*[^\\[]\\w*(\\w)((?!\\1)\\w)\\1\\w*[^\\]]\\w*\\2\\1\\2.*");
        //.*[^\[]\w*(\w)((?!\1)\w)\1\w*[^\]].*\2\1\2.*
        //\w*[^\[]\w*(\w)((?!\1)\w)\1\w*[^\]]\w*\2\1\2.*
        
        while(sc.hasNext()){
            String nextLine = sc.next();
            if(nextLine.equals("exit")) break;
            else{
                Matcher goalMatch = goal.matcher(nextLine);
                Matcher badMatch = exclude.matcher(nextLine);
                
                Matcher sslMatcher = abaPat.matcher(nextLine);
                
                if(goalMatch.matches() && !badMatch.matches()){
                    //System.out.println("valid IP :");
                    //System.out.println(nextLine);
                    //System.out.println("");
                    goodIPs ++;
                }
                
                if(sslMatcher.matches()){
                    System.out.println(nextLine);
                    System.out.println("");
                    sslSupportCount++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day7 d7 = new Day7();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        
        d7.parseInput(sc);
        System.out.println("There are " + d7.goodIPs + " IPs that support TSL");
        System.out.println("There are " + d7.sslSupportCount + " IPs that support SSL");
    }
    
}
