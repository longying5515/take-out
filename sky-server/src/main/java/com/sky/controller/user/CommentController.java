package com.sky.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sky.context.BaseContext;
import com.sky.dto.CommentQueryDTO;
import com.sky.entity.Comment;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RestController("userCommentController")
@RequestMapping("/user/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comments")
    public Result addComment(@RequestBody Comment comment) {
        Random random = new Random();
        log.info("comment:{}", comment);
        comment.setCreateTime(LocalDateTime.now());
        comment.setUserId(random.nextLong());
        commentService.addComment(comment);
        return Result.success();
    }
    @GetMapping("/comments")
    public IPage<Comment> getComments(@RequestBody CommentQueryDTO commentQueryDTO) {
        log.info("commentQueryDTO:{}", commentQueryDTO);
        var result=commentService.getComments(commentQueryDTO);
        return Result.success(result).getData();
    }
    @DeleteMapping("/comments/{id}")
    public Result deleteComment(@PathVariable long id) {
        commentService.deleteById(id);
        return Result.success();
    }
}
