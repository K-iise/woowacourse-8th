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
    private String basic_text;
    private String total_text;
    @BeforeEach
    public void setup(){
        this.separator = new Separator(new Validator());
        this.custom_text = "//s\\n1s2s3";
        this.basic_text = "1;2,3";
        this.total_text = "//?\\n1?2;3,4";
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

    @Test
    @DisplayName("커스텀 구분자 계산 테스트")
    public void custom_calculataTotalTest(){
        int total = separator.calculateTotal(custom_text);
        Assertions.assertThat(total).isEqualTo(6);
    }

    @Test
    @DisplayName("기본 구분자 계산 테스트")
    public void basic_calculateTotalTest(){
        int total = separator.calculateTotal(basic_text);
        Assertions.assertThat(total).isEqualTo(6);
    }

    @Test
    @DisplayName("기본, 커스터 구분자 혼용 계산 테스트")
    public void total_calculateTotalTest(){
        int total = separator.calculateTotal(total_text);
        Assertions.assertThat(total).isEqualTo(10);
    }

    @Test
    @DisplayName("음수 또는 소수가 입력된 경우")
    public void errornumberTest(){
        String illegaltext = "-1;3.14";
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> separator.calculateTotal(illegaltext));
    }

}
