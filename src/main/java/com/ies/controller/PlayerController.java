package com.ies.controller;

import com.ies.constast.SysConstast;
import com.ies.service.PlayerService;
import com.ies.utils.DataGridView;
import com.ies.utils.ResultObj;
import com.ies.vo.PlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: game
 * @description: 选手
 * @author: fuchen
 * @create: 2020-03-05 20:06
 **/
@RestController
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping("loadAllPlayer")
    public DataGridView loadAllUser(PlayerVo playerVo) {
        return playerService.queryAllPlayer(playerVo);
    }

    @RequestMapping("addPlayer")
    public ResultObj addPlayer(PlayerVo playerVo) {
        try {
            if (playerVo.getId() != null) {
                playerVo.setId(null);
            }
            playerService.checkPlayer(playerVo);
            playerService.addPlayer(playerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return new ResultObj(SysConstast.CODE_ERROR, e.getMessage());
        }
    }

    @RequestMapping("updatePlayer")
    public ResultObj updateUser(PlayerVo playerVo) {
        try {
            playerService.updatePlayer(playerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }


    @RequestMapping("deleteBatchPlayer")
    public ResultObj deleteBatchUser(PlayerVo playerVo) {
        try {
            playerService.deleteBatchPlayer(playerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("deletePlayer")
    public ResultObj deleteUser(PlayerVo playerVo) {
        try {
            playerService.deletePlayer(playerVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }


}
