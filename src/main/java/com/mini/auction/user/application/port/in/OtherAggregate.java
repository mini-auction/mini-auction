package com.mini.auction.user.application.port.in;

/*
    다른 aggregate 에서 현재의 aggregate 의 모델에 접근해야 할 경우에 해당 인터페이스를 사용하여 필요한 리소스를 가져갈 수 있게한다.
    ** 해당 인터페이스 사용을 제외하고, 서로 다른 aggregate 를 넘나들지 않도록 한다. **
 */
public interface OtherAggregate {
}
