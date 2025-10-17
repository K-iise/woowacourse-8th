package calculator.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {
    private final String BASIC_DELIMITER_REGEX = "[,;]";
    private final String CUSTOM_DELIMTER_PATTERN = "//(.)\\\\n(.*)$";

    /**
     * 커스텀 구분자 추출
     */
    public String getDelimiterRegex(String text){
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
    public String getExtraText(String text){
        Pattern ptn = Pattern.compile(CUSTOM_DELIMTER_PATTERN);
        Matcher mtc = ptn.matcher(text);
        if (mtc.find()){
            return mtc.group(2);
        }
        return text;
    }

}
