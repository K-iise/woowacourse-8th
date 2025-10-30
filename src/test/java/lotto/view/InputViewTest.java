package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputViewTest {

    @AfterEach
    public void tearDown(){
        Console.close();
    }

    @Test
    public void 로또_구입_금액_입력(){
        // given
        String input = "14000";
        InputStream fakeInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(fakeInputStream);

        InputView inputView = new InputView();

        // when
        String lottoPrice = inputView.readPurchaseAmount();

        // then
        Assertions.assertEquals(input, lottoPrice);
    }
}
