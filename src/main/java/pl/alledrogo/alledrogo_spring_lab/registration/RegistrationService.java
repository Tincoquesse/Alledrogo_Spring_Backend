package pl.alledrogo.alledrogo_spring_lab.registration;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alledrogo.alledrogo_spring_lab.appUser.AppUser;
import pl.alledrogo.alledrogo_spring_lab.appUser.AppUserRole;
import pl.alledrogo.alledrogo_spring_lab.appUser.AppUserService;
import pl.alledrogo.alledrogo_spring_lab.registration.token.ConfirmationToken;
import pl.alledrogo.alledrogo_spring_lab.registration.token.ConfirmationTokenService;

@Service
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public RegistrationService(AppUserService appUserService, EmailValidator emailValidator, ConfirmationTokenService confirmationTokenService) {
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
    }

    public String register(RegistrationRequest request) {
        Boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid!");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt()!= null) {
            throw new IllegalStateException("email already confirmed");
        }
    }

}
