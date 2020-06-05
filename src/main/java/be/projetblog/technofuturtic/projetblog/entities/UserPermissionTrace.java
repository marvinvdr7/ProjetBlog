package be.projetblog.technofuturtic.projetblog.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "user_permission_trace")
@SequenceGenerator(name = "z_seq_user_permission_trace", initialValue = 100, allocationSize = 1)
public class UserPermissionTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "z_seq_user_permission_trace")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "permission_id")
    private Long permission_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Long permission_id) {
        this.permission_id = permission_id;
    }

    @Override
    public String toString() {
        return "UserPermissionTrace{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user_id=" + user_id +
                ", permission_id=" + permission_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionTrace that = (UserPermissionTrace) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(permission_id, that.permission_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, user_id, permission_id);
    }
}
