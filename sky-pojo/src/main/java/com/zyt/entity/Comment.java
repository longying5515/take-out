package com.zyt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private long id;
    private String content;
    private long userId;
    private LocalDateTime createTime;
    private ArrayList<String> image;
}
