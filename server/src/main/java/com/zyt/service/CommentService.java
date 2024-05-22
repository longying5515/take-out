package com.zyt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyt.dto.CommentQueryDTO;
import com.zyt.entity.Comment;

public interface CommentService {
    void addComment(Comment comment);


    IPage<Comment> getComments(CommentQueryDTO commentQueryDTO);

    void deleteById(long id);
}
