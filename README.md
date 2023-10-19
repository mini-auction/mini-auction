# mini-auction

Code 컨벤션
1. 1 Tab 의 size는 4로 지정한다.
2. 모든 변수 및 메서드명에는 카멜케이스를 사용한다.
3. 메서드 이름은 동사/전치사로 시작한다.
4. 상수는 대문자와 언더스코어로 구성한다.
5. 식별자와 여는 소괄호 사이에 공백 미삽입한다.
   1. 식별자와 여는 소괄호(`(`) 사이에는 공백을 삽입하지 않는다. 생성자와 메서드의 선언, 호출, 애너테이션 선언 뒤에 쓰이는 소괄호가 그에 해당한다.
        
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
        
    6. 제어문 키워드와 여는 소괄호 사이에 공백 삽입한다.
        1. `if`, `for`, `while`, `catch`, `synchronized`, `switch` 와 같은 제어문 키워드의 뒤에 소괄호(`(`, `)`)를 선언하는 경우, 시작 소괄호 앞에 공백을 삽입한다.
        
        ```java
        good example
        if (maxLine > LIMITED) {
            return false;
        }
        ```
        
    7. 콤마(,)와 세미 콜론(;) 뒤에는 공백을 삽입한다.
        
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
        
    8. 콜론(:)의 앞뒤로는 공백을 삽입한다.
        
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
        
    9. 가로로 긴 코드는 지양한다.
        
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
        
    10. 닫는 중괄호와 같은 줄에 `else`, `catch`, `finally`, `while` 선언한다.
        
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
        
    11. 조건문 & 반복문에 중괄호 필수 사용한다.
        
        ```java
        bad example
        if (exp == null) return false; 
        //중괄호 없이 이렇게도 사용이 가능함.(혹시 모르는 분이 있을까봐! 나는 회사 입사 전에는 몰랐었음..)
        
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
