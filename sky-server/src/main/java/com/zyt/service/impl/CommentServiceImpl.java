package com.zyt.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyt.dto.CommentQueryDTO;
import com.zyt.entity.Comment;
import com.zyt.mapper.CommentMapper;
import com.zyt.service.CommentService;
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
