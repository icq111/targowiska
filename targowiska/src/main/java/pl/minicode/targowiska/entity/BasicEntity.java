package pl.minicode.targowiska.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import pl.minicode.targowiska.common.Status;

@MappedSuperclass
public class BasicEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date insertStamp;
    
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateStamp;
    
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getInsertStamp() {
		return insertStamp;
	}

	public void setInsertStamp(Date insertStamp) {
		this.insertStamp = insertStamp;
	}

	public Date getUpdateStamp() {
		return updateStamp;
	}

	public void setUpdateStamp(Date updateStamp) {
		this.updateStamp = updateStamp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BasicEntity [id=" + id + ", insertStamp=" + insertStamp + ", updateStamp=" + updateStamp + ", status="
				+ status + "]";
	}
}
