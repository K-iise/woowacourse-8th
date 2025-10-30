package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";

    public String readPurchaseAmount(){
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
        return Console.readLine();
    }
}
