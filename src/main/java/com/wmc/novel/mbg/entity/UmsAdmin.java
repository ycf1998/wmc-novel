package com.wmc.novel.mbg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author money
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_admin")
public class UmsAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 状态：0：禁用；1：启用
     */
    @TableField("status")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 最后登录时间
     */
    @TableField("last_time")
    private LocalDateTime lastTime;

    /**
     * 初次登录：0：不是；1：是
     */
    @TableField("init_login")
    private Boolean initLogin;

    /**
     * 管理员类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
