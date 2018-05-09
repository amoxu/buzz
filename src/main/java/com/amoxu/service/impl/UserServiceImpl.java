package com.amoxu.service.impl;

import com.amoxu.entity.MailUrlBuilder;
import com.amoxu.entity.User;
import com.amoxu.entity.UserExample;
import com.amoxu.exception.UnLoginException;
import com.amoxu.mapper.UserMapper;
import com.amoxu.service.UserService;
import com.amoxu.util.MailSender;
import com.amoxu.util.StaticEnum;
import com.amoxu.util.ToolKit;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserMapper mapper;
    @Autowired
    private MailSender mailSender;


    @Override
    public int insetUser(User user) {
        MailUrlBuilder builder = new MailUrlBuilder().builder(user.getEmail(), user.getNickname());


        user.setCity("北京-西城");
        user.setSex("男");
        user.setState(StaticEnum.STATE_ACTIVATED);
        user.setBirth(new Date());
        user.setRid(1);
        user.setState(0);
        user.setNote(builder.getNote());
        user.setIcons(StaticEnum.PREFIX_ICON + ToolKit.md5Hex(user.getEmail()) + "?d=retro");

        int code = 0;

        try {
            code = mapper.insert(user);
            mailSender.send(user.getNickname(),
                    StaticEnum.MAIL_REGISTER,
                    builder.getMailUrl(),
                    user.getEmail()
            );

        } catch (DuplicateKeyException e) {
            logger.error(e.getMessage());
            logger.error(e.getCause());
            Pattern pattern = Pattern.compile("Duplicate entry '(.+)' for key 'user_nickname'");
            if (pattern.matcher(e.getMessage()).find()) {
                logger.warn("用户已存在");
                return StaticEnum.REG_ACCOUNT_ERROR;/*用户已存在*/
            }
            pattern = Pattern.compile("Duplicate entry '(.+)' for key 'user_email'");
            if (pattern.matcher(e.getMessage()).find()) {
                logger.warn("邮箱已存在");
                return StaticEnum.REG_MAIL_ERROR;/*用户已存在*/
            }
            e.printStackTrace();
        }
        if (1 == code) {
            return StaticEnum.REG_SUCCESS;
        } else {
            return StaticEnum.REG_ERROR;
        }

    }

    @Override
    public int updateUser(User user) {
        return mapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> selectUserLikeName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameLike(name);
        return mapper.selectByExample(example);
    }

    @Override
    public User selectUserByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(name);
        List<User> users = mapper.selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User selectUserById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public User getLoginUser(String user, String pwd) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(user);
        criteria.andPasswordEqualTo(pwd);
        List<User> users = mapper.selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    /**
     *
     * 获取用户信息时，如果用户已登录，则返回当前用户在缓存中的信息，
     * 注意要复制一个一个user对象，不要直接把subject中的对对象直接返回，避免在返回页面时渲染产生的影响。
     * */
    @Override
    public User getUserInfo(Integer uid) throws UnLoginException {
        User user;
        if (uid == StaticEnum.SELF_ID) {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                throw new UnLoginException();
            }
            user = ((User) subject.getPrincipal()).clone();
            logger.info(user);
        } else {
            user = mapper.selectByPrimaryKey(uid);
        }

        return user;
    }

    @Override
    public int updateUserPassword(String oldPasswor, String newPasswor) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal(); //当前登录用户，用于获取用户ID
        logger.info("Subject user Object: " + user);
        logger.info(ToolKit.aesDecrypt(oldPasswor));
        logger.info(ToolKit.aesDecrypt(newPasswor));
        Integer uid = user.getUid();
        User userInfo = mapper.selectByPrimaryKey(uid);

        oldPasswor = ToolKit.shaEncode(ToolKit.aesDecrypt(oldPasswor));
        newPasswor = ToolKit.shaEncode(ToolKit.aesDecrypt(newPasswor));

        if (!userInfo.getPassword().equals(oldPasswor)) {
            return 0;
        } else {
            userInfo.setPassword(newPasswor);
            logger.info(userInfo);
            return updateUser(userInfo);
        }

    }

    @Override
    public int sendMail2NewNail(String mail) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        MailUrlBuilder builder = new MailUrlBuilder().builder(mail, user.getNickname());
        user.setNote(builder.getNote());
        mapper.updateByPrimaryKeySelective(user);

        mailSender.send(user.getNickname(),
                StaticEnum.MAIL_ACTIVE,
                builder.getMailUrl(),
                mail
        );
        return 0;
    }

    @Override
    public String activeUserMail(String id, String key) {
        try {
            id = ToolKit.aesDecrypt(id);
            logger.info(id);

            User user = selectUserByName(id);
            boolean status = ToolKit.md5Hex(user.getNote()).equals(key);
            if (status) {
                /**
                 * [0] 时间戳
                 * [1] 新邮箱
                 * */
                String[] notes = user.getNote().split("\\$");
                Long sendTime = Long.valueOf(notes[0]);/*毫秒值 */
                Long now = new Date().getTime();
                logger.info(now);
                logger.info(sendTime);
                boolean timeoff = now - sendTime > 30 * 60 * 1000;/*超过30分钟过期*/
                int userState = user.getState();
                /**
                 * 用户未激活过邮箱并且激活未超时
                 * */
                if (0 == userState && !timeoff) {
                    /*
                     * 更新头像
                     * 将邮箱md5更新为当前邮箱
                     * */
                    String[] split = user.getIcons().split("\\?d=");
                    String newIcon = ToolKit.md5Hex(user.getEmail());

                    newIcon = StaticEnum.PREFIX_ICON + newIcon;
                    newIcon += "?d=";
                    newIcon += split[1];
                    user.setIcons(newIcon);
                    user.setEmail(notes[1]);
                    user.setNote("");
                    user.setState(1);

                    mapper.updateByPrimaryKeySelective(user);
                    return StaticEnum.MAIL_ACTIVE_SUC;
                } else {
                    return StaticEnum.MAIL_TIMEOFF;
                }


            }
            return StaticEnum.MAIL_VER_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return StaticEnum.MAIL_VER_ERROR;
        }
    }

    @Override
    public String findPassword(String email) {


        int verCode = new Random().nextInt(900000) + 100000;

        String noteVerify = new Date().getTime() + "$";

        noteVerify += verCode;

        User user = new User();
        user.setEmail(email);
        user.setNote(noteVerify);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        int code = mapper.updateByExampleSelective(user, example);


        if (code == 1) {
            mailSender.send("亲爱的乐热评会员", StaticEnum.MAIL_FIND_PASSWORD, String.valueOf(verCode), email);
            return StaticEnum.OPT_SUCCESS;
        } else {
            return "邮箱不存在";
        }
    }

    @Override
    public String findPassword(String email, String noteVerify, String password) {
        password = ToolKit.aesDecrypt(password);
        password = ToolKit.shaEncode(password);

        User user = new User();
        user.setEmail(email);
        user.setNote("");
        user.setPassword(password);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        criteria.andNoteLike("%" + noteVerify);

        List<User> users = mapper.selectByExample(example);

        if (users.size() < 1) {
            return "邮箱或验证码错误";
        }

        String[] notes = users.get(0).getNote().split("\\$");
        Long sendTime = Long.valueOf(notes[0]);
        Long now = new Date().getTime();
        boolean timeoff = now - sendTime > 30 * 60 * 1000;
        if (timeoff) {
            return StaticEnum.MAIL_TIMEOFF;
        } else {
            if (1 == mapper.updateByExampleSelective(user, example)) {

                return StaticEnum.OPT_SUCCESS;
            } else {
                return StaticEnum.OPT_ERROR;
            }

        }

    }

    /**
     *
     * TODO : 需要增加一个删除旧头像的功能
     * 2018/4/15
     * amoxuk
     * */
    @Override
    public int updataIcon(String url) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String md5 = ToolKit.md5Hex(user.getEmail());

        try {
            url = StaticEnum.PREFIX_ICON + md5 + "?d=" + URLEncoder.encode("https://icon.amoxuk.com/icon/" + md5 + ".jpg", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info(url);
        user.setIcons(url);
        return mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public String findUserIcon(Integer id) {
        User u = mapper.selectByPrimaryKey(id);
        if (u != null) {
            return u.getIcons();
        } else {
            return "ab8138dfc78e29709d0f3651054b433c";
        }
    }


}
