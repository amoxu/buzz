package com.amoxu.controller;

import com.amoxu.entity.AjaxResult;
import com.amoxu.entity.Comments;
import com.amoxu.entity.User;
import com.amoxu.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping(value = "/index")
@Controller
public class CommentController {
    @Resource(name = "commentServiceImpl")
    private CommentService commentService;


    @RequestMapping(value = "/comment",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String pushComment() {

        AjaxResult<Comments> ajaxResult = new AjaxResult<>();
        Subject subject = SecurityUtils.getSubject();
        Comments indexComment;
        if (subject.isAuthenticated()) {
            User principal = (User) subject.getPrincipal();
            indexComment = commentService.getIndexLikeComment(principal.getUid());
        } else {
            indexComment = commentService.getIndexTopComment();
        }
        ajaxResult.setData(indexComment);
        ajaxResult.ok();

        return ajaxResult.toString();
    }
}
