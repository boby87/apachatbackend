package iconsoft.ftg.ApAchat.gestionUtilisateur;

import java.security.SecureRandom;

public class RandomReference {
    static SecureRandom rnd = new SecureRandom();
    public static String randomString(int len){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}
