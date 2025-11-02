package lotto.domain;

import lotto.exception.ErrorMessage;

public class LottoPurchase {

    private final int PURCHASE_PRICE = 1000;
    private final int amount;
    private final int lottoCount;

    public LottoPurchase(int amount) {
        validateAmount(amount);
        this.amount = amount;
        this.lottoCount = purchaseLotto(amount);
    }

    public int getAmount() {
        return this.amount;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_MUST_BE_POSITIVE.getMessage());
        }
        if (amount % PURCHASE_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_MUST_BE_DIVISIBLE.getMessage());
        }

    }

    private int purchaseLotto(int amount) {
        return amount / PURCHASE_PRICE;
    }


}
