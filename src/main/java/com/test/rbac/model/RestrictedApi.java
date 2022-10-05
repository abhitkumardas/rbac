package com.test.rbac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "RESTRICTED_APIS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestrictedApi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String uri;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User createdBy;

    @Column
    @CreatedDate
    private Date createdDate;
}
