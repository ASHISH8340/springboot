package com.gl.EmployeeManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Builder
@Entity
@Table(name="role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String roleId;
    private String roleName;
}
