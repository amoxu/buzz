package com.amoxu.service.impl;

import com.amoxu.entity.Comments;
import com.amoxu.entity.CommentsExample;
import com.amoxu.mapper.CommentsMapper;
import com.amoxu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public Comments getIndexTopComment() {
        CommentsExample example = new CommentsExample();
        example.setOrderByClause("likes");
        example.setOffset(new Random().nextInt(20));
        example.setLimit(1);

        List<Comments> comments = commentsMapper.selectByExample(example);
        if (comments.size() > 0) {
            return comments.get(0);
        }
        return null;
    }

    @Override
    public Comments getIndexLikeComment(int uid) {
        CommentsExample example = new CommentsExample();
        example.setOrderByClause("likes");

        example.setOffset(new Random().nextInt(20));
        example.setLimit(1);

        List<Comments> comments = commentsMapper.selectByExample(example);
        if (comments.size() > 0) {
            return comments.get(0);
        }
        return null;
    }
}
