package be.projetblog.technofuturtic.projetblog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "permission")
@SequenceGenerator(name = "z_seq_permission", initialValue = 100, allocationSize = 1)
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "z_seq_permission")
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "name" , nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserPermission> userPermissions;

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<RolePermission> rolePermissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userPermissions=" + userPermissions +
                ", rolePermissions=" + rolePermissions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(userPermissions, that.userPermissions) &&
                Objects.equals(rolePermissions, that.rolePermissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userPermissions, rolePermissions);
    }
}
