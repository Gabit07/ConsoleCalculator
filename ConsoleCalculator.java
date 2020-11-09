import java.util.Arrays;
import java.util.Scanner;

public class Calculator{

    static char operator;
    static int first, second;
    static boolean first_rom = false;
    static boolean second_rom = false;

    public static void main(String args[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter input: ");
        String input = scan.nextLine();

        convert(input);
        Integer result = calculate();

        if (result!=null) {
            if (first_rom == true) {
                System.out.println(converToRoman(result));
            }
            else {
                System.out.println(result);
            }
        }

    }

    public static Integer calculate () throws Exception {
        Integer result = null;

        if (first_rom == second_rom){
            if (first < 0 || first > 10){
                throw new Exception("Check 1st input (input : 0 - 10)");
            }

            if (second < 0 || second > 10) {
                throw new Exception("Check 2nd input (input : 0 - 10)");
            }

            switch(operator)
            {
                case '+':
                    result = first + second;
                    break;

                case '-':
                    result = first - second;
                    break;

                case '*':
                    result = first * second;
                    break;

                case '/':
                    result = first / second;
                    break;

                // operator doesn't match any case constant (+, -, *, /)
                default:
                    throw new Exception("operator is not correct");
            }

            return result;
        }
        else {
            throw new Exception("Both inputs should be roman or decimal numbers");
        }
    }

    public static void convert(String input){

        char[] chars = input.toCharArray();

        int charsLength = chars.length;

        String op = "+-*/";
        String des = "IiVvXxLlCcDdMm";

        for (int i=0;i<charsLength;i++) {

            if (op.indexOf(chars[i]) >= 0) {
                operator = chars[i];

                char[] chr_first = Arrays.copyOfRange(chars, 0, i);
                String str_first = String.valueOf(chr_first);
                str_first = str_first.trim();

                char[] chr_second = Arrays.copyOfRange(chars, i+1, charsLength);
                String str_second = String.valueOf(chr_second);
                str_second = str_second.trim();

                if (des.indexOf(str_first.charAt(0)) >= 0) {
                    first = convertToDecimal(str_first);
                    first_rom = true;
                }
                else {
                    try {
                        first = Integer.parseInt(str_first);
                    } catch (NumberFormatException e) {
                        first = -1;
                    }
                }

                if (des.indexOf(str_second.charAt(0)) >= 0) {
                    second = convertToDecimal(str_second);
                    second_rom = true;
                }
                else {
                    try {
                        second = Integer.parseInt(str_second);
                    } catch (NumberFormatException e) {
                        second = -1;
                    }
                }
            }
        }

    }

    public static int convertToDecimal (String romanNumeral) {
        int decimalNum = 0;

        romanNumeral = romanNumeral.toUpperCase();

        int l=  romanNumeral.length();
        int num=0;
        int previousnum = 0;
        for (int i=l-1;i>=0;i--)
        {
            char x =  romanNumeral.charAt(i);
            x = Character.toUpperCase(x);
            switch(x)
            {
                case 'I':
                    previousnum = num;
                    num = 1;
                    break;
                case 'V':
                    previousnum = num;
                    num = 5;
                    break;
                case 'X':
                    previousnum = num;
                    num = 10;
                    break;
            }
            if (num<previousnum)
            {decimalNum= decimalNum-num;}
            else
                decimalNum= decimalNum+num;
        }

        return decimalNum;
    }

    public static String converToRoman(int num) {

        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }

        return roman.toString();
    }
}