package com.mini.auction.auction.adapter.out.persistence;

import com.mini.auction.auction.domain.Comments;

class CommentsEntityMapper {

    Comments mapToDomain(CommentsEntity entity){
        return new Comments(
            entity.getContents(),
            entity.getWriter(),
            entity.getId(),
            entity.isDeleted()
        );
    }

}
