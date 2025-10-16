package calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private final String BASIC_DELIMITER_REGEX = "[,;]";
    private final String CUSTOM_DELIMTER_PATTERN = "//(.)\\\\n(.*)$";

    private final Validator validator;

    public Separator(Validator validator){
        this.validator = validator;
    }
    
    /**
     * 커스텀 구분자 추출
     */
    private String getDelimiterRegex(String text){
        Pattern ptn = Pattern.compile(CUSTOM_DELIMTER_PATTERN);
        Matcher mtc = ptn.matcher(text);
        if (mtc.find()){
            return BASIC_DELIMITER_REGEX + "|" + Pattern.quote(mtc.group(1));
        }
        return BASIC_DELIMITER_REGEX;
    };

    /**
     * 문자열 가공
     */
    private String getExtraText(String text){
        Pattern ptn = Pattern.compile(CUSTOM_DELIMTER_PATTERN);
        Matcher mtc = ptn.matcher(text);
        if (mtc.find()){
            return mtc.group(2);
        }
        return text;
    }

    /**
     *  문자열 계산
     */
    private int calculatateTotal(String text){

        String extraText = getExtraText(text);
        String totalRegex = getDelimiterRegex(text);
        String []tokens = extraText.split(totalRegex);
        int total = 0;
        for (String token : tokens){
            if (validator.IsBlank(token))
                continue;
            if (validator.IsInteger(token))
                total += Integer.parseInt(token);
        }
        return total;
    }
}
