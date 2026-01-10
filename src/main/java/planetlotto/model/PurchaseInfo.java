package planetlotto.model;

import planetlotto.util.ErrorCode;

public class PurchaseInfo {
    private final int quantity;

    private PurchaseInfo(int quantity) {
        this.quantity = quantity;
    }

    public static PurchaseInfo FromPrice(int price){
        validatePrice(price);
        int quantity = price / 500;
        return new PurchaseInfo(quantity);
    }

    private static void validatePrice(int price) {
        if (price % 500 != 0) {
            throw new IllegalArgumentException(ErrorCode.PRICE_ERROR.getMessage());
        }
    }
}
