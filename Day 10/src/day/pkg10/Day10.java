/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg10;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 *
 * @author jkester
 */
public class Day10 {

    /**
     * @param args the command line arguments
     */
    //TreeMap<Integer, int[]> bots = new TreeMap();
    //Integer = bot#; int size 2 -> first val, second val
    
    TreeMap<Integer, Bot> bots = new TreeMap();
    
    TreeMap<Integer, Integer> outputs = new TreeMap();
    
    //TreeMap<Integer, Integer> inputVals = new TreeMap();
    //first val robot, second value chip#
    ArrayList<String> directions = new ArrayList();
    
    public void parseInput(Scanner sc){
        Pattern startVal = Pattern.compile("^value\\s(\\d+)\\s.+\\s(\\d+)$");
        
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("exit")) break;
            else{
                Matcher startPat = startVal.matcher(line);
                
                if(startPat.matches()){
                    //System.out.println(line);
                    
                    int botNum = Integer.parseInt(startPat.group(2));
                    int chipNum = Integer.parseInt(startPat.group(1));
                    
                    //add holding chips to the bots
                    if(bots.containsKey(botNum)){//if the bot already exists
                        bots.get(botNum).addChip(chipNum);
                    }
                    else{
                        Bot newBot = new Bot(botNum, chipNum, 0);
                        bots.put(botNum, newBot);
                    }
                }
                else{
                    directions.add(line);
                }
            }
        }
        
    }
    
    public void followDirs(){
        Pattern dir = Pattern.compile("^bot\\s(\\d+)\\s.+\\s(\\w+)\\s(\\d+)\\s.+\\s(\\w+)\\s(\\d+)$");
        
        while(!directions.isEmpty()){//keep going through the directions until they are all done
            
            ArrayList<String> removeDirs = new ArrayList();
            for(String s: directions){
                int dirsFollowed = 0;
                Matcher m = dir.matcher(s);
                m.find();
                int botNum = Integer.parseInt(m.group(1)); 
                
                String whereHigh = m.group(2);
                int whereHNum = Integer.parseInt(m.group(3));
                
                String whereLow = m.group(4);
                int whereLNum = Integer.parseInt(m.group(5));
                
                if(!bots.containsKey(botNum)){//if current bot number doesn't exist,
                    //create new one
                    Bot newBot = new Bot(botNum, 0, 0);
                    bots.put(botNum, newBot);
                }
                else{
                    Bot currentBot = bots.get(botNum);
                    if(currentBot.getHolding() == 2){
                        if (currentBot.holds(61, 17)){
                            System.out.println("The bot holding 61 and 17 is " + currentBot.id);
                            //return;//we're done
                        }
                        
                        //now let's go through the directions on the line
                        followDirections(currentBot, whereHigh, whereHNum, whereLow, whereLNum);
                        removeDirs.add(s);//add s to list of directions to remove
                       
                    }
                    else{//current Bot doesn't have 2 chips yet
                        //do nothing, we want to repeat this direction later
                    }
                }
            }
            for(String s: removeDirs){
                directions.remove(s);
            }
        }
    }
    
    public void printOutputs(){
        for (Map.Entry<Integer, Integer> entry : outputs.entrySet()) {
            System.out.println("Output #" + entry.getKey() + " holds chip " +  entry.getValue());
        }
    }
    
    public void followDirections(Bot bot, String botOrOutputLow, int idLow, String botOrOutputHigh, int idHigh){
     
        int highChip = bot.highest();
        int lowChip = bot.lowest();
        
        //for low value
        switch(botOrOutputLow){
            case "output":
                outputs.put(idLow, lowChip);
                break;
            case "bot":
                if(!bots.containsKey(idLow)){//if current bot number doesn't exist,
                    //create new one
                    Bot newBot = new Bot(idLow, lowChip, 0);
                    bots.put(idLow, newBot);
                    
                }
                else{
                    Bot gettingBot = bots.get(idLow);
                    gettingBot.addChip(lowChip);
                    bots.put(idLow, gettingBot);
                }
                break;
            default:
                System.out.println("something went wrong, no correct direction given");
                break;
        }
        
        //for high value
        switch(botOrOutputHigh){
            
            case "output":
                outputs.put(idHigh, highChip);
                break;
            case "bot":
                if(!bots.containsKey(idHigh)){//if current bot number doesn't exist,
                    //create new one
                    Bot newBot = new Bot(idHigh, highChip, 0);
                    bots.put(idHigh, newBot);
                }
                else{
                    Bot gettingBot = bots.get(idHigh);
                    gettingBot.addChip(highChip);
                    bots.put(idHigh, gettingBot);
                }
                break;
            default:
                System.out.println("something went wrong, no correct direction given");
                break;
        }
    }
    
    public boolean oneOfEquals(int a, int b, int expected) {
        return (a == expected) || (b == expected);
    }
    
    public static void main(String[] args) {
        Day10 d10 = new Day10();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        
        d10.parseInput(sc);
        d10.followDirs();
        //d10.printOutputs();
        System.out.println("product of output 0, 1, and 2 are: " + d10.outputs.get(0)*d10.outputs.get(1)*d10.outputs.get(2));
    }
    
}
