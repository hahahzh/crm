package com.crm.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.crm.models.ExtendUserRecord;


public interface ExtendUserRecordDao extends CrudRepository<ExtendUserRecord, Long>,JpaRepository<ExtendUserRecord, Long> {

	@Query("select count(1) from  ExtendUserRecord a where invite_code=?1 and update_ts>?2 and update_ts<?3 and status = 0")
	public Integer findCountByDay(String invite_code, Date start, Date end);

	@Query("select count(1) from  ExtendUserRecord a where invite_code=?1 and update_ts>?2 and update_ts<?3 and ip=?4")
	public Integer findCountByIp(String invite_code, Date start, Date end,String ip);

}
