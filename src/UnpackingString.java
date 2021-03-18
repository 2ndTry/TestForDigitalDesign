/*
Author Alexey Miroshnikov
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnpackingString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку типа - число[строка]");
        System.out.println("Допустимые символы на вход: латинские буквы, числа и скобки []");
        System.out.print("Вход: ");
        String unpackedString = scanner.nextLine();

        if (unpackedString.matches("[A-z0-9\\[\\]]*")) {
            while (unpackedString.matches("(.*)\\d\\[[a-zA-Z]*](.*)")) {
                unpackedString = outputString(unpackedString);
            }
            System.out.println("Выход: " + validTestString(unpackedString));
        } else {
            System.out.println("Вы ввели не верную строку: " + unpackedString);
        }
    }

    public static String outputString(String inputCheckString) {
        StringBuffer stringBuffer = new StringBuffer(inputCheckString);
        Pattern pattern = Pattern.compile("(\\d\\[[a-zA-Z]*])");
        Matcher matcher = pattern.matcher(stringBuffer);

        while (matcher.find()) {
            String bufString = stringBuffer.toString().replace(matcher.group(), parseString(matcher.group()));
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append(bufString);
            matcher.reset();
        }
        return stringBuffer.toString();
    }

    public static String parseString(String inputCheckString) {
        int number = Integer.valueOf(inputCheckString.replaceAll("\\[.*\\]", ""));
        String text = inputCheckString.replaceAll("\\]", "").replaceAll("\\d*\\[", "");
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < number; i++) {
            stringBuffer.append(text);
        }
        return String.valueOf(stringBuffer);
    }

    public static String validTestString(String inputCheckString) {
        if (inputCheckString.matches("^[a-zA-Z]+$")) {
            return inputCheckString;
        }
        return "Вы ввели не верную строку: " + inputCheckString;
    }
}
