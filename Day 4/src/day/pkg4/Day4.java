/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg4;

import java.util.ArrayList;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day4 {

    /**
     * @param args the command line arguments
     */
    
    int sectorIdTot = 0;
    
    public void parseInput(Scanner sc){
        
        while(sc.hasNext()){
            String nextLine = sc.nextLine();
            if(nextLine.equals("exit")){
                break;
            }
            else{
                Pattern pattern = Pattern.compile("(\\w*)\\-(\\d*)");
                //(a*b*c*d*e*f*g*h*i*j*k*l*m*n*o*p*q*r*s*t*u*v*w*x*y*z*)\-(\d*)
                Matcher m = pattern.matcher(nextLine);
                
                int sectorIdLoc = 0;
                int sectorID = 0;
                
                ArrayList<String> letters = new ArrayList<>();
                ArrayList<String> sentence = new ArrayList<>();//for p2, keep track of spaces as well
                //use first patter to find all letters not in checksum
                while(m.find()){
                    //System.out.println("group count is " + m.groupCount());
                    for(int i = 1; i <= m.groupCount(); i++){
                        //System.out.print(i);
                        if(i%2 == 1) {
                            letters.add(m.group(i));
                            sentence.add(m.group(i));
                            sentence.add(" ");
                        }
                            //letters.add(" ");
                        //System.out.println(m.group(i));
                        //if(i == m.groupCount()) System.out.println(m.group(i));
                    }
                }
                
                StringBuffer sBuffer = new StringBuffer("");
                
                for(String letterGroup: letters){
                    //System.out.print(letterGroup);
                    sBuffer.append(letterGroup);
                    
                }
                
                String theName = sBuffer.toString();
                //System.out.println("The encrypted name is " + theName);
                
                
                //for p2
                StringBuilder sb = new StringBuilder("");
                for(String s: sentence){
                    //System.out.print(s);
                    sb.append(s);
                }
                String fullSentence = sb.toString();
                //System.out.println("The sentence is " + fullSentence);
                
                //find sectorID
                Pattern numPattern = Pattern.compile("(\\d*)\\[");
                Matcher numMatcher = numPattern.matcher(nextLine);
                
                
                
                while(numMatcher.find()){
                    sectorID = Integer.parseInt(numMatcher.group(1));
                }
                
                //System.out.println("The sector ID is " + sectorID);
                
                //find the checksum
                Pattern pattern2 = Pattern.compile("\\[(\\w*)\\]$");
                Matcher m2 = pattern2.matcher(nextLine);
                
                String checksum = "";
                
                while(m2.find()){
                    for(int i = 1; i <= m2.groupCount(); i++){
                        //System.out.println(m2.group(i));
                        checksum = m2.group(i);
                    }
                }
                
                //System.out.println("checksum = " + checksum);
                
                Map<Character, Integer> dictionary = buildDictionary(theName);
                
                //dictionary = sortDictionary(dictionary);
                
                if(checkEncName(theName, dictionary, checksum)){//if the room is a real room
                    sectorIdTot += sectorID;
                    //System.out.println("current total is " + sectorIdTot);
                    //System.out.println("This is a valid room");
                    
                    fullSentence = cipher(fullSentence, sectorID);
                    
                    if(fullSentence.contains("northpole")) {//p2 find correct room
                        System.out.println(fullSentence);
                        System.out.println("The id where the items are stored is " + sectorID);
                    }
                        
                }
            }
        }
        //System.out.println(rightRoom);
        System.out.println("The sector ID total is: " + sectorIdTot);
    }
    
   
    //will decipher the shift cipher
    String cipher(String sentence, int offset) {
        String s = "";
        for(int i = 0; i < sentence.length(); i++) {
          char c = (char)(sentence.charAt(i));
          if (c >= 'A' && c <= 'Z') {     
            s += (char)((c - 'A' + offset) % 26 + 'A');
          } else if (c >= 'a' && c <= 'z') {
            s += (char)((c - 'a' + offset) % 26 + 'a');
          } else {
            s += c;
          }
        }
        return s;
      }
    
    public Map<Character, Integer> sortDictionary(Map<Character, Integer> dictionary){
        //System.out.println("reached the sortDictionary function");
        Map<Character, Integer> map = new TreeMap<Character, Integer>(dictionary);
        
        //System.out.println("In the sorting function:");
        for (Character key : map.keySet()) { 
           // do something
            //System.out.println(key);
        }
        
        return map;
    }
    
    public Map<Character, Integer> buildDictionary(String name){
         Map<Character, Integer> dictionary = new HashMap<Character, Integer>();
        
        //build dictionary that will contain counts of characters
        for(char c: name.toCharArray()){
            Character theCharacter = new Character(c);
            if(dictionary.containsKey(theCharacter)) {
                    //String charString = Character.toString(c);
                    //Integer val = dictionary.get(theCharacter);
                    //System.out.println(dictionary.get(theCharacter));
                    dictionary.put(theCharacter, dictionary.get(theCharacter) + 1);
                }
            else{
                Integer i = 1;
                dictionary.put(theCharacter, i);
            }
        }
        
        /*
        for(Character key: dictionary.keySet())
            System.out.println(key + ": " + dictionary.get(key));
        */
                
        
        return dictionary;
    }
    
    //do I need the String name??
    public Boolean checkEncName(String name, Map<Character, Integer> dictionary, String checksum){
        
        
        //find highest repeating letter
        Integer maxVal = 0;
        for(Character key: dictionary.keySet()){
           Integer val = dictionary.get(key);
           if(maxVal < val){
               maxVal = val;
           }
       }
        //System.out.println("Most common letter repeats : " + maxVal);
        
        //check all letters down to 0 (stop after 5 letters)
        
        int lettersCount = 0;
        ArrayList<Character> charsToCheck = new ArrayList<>();
        //ArrayList<Integer> associatedCounts = new ArrayList<>();
        
        
        for(Integer i = maxVal; i > 0; i--){
            Map<Character, Integer> tempDic = new HashMap<Character, Integer>();
            for(Character key: dictionary.keySet()){
                Integer val = dictionary.get(key);
                
                
                
                if(val == i){
                    //System.out.println("there are " + i + " " + key);
                    tempDic.put(key, i);
                    //charsToCheck.add(key);
                    //associatedCounts.add(val);
                    lettersCount++;
                }
            }
            
            
            //System.out.println("i changed");
             tempDic = sortDictionary(tempDic);
            
            //System.out.println("after sort:");
            for(Character c: tempDic.keySet()){
                //System.out.println(c);
                charsToCheck.add(c);
            }
            
            //sort the dictionary
            if(lettersCount > 5) break;
            
        }
        
        /*
        System.out.println("");
        for(Character c: charsToCheck){
            System.out.print(c);
        }
        System.out.println("");
        
        */
        return(compareCheckSum(charsToCheck, checksum));//return whether or not the checksums match
        
    }
    
    int rightRoom = 0;
    
    public Boolean compareCheckSum(ArrayList<Character> chars, String checksum){
        int compareCount = 0;
        
        for(int i = 0; i < 5; i++){
            if(checksum.charAt(i) == chars.get(i)) compareCount++;
        }
        
        if(compareCount == 5) {
            rightRoom++;
            return true;
        }
        else return false;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day4 d4 = new Day4();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        
        d4.parseInput(sc);
    }
    
}
