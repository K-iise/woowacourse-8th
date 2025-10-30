package lotto.domain;

public class LottoPurchase {

    private final int PURCHASE_PRICE = 1000;

    private final int amount;

    public LottoPurchase(int amount){
        validateAmount(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void validateAmount(int amount){
        if (amount % PURCHASE_PRICE != 0)
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력받아야 합니다.");
    }

}
