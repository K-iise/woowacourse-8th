package planetlotto.model;

import planetlotto.util.ErrorCode;

public class PurchaseInfo {
    private final int quantity;
    private static final int PRICE_UNIT = 500;

    private PurchaseInfo(int quantity) {
        this.quantity = quantity;
    }

    public static PurchaseInfo FromPrice(int price){
        validatePrice(price);
        int quantity = price / PRICE_UNIT;
        return new PurchaseInfo(quantity);
    }

    private static void validatePrice(int price) {
        if (price % PRICE_UNIT != 0) {
            throw new IllegalArgumentException(ErrorCode.PRICE_ERROR.getMessage());
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
