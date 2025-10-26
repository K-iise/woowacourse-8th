package racingcar.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.WinnerFinder;

public class OutputViewTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUpStearm() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void 자동차_진행_상태_출력() {
        // given
        OutputView outputView = new OutputView();
        List<Car> cars = new ArrayList<>(List.of(new Car("yun"), new Car("cobi")));
        cars.get(1).addWeight(5);

        String expected = String.join(System.lineSeparator(),
                "yun : ",
                "cobi : -",
                "");

        // when
        outputView.printProcess(cars);

        // then
        Assertions.assertEquals(expected, outputStream.toString());
    }

    @Test
    public void 최종_우승자_출력_단일우승() {
        // given
        OutputView outputView = new OutputView();
        WinnerFinder winnerFinder = new WinnerFinder();
        List<Car> cars = new ArrayList<>(List.of(new Car("yun"), new Car("cobi")));
        cars.get(1).addWeight(5);

        String expected = "최종 우승자 : cobi";

        // when
        outputView.printWinner(winnerFinder.findWinners(cars));

        // then
        Assertions.assertEquals(expected, outputStream.toString());
    }

    @Test
    public void 최종_우승자_출력_공동우승() {
        // given
        OutputView outputView = new OutputView();
        WinnerFinder winnerFinder = new WinnerFinder();
        List<Car> cars = new ArrayList<>(List.of(new Car("yun"), new Car("cobi"), new Car("james")));
        cars.get(1).addWeight(5);
        cars.get(2).addWeight(5);

        String expected = "최종 우승자 : cobi, james";

        // when
        outputView.printWinner(winnerFinder.findWinners(cars));

        // then
        Assertions.assertEquals(expected, outputStream.toString());
    }
}
