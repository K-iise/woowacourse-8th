package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.awt.*;

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

    }
}
