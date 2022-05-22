package iconsoft.ftg.ApAchat.gestionUtilisateur.Service;

import iconsoft.ftg.ApAchat.gestionUtilisateur.ConstanteRoles;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoRolesUser;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoUtilisateur;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RegisterDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RolesUserDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.UtilisateurDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.RolesUser;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.Utilisateur;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Metier.MetierAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceAccount implements MetierAccount {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    DaoUtilisateur daoUtilisateur;
    DaoRolesUser daoRolesUser;

    @Override
    public RegisterDto saveUser(RegisterDto registerDto) {
        Utilisateur utilisateur = daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(registerDto.getMatricule());
        if (utilisateur != null) throw new RuntimeException();
        utilisateur = new Utilisateur();
        String hash = bCryptPasswordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(hash);
        BeanUtils.copyProperties(registerDto, utilisateur);
        utilisateur = daoUtilisateur.save(utilisateur);
        utilisateur = addRoleToUser(utilisateur.getMatricule(), registerDto.getFonction());
        BeanUtils.copyProperties(utilisateur, registerDto);
        return registerDto;
    }

    @Override
    public RolesUser saveRole(RolesUserDto rolesUserDto) {
        RolesUser rolesUser = new RolesUser();
        BeanUtils.copyProperties(rolesUserDto, rolesUser);
        return daoRolesUser.save(rolesUser);
    }

    @Override
    public Utilisateur addRoleToUser(String matricule, String rolename) {
        if (ConstanteRoles.DIRECTEUR_ACHAT.equals(rolename) || ConstanteRoles.ACHETEUR_METIER.equals(rolename) || ConstanteRoles.RESPONSABLE_STOCK.equals(rolename)) {
            Utilisateur utilisateur = daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(matricule);
            RolesUser rolesUser = daoRolesUser.findByRolesnameAndActiveIsTrue(rolename);
            utilisateur.getRolesUsers().add(rolesUser);
            return utilisateur;
        } else throw new RuntimeException();

    }

    @Override
    public List<UtilisateurDto> findAllUstilisateur() {
        List<UtilisateurDto> utilisateurDtos=new ArrayList<>();
        daoUtilisateur.findAll().forEach(u->{
            UtilisateurDto utilisateurDto=new UtilisateurDto();
            BeanUtils.copyProperties(u,utilisateurDto);
            utilisateurDtos.add(utilisateurDto);
        });
        return utilisateurDtos;
    }

    @Override
    public UtilisateurDto findByMatriculeOrLoginAndActiveIsTrue(String username) {
        Utilisateur utilisateur = daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(username);
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        BeanUtils.copyProperties(utilisateur, utilisateurDto);
        return utilisateurDto;
    }
}
