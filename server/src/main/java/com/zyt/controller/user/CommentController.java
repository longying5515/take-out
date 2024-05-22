package com.zyt.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyt.dto.CommentQueryDTO;
import com.zyt.entity.Comment;
import com.zyt.result.Result;
import com.zyt.service.CommentService;
import lombok.extern.slf4j.Slf4j;
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
    public Result<IPage<Comment>> getComments(@RequestBody CommentQueryDTO commentQueryDTO) {
        log.info("commentQueryDTO:{}", commentQueryDTO);
        IPage<Comment> result = commentService.getComments(commentQueryDTO);
        return Result.success(result);
    }
    @DeleteMapping("/comments/{id}")
    public Result deleteComment(@PathVariable long id) {
        commentService.deleteById(id);
        return Result.success();
    }
}
