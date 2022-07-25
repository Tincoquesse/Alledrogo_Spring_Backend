package pl.alledrogo.alledrogo_spring_lab.service;


import pl.alledrogo.alledrogo_spring_lab.model.AppUser;
import pl.alledrogo.alledrogo_spring_lab.model.Role;

import java.util.List;

public interface AppUserService {

    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser saveAdmin(AppUser user);
    AppUser getAppUser(String username);
    List<AppUser> getAppUsers();

}
