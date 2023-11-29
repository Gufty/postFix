import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        String a = "A * B + C * D";
        String b = "A + B + C * D";
        String c = "A * B + ( C + A * B )";
        String d = "A + ( B + C * ( A / C ) )";
        String e = "A + B * C + D * E";
        String f = "B * C + E ^ P + C * F";
        String g = "A + B * C * D ^ P";
        System.out.println("Infix: " + a + " ==> " + postFix(a));
        System.out.println("Infix: " + b + " ==> " + postFix(b));
        System.out.println("Infix: " + c + " ==> " + postFix(c));
        System.out.println("Infix: " + d + " ==> " + postFix(d));
        System.out.println("Infix: " + e + " ==> " + postFix(e));
        System.out.println("Infix: " + f + " ==> " + postFix(f));
        System.out.println("Infix: " + g + " ==> " + postFix(g));
    }

    public static String postFix(String s) {
        String postfix = "";
        int pCounter = 0;
        int fP = s.indexOf("(");
        for (int i = fP; i < s.length() && i != -1; i++) {
            if (s.charAt(i) == '(') {
                pCounter++;
            } else if (s.charAt(i) == ')') {
                pCounter--;
            }
            if (pCounter == 0) {
                String temp = postFix(s.substring(fP + 2, i - 1));
                s = s.substring(0, fP) + temp + s.substring(i + 1, s.length());
                fP = s.indexOf("(");
                i = fP - 1;
            }
        }

        ArrayList<String> list = new ArrayList<>(Arrays.asList(s.split(" ")));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("^")) {
                combine(list, i);
                i--;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("*") || list.get(i).equals("/")) {
                combine(list, i);
                i--;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("+") || list.get(i).equals("-")) {
                combine(list, i);
                i--;
            }
        }
        for (String x : list) {
            postfix += x;
        }
        return postfix;
    }

    private static void combine(ArrayList<String> list, int i) {
        list.set(i, list.get(i - 1) + list.get(i + 1) + list.get(i));
        list.remove(i + 1);
        list.remove(i - 1);
    }
}