package com.mini.auction.user.adapter.out.persistence;

import com.mini.auction.common.domian.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/*
    Entity 는 mapper 를 통해서 생성 및 변경 된다.
    Entity 내부에는 필드값을 제외한 아무런 코드도 작성하지 않는다.
 */
@Entity
@AllArgsConstructor //모든 필드를 매개변수로 받는 생성자를 자동으로 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 자동 생성, 생성된 생성자는 동일 패키지 내에서 또는 서브클래스에서만 접근할 수 있다.
@Table(name = "user")
class UserEntity extends BaseJpaEntity {

    @Comment("이름")
    @Column(length = 100, nullable = false)
    private String name;

    @Comment("비밀번호")
    @Column(length = 20, nullable = false)
    private String password;

    @Comment("이메일")
    @Column(nullable = false)
    private String email;

    @Comment("핸드폰 번호")
    @Column(length = 13)
    private String phoneNo;

    @Comment("잔여 금액")
    @Column()
    private int money = 0;

}
