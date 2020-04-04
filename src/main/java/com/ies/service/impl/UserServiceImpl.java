package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.constast.SysConstast;
import com.ies.domain.User;
import com.ies.domain.UserCriteria;
import com.ies.mapper.UserMapper;
import com.ies.service.UserService;
import com.ies.utils.DataGridView;
import com.ies.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserVo userVo) {
        String pwd = DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes());
        userVo.setPassword(pwd);
        UserCriteria example = new UserCriteria();
        example.createCriteria().andUsernameEqualTo(userVo.getUsername()).andPasswordEqualTo(userVo.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if(users.size()>=1){
            return users.get(0);
        }else{
            return null;
        }

    }
    @Override
    public DataGridView queryAllUser(UserVo uservo) {
        UserCriteria example = new UserCriteria();
        UserCriteria.Criteria criteria = example.createCriteria();
        if(uservo.getUsername()!=null){
            criteria.andUsernameLike("%"+uservo.getUsername()+"%");
        }
        if (uservo.getType() != null) {
            criteria.andTypeEqualTo(uservo.getType());
        }
        Page<Object> page = PageHelper.startPage(uservo.getPage(),uservo.getLimit());
        List<User> users = userMapper.selectByExample(example);
        return new DataGridView(page.getTotal(),users);
    }

    @Override
    public void addUser(UserVo uservo) {
        userMapper.insert(uservo);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer id:ids){
            deleteUser(id);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void resetUserPwd(Integer id) {
        String pwd = DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes());
        UserCriteria example = new UserCriteria();
        example.createCriteria().andPasswordEqualTo(pwd);
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(pwd);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User queryById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
