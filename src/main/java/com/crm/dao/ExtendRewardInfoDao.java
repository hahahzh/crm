package com.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.crm.models.ExtendRewardInfo;


public interface ExtendRewardInfoDao extends CrudRepository<ExtendRewardInfo, Long>,JpaRepository<ExtendRewardInfo, Long> {

	@Query("select ema from ExtendRewardInfo ema where app_id=?1 and alliance_id=?2 and channel_tag=?3")
	public ExtendRewardInfo getAppInfo(Long app_id, String alliance_id, String channel_tag);
}
