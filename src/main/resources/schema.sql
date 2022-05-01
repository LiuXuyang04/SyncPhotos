DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    id       BIGINT(20)  NOT NULL COMMENT '主键ID',
    username VARCHAR(30) NOT NULL COMMENT 'uname',
    password VARCHAR(20) NOT NULL COMMENT 'pwd',
    email    VARCHAR(50) NOT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    id            BIGINT(20)   NOT NULL COMMENT '主键ID',
    name          VARCHAR(50) NOT NULL COMMENT '文件名',
    original_name VARCHAR(255) NOT NULL COMMENT '原文件名',
    file_type     CHAR(1)     DEFAULT '0' COMMENT '0-图片，1-视频',
    file_size     BIGINT(32)  NOT NULL COMMENT '文件大小',
    md5hex        VARCHAR(32) DEFAULT NULL COMMENT '0-已解析，1-待执行',
    del_flag      CHAR(1)     DEFAULT '0' COMMENT '0-正常，1-删除',
    PRIMARY KEY (id, name)
);

DROP TABLE IF EXISTS `file_exif`;
CREATE TABLE `file_exif`
(
    file_id    BIGINT(20)  NOT NULL COMMENT '文件ID',
    latitude   VARCHAR(20) DEFAULT NULL COMMENT '经度',
    longitude  VARCHAR(20) DEFAULT NULL COMMENT '纬度',
    camera     VARCHAR(20) DEFAULT NULL COMMENT '镜头',
    resolution VARCHAR(20) NOT NULL COMMENT '分辨率',
    origin_time DATETIME    NOT NULL COMMENT '时间',
    duration VARCHAR(20) DEFAULT NULL COMMENT '视频时长',
    PRIMARY KEY (file_id, origin_time)
);