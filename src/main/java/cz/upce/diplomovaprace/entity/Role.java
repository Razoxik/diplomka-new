package cz.upce.diplomovaprace.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by to068466 on 29.10.2017.
 */
@Entity
public class Role {

    private int roleId;
    private Timestamp created;
    private String name;
    private Collection<User> usersByRoleId;

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (created != null ? !created.equals(role.created) : role.created != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleRoleId")
    public Collection<User> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<User> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }


    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", created=" + created +
                ", name='" + name + '\'' +
                '}';
    }
}
