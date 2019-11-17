package com.tsystem.urlcrawler.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

		@Transient
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Column(name = "created_date", nullable = false, updatable = false)
		@CreatedDate
		private LocalDateTime createdDate;

		@Column(name = "modified_date")
		@LastModifiedDate
		private LocalDateTime modifiedDate;

		@Column(name = "created_by")
		@CreatedBy
		private String createdBy;

		@Column(name = "modified_by")
		@LastModifiedBy
		private String modifiedBy;

		private boolean deleted;


	}

