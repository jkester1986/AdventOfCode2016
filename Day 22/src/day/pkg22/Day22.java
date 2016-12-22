/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg22;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day22 {

    Node[][] compNodes = new Node[34][30];//do I need this????
    ArrayList<Node> nodeList = new ArrayList();
    
    int lowest = 0;
    
    int count = 0;
    
    public void fillNodes(Scanner sc){
        Pattern p = Pattern.compile(".*(\\d+)T.*\\s(\\d+)T.*\\s(\\d+)T.*\\s(\\d+)%$");
        for(int y = 0; y < 30; y++){
            for(int x = 0; x < 34; x++){
                String data = sc.nextLine();
                Matcher m = p.matcher(data);
                m.find();
                
                Node n = new Node(x, y,
                        Integer.parseInt(m.group(1)), 
                        Integer.parseInt(m.group(2)), 
                        Integer.parseInt(m.group(3)), 
                        Integer.parseInt(m.group(4)));
                
                if(x == 33 && y == 0) n.hasData = true;
                
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
        System.out.println("There are " + pairs + " pairs now");
        return nodePairs;
    }
    
    public ArrayList<Node[]> borderPairs(){
        int pairs = 0;
        
        ArrayList<Node[]> nodePairs = new ArrayList();
        
        for(Node n: nodeList){
            if(n.used != 0){
                for(Node c: nodeList){
                    if(!n.equals(c) && n.used <= c.available){
                        if((n.x + 1 == c.x && n.y == c.y) ||
                           (n.x - 1 == c.x && n.y == c.y) ||
                           (n.x == c.x && n.y - 1 == c.y) ||
                           (n.x == c.x && n.y + 1 == c.y)){
                                pairs++;
                                Node[] node = {n, c};
                                nodePairs.add(node);
                        }
                        
                    }
                }
            }
        }
        System.out.println("There are " + pairs + " adjacent pairs now");
        return nodePairs;
    }
    
    public void countSteps(ArrayList<Node[]> pairs){
        count ++;
        System.out.println("count: " + count);
        if(count < lowest || lowest == 0){
            for(Node[] nPair: pairs){
                Node a = new Node(nPair[0]);
                Node b = new Node(nPair[1]);

                //move contents, check to see if b has the data we want
                Node tempA = new Node(a);
                Node tempB = new Node(b);
                int indA = nodeList.indexOf(a);
                int indB = nodeList.indexOf(b);

                b.used += a.used;
                b.available -= a.used;
                b.hasData = a.hasData;

                if(b.x == 33 && b.y == 0 && b.hasData == true){
                    if(lowest == 0){
                        lowest = count;
                        return;
                    }
                    else if(count < lowest){
                        lowest = count;
                        return;
                    }
                    else return;
                }

                //change a and b in nodelist (keep temp??)
                nodeList.get(indA).available = a.size;
                nodeList.get(indA).used = 0;
                nodeList.get(indA).hasData = false;

                nodeList.set(indB, b);

                //find all new border pairs and iterate through (with return)
                ArrayList<Node[]> adjacentPairs = borderPairs();
                countSteps(adjacentPairs);

                //undo everything
                nodeList.set(indA, tempA);
                nodeList.set(indB, tempB);
            }
        }
        count--;
        return;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day22 d22 = new Day22();
        Scanner sc = new Scanner(System.in);
        System.out.println("what is your input?");
        
        d22.fillNodes(sc);
        ArrayList<Node[]> firstSet = d22.createPairs();
        
        ArrayList<Node[]> adjacentPairs = d22.borderPairs();
        
        d22.countSteps(adjacentPairs);
        System.out.println("lowest amount of steps: " + d22.lowest);
    }
    
}
