package baseball.service;

import java.util.ArrayList;
import java.util.List;

public class Separator {
    public List<Integer> getNumberList(String numbers){
        List<Integer> number = new ArrayList<>();
        for (int i = 0; i < numbers.length(); i++) {
            int num = numbers.charAt(i) - '0';
            number.add(num);
        }
        return number;
    }
}
