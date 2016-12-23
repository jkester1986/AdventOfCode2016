/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg22;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day22 {

    Node[][] compNodes = new Node[34][30];//do I need this????
    ArrayList<Node> nodeList = new ArrayList();
    
    Node empty;
    int wallYIndex;
    
    int lowest = 0;
    
    
    public void fillNodes(Scanner sc){
        Pattern p = Pattern.compile(".*\\s(\\d+)T.*\\s(\\d+)T.*\\s(\\d+)T.*\\s(\\d+)%$");
        for(int x = 0; x < 34; x++){
            for(int y = 0; y < 30; y++){
                String data = sc.nextLine();
                Matcher m = p.matcher(data);
                m.find();
                
                //System.out.println(x + ", " + y);
                
                Node n = new Node(x, y,
                        Integer.parseInt(m.group(1)), 
                        Integer.parseInt(m.group(2)), 
                        Integer.parseInt(m.group(3)), 
                        Integer.parseInt(m.group(4)));
                
                if(x == 0 && y == 29) n.hasData = true;
                if(Integer.parseInt(m.group(2)) == 0) empty = n;
                if(n.size >= 500) wallYIndex = y;
                
                compNodes[x][y] = n;
                nodeList.add(n);
            }
        }
    }
    
    public ArrayList<Node[]> createPairs(){
        int pairs = 0;
        
        ArrayList<Node[]> nodePairs = new ArrayList();
        
        for(Node n: nodeList){
            if(n.used != 0){
                for(Node c: nodeList){
                    if(!n.equals(c) && n.used <= c.available){
                        pairs++;
                        Node[] node = {n, c};
                        nodePairs.add(node);
                    }
                }
            }
        }
        //System.out.println("There are " + pairs + " pairs now");
        return nodePairs;
    }
    
    public ArrayList<Node[]> borderPairs(ArrayList<Node> list){
        int pairs = 0;
        
        ArrayList<Node[]> nodePairs = new ArrayList();
        
        for(Node n: list){
            if(n.used != 0){
                for(Node c: list){
                    if(!n.equals(c) && n.used <= c.available){
                        if((n.x + 1 == c.x && n.y == c.y) ||
                           (n.x - 1 == c.x && n.y == c.y) ||
                           (n.x == c.x && n.y - 1 == c.y) ||
                           (n.x == c.x && n.y + 1 == c.y)){
                                pairs++;
                                Node tempN = new Node(n);
                                Node tempC = new Node(c);
                                Node[] node = {n, c};
                                nodePairs.add(node);
                        }
                    }
                }
            }
        }
        //System.out.println("There are " + pairs + " adjacent pairs now");
        return nodePairs;
    }
    
    public void countSteps(){
        int count = 0;
        boolean hitWall = false;
        boolean passedWall = false;
        
        boolean reachedX32 = false;
        
        System.out.println("wall is at y=" + wallYIndex);
        System.out.println("empty is at y = " + empty.y);
        count = empty.y - (wallYIndex + 1);//steps to get to the wall
        count +=5;//steps to get around the wall
        //should be at 1,1 now. need to get to 32
        count += 32-1;//right above where we should be
        count ++;//right in front of the data we need to move
        System.out.println("the count is " + count);
        
        count++;
        for(int i = 32; i > 0; i--){//do all the rotations from here
            count +=5;
        }
        System.out.println("total count: " + count);
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day22 d22 = new Day22();
        Scanner sc = new Scanner(System.in);
        System.out.println("what is your input?");
        
        d22.fillNodes(sc);
        ArrayList<Node[]> firstSet = d22.createPairs();
        
        ArrayList<Node[]> adjacentPairs = d22.borderPairs(d22.nodeList);
        for(Node[] node: adjacentPairs){
            System.out.println("Node A: " + node[0] + " Node B: " + node[1]);
            ArrayList<Node[]> newList = new ArrayList(adjacentPairs);
        }
        
        for(int y = 0; y < 30; y++){
            for (int x = 0; x < 34; x++){
                System.out.print(d22.compNodes[x][y].used + "/" + d22.compNodes[x][y].size + "     ");
                
            }
            System.out.print("\n");
        }
        
        
        d22.countSteps();

        
    }
    
}
