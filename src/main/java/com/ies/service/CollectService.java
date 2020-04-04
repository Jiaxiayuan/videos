package com.ies.service;

import com.ies.domain.Collect;
import com.ies.utils.DataGridView;
import com.ies.vo.CollectVo;

public interface CollectService {

    Integer queryByVideoCount(Integer videoId);

    Collect queryByUserIdAndVideoId(Integer userId, Integer videoId);

    void addCollect(Integer userId, Integer videoId);

    void updateCollect(Collect collect);

    DataGridView queryAllCollect(CollectVo collectVo);
}
