package lotto.domain;

public class LottoPurchase {

    private final int PURCHASE_PRICE = 1000;

    private final int amount;

    public LottoPurchase(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

}
