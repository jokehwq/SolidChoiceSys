package com.foreveross.webbase.solidchoice.service;

import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.solidchoice.entity.Topic;
import com.foreveross.webbase.solidchoice.entity.Voting;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by HIAPAD on 2018/9/10.
 */
public interface VotingService extends ICrudService<Voting>{
	/**
	 * 查询所有
	 * @return
	 */
    List<Voting> selectVotingList();

    CommonResponse queryDictInfo(Voting voting);
    
    /***
     * 根据用户Id查询单条投票信息
     * @param voting
     * @return
     */
    public Voting selectVotingById(String id);
    
    /***
     * 根据用户Id删除单条投票信息(逻辑删除)
     * @param voting
     * @return
     */
    public boolean delVotingById(String id);
    
    /***
     * 根据用户Id更新单条投票信息
     * @param voting
     * @return
     */
    public boolean updateVotingById(Voting voting);
    
    /***
     * 插入对象
     * @param voting
     * @return
     */
    public boolean insertVoting(Voting voting);
    
    /**
     * 修改审核状态
     * @param topicId
     * @param status
     * @return
     */
    public boolean checkStatus(Topic topic);

}
