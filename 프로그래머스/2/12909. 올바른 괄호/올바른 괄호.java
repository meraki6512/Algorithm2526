import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> stack = new Stack();
        
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c);
            else if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
            else {
                answer = false;
                break;
            }
        }
        
        if (!stack.isEmpty()) answer = false;

        return answer;
    }
}