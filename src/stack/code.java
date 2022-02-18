package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class code {
    static boolean pairedParentheses(String s){
        int count = 0;
        for (char character : s.toCharArray()){
            if (character == '(') {
                count++;
            } else if (character == ')'){
                if (count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }
    static boolean befittingBrackets(String s) {

        Stack<Character> stack = new Stack<>();

        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        for(char character : s.toCharArray()) {
            if(map.containsKey(character) && !stack.isEmpty()){
                // for closing brackets we confirm if it has a corresponding opening bracket at the top of the stack
                int value = stack.pop();
                if(map.get(character) != value) return false;
            } else stack.push(character); // always pushing the opening brackets
        }


        return stack.isEmpty();

    }

    static String decompressBraces(String s){
        String numberCharacters = "123456789";
        Stack<String> stack = new Stack<>();
        for (char character : s.toCharArray()){
            if (numberCharacters.indexOf(character) != -1){
                stack.push(String.valueOf(character)); // number character btwn 1 - 9
            } else {
                // closing brace. this is what we care about, deeper logic
                if (character == '}') {
                    // popping sub ; keeping popping until a number appears
                    StringBuilder segment = new StringBuilder();
                    while (numberCharacters.contains(stack.peek())){
                        String popped = stack.pop();
                        segment.insert(0, popped);
                    }
                    // top of the stack is now a number
                    int number = Integer.parseInt(stack.pop());
                    stack.push(repeat(segment.toString(), number));
                } else if (character != '{') {
                    // alpha character here i.e not a number, not open brace, not close brace
                    stack.push(String.valueOf(character));
                }
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) result.insert(0, stack.pop());
        return result.toString();
    }
    static String repeat(String s, int n){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i ++) result.append(s);
        return result.toString();
    }

    static int nestingScore(String s){
        Stack<Integer> stack = new Stack<>();
        // start with a zero at top of stack
        stack.push(0);
        for (char character : s.toCharArray()){
            if (character =='[') {
                // just push a zero
                stack.push(0);
            } else {
                int popped = stack.pop();
                if (popped == 0) {
                    // top is zero, just a close brace, add one to the current top
                    int currentTop = stack.pop();
                    stack.push(currentTop + 1);
                } else {
                    // there is a nesting of braces if current top is not zero hence multiply by two
                    int currentTop = stack.pop();
                    stack.push(currentTop * 2);
                }
            }
        }
        return stack.pop();
    }
}
