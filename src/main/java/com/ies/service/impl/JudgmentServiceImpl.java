package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.constast.SysConstast;
import com.ies.domain.Judgment;
import com.ies.domain.JudgmentCriteria;
import com.ies.domain.User;
import com.ies.domain.UserCriteria;
import com.ies.mapper.JudgmentMapper;
import com.ies.mapper.UserMapper;
import com.ies.service.JudgmentService;
import com.ies.utils.DataGridView;
import com.ies.vo.JudgmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * @program: game
 * @description: 裁判处理
 * @author: fuchen
 * @create: 2020-03-06 22:04
 **/
@Service
public class JudgmentServiceImpl implements JudgmentService {

    @Autowired
    private JudgmentMapper judgmentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public DataGridView queryAllJudgment(JudgmentVo judgmentVo) {
        JudgmentCriteria judgmentCriteria = new JudgmentCriteria();
        JudgmentCriteria.Criteria criteria = judgmentCriteria.createCriteria();
        if (!StringUtils.isEmpty(judgmentVo.getName())) {
            criteria.andNameLike("%" + judgmentVo.getName() + "%");
        }
        Page<Object> page = PageHelper.startPage(judgmentVo.getPage(),judgmentVo.getLimit());
        List<Judgment> players = judgmentMapper.selectByExample(judgmentCriteria);
        return new DataGridView(page.getTotal(),players);
    }

    @Override
    public void checkJudgment(JudgmentVo judgmentVo) throws Exception {
        UserCriteria example = new UserCriteria();
        example.createCriteria().andUsernameEqualTo(judgmentVo.getUsername());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            throw new Exception("该用户名已经存在");
        }
    }

    @Override
    public void addJudgment(JudgmentVo judgmentVo) {
        User user = new User();
        user.setUsername(judgmentVo.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(judgmentVo.getPassword().getBytes()));
        user.setType(SysConstast.USER_TYPE_PLAYER);
        userMapper.insertSelective(user);
        judgmentVo.setUserId(user.getId());
        judgmentMapper.insert(judgmentVo);
    }

    @Override
    public void updateJudgment(JudgmentVo judgmentVo) {
        judgmentMapper.updateByPrimaryKey(judgmentVo);
    }

    @Override
    public void deleteBatchJudgment(Integer[] ids) {
        Stream.of(ids).forEach(id -> deleteJudgment(id));
    }

    @Override
    public void deleteJudgment(Integer id) {
        Judgment judgment = judgmentMapper.selectByPrimaryKey(id);
        if (judgment != null) {
            userMapper.deleteByPrimaryKey(judgment.getUserId());
        }
        judgmentMapper.deleteByPrimaryKey(id);
    }
}
