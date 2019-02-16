package com.foreveross.webbase.solidchoice.service.impl;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.OssUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.solidchoice.dao.VotingMapper;
import com.foreveross.webbase.solidchoice.entity.Topic;
import com.foreveross.webbase.solidchoice.entity.Voting;
import com.foreveross.webbase.solidchoice.service.VotingService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by HIAPAD on 2018/9/10.
 */
@Service
@Transactional(readOnly=false)
public class VotingServiceImpl extends CrudService<VotingMapper,Voting> implements VotingService{

    @Resource
    private VotingMapper votingDao;

    @Override
    public List<Voting> selectVotingList(){

        return votingDao.selectVotingList();
    }

    @Override
    public CommonResponse queryDictInfo(Voting voting){

         /*List<Voting> dictList= votingDao.queryDictListByCondition(voting.getId(),voting.getText());
        if (CollectionUtils.isNotEmpty(dictList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), dictList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();*/
        return  null;
    }

	@Override
	public Voting selectVotingById(String id) {
		// TODO Auto-generated method stub
		return votingDao.selectVotingById(id);
	}

	@Override
	public boolean delVotingById(String id) {
		// TODO Auto-generated method stub
		int delVotingById = votingDao.delVotingById(id);
		if(delVotingById==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean updateVotingById(Voting voting) {
		// TODO Auto-generated method stub
		int updateVotingById = votingDao.updateVotingById(voting);
		if(updateVotingById==0){
			return false;
		}
		return true;
	}
	@Override
	public boolean insertVoting(Voting voting) {
		// TODO Auto-generated method stub
		int insertVoting = votingDao.insertVoting(voting);
		if(insertVoting==0){
			return false;
		}
		return true;
	}

	@Override
	public boolean checkStatus(Topic topic) {
		// TODO Auto-generated method stub
		int checkPass = votingDao.checkStatus(topic);
		if(checkPass==0){
			return false;
		}
		return true;
	}
}
