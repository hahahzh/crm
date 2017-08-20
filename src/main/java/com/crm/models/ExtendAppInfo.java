package com.crm.models;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "extend_app_info")
public class ExtendAppInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5279222515029345521L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Index(name="extend_app_info_app_id")
	@Column(name="app_id")
	@Size(max = 16)
	private Long appid;

	@NotNull
	private String home_page_url;
	@NotNull
	private String game_reward_url;
	@NotNull
	private Long expired;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)  
	@Column(updatable = true)  
	@org.hibernate.annotations.CreationTimestamp
	private Date create_ts;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)  
	@Column(updatable = true)  
	@org.hibernate.annotations.UpdateTimestamp
	private Date update_ts;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getApp_id() {
		return appid;
	}
	public void setApp_id(Long appid) {
		this.appid = appid;
	}
	public String getHome_page_url() {
		return home_page_url;
	}
	public void setHome_page_url(String home_page_url) {
		this.home_page_url = home_page_url;
	}
	public Long getExpired() {
		return expired;
	}
	public void setExpired(Long expired) {
		this.expired = expired;
	}
	public Date getCreate_ts() {
		return create_ts;
	}
	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}
	public Date getUpdate_ts() {
		return update_ts;
	}
	public void setUpdate_ts(Date update_ts) {
		this.update_ts = update_ts;
	}

	public String getGame_reward_url() {
		return game_reward_url;
	}

	public void setGame_reward_url(String game_reward_url) {
		this.game_reward_url = game_reward_url;
	}
}
