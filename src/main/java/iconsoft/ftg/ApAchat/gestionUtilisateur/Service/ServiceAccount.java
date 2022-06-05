package iconsoft.ftg.ApAchat.gestionUtilisateur.Service;

import iconsoft.ftg.ApAchat.gestionUtilisateur.ConstanteRoles;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoAcheteurMetier;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoAdmin;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoDirecteurAchat;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoRolesUser;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dao.DaoUtilisateur;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RegisterDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RolesUserDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.UtilisateurDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.*;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Metier.MetierAccount;
import iconsoft.ftg.ApAchat.gestionUtilisateur.RandomReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    @Autowired
    DaoRolesUser daoRolesUser;
    @Autowired
    DaoDirecteurAchat daoDirecteurAchat;
    @Autowired
    DaoAdmin daoAdmin;
    @Autowired
    DaoAcheteurMetier daoAcheteurMetier;

    @Override
    public RegisterDto saveUser(RegisterDto registerDto) {
        Utilisateur utilisateur = daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(registerDto.getMatricule());
        if (utilisateur != null) throw new RuntimeException();
        utilisateur = new Utilisateur();
        String hash = bCryptPasswordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(hash);
        BeanUtils.copyProperties(registerDto, utilisateur);
        utilisateur.setReference(RandomReference.randomString(5));
        if (ConstanteRoles.DIRECTEUR_ACHAT.equals(utilisateur.getFonction())){
            DirecteurAchat directeurAchat=new DirecteurAchat();
            BeanUtils.copyProperties(utilisateur,directeurAchat);
            utilisateur = daoDirecteurAchat.save(directeurAchat);
        }else if (ConstanteRoles.ADMIN.equals(utilisateur.getFonction())){
            AdminUtilisateur adminUtilisateur=new AdminUtilisateur();
            BeanUtils.copyProperties(utilisateur,adminUtilisateur);
            utilisateur = daoAdmin.save(adminUtilisateur);
        } else if (ConstanteRoles.ACHETEUR_METIER.equals(utilisateur.getFonction())) {
            AcheteurMetier acheteurMetier = new AcheteurMetier();
            BeanUtils.copyProperties(utilisateur, acheteurMetier);
            utilisateur = daoAcheteurMetier.save(acheteurMetier);
        }
        addRoleToUser(utilisateur.getMatricule(), registerDto.getFonction());
        utilisateur =daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(utilisateur.getMatricule());
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
        if (ConstanteRoles.DIRECTEUR_ACHAT.equals(rolename) || ConstanteRoles.ACHETEUR_METIER.equals(rolename) || ConstanteRoles.RESPONSABLE_STOCK.equals(rolename)|| ConstanteRoles.ADMIN.equals(rolename)) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("User matricule : " + matricule);
            System.out.println("--------------------------------------------------------------");
            Utilisateur utilisateur = daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(matricule);
            RolesUser rolesUser = daoRolesUser.findByRolesnameAndActiveIsTrue(rolename);
            utilisateur.getRolesUsers().add(rolesUser);
            daoUtilisateur.save(utilisateur);
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
    public List<Utilisateur> findAllUstilisateurreel() {
        return daoUtilisateur.findAll();
    }

    @Override
    public UtilisateurDto findByMatriculeOrLoginAndActiveIsTrue(String username) {
        Utilisateur utilisateur = daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(username);
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        List<RolesUserDto> rolesUserDto=new ArrayList<>();
        if (utilisateur!=null) BeanUtils.copyProperties(utilisateur, utilisateurDto);
        if (utilisateur.getRolesUsers()!=null) {
            utilisateur.getRolesUsers().forEach(r->{
                RolesUserDto rolesUserDto1=new RolesUserDto();
                BeanUtils.copyProperties(r, rolesUserDto1);
                rolesUserDto.add(rolesUserDto1);

            });
        }
        utilisateurDto.setRolesUsers(rolesUserDto);
        return utilisateurDto;
    }

    @Override
    public AcheteurMetier LocalfindByMatriculeOrLoginAndActiveIsTrue(String matriculeAcheteurmetier) {
        return (AcheteurMetier) daoUtilisateur.findByMatriculeOrLoginAndActiveIsTrue(matriculeAcheteurmetier);
    }

    @Override
    public Utilisateur findByFonction(String fonction) {
        return daoUtilisateur.findByFonction(fonction);
    }

    @Override
    public DirecteurAchat findByDirecteurReferenceAndActiveIsTrue(String reference) {
        return daoDirecteurAchat.findByReferenceAndActiveIsTrue(reference);
    }

    @Bean
    void generateAdmin(){
        if (daoAdmin.findAll().isEmpty()){
            daoRolesUser.save(new RolesUser(ConstanteRoles.ADMIN));
            daoRolesUser.save(new RolesUser(ConstanteRoles.ACHETEUR_METIER));
            daoRolesUser.save(new RolesUser(ConstanteRoles.DIRECTEUR_ACHAT));
            daoRolesUser.save(new RolesUser(ConstanteRoles.RESPONSABLE_STOCK));
            RegisterDto registerDto=new RegisterDto();
            registerDto.setPassword("12345678");
            registerDto.setEmail("admin@gmail.com");
            registerDto.setFonction(ConstanteRoles.ADMIN);
            registerDto.setLogin("admin");
            registerDto.setTelephone("7777777");
            registerDto.setMatricule("sophie");
            saveUser(registerDto);
        }

    }
}
