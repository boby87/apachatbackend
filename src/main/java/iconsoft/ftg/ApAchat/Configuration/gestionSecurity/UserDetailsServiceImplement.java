package iconsoft.ftg.ApAchat.Configuration.gestionSecurity;

import iconsoft.ftg.ApAchat.gestionUtilisateur.Dto.UtilisateurDto;
import iconsoft.ftg.ApAchat.gestionUtilisateur.Metier.MetierAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {
    @Autowired
    MetierAccount metierAccount;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurDto utilisateurDto=metierAccount.findByMatriculeOrLoginAndActiveIsTrue(username);
        if (utilisateurDto==null)throw new RuntimeException();
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        utilisateurDto.getRolesUsers().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRolesname()));
        });
        return new User(utilisateurDto.getMatricule(),utilisateurDto.getPassword(),authorities);
    }
}
