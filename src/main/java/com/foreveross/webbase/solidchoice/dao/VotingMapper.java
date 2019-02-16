package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.solidchoice.entity.Topic;
import com.foreveross.webbase.solidchoice.entity.Voting;

import java.util.List;

/**
 * Created by HIAPAD on 2018/9/10.
 */
@MyBatisDao
public interface VotingMapper extends CrudDao<Voting> {
    /**
     * 查询全部投票
     * @return
     */
    public List<Voting> selectVotingList();
    //根据voting简介搜索voting内容
//    List<Voting>  queryDictListByCondition(@Param("id")Integer id,@Param("text") String text);
    
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
    public int delVotingById(String topicId);
    
    /***
     * 根据用户Id更新单条投票信息
     * @param voting
     * @return
     */
    public int updateVotingById(Voting voting);
    
    /***
     * 插入对象
     * @param voting
     * @return
     */
    public int insertVoting(Voting voting);
    
    /***
     * 修改审核状态
     * @param voting
     * @return
     */
    public int checkStatus(Topic topic);
    
    
    
}
