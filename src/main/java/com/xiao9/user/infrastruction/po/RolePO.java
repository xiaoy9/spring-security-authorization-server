package com.xiao9.user.infrastruction.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
public class RolePO extends AbstractAuditingEntity {

    // 角色名称
    @Id
    @Column(length = 64)
    private String name;
}
