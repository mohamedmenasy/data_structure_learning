import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        boolean isSucceed = false;
        int errorPosition = -1;
        Stack<Bracket> opening_brackets_stack = new Stack<>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
            }
            if (next == ')' || next == ']' || next == '}') {
                Bracket bracket = opening_brackets_stack.pop();
                if (!bracket.Match(next)) {
                    isSucceed = false;
                    errorPosition = position + 1;
                    break;
                }
            }

            if (opening_brackets_stack.empty()) {
                isSucceed = true;
            }
        }
        if (isSucceed) {
            System.out.println("Success");
        } else if (!opening_brackets_stack.empty() && errorPosition == -1) {
            System.out.println(opening_brackets_stack.pop().position + 1);
        } else {
            System.out.println(errorPosition);
        }
    }
}
