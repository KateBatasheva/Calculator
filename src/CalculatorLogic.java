import java.util.LinkedList;

public class CalculatorLogic {

    public CalculatorLogic() {
    }
    static boolean isDelim(char c) { // тру если пробел
        return c == ' ';
    }
    static boolean isOperator(char c) { // возвращяем тру если один из символов ниже
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }
    static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
    static void processOperator(LinkedList<Long> st, char op) {
        long r = st.removeLast(); // take out the last element from the linked list
        long l = st.removeLast();
        switch (op) { // make an action between "l" and "r" depends of operator and put the result into linked list
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
            case '^':
                long res = 1;
                while (r>1) {
                    if (r % 2 == 1) {
                        res *= l;
                    } else {
                        l *= l;
                    }
                    r /= 2;
                }
                if (r>0){
                    res*=l;

                }
                st.add(res);
                break;
        }
    }
    public Long eval(String s) {
        LinkedList<Long> st = new LinkedList<Long>(); // put digits
        LinkedList<Character> op = new LinkedList<Character>(); // put operators
        for (int i = 0; i < s.length(); i++) { // parse the string with the expression and calculate
            char c = s.charAt(i);
            if (isDelim(c))
                continue;
            if (c == '(')
                op.add('(');
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st,op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    operand += s.charAt(i++);
                --i;
                st.add(Long.parseLong(operand));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return st.get(0);  // return the result
    }
}
