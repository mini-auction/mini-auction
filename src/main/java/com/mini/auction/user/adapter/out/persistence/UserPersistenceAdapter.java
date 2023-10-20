package com.mini.auction.user.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
    패키지 외부에서 Entity Repository 에 직접 의존하지 않도록 해주는 역할을 한다.
 */
@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter {
    private final UserRepository userRepository;
}
