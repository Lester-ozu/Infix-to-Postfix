import java.util.Stack;

public class ParenthesesBalancing {
    
    private Stack <Character> myStack;
    private char [] symbols;

    public ParenthesesBalancing() {

        this.myStack = new Stack<Character>();
    }

    public boolean isBalance(String parentheses) {
        
        myStack.clear();

        if(!parentheses.matches("^[(){}\\[\\]]+$")) return false;

        this.symbols = parentheses.toCharArray();
        
        for (char symbol : symbols) {

            if(symbol == '(' || symbol == '[' || symbol == '{') {

                myStack.push(symbol);
            }

            if(symbol == ')' || symbol == ']' || symbol == '}') {

                if(!myStack.isEmpty()) myStack.pop();
                else return false;
            }
        }

        if (myStack.isEmpty()) return true;
        else return false;
    }
}
