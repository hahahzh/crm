package com.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.crm.models.ExtendAppInfo;


public interface ExtendAppInfoDao extends CrudRepository<ExtendAppInfo, Long>,JpaRepository<ExtendAppInfo, Long> {

	@Query("select count(1) from ExtendAppInfo ema where app_id=?1")
	public Integer findCount(Long appid);

	@Query("select ema from ExtendAppInfo ema where app_id=?1")
	public ExtendAppInfo findByAppid(Long appid);
}
