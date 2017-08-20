package com.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.crm.models.ExtendGetReward;


public interface ExtendGetRewardDao extends CrudRepository<ExtendGetReward, Long>,JpaRepository<ExtendGetReward, Long> {
	

}
