package com.dwring.springboot.model;

import javax.persistence.*;

@Table(name = "user_roles")
public class UserRoles {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "roles_id")
    private Long rolesId;

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return roles_id
     */
    public Long getRolesId() {
        return rolesId;
    }

    /**
     * @param rolesId
     */
    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }
}