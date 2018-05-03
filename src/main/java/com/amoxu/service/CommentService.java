package com.amoxu.service;

import com.amoxu.entity.Comments;

public interface CommentService {
    Comments getIndexTopComment();

    Comments getIndexLikeComment(int uid);
}
