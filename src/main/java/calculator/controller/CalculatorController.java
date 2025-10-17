package calculator.controller;

import calculator.service.Separator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final Separator separator;
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(Separator separator, InputView inputView, OutputView outputView){
        this.separator = separator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        String text = inputView.getIntput();
        int result = separator.calculateTotal(text);
        outputView.printResult(result);
    }
}
