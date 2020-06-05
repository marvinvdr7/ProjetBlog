package be.projetblog.technofuturtic.projetblog.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

    private Long id;
    private String name;
    private List<PermissionDTO> permissions = new ArrayList<>();

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

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
