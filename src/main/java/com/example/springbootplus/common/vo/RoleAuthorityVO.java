package com.example.springbootplus.common.vo;

import com.example.springbootplus.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleAuthorityVO {
    private Role role;
    private List<Long> authorityId;
}
