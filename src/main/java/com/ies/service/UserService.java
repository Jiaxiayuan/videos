package com.ies.service;

import com.ies.domain.User;
import com.ies.utils.DataGridView;
import com.ies.vo.UserVo;

public interface UserService {
    User login(UserVo userVo);

    DataGridView queryAllUser(UserVo uservo);

    void addUser(UserVo uservo);

    void updateUser(User user);

    void deleteBatchRole(Integer[] ids);

    void deleteUser(Integer id);

    void resetUserPwd(Integer id);

    User queryById(Integer userId);
}
