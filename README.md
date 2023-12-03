# mini-auction
"만들면서 배우는 클린 아키텍처"를 읽고, 헥사고날 아키텍처를 적용해본 학습 프로젝트.


-----

Code 컨벤션
1. 1 Tab 의 size는 4로 지정한다.
2. 모든 변수 및 메서드명에는 카멜케이스를 사용한다.
3. 메서드 이름은 동사/전치사로 시작한다.
4. 클래스 이름은 명사나 명사절로 짓는다.
5. 인터페이스(interface)의 이름은 클래스 이름은 명사/명사절로 혹은 형용사/형용사절로 짓는다.
6. 상수는 대문자와 언더스코어로 구성한다.
7. 식별자와 여는 소괄호 사이에 공백 미삽입한다.
   - 식별자와 여는 소괄호(`(`) 사이에는 공백을 삽입하지 않는다. 생성자와 메서드의 선언, 호출, 애너테이션 선언 뒤에 쓰이는 소괄호가 그에 해당한다.
        
        ```java
        good example
        public StringProcessor() {} // 생성자
        
        good example
        @Cached("local")
        public String removeEndingDot(String original) {
            assertNotNull(original);
            ...
        }
        ```
        
8. 제어문 키워드와 여는 소괄호 사이에 공백 삽입한다.
   - `if`, `for`, `while`, `catch`, `synchronized`, `switch` 와 같은 제어문 키워드의 뒤에 소괄호(`(`, `)`)를 선언하는 경우, 시작 소괄호 앞에 공백을 삽입한다.
        
        ```java
        good example
        if (maxLine > LIMITED) {
            return false;
        }
        ```
        
9. 콤마(,)와 세미 콜론(;) 뒤에는 공백을 삽입한다.
        
    ```java
    bad example
    for (int i = 0;i < length;i++) {
       display(level,message,i)
    }
        
    good example
    for (int i = 0; i < length; i++) {
       display(level, message, i)
    }
    ```
        
10. 콜론(:)의 앞뒤로는 공백을 삽입한다.
        
   ```java
        good example
        switch (grade) {
        	case GOLD :
        	  sendSms(customer);
          case SILVER :
            sendEmail(customer);
          default :
            inreasePoint(customer)
        }
   ```
        
11. 가로로 긴 코드는 지양한다.
        
    ```java
        bad example
        public void testFunction(String name, String password, int age, String email, String phoneNo){
        }
        
        good example
        public void testFunction(
        	String name,
        	String password,
        	int age,
        	String email,
        	String phoneNo
        ){
        }
        
        bad example
        LogLogLogLogClassName logLogLogLogClassExampleVariable = logLogLogLogClassExampleRepository.findById(id);
        
        good example
        LogLogLogLogClassName logLogLogLogClassExampleVariable = 
        	logLogLogLogClassExampleRepository.findById(id);
        
        bad example
        LogLogLogLogClassName logLogLogLogClassExampleVariable = logLogLogLogClassExampleRepository.findByNameAndPhoneNoAndEmailAndAgeAndName2AndPhoneNo2(name, phoneNo, email, age, name2, phoneNo2);
        
        good example
        LogLogLogLogClassName logLogLogLogClassExampleVariable = 
        	logLogLogLogClassExampleRepository.findByNameAndPhoneNoAndEmailAndAgeAndName2AndPhoneNo2(
        			name, 
        			phoneNo, 
        			email, 
        			age, 
        			name2, 
        			phoneNo2
        	);
        // ** 파라미터가 너무 많이 필요할 경우, 객체화하는 것을 지향한다. ** 
    ```
        
12. 닫는 중괄호와 같은 줄에 `else`, `catch`, `finally`, `while` 선언한다.
        
    ```java
        good example
        if (line.startWith(WARNING_PREFIX)) {
            return LogPattern.WARN;
        } else if (line.startWith(DANGER_PREFIX)) {
            return LogPattern.NORMAL;
        } else {
            return LogPattern.NORMAL;
        }
        
        good example
        try {
            writeLog();
        } catch (IOException ioe) {
            reportFailure(ioe);
        } finally {
            writeFooter();
        }
    ```
        
13. 조건문 & 반복문에 중괄호 필수 사용한다.
        
    ```java
        bad example
        if (exp == null) return false; 
        //중괄호 없이 이렇게도 사용이 가능함.
        
        for (char ch : exp.toCharArray()) if (ch == 0) return false;
        
        good example
        if (exp == null) {
            return false;
        }
        
        for (char ch : exp.toCharArray()) {
        
            if (ch == 0) {
                return false;
            }
        
        }
    ```
