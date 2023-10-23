# ExceptionHandler Example
발생경우와 응답예시를 기술해놓았다. 사용하게 될 경우, 기술해놓은 알맞은 상황에 사용하도록 한다. <br>
거의 모든 유스케이스에서는 BadRequestException 을 사용하면 될 것.

## BadRequestException
잘 못된 요청이 왔을 경우 발생한다. <br> 비지니스 로직 안에서 잘 못된 요청에 대한 기본적인 예외처리를 할 때 사용하도록 한다. <br>
* example <br> 
response : 
    ```json
  Status: 400
    {
      "code": "E00001",
      "message":"잘못된 요청입니다." 
  }
    ``` 

----
## NotFoundResourceException
요청온 값으로 조회되는 결과가 없는 경우 발생한다.
* example <br>
  response :
    ```json
  Status: 404
    {
      "code": "E00002",
      "message":"조회되는 값이 없습니다." 
  }
    ``` 
----
## NotFoundUserException 
로그인 실패시 사용한다. 로그인 실패에 맞게 HttpStatus 를 "UNAUTHORIZED" 로 내려주기 위해 만들었다. 
* example <br>
  response :
    ```json
  Status: 401
    {
      "code": "E00004",
      "message":"만료된 토큰입니다." 
  }
    ``` 
----

## MissingPathVariableException
URI 변수의 파라미터 타입이 맞지 않을 경우 발생한다.
타입이 맞지 않는 것이 여러개인 경우에도 제일 처음 찾은 한가지 값에 대한 에러메세지만 출력된다. <br>
* example <br>
  request uri 설정 -> uri : /test/{age} . int age <br> 인입된 request uri : /test/test123 <br>
  response :
    ```json
  Status: 401
    {
      "code": "E00002",
      "message": "파라미터 타입이 일치하지 않습니다.",
      "detail": "Required URI template variable 'age' for method parameter type int is not present"
  }
    ``` 
----

## MissingServletRequestParameterException
param 을 받아야 할 때, 필수 param 이 없을 때 발생한다.
필수 param 여러개가 없을 경우에도 제일 처음 찾은 한가지 값에 대한 에러메세지만 출력된다. <br>

* example <br>
  받아야하는 param 값 -> id(required = true), name(required = false) <br>
  response :
    ```json
  Status: 400
    {
      "code": "E00001",
      "message": "필수 파라미터가 없습니다.",
      "detail": "Required request parameter 'id' for method parameter type String is not present" 
  }
    ``` 
---
## MethodArgumentTypeMismatchException
param 을 받아야 할 때, param 의 data type 이 맞지 않을 때 발생한다.
* example <br>
  받아야하는 param 값 -> String id, int age<br>
  request :
  ```json
    "id": 11111,
    "age": "test"
  ```
  response :
    ```json
  Status: 400
    {
      "code": "E00002",
      "message": "파라미터 타입이 일치하지 않습니다.",
      "detail": "'age' is must be 'int' type." 
  }
    ``` 
---

## MethodArgumentNotValidException
requestBody 로 받아야할 dto 에서 valid error 가 발생할 경우 발생한다.
* example <br>
  dto : 
    ```java
    class FooDto {  
        @NotBlank
        private String name;
        @Max(10)
        private int age;
    }

  ```

  response :
    ```json
  Status: 400
    {
      "code": "E00001",
      "message": "필수 파라미터가 없습니다.",
      "detail": {
         "name": "공백일 수 없습니다",
         "age": "10 이하여야 합니다"
      }
  }
    ``` 
----
## HttpMessageNotReadableException
requestBody 에 대한 기타 에러 발생 시 발생한다. (ex. data type mismatch, deserialization error .. ) <br>
HttpMessageNotReadableException 의 하위 익셉션이 많이 있는 데, 그 중 예측 가능한 익셉션 몇가지를 커스텀 해놓았다. <br>
모든 하위 익셉셥을 전부 커스텀 하기에는 무리가 있으므로 나머지들은 else 로 묶었다.
### InvalidFormatException
JSON 데이터를 Java 객체로 역직렬화 할 때 발생하는 예외로, 주로 데이터 타입이 일치하지 않을 때 발생한다.
* example <br>
  dto :
    ```java
    class FooDto {  
      private String name;
      private int age;
    }
  ```
  request : 
  ```json
  {
    "name": 12345,
    "age": "kim"
  }
  -> name 에서는 익셉션이 발생하지 않는데, 
  문자열과 숫자형은 호환이 되어 익셉션이 발생하지 않고 형변환하여 인식한다.
  ```
  response :
    ```json
  Status: 400
    {
    "code": "E00003",
    "message": "요청 데이터가 올바르지 않습니다.",
    "detail": {
        "Value": "kim",
        "Target Type": "int"
    }
  }
    ``` 
<br>

### MismatchedInputException
  JSON 데이터와 java class 의 타입이 일치하지 않을 때 발생한다.
* example <br>
  list type 으로 잘 못 요청했을 경우.<br>
  request 1 : 
  ```json
  {
    "name": [],
    "age": 20
  }
  ```
  response 1 :
    ```json
  Status: 400
    {
      "code": "E00003",
      "message": "요청 데이터가 올바르지 않습니다.",
       "detail": "Cannot deserialize value of type `java.lang.String` from Array value (token `JsonToken.START_ARRAY`)"
  }
    ``` 
  request 2 :
  ```json
  [
    {
      "name": "test",
      "age": 20
    }
  ]
  ```
  response 2 :
    ```json
  Status: 400
    {
      "code": "E00003",
      "message": "요청 데이터가 올바르지 않습니다.",
      "detail": "Cannot deserialize value of type `com.mini.auction.FooDto` from Array value (token `JsonToken.START_ARRAY`)"
  }
    ``` 
<br>

### JsonParseException
  JSON 데이터의 형식이 올바르지 않을 때 발생한다.
* example 1 <br>
  request :
  ```json
  {
    "name": 2023-01-01 11:11:11,
    "age": "rla"
  }
  ```
  response :
    ```json
  Status: 400
  {
      "code": "E00003",
      "message": "요청 데이터가 올바르지 않습니다.",
      "detail": "Unexpected character ('-' (code 45)): was expecting comma to separate Object entries"
  }
    ``` 
* example 2 <br>
  request :
  ```json
  {
    \"name": "test"\,
    "age": 10\
  }
  ```
  response :
    ```json
  Status: 400
  {
    "code": "E00003",
    "message": "요청 데이터가 올바르지 않습니다.",
    "detail": "Unexpected character ('\\' (code 92)): was expecting double-quote to start field name"
  }
    ``` 
<br>

### else...
response 의 형태는 위와 같다. 발생 케이스는 상당히 다양하기 때문에 기입하지 않았다.
<br>

----

## Exception
예측하지 못한 것들에 대한 모든 예외상황에서 발생한다. 예측하지 못한 상황이므로 상세한 기본 익셉션 메세지를 리턴한다.