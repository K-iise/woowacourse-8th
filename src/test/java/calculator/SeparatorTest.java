package calculator;

import calculator.service.Separator;
import calculator.service.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SeparatorTest {

    private Separator separator;
    private String custom_text;

    @BeforeEach
    public void setup(){
        this.separator = new Separator();
        this.custom_text = "//s\\n1s2s3";

    }

    @Test
    @DisplayName("커스텀 구분자 추출 테스트")
    public void getDelimiterRegexTest(){
        String delimiterRegex = separator.getDelimiterRegex(custom_text);
        Assertions.assertThat(delimiterRegex).contains("s");
    }

    @Test
    @DisplayName("문자열 가공 테스트")
    public void getExtraTextTest(){
        String extraText = separator.getExtraText(custom_text);
        Assertions.assertThat(extraText).isEqualTo("1s2s3");
    }


}
