CREATE TABLE `users`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 id',
    `login`              VARCHAR(64)  NOT NULL DEFAULT '0' COMMENT '登录名(同时也是用户唯一标识)',
    `password_hash`      VARCHAR(64)  NOT NULL DEFAULT '0' COMMENT '加盐后的密码',
    `nickname`           VARCHAR(127) NULL COMMENT '用户界面展示的名称',
    `email`              VARCHAR(255) NULL COMMENT '用户注册邮箱',
    `image_url`          VARCHAR(512) NULL COMMENT '用户头像',
    `activated`          TINYINT      NOT NULL COMMENT '用户是否激活',
    `lang_key`           VARCHAR(64)  NOT NULL DEFAULT '0' COMMENT '用户惯用国家语言',
    `activation_key`     VARCHAR(64)  NULL COMMENT '用户激活码',
    `reset_key`          VARCHAR(64)  NOT NULL DEFAULT '0' COMMENT '用户密码重置口令',
    `reset_date`         TIMESTAMP    NULL COMMENT '上一次密码重置时间',
    `created_by`         VARCHAR(64)  NOT NULL DEFAULT '0' COMMENT '创建人',
    `created_date`       TIMESTAMP    NULL COMMENT '用户注册日期',
    `last_modified_by`   VARCHAR(64)  NOT NULL DEFAULT '0' COMMENT '上一次修改人',
    `last_modified_date` TIMESTAMP    NULL COMMENT '上一次修改日期',
    PRIMARY KEY (`id`),
    UNIQUE (`login`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';


CREATE TABLE `roles`
(
    `name`               VARCHAR(64) NOT NULL comment '角色名称',
    `created_by`         VARCHAR(64) NOT NULL DEFAULT '0' COMMENT '创建人',
    `created_date`       TIMESTAMP   NULL COMMENT '用户注册日期',
    `last_modified_by`   VARCHAR(64) NOT NULL DEFAULT '0' COMMENT '上一次修改人',
    `last_modified_date` TIMESTAMP   NULL COMMENT '上一次修改日期',
    PRIMARY KEY (`name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '角色表';



CREATE TABLE `user_role`
(
    `user_id`            BIGINT      NOT NULL COMMENT '用户id',
    `authority_name`     VARCHAR(64) NOT NULL comment '角色名称',
    `created_by`         VARCHAR(64) NOT NULL DEFAULT '0' COMMENT '创建人',
    `created_date`       TIMESTAMP   NULL COMMENT '用户注册日期',
    `last_modified_by`   VARCHAR(64) NOT NULL DEFAULT '0' COMMENT '上一次修改人',
    `last_modified_date` TIMESTAMP   NULL COMMENT '上一次修改日期',
    PRIMARY KEY (`user_id`, `authority_name`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户角色表';
