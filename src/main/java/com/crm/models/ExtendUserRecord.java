package com.crm.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "extend_user_record")
public class ExtendUserRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052745767877807578L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String invite_code;
	@NotNull
	@Size(max = 16)
	private String ip;
	private Integer status; // 状态 0：有效点击 1：无效点击
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
	public String getInvite_code() {
		return invite_code;
	}
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getUpdate_ts() {
		return update_ts;
	}
	public void setUpdate_ts(Date update_ts) {
		this.update_ts = update_ts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
