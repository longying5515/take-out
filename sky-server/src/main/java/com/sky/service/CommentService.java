package com.sky.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.dto.CommentQueryDTO;
import com.sky.entity.Comment;
import com.sky.result.PageResult;

public interface CommentService {
    void addComment(Comment comment);


    IPage<Comment> getComments(CommentQueryDTO commentQueryDTO);

    void deleteById(long id);
}
