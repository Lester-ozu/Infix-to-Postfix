import java.util.Stack;
import java.lang.Math;

public class InfixToPostfixEval {
    
    private Stack <Character> myStack;
    private Stack <Integer> numberStack;
    private char [] symbols;
    private String operands;
    private KeyValue keyValue;

    public InfixToPostfixEval() {

        this.myStack = new Stack<Character>();
        this.numberStack = new Stack<Integer>();
    }

    public KeyValue getKeyValue() {

        return keyValue;
    }

    public String toPostfix(String notation) {

        String output = "";
        boolean absFlag = true;
        symbols = null;

        symbols = notation.toCharArray();

        for (char symbol : symbols) {

            if (symbol == '+'|| symbol == '-' || symbol == '*' || symbol == '/' || symbol == '(' || symbol == ')' || symbol == '^' || symbol == '|') {

                if (symbol == '|') {

                    if(absFlag) {

                        myStack.push(symbol);
                        absFlag = false;
                    }

                    else {

                        while (!myStack.isEmpty() && myStack.peek() != '|') {
                            output += myStack.pop();
                        }

                        myStack.pop();
                        output += "||";
                        absFlag = true;
                    }
                }

                else if (symbol == '(') {

                    myStack.push(symbol);
                }

                else if (symbol == ')') {

                    while (!myStack.isEmpty()  && myStack.peek() != '(') {

                        output += myStack.pop();
                    }
                    myStack.pop();
                }

                else if (myStack.isEmpty()) {

                    myStack.push(symbol);
                }

                else {

                    while(!myStack.isEmpty() && (rank(symbol) < rank(myStack.peek()) || rank(symbol) == rank(myStack.peek()) && isLeftAssociative(symbol))) {

                        output += myStack.pop();
                    }

                    myStack.push(symbol);
                }
            }

            else {

                output += symbol;
            }
        }

        while(!myStack.isEmpty()) {

            output += myStack.pop();
        }

        return output;
    }


    public int evaluate(String notation) {

        boolean flag = true;
        symbols = null;

        symbols = notation.toCharArray();
        int num1 = 0, num2 =0;

        for (char symbol : symbols) {

            if(symbol == '+') {

                num2 = numberStack.pop();
                num1 = numberStack.pop();

                numberStack.push(num1+num2);
            }

            else if(symbol == '-') {

                num2 = numberStack.pop();
                num1 = numberStack.pop();

                numberStack.push(num1-num2);
            }

            else if(symbol == '*') {

                num2 = numberStack.pop();
                num1 = numberStack.pop();

                numberStack.push(num1*num2);
            }

            else if(symbol == '/') {

                num2 = numberStack.pop();
                num1 = numberStack.pop();

                numberStack.push(num1/num2);
            }

            else if(symbol == '|') {

                if(flag) {

                    num1 = numberStack.pop();

                    numberStack.push(Math.abs(num1));
                    flag = false;
                }

                else {

                    flag = true;
                    continue;
                }
            }

            else if (symbol == '^') {

                num2 = numberStack.pop();
                num1 = numberStack.pop();

                numberStack.push((int)Math.pow(num1, num2));
            }

            else {

                numberStack.push(keyValue.getValue(symbol));
            }
        }

        return numberStack.pop();

    }

    public int rank(char e) {

        switch(e) {
            
            case '^':
                return 3;
            case '*':
                return 2;
            case '/':
                return 2;
            case '+':
                return 1;
            case '-':
                return 1;
        }

        return -1;
    }

    public boolean isLeftAssociative(char e) {

        switch (e) {

            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    public String getOperands(String notation) {

        operands = "";

        symbols = notation.toCharArray();

        for (int i = 0 ; i < symbols.length ; i++) {

            if(symbols[i] >= 'A' && symbols[i] <= 'Z') {

                operands += symbols[i];
            }
        }

        keyValue = new KeyValue(operands);

        return operands;
    }
    
}
