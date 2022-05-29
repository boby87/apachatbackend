package iconsoft.ftg.ApAchat.gestionUtilisateur.Metier;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RegisterDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RolesUserDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.UtilisateurDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.AcheteurMetier;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.DirecteurAchat;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.RolesUser;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.Utilisateur;

import java.util.List;

public interface MetierAccount {
    RegisterDto saveUser(RegisterDto registerDto);
    RolesUser saveRole(RolesUserDto rolesUserDto);
    Utilisateur addRoleToUser(String matricule,String rolename);
    List<UtilisateurDto> findAllUstilisateur();
    UtilisateurDto findByMatriculeOrLoginAndActiveIsTrue(String username);
    AcheteurMetier LocalfindByMatriculeOrLoginAndActiveIsTrue(String referenceAcheteurmetier);
    Utilisateur findByFonction(String role);
}
