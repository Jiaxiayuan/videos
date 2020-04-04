package com.ies.service;

import com.ies.utils.DataGridView;
import com.ies.vo.PlayerVo;

public interface PlayerService {

    DataGridView queryAllPlayer(PlayerVo playerVo);

    void addPlayer(PlayerVo playerVo);

    void updatePlayer(PlayerVo playerVo);

    void deletePlayer(Integer id);

    void deleteBatchPlayer(Integer[] ids);

    void checkPlayer(PlayerVo playerVo) throws Exception;
}
