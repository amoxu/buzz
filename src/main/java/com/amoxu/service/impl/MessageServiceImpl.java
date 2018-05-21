package com.amoxu.service.impl;

import com.amoxu.entity.*;
import com.amoxu.mapper.FriendsMapper;
import com.amoxu.mapper.MessageMapper;
import com.amoxu.mapper.PermissionMapper;
import com.amoxu.service.MessageService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private MessageMapper msgMapper;

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private FriendsMapper friendsMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 返回不是成功就返回错误信息
     */
    @Override
    public String sendMsg(int suid, int ruid, String content) {

        content = ToolKit.aesDecrypt(content);
        if (StringUtils.isEmpty(content)) {
            return "发送信息为空";
        } else if (content.length() > 255) {
            return "发送消息过长（字符数大于255）";
        }
        logger.info(content);

        Message msg = new Message();
        msg.setContent(content);
        msg.setSuid(suid);
        msg.setRuid(ruid);
        return 0 == msgMapper.insertSelective(msg) ? StaticEnum.OPT_ERROR : StaticEnum.OPT_SUCCESS;
    }

    /**
     * 用户删除消息时，通过权限和操作id进行判断
     * <p>
     * 如果权限是管理员则可以进行操作，
     * 如果是用户进行操作时，则是对于信息的id和用户id同时进行判断，相同进行删除
     */
    @Override
    public boolean deleteMsg(Subject subject, Integer mid) {
        try {
            if (subject.hasRole("管理员")) {
                return 1 == msgMapper.deleteByPrimaryKey(mid);
            } else {
                int optId = ((User) subject.getPrincipal()).getUid();
                MessageExample example = new MessageExample();

                MessageExample.Criteria receive = example.createCriteria();
                receive.andRuidEqualTo(optId);/*接收者可删除*/
                receive.andMidEqualTo(mid);

                MessageExample.Criteria sender = example.or();
                sender.andSuidEqualTo(optId);
                sender.andMidEqualTo(mid);

                return 1 == msgMapper.deleteByExample(example);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*获取对应ID用户收到的消息*/
    @Override
    public AjaxResult<List<Message>> getMessage(PageResult<Message> pageResult, Integer uid) {
        AjaxResult<List<Message>> ajaxResult = new AjaxResult<>();

        Subject subject = SecurityUtils.getSubject();
        if (uid == null && subject.isAuthenticated()) {
            uid = ((User) subject.getPrincipal()).getUid();
        } else if (uid == null && !subject.isAuthenticated()) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.OPT_UNLOGIN);
            return ajaxResult;
        }

        Permission permission = permissionMapper.selectByPrimaryKey(uid);
        if (permission == null) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.USER_NOT_EXIST);
            return ajaxResult;
        }
        Integer messageRole = permission.getMessage();

         if (!subject.isAuthenticated() && messageRole > 0) {
            ajaxResult.failed();
            ajaxResult.setMsg(StaticEnum.PERMISSION_DENIED);
            return ajaxResult;
        } else if (messageRole > 0) {
            Integer viewId = ((User) subject.getPrincipal()).getUid();

            if (messageRole == 2 && viewId != uid) {
                //仅自己可见
                ajaxResult.failed();
                ajaxResult.setMsg(StaticEnum.PERMISSION_DENIED);
                return ajaxResult;
            }
            if (messageRole == 1 && viewId != uid) {
                //朋友可见
                FriendsExample friendsExample = new FriendsExample();
                friendsExample.or().andSuidEqualTo(uid).andDuidEqualTo(viewId);
                List<Friends> friends = friendsMapper.selectByExample(friendsExample);
                if (friends != null && friends.size() > 0) {
                    /*
                     * 不用friends == null || friends.size() <= 0
                     * 避免空指针异常
                     * */
                } else {
                    ajaxResult.failed();
                    ajaxResult.setMsg(StaticEnum.PERMISSION_DENIED);
                    return ajaxResult;
                }
            }
        }


        /*int uid = 1;*/
        logger.info(pageResult);

        ajaxResult.ok();


        MessageExample example = new MessageExample();
        example.or().andRuidEqualTo(uid);
        example.or().andSuidEqualTo(uid);

        /**
         *  在这里不用关闭连接池，接着进行第二次查询
         *
         * */

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
            /*提前获取数量*/
            pageResult.setCount(messageMapper.countByExample(example));

            /*然后配置分页*/
            example.setLimit(pageResult.getLimit());
            example.setOffset(pageResult.getOffset());
            example.setOrderByClause("message.ctime desc");/*根据发送时间排序 倒序*/
            pageResult.setList(messageMapper.selectByExampleSelective(example));

        } finally {
            sqlSession.close();
        }

        ajaxResult.setData(pageResult.getList());

        ajaxResult.setCount(pageResult.getCount());
        return ajaxResult;
    }
}
