package com.crm.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "extend_get_reward")
public class ExtendGetReward implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1623700054934929671L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private Long uid;
	@NotNull
	@Size(max = 16)
	private String invite_code;
	private Long eri_id;
	private String order_id;
	@org.hibernate.annotations.UpdateTimestamp
	private Date update_ts;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getInvite_code() {
		return invite_code;
	}
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	public Long getEri_id() {
		return eri_id;
	}
	public void setEri_id(Long eri_id) {
		this.eri_id = eri_id;
	}
	public Date getUpdate_ts() {
		return update_ts;
	}
	public void setUpdate_ts(Date update_ts) {
		this.update_ts = update_ts;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
}
