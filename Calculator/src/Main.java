import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String operators = "+-*/";
    //Numbers are not greater than 10
    private static final String[] numerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static boolean firstIsRoman = false;
    private static boolean secondIsRoman = false;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.length() > 0) {
                System.out.println(calc(input));
            } else {
                break;
            }
        }
    }
    public static int roman2Decimal(String roman) {
        int result = 0;
        for (int i = 0; i < numerals.length; i++) {
            while (roman.startsWith(numerals[i])) {
                result += values[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        return result;
    }

    public static String decimal2Roman(int decimal) {
        String result = "";
        for (int i = 0; i < numerals.length; i++) {
            while (decimal >= values[i]) {
                result = result.concat(numerals[i]);
                decimal -= values[i];
            }
        }
        return result;
    }

    public static String calc(String input) throws IOException {
        String[] words = input.split(" ");
        if (words.length != 3) { //Rule 1
            throw new IOException();
        }
        if (words[1].length() != 1) { //Rule 1
            throw new IOException();
        }
        if (-1 == operators.indexOf(words[1].charAt(0))) { //Rule 8
            throw new IOException();
        }
        Integer i1 = 0, i2 = 0;
        i1 = roman2Decimal(words[0]);
        firstIsRoman = i1 != 0; //Rule 2
        if (!firstIsRoman) {
            i1 = Integer.parseInt(words[0]); //will generate exception if violates Rule 4
        }
        i2 = roman2Decimal(words[2]);
        secondIsRoman = i2 != 0; //Rule 2
        if (!secondIsRoman) {
            i2 = Integer.parseInt(words[2]); //will generate exception if violates Rule 4
        }
        if (i1 > 10 || i2 > 10) { //Rule 3, 7
            throw new IOException();
        }
        if (firstIsRoman != secondIsRoman) { //Rule 5
            throw new IOException();
        }
        Integer answer = 0;
        switch (words[1].charAt(0)) {
            case ('+'):
                answer = i1 + i2;
                break;
            case ('-'):
                answer = i1 - i2;
                break;
            case ('*'):
                answer = i1 * i2;
                break;
            case ('/'):
                answer = i1 / i2; //Rule 9 by default
                break;
            default:
                throw new IOException();
        }
        if (firstIsRoman) { //Rule 6
            if (answer < 1) { //Rule 10
                throw new IOException();
            }
            return decimal2Roman(answer);
        } else {
            return String.valueOf(answer);
        }
    }
}