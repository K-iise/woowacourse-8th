package lotto.domain;

public class LottoPurchase {

    private final int purchaseAmount;

    public LottoPurchase(String amount){
        try {
            purchaseAmount = Integer.parseInt(amount);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 숫자여야 합니다.");
        }

    }

    public int getAmount() {
        return purchaseAmount;
    }
}
