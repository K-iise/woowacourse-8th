package racingcar.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class InputViewTest {

  @Test
  public void 자동차_이름_입력() {
    //given
    String input = "pobi,woni,jun";
    InputStream fakeInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(fakeInputStream);
    InputView inputView = new InputView();

    //when
    String carsname = inputView.getCarName();

    //then
    Assertions.assertThat(carsname).isEqualTo("pobi,woni,jun");
  }

  @Test
  public void 시도_횟수_입력() {
    // given
    String input = "5";
    InputStream fakeInputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(fakeInputStream);
    InputView inputView = new InputView();

    // when
    String count = inputView.getTryCount();

    // then
    Assertions.assertThat(count).isEqualTo("5");
  }
}
