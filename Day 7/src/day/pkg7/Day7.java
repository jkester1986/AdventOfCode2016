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
        
        Pattern abaPat1 = Pattern.compile("^(?:\\w*\\[\\w*\\])*\\w*(\\w)(?!\\1)(\\w)\\1.*\\[\\w*(\\2\\1\\2)\\w*\\].*");
        //aba ! in [...], bab is
        //without double escapes ^(?:\w*\[\w*\])*\w*(\w)(?!\1)(\w)\1.*\[\w*(\2\1\2)\w*\].*
        
        Pattern abaPat2 = Pattern.compile("^.*(\\[\\w*(\\w)((?!\\2)\\w)\\2\\w*\\])(?:\\w*\\[\\w*\\])*\\w*(\\3\\2\\3).*");
        //aba in [..], bab is not
        //without double escapes: ^.*(\[\w*(\w)((?!\2)\w)\2\w*\])(?:\w*\[\w*\])*\w*(\3\2\3).*
        
        while(sc.hasNext()){
            String nextLine = sc.next();
            if(nextLine.equals("exit")) break;
            else{
                Matcher goalMatch = goal.matcher(nextLine);
                Matcher badMatch = exclude.matcher(nextLine);
                
                Matcher sslMatcher1 = abaPat1.matcher(nextLine);
                Matcher sslMatcher2 = abaPat2.matcher(nextLine);
                
                if(goalMatch.matches() && !badMatch.matches()){
                    goodIPs ++;
                }
                
                if(sslMatcher1.matches() || sslMatcher2.matches()){
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
