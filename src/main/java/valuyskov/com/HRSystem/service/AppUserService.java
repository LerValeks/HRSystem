package valuyskov.com.HRSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import valuyskov.com.HRSystem.model.AppUser;
import valuyskov.com.HRSystem.repository.AppUserRepository;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

private final static String USER_NOT_FOUND = "user with email %s not found";


private final AppUserRepository appUserRepository;
private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean  userExist  = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if (userExist) {
            throw new IllegalStateException("email already taken");
        }

        String  encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        //ToDo: send confirmation and tocken
        return "it works";
    }
}
