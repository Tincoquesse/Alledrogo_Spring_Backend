package pl.alledrogo.alledrogo_spring_lab.service;


import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Role;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AppUserService {

    void registerUser(AppUser user, String siteUrl) throws MessagingException, UnsupportedEncodingException;
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser saveAdmin(AppUser user);
    AppUser getAppUser(String username);
    List<AppUser> getAppUsers();
    boolean verify(String verificationCode);
}
