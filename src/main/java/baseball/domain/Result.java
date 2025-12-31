package baseball.domain;

public class Result {
    private int strike;
    private int ball;

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public void addStrike(){
        strike++;
    }

    public void addBall(){
        ball++;
    }
}
