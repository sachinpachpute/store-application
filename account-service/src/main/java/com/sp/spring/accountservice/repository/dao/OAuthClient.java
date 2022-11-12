package com.sp.spring.accountservice.repository.dao;

import com.sp.spring.commons.util.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: Devaraj Reddy, Date : 2019-05-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_client_details")
@Builder
public class OAuthClient extends DateAudit {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "CLIENT_ID", updatable = false, nullable = false)
  private String client_id;

  @Column(name = "CLIENT_SECRET", updatable = false, nullable = false)
  private String client_secret;
  private String authorized_grant_types;
  private String authorities;
  private String scope;
  private String resource_ids;

}
