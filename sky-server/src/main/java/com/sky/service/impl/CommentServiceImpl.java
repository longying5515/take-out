package com.sky.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.dto.CommentQueryDTO;
import com.sky.entity.Comment;
import com.sky.mapper.CommentMapper;
import com.sky.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public IPage<Comment> getComments(CommentQueryDTO commentQueryDTO) {
        Page<Comment> page = new Page<>(commentQueryDTO.getPage(),commentQueryDTO.getPageSize());
        QueryWrapper<Comment> queryWrapper = new QueryWrapper< >();
        return commentMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void deleteById(long id) {
        commentMapper.deleteById(id);
    }
}
