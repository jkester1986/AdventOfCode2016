/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg6;

import java.util.*;

/**
 *
 * @author jkester
 */
public class Day6 {

    /**
     * @param args the command line arguments
     */
    ArrayList<Map<Character, Integer>> dictionaries = new ArrayList<>();
    
    
    
    
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
    
    public void parseInput(Scanner sc){
        
        while(sc.hasNext()){
            String line = sc.nextLine();
            
            if(line.equals("exit")){
               break; 
            }
            else{
                int i = 0;
                while(i < 8){
                    System.out.println("index is + " + i);
                    buildDictionary(line.charAt(i), dictionaries.get(i));
                    i++;
                }
            }
            
        }
    }
    
    public Map<Character, Integer> buildDictionary(char c, Map<Character, Integer> dictionary){
         //Map<Character, Integer> dictionary = new HashMap<Character, Integer>();
        
        //build dictionary that will contain counts of characters
        
            
            if(dictionary.containsKey(c)) {
                    //String charString = Character.toString(c);
                    //Integer val = dictionary.get(theCharacter);
                    //System.out.println(dictionary.get(theCharacter));
                    dictionary.put(c, dictionary.get(c) + 1);
                }
            else{
                Integer i = 1;
                dictionary.put(c, i);
            }
        return dictionary;
    }
    
    public Character getMaxChar(Map<Character, Integer> dictionary){
        Integer highest = 0;
        Character highChar = '0';
        
        for(Character c: dictionary.keySet()){
            Integer val = dictionary.get(c);
            if(val > highest){
                highest = val;
                highChar = c;
            }
        }
        
        return highChar;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day6 d6 = new Day6();
        
        for(int i = 0; i < 8; i++){
            Map<Character, Integer> dictionary = new HashMap<>();
            d6.dictionaries.add(dictionary);
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        
        d6.parseInput(sc);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++){
            sb.append(d6.getMaxChar(d6.dictionaries.get(i)));
        }
        System.out.println("The word is " + sb);
        
    }
    
}
