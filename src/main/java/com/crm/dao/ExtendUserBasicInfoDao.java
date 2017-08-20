package com.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.crm.models.ExtendUserBasicInfo;


public interface ExtendUserBasicInfoDao extends CrudRepository<ExtendUserBasicInfo, Long>,JpaRepository<ExtendUserBasicInfo, Long> {

	@Query("select count(1) from ExtendUserBasicInfo ema where invite_code=?1")
	public Integer findByInvitecode(String invitecode);
	@Query("select ema from ExtendUserBasicInfo ema where uid=?1 and app_id=?2 and alliance_id=?3 and channel_tag=?4 and server=?5")
	public ExtendUserBasicInfo findOne(Long uid, Long app_id, String alliance_id, String channel_tag, String server);
	@Query("select ema from ExtendUserBasicInfo ema where invite_code=?1")
	public ExtendUserBasicInfo findByCode(String invitecode);

}
