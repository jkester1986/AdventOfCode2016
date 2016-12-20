/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg20;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day20 {

    SortedMap<Long, Long> ranges = new TreeMap<Long, Long>();
    
    public void parseInput(Scanner sc){
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("exit")) 
                break;
            else{
                Pattern p = Pattern.compile("^(\\d+)\\-(\\d+)$");
                Matcher m = p.matcher(line);
                m.find();
                
                Long low = Long.parseLong(m.group(1));
                Long high = Long.parseLong(m.group(2));
                
                //ranges.put(low, high);
                
                if(ranges.isEmpty()){
                    ranges.put(low, high);
                    //System.out.println("the set was empty");
                }
                else{
                    Long lowRange = 0L;
                    Long highRangeKey = 0L;
                    boolean foundLow = false;
                    boolean foundHigh = false;
                    
                    for (Long key: ranges.keySet()){
                        if(!foundLow){
                            if(low >= key && low <= ranges.get(key)){
                                if(high < ranges.get(key)){
                                    foundHigh = true;
                                    foundLow = true;
                                    highRangeKey = key;
                                    lowRange = key;
                                    break;
                                }//the range is already within an existing range
                                else{
                                    lowRange = key;
                                    foundLow = true;
                                }
                            }
                        }
                        if(!foundHigh){
                            if(high >= key && high <= ranges.get(key)){
                                highRangeKey = key;
                                foundHigh = true;
                            }
                        }
                        if(foundHigh && foundLow)
                            break;
                    }
                    
                    if(foundHigh && foundLow){
                        //if found ranges that both the high and low fit into
                        //System.out.println("found high and low");
                        if(lowRange == highRangeKey){//if the current low/high range is within a range already
                            //do nothing
                        }
                        else{//take the low key and the value of the high key and combine
                            Long highVal = ranges.get(highRangeKey);
                            
                            ranges.put(lowRange, highVal);
                            ranges.remove(highRangeKey);
                        }
                        
                    }
                    else if(foundLow){
                        //System.out.println("only low fit in a range");
                        //only found a range that the low number fit into
                        ranges.put(lowRange, high);
                    }
                    
                    else if(foundHigh){
                        //only found a range that the high number fit into
                        //System.out.println("only high fit in a range");
                        Long highVal = ranges.get(highRangeKey);
                            
                        ranges.put(low, highVal);
                        ranges.remove(highRangeKey);
                    }
                    else{//not in a range at all, need to add to the set
                        ranges.put(low, high);
                    }
                }

            }
        }
    }
    
    public void combineRanges(){
        SortedMap<Long, Long> shortenedRanges = new TreeMap<Long, Long>();
        Long prevHigh = null;
        Long lowRange = null;
        
        Iterator<Map.Entry<Long, Long>> entries = ranges.entrySet().iterator();
        
        while(entries.hasNext()){
            Map.Entry<Long, Long> entry = entries.next();
            Long key = entry.getKey();
            Long val = entry.getValue();
            if(lowRange == null){//if no low range has been set yet
                lowRange = key;
            }
            
            if(prevHigh != null){
                if(key-1 == prevHigh){
                    prevHigh = val;
                    if(!entries.hasNext()){
                        shortenedRanges.put(lowRange, prevHigh);
                    }
                }
                else{
                    shortenedRanges.put(lowRange, prevHigh);
                    lowRange = key;
                    prevHigh = val;
                    if(!entries.hasNext()){
                        shortenedRanges.put(lowRange, prevHigh);
                    }
                }
            }
            else{
                prevHigh = val;
            }
        }
        
        ranges = new TreeMap<Long, Long>(shortenedRanges);
    }
    
    public void combineMore(){
        Iterator<Map.Entry<Long, Long>> entries = ranges.entrySet().iterator();
        Map.Entry<Long, Long> previous = entries.next();
        
        SortedMap<Long, Long> shortened = new TreeMap<Long, Long>();
        shortened.put(previous.getKey(), previous.getValue());
        
        while(entries.hasNext()){
            Map.Entry<Long, Long> entry = entries.next();
            if(previous.getKey() < entry.getKey() && previous.getValue() > entry.getValue()){
                //do nothing, we don't want these
            }
            else
                shortened.put(previous.getKey(), previous.getValue());
            
            previous = entry;
        }
        
        ranges = new TreeMap<Long, Long>(shortened);
    }
    
    public void fixOverlap(){
        SortedMap<Long, Long> shortened = new TreeMap<Long, Long>();
        
        Iterator<Map.Entry<Long, Long>> entries = ranges.entrySet().iterator();
        Map.Entry<Long, Long> entry = entries.next();
        
        Long low = entry.getKey();
        Long high = entry.getValue();
        
        while(entries.hasNext()){
            entry = entries.next();
            Long nextLow = entry.getKey();
            Long nextHigh = entry.getValue();
            
            if(low <= nextLow && nextHigh <= high){
                //do nothing
            }
            else if(low < nextLow && nextLow < high && high < nextHigh){
                high = nextHigh;
            }
            else{
                shortened.put(low, high);
                low = nextLow;
                high = nextHigh;
            }
            if(!entries.hasNext()){
                shortened.put(low, high);
            }
        }
        
        ranges = new TreeMap<Long, Long>(shortened);
        
    }
    
    public int numValid(){
        Iterator<Map.Entry<Long, Long>> entries = ranges.entrySet().iterator();
        
        Long lowerVal = entries.next().getValue();
        
        int count = 0;
        
        while(entries.hasNext()){
            Map.Entry<Long, Long> entry = entries.next();
            Long upperVal= entry.getKey();
            
            
            Long tempCount = 0L;
            for(Long i = lowerVal+1; i < upperVal; i++){
                count++;
                tempCount ++;
            }
            
            lowerVal = entry.getValue();
            if(!entries.hasNext()){
                tempCount = 0L;
                for(Long i = lowerVal+1; i <= 4294967295L; i++){
                    count++;
                    tempCount++;
                }
            }
            
        }
        return count;
    }
    
    public static void main(String[] args) {
        Day20 d20 = new Day20();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        d20.parseInput(sc);
        
        System.out.println("");
        

        d20.combineRanges();
        //d20.combineMore();
        d20.fixOverlap();
        
        System.out.println("The lowest valid number is: " + (d20.ranges.get(0L)+1));
        
        System.out.println(d20.numValid() + " numbers are valid");
        
    }
    
}
