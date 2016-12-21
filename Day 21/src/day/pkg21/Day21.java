/*
//This puzzle was extremely tedious......
*/
package day.pkg21;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class Day21 {

    String input = "abcdefgh";
    
    Pattern swapPosP = Pattern.compile("^swap\\sposition\\s(\\d).+(\\d)$");
    Pattern swapLetP = Pattern.compile("^swap\\sletter\\s(\\w).+(\\w)$");
    Pattern rotateLRP = Pattern.compile("^rotate\\s(\\w+)\\s(\\d)\\ssteps?$");
    Pattern rotateBasedP = Pattern.compile("^rotate\\sbased.*(\\w)$");
    Pattern reverseP = Pattern.compile("^reverse\\spositions\\s(\\d).+(\\d)$");
    Pattern moveP = Pattern.compile("^move.+(\\d).+(\\d)$");
    
    ArrayList<String> directions = new ArrayList();
    
    public void parseReverse(){
        input = "fbgdceah";
        int size = directions.size();
        for(int i = size-1; i >= 0;i--){
            String direction = directions.get(i);
            if(!direction.equals("exit")){
                Matcher mSwapPos = swapPosP.matcher(direction);
                Matcher mSwapLet = swapLetP.matcher(direction);
                Matcher mRotateLR = rotateLRP.matcher(direction);
                Matcher mRotateBased = rotateBasedP.matcher(direction);
                Matcher mReverse = reverseP.matcher(direction);
                Matcher mMove = moveP.matcher(direction);
                
                
                
                if(mSwapPos.matches()){
                    int posX = Integer.parseInt(mSwapPos.group(1));
                    int posY = Integer.parseInt(mSwapPos.group(2));
                    char xChar= input.charAt(posX);
                    char yChar = input.charAt(posY);
                    
                    char[] stringArr = input.toCharArray();
                    stringArr[posX] = yChar;
                    stringArr[posY] = xChar;
                    
                    input = String.valueOf(stringArr);
                }
                else if(mSwapLet.matches()){
                    String xChar = mSwapLet.group(1);
                    String yChar = mSwapLet.group(2);
                    int posX = input.indexOf(xChar);
                    int posY = input.indexOf(yChar);
                    
                    
                    char[] stringArr = input.toCharArray();
                    stringArr[posX] = yChar.charAt(0);
                    stringArr[posY] = xChar.charAt(0);
                    
                    input = String.valueOf(stringArr);
                }
                else if(mRotateLR.matches()){
                    
                    switch(mRotateLR.group(1)){
                        case "right":
                            int lShift = Integer.parseInt(mRotateLR.group(2));
                            if(lShift != 0 && lShift != input.length())
                                input = new String(input.substring(lShift) + input.substring(0, lShift));
                            break;
                        case "left":
                            int rShift = Integer.parseInt(mRotateLR.group(2));
                            
                            if(rShift != 0 && rShift != input.length())
                                input = new String(input.substring(input.length()-rShift) + input.substring(0, input.length()-rShift));
                                    
                            break;
                        default:
                            System.out.println("something went wrong, idk what direction to rotate");
                            break;
                    }
                }
                //this is changed
                else if(mRotateBased.matches()){
                    String l = mRotateBased.group(1);
                    int rotation = input.indexOf(l);
                    
                    switch(rotation){
                        case 0:
                            rotation = 7;
                            break;
                        case 1:
                            rotation = -1;
                            break;
                        case 2: 
                            rotation = 2;
                            break;
                        case 3:
                            rotation = -2;
                            break;
                        case 4:
                            rotation = 1;
                            break;
                        case 5:
                            rotation = -3;
                            break;
                        case 6:
                            rotation = 0;
                            break;
                        case 7:
                            rotation = -4;
                            break;
                        default:
                            System.out.println("uh oh, didn't get a good index");
                            break;
                    }
                    
                    //rotation = rotation%input.length();
                    if(rotation < 0)
                        rotation = input.length()+rotation;
                    
                    if(rotation != 0 && rotation != input.length()){
                        String s1 = input.substring(input.length()-rotation);
                        String s2 = input.substring(0, input.length()-rotation);
                        input = s1 + s2;
                    }
                }
                else if(mReverse.matches()){
                    int pos1 = Integer.parseInt(mReverse.group(1));
                    int pos2 = Integer.parseInt(mReverse.group(2));
                    
                    String sb = new StringBuilder(input.substring(pos1, pos2+1)).reverse().toString();
                    if(pos2 != input.length()) 
                        input = new String(input.substring(0, pos1) + sb + input.substring(pos2+1));
                    else
                        input = new String(input.substring(0, pos1) + sb);
                }
                else if(mMove.matches()){
                    int pos1 = Integer.parseInt(mMove.group(2));
                    int pos2 = Integer.parseInt(mMove.group(1));
                    
                    String s = String.valueOf(input.charAt(pos1));
                    
                    if(pos1 < pos2){
                        
                        if(input.length() == pos2+1)
                            input = new String(input.substring(0, pos1) + input.substring(pos1+1, pos2+1) + s);
                        else 
                            input = new String(input.substring(0, pos1) + input.substring(pos1+1, pos2+1) + s + input.substring(pos2+1));
                    }
                    else
                        input = new String(input.substring(0, pos2) + s + input.substring(pos2, pos1) + input.substring(pos1+1));
                }
                else System.out.println("Something is wrong, didn't catch this direction (p2): " + direction);
            }
            else break;
        }
    }
    
    public void parseDirections(Scanner sc){
        while(sc.hasNext()){
            String direction = sc.nextLine();
            if(!direction.equals("exit")){
                directions.add(direction);
                Matcher mSwapPos = swapPosP.matcher(direction);
                Matcher mSwapLet = swapLetP.matcher(direction);
                Matcher mRotateLR = rotateLRP.matcher(direction);
                Matcher mRotateBased = rotateBasedP.matcher(direction);
                Matcher mReverse = reverseP.matcher(direction);
                Matcher mMove = moveP.matcher(direction);
                
                if(mSwapPos.matches()){
                    int posX = Integer.parseInt(mSwapPos.group(1));
                    int posY = Integer.parseInt(mSwapPos.group(2));
                    char xChar= input.charAt(posX);
                    char yChar = input.charAt(posY);
                    
                    char[] stringArr = input.toCharArray();
                    stringArr[posX] = yChar;
                    stringArr[posY] = xChar;
                    
                    input = String.valueOf(stringArr);
                }
                else if(mSwapLet.matches()){
                    String xChar = mSwapLet.group(1);
                    String yChar = mSwapLet.group(2);
                    int posX = input.indexOf(xChar);
                    int posY = input.indexOf(yChar);
                    
                    
                    char[] stringArr = input.toCharArray();
                    stringArr[posX] = yChar.charAt(0);
                    stringArr[posY] = xChar.charAt(0);
                    
                    input = String.valueOf(stringArr);
                }
                else if(mRotateLR.matches()){
                    switch(mRotateLR.group(1)){
                        case "left":
                            int lShift = Integer.parseInt(mRotateLR.group(2));
                            if(lShift != 0 && lShift != input.length())
                                input = new String(input.substring(lShift) + input.substring(0, lShift));
                            break;
                        case "right":
                            int rShift = Integer.parseInt(mRotateLR.group(2));
                            
                            if(rShift != 0 && rShift != input.length())
                                input = new String(input.substring(input.length()-rShift) + input.substring(0, input.length()-rShift));
                                    
                            break;
                        default:
                            System.out.println("something went wrong, idk what direction to rotate");
                            break;
                    }
                }
                else if(mRotateBased.matches()){
                    String l = mRotateBased.group(1);
                    int rotation = input.indexOf(l);
                    if(rotation >= 4){
                        rotation++;
                    }
                    rotation ++;
                    rotation = rotation%input.length();
                    
                    if(rotation != 0 && rotation != input.length()){
                        String s1 = input.substring(input.length()-rotation);
                        String s2 = input.substring(0, input.length()-rotation);
                        input = s1 + s2;
                    }
                }
                else if(mReverse.matches()){
                    int pos1 = Integer.parseInt(mReverse.group(1));
                    int pos2 = Integer.parseInt(mReverse.group(2));
                    
                    String sb = new StringBuilder(input.substring(pos1, pos2+1)).reverse().toString();
                    if(pos2 != input.length()) 
                        input = new String(input.substring(0, pos1) + sb + input.substring(pos2+1));
                    else
                        input = new String(input.substring(0, pos1) + sb);
                }
                else if(mMove.matches()){
                    int pos1 = Integer.parseInt(mMove.group(1));
                    int pos2 = Integer.parseInt(mMove.group(2));
                    
                    String s = String.valueOf(input.charAt(pos1));
                    
                    if(pos1 < pos2){
                        
                        if(input.length() == pos2+1)
                            input = new String(input.substring(0, pos1) + input.substring(pos1+1, pos2+1) + s);
                        else 
                            input = new String(input.substring(0, pos1) + input.substring(pos1+1, pos2+1) + s + input.substring(pos2+1));
                    }
                    else
                        input = new String(input.substring(0, pos2) + s + input.substring(pos2, pos1) + input.substring(pos1+1));
                }
                else System.out.println("Something is wrong, didn't catch this direction (p1): " + direction);
            }
            else break;
        }
    }
    
    public static void main(String[] args) {
        Day21 d21 = new Day21();
        Scanner sc = new Scanner(System.in);
        System.out.println("what is your input?");
        d21.parseDirections(sc);
        System.out.println("The scrambled string is: " + d21.input);
        
        d21.parseReverse();
        System.out.println("The unscrambled string is: " + d21.input);
    }
    
}
