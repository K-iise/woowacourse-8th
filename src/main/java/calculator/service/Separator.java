package calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private final String BASIC_DELIMITER_REGEX = "[,;]";
    private final String CUSTOM_DELIMITER_REGEX = "//(.+)\\\\n(.*)$";

    public String getDelimiterRegex(String text){
        Pattern ptn = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        Matcher mtc = ptn.matcher(text);
        if (mtc.find()){
            if(!mtc.group(1).matches("^[a-zA-Z가-힣]$")) {
                throw new IllegalArgumentException("구분자는 문자만 입력해야 합니다.");
            }
            return BASIC_DELIMITER_REGEX + "|" + Pattern.quote(mtc.group(1));
        }
        return BASIC_DELIMITER_REGEX;
    };

    public String getExtraText(String text){
        Pattern ptn = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        Matcher mtc = ptn.matcher(text);
        if (mtc.find()){
            return mtc.group(2);
        }
        return text;
    }

}
