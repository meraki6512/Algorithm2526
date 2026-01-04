class Solution {
    public boolean isValid(String s) {

        Map<Character, Character> brackets = Map.of('(',')', '{','}', '[',']');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (brackets.containsKey(c)) {
                stack.push(brackets.get(c));
            }
            else if (brackets.containsValue(c)) {
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) return false;
        return true;
    }
}