package valuyskov.com.HRSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import valuyskov.com.HRSystem.ENUM.AppUserRole;
import valuyskov.com.HRSystem.model.AppUser;
import valuyskov.com.HRSystem.model.RegistrationRequest;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;


    public String register(RegistrationRequest request) {
        boolean isValidEmail =  emailValidator.test(request.getEmail());

        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getEmail(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER)
        );

    }
}
