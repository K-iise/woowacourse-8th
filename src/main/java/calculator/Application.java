package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해주세요.");
        String input = Console.readLine();

        // 빈 문자열 및 공백 처리
        if(input == null || input.isBlank()){
            System.out.print("결과값 : 0");
            return;
        }

        // 단일 숫자 처리
        try {
            int sum = Integer.parseInt(input);
            System.out.print("결과값 : " + sum);
            return;
        } catch (NumberFormatException e){

        }

      // 기본 구분자 처리
        String[] numbers = input.split("[,;]");
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            // 구분자 사이에 숫자가 없을 경우
            if (numbers[i] == null || numbers[i].isBlank()) {
                result += 0;
            } else
                result += Integer.parseInt(numbers[i]);
        }
        if (result > 0)
            System.out.print("결과값 : " + result);

        // 커스텀 구분자 처리
        Pattern pattern = Pattern.compile("//(.)\\\\n(.*)$");
        Matcher matcher = pattern.matcher(input);
        String delimiter;
        if (matcher.find()) {
            delimiter = matcher.group(1);
            String inputs = matcher.group(2);
            System.out.println(delimiter);

            String[] number = inputs.split(delimiter);

            int results = 0;
            for (int i = 0; i < numbers.length; i++) {
                // 구분자 사이에 숫자가 없을 경우
                if (number[i] == null || number[i].isBlank()) {
                    result += 0;
                }
                // 구분자 사이의 문자가 양수인 경우
                else if(number[i].matches("^\\d+$")) {
                    result += Integer.parseInt(number[i]);
                    System.out.println(number[i]);
                }
            }
            if (result > 0)
                System.out.print("결과값 : " + result);
        }


    }
}
