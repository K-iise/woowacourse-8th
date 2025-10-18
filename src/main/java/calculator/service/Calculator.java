package calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private final Validator validator;
    private final Separator separator;
    public Calculator(Validator validator, Separator separator){
        this.validator = validator;
        this.separator = separator;
    }

    public int calculateTotal(String text){
        String extraText = separator.getExtraText(text);
        String totalRegex = separator.getDelimiterRegex(text);
        String []tokens = extraText.split(totalRegex);
        int result = 0;
        for (String token : tokens){
            result += validator.getValidateValue(token);
        }
        return result;
    }
}
