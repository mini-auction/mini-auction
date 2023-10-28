package com.mini.auction.member.application.port.out;

/*
    파일이 없으면 패키지가 사라지기 때문에 임시로 만들어놓은 파일. 작업 시 삭제 바람.
 */
public interface FindMemberPort {

    boolean existsByIdAndIsDeletedFalse(String id);
}
