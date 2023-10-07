import java.util.*;
public class posttoprefix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter you postfix string :");
        String s=sc.nextLine();
        System.out.println(preToPost(s));

    }
    public static String preToPost(String pre_exp) {
        Stack<String> s = new Stack<>();
        
        // Step 1: Start iterating through the prefix expression from right to left
        for (int i=0;i<pre_exp.length(); i++) {
            char current = pre_exp.charAt(i);
            
            // Step 2: If the current character is a letter (operand), push it onto the stack
            if ((current >= 'a' && current <= 'z') || (current >= 'A' && current <= 'Z')) {
                s.push(String.valueOf(current));
            }
            // Step 3: If the current character is an operator
            else {
                // Step 4: Pop the first operand (fop) and the second operand (sop) from the stack
                String fop = s.pop();
                String sop = s.pop();
                
                // Step 5: Create a new string by concatenating fop, sop, and the current operator
                s.push(current+sop+fop);
            }
        }
        
        // Step 6: The final result in the stack is the postfix expression
        return s.pop();
    }

}
