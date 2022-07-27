package pl.alledrogo.alledrogo_spring_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.alledrogo.alledrogo_spring_lab.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    AppUser findByVerificationCode(String code);
}
