package com.crm.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "extend_reward_info")
public class ExtendRewardInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2557523624311600081L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private Long app_id;
	@Size(max = 16)
	private String alliance_id;
	@Size(max = 16)
	private String channel_tag;
	
	@NotNull
	private Integer day_ip_max;
	private Integer day_max;
	private Integer week_max;
	private Integer invite_level;
	private String invite_item_count;
	@Size(max = 30)
	private String invite_item_id;
	@Size(max = 50)
	private String invite_item_name;
	private Integer get_count;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true)
	@org.hibernate.annotations.CreationTimestamp
	private Date create_ts;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true)
	@org.hibernate.annotations.UpdateTimestamp
	private Date update_ts;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getApp_id() {
		return app_id;
	}
	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}
	public String getAlliance_id() {
		return alliance_id;
	}
	public void setAlliance_id(String alliance_id) {
		this.alliance_id = alliance_id;
	}
	public String getChannel_tag() {
		return channel_tag;
	}
	public void setChannel_tag(String channel_tag) {
		this.channel_tag = channel_tag;
	}

	public Integer getInvite_level() {
		return invite_level;
	}
	public void setInvite_level(Integer invite_level) {
		this.invite_level = invite_level;
	}
	public String getInvite_item_count() {
		return invite_item_count;
	}
	public void setInvite_item_count(String invite_item_count) {
		this.invite_item_count = invite_item_count;
	}
	public String getInvite_item_id() {
		return invite_item_id;
	}
	public void setInvite_item_id(String invite_item_id) {
		this.invite_item_id = invite_item_id;
	}
	public String getInvite_item_name() {
		return invite_item_name;
	}
	public void setInvite_item_name(String invite_item_name) {
		this.invite_item_name = invite_item_name;
	}
	public Integer getGet_count() {
		return get_count;
	}
	public void setGet_count(Integer get_count) {
		this.get_count = get_count;
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
	public Integer getDay_ip_max() {
		return day_ip_max;
	}
	public void setDay_ip_max(Integer day_ip_max) {
		this.day_ip_max = day_ip_max;
	}
	public Integer getDay_max() {
		return day_max;
	}
	public void setDay_max(Integer day_max) {
		this.day_max = day_max;
	}
	public Integer getWeek_max() {
		return week_max;
	}
	public void setWeek_max(Integer week_max) {
		this.week_max = week_max;
	}

	
}
