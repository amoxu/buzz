package com.amoxu.service.impl;

import com.amoxu.entity.Message;
import com.amoxu.entity.MessageExample;
import com.amoxu.entity.PageResult;
import com.amoxu.entity.User;
import com.amoxu.mapper.MessageMapper;
import com.amoxu.service.MessageService;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;


@Controller
public class MessageServiceImpl implements MessageService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private MessageMapper msgMapper;

/*    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;*/
/**
 *
 * 返回不是成功就返回错误信息
 *
 * */
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
     *
     * 如果权限是管理员则可以进行操作，
     * 如果是用户进行操作时，则是对于信息的id和用户id同时进行判断，相同进行删除
     *
     * */
    @Override
    public boolean deleteMsg(Subject subject,Integer mid) {
        try {
            if (subject.hasRole("管理员")) {
                return 1 == msgMapper.deleteByPrimaryKey(mid);
            } else {
                int optId = ((User) subject.getPrincipal()).getUid();
                MessageExample example = new MessageExample();
                MessageExample.Criteria criteria = example.createCriteria();
                criteria.andSuidEqualTo(optId);
                criteria.andMidEqualTo(mid);
                return 1 == msgMapper.deleteByExample(example);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public PageResult<Message> getMessage(PageResult<Message> pageResult, int uid) {

        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        criteria.andSuidEqualTo(uid);
        /**
         *  TODO 希望在这里不用关闭连接池，接着进行第二次查询
         * */
        /*提前获取数量*/
        pageResult.setCount(msgMapper.countByExample(example));

        /*然后配置分页*/
        example.setLimit(pageResult.getLimit());
        example.setOffset(pageResult.getOffset());
        pageResult.setList(msgMapper.selectByExample(example));
        return pageResult;
    }
}
