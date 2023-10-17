package com.example.springbootplus.common.bo;

import com.example.springbootplus.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleAuthorityBO {
    private Role role;
    private List<Long> authorityId;
}
