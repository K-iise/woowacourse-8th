package calculator.controller;

import calculator.service.Calculator;
import calculator.service.Separator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final Calculator calculator;
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(Calculator calculator, InputView inputView, OutputView outputView){
        this.calculator = calculator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        String text = inputView.getIntput();
        int result = calculator.calculateTotal(text);
        outputView.printResult(result);
    }
}
