package com.ies.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ies.constast.SysConstast;
import com.ies.domain.Player;
import com.ies.domain.PlayerCriteria;
import com.ies.domain.User;
import com.ies.domain.UserCriteria;
import com.ies.mapper.PlayerMapper;
import com.ies.mapper.UserMapper;
import com.ies.service.PlayerService;
import com.ies.utils.DataGridView;
import com.ies.vo.PlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * @program: game
 * @description: 选手处理类
 * @author: fuchen
 * @create: 2020-03-05 20:08
 **/
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public DataGridView queryAllPlayer(PlayerVo playerVo) {
        PlayerCriteria playerCriteria = new PlayerCriteria();
        PlayerCriteria.Criteria criteria = playerCriteria.createCriteria();
        if (!StringUtils.isEmpty(playerVo.getName())) {
            criteria.andNameLike("%" + playerVo.getName() + "%");
        }
        Page<Object> page = PageHelper.startPage(playerVo.getPage(),playerVo.getLimit());
        List<Player> players = playerMapper.selectByExample(playerCriteria);
        return new DataGridView(page.getTotal(),players);
    }

    @Override
    public void addPlayer(PlayerVo playerVo) {
        User user = new User();
        user.setUsername(playerVo.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(playerVo.getPassword().getBytes()));
        user.setType(SysConstast.USER_TYPE_PLAYER);
        userMapper.insertSelective(user);
        playerVo.setUserId(user.getId());
        playerMapper.insert(playerVo);
    }

    @Override
    public void updatePlayer(PlayerVo playerVo) {
        playerMapper.updateByPrimaryKey(playerVo);
    }

    @Override
    public void deletePlayer(Integer id) {
        Player player = playerMapper.selectByPrimaryKey(id);
        if (player != null) {
            userMapper.deleteByPrimaryKey(player.getUserId());
        }
        playerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatchPlayer(Integer[] ids) {
        Stream.of(ids).forEach(id -> deletePlayer(id));
    }

    @Override
    public void checkPlayer(PlayerVo playerVo) throws Exception {
        UserCriteria example = new UserCriteria();
        example.createCriteria().andUsernameEqualTo(playerVo.getUsername());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            throw new Exception("该用户名已经存在");
        }
    }
}
