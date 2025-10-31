package lotto.service;


import java.util.Collections;
import java.util.TreeSet;

public class Parser {
    public int changeInteger(String purchaseAmount){
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    public TreeSet<Integer> separateNumber(String input){
        TreeSet<Integer> lotteryNumber = new TreeSet<>();
        String [] numberList = input.split(",");

        for (String number : numberList){
            try {
                int lotteryNum = Integer.parseInt(number);
                if (lotteryNum > 45) throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 범위에 속해야 합니다.");
                lotteryNumber.add(lotteryNum);
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
            }
        }

        if (lotteryNumber.size() != 6) throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");

        return new TreeSet<>(lotteryNumber);
    }
}
