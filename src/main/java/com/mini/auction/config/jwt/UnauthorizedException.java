package com.mini.auction.config.jwt;


import java.io.Serial;

/*
* TODO: ExceptionHandler 병합 후 리팩토링 필요함
*/
public class UnauthorizedException extends RuntimeException{
    /*
    RuntimeException이 상속받는 최상위 class에 Serializable이 존재하기 때문에 Serialize를 위한 serialVersionUID가 필요함
    ref: https://stackoverflow.com/questions/46599012/why-do-we-need-serialversionuid-when-extending-runtimeexception
    Compile 시에 직렬화할 경우 오류가 발생할 수 있음으로 직접 지정해줌
    ref: https://m.blog.naver.com/writer0713/220922099055
     */
    @Serial
    private static final long serialVersionUID = -2238030302650813813L;

    public UnauthorizedException() {
        super("계정 권한이 유효하지 않습니다.\n다시 로그인을 해주세요.");
    }

}
