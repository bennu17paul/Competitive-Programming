package DailyQuestion;
import java.util.*;

//Time Complexity: O(N)
//Space Complexity: O(N)

public class ParsingABooleanExpression_1106 {
	public boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();

        for (char currChar : expression.toCharArray()) {
            if (currChar == ',' || currChar == '(') continue; 
            if (
                currChar == 't' ||
                currChar == 'f' ||
                currChar == '!' ||
                currChar == '&' ||
                currChar == '|'
            ) {
                st.push(currChar);
            }
            else if (currChar == ')') {
                boolean hasTrue = false, hasFalse = false;

                while (
                    st.peek() != '!' && st.peek() != '&' && st.peek() != '|'
                ) {
                    char topValue = st.pop();
                    if (topValue == 't') hasTrue = true;
                    if (topValue == 'f') hasFalse = true;
                }

                char op = st.pop();
                if (op == '!') {
                    st.push(hasTrue ? 'f' : 't');
                } else if (op == '&') {
                    st.push(hasFalse ? 'f' : 't');
                } else {
                    st.push(hasTrue ? 't' : 'f');
                }
            }
        }
        return st.peek() == 't';
    }
}
/*
Example 1:
Input: expression = "&(|(f))"
Output: false
Explanation: 
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.

Example 2:
Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.

Example 3:
Input: expression = "!(&(f,t))"
Output: true
Explanation: 
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.
*/