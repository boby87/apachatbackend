package iconsoft.ftg.ApAchat.gestionUtilisateur.viewRestfull;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.RegisterDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.UtilisateurDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Entities.Utilisateur;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Metier.MetierAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("utilisateur")
public class RegisterRestFull {
    @Autowired
    MetierAccount metierAccount;

    @PostMapping("save")
    RegisterDto registerUser(@RequestBody RegisterDto registerDto){
        return metierAccount.saveUser(registerDto);
    }
    @GetMapping("get/{login}")
    UtilisateurDto findUtilisateur(@PathVariable String login){
        return metierAccount.findByMatriculeOrLoginAndActiveIsTrue(login);
    }
    @GetMapping("all")
    public List<UtilisateurDto> findAllUstilisateur() {
        return metierAccount.findAllUstilisateur();
    } 
    @GetMapping("alls")
    public List<Utilisateur> findAllUstilisateurs() {
        return metierAccount.findAllUstilisateurreel();
    }
    @PutMapping("update")
    public UtilisateurDto updateUser(@RequestBody UtilisateurDto utilisateurDto){
        return metierAccount.updateUser(utilisateurDto);
    }


}
