package lotto.service;


public class Parser {
    public int changeInteger(String purchaseAmount){
        try {
            return Integer.parseInt(purchaseAmount);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }
}
