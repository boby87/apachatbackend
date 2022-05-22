package iconsoft.ftg.ApAchat.gestionUtilisateur.Entities;

import javax.persistence.*;

@Entity
public class RolesUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolesname;
    private boolean active=true;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolesname() {
        return rolesname;
    }

    public void setRolesname(String rolesname) {
        this.rolesname = rolesname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
