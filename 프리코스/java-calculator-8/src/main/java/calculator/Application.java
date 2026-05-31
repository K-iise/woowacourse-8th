package calculator;

import calculator.controller.CalculatorController;
import calculator.service.Calculator;
import calculator.service.Separator;
import calculator.service.Validator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        CalculatorController calculatorController = new CalculatorController(
                new Calculator(new Validator(), new Separator()),
                new InputView(),
                new OutputView()
        );
        calculatorController.run();
    }
}
