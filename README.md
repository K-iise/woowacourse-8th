## 자동차 경주

### 🚗 프로그램 개요

이 프로그램는 콘솔 기반의 **자동차 경주 게임**입니다.  
사용자로부터 자동차 이름과 시도 횟수를 입력받아,  
랜덤 값을 통해 자동차의 전진 여부를 결정하고 최종 우승자를 출력합니다.  
**TDD(Test Driven Development)** 및 **MVC 패턴 설계**를 통해 진행되었습니다.

---

### 🧩 프로그램 실행 흐름

1. 사용자로부터 자동차 이름과 시도 횟수를 입력받는다.
2. 입력값을 검증한다.
3. 각 자동차는 매 시도마다 랜덤 값을 생성하여 전진 여부를 결정한다.
4. 각 시도 결과를 출력한다.
5. 모든 시도가 끝나면 최종 우승자를 출력한다.

---

### ⚙️ 기능 목록 정리

**1. 입력**

- [x] 사용자로 부터 자동차 이름을 받는다.
    - 쉼표```'```를 기준으로 이름을 분리한다.
    - 이름이 5자 초과인 경우 ```IllegalArgumentException```를 발생시킨다.
    - 이름이 비어 있거나 중복된 경우 ```IllegalArgumentException```를 발생시킨다.
- [x] 사용자로 부터 시도할 횟수를 입력 받는다.
    - 시도 횟수가 숫자가 아닌 경우 ```IllegalArgumentException```를 발생시킨다.
    - 시도 횟수가 0 이하인 경우 ```IllegalArgumentException```를 발생시킨다.

**2. 게임 진행**

- [x] 각 시도마다 모든 자동차가 랜덤 값(0-9)를 생성한다.
- [x] 랜덤 값이 4 이상이면 전진(```-```) 가중치를 증가시킨다..
- [x] 매 시도마다 각 자동차의 진행 상태를 출력한다.

**3. 우승자 선정**

- [x] 가장 많이 전진한 자동차를 찾는다.
- [x] 여러 대가 동점인 경우, 모든 우승자를 출력한다.

**4. 출력**

- [x] 각 시도 후 ```자동차 이름 : -``` 형식으로 진행 결과를 출력한다.
- [x] 경주가 끝난 후 ```"최종 우승자 : 자동차 이름```을 출력한다.

---

### 🗂️ 디렉토리 구조

📦 racingcar  
┣ 📂 controller  
┃ ┗ 📄 RacingController.java  
┣ 📂 domain  
┃ ┣ 📄 Car.java  
┃ ┣ 📄 RacingGame.java  
┃ ┣ 📄 WinnerFinder.java  
┃ ┗ 📄 CarGenerator.java  
┣ 📂 service  
┃ ┣ 📄 RandomGenerator.java  
┃ ┣ 📄 Validator.java  
┃ ┗ 📄 Separator.java  
┣ 📂 view  
┃ ┣ 📄 InputView.java  
┃ ┗ 📄 OutputView.java  
┗ 📄 Application.java

---

### 🧱 아키텍처 설계

- **Controller**  
  View로부터 입력을 받아 Domain과 Service를 연결하며, 전체 게임 흐름을 제어합니다.

- **View**  
  사용자 입력(`InputView`)과 출력(`OutputView`)을 담당합니다.

- **Service**  
  입력값에 대한 검증 및 비즈니스 로직 중 일부를 처리합니다.

    - `RandomGenerator` : 자동차 전진 여부 랜덤 결정
    - `Validator` : 자동차 이름 및 시도 횟수 검증
    - `Separator` : 자동차 이름 쉼표 기준 분리


- **Domain**  
  핵심 비즈니스 로직을 담당하며, 객체의 상태와 행위를 관리합니다.
    - `Car` : 자동차 객체의 상태와 이동 로직
    - `RacingGame` : 전체 경주 진행 제어
    - `WinnerFinder` : 최종 우승자 판별
    - `CarGenerator` : 자동차 객체 생성 

