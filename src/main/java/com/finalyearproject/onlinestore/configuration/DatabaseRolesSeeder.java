package com.mitron.onlinestore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mitron.onlinestore.domain.entities.Role;
import com.mitron.onlinestore.repository.UserRoleRepository;

import static com.mitron.onlinestore.util.constants.ValidationErrorMessages.*;

import javax.annotation.PostConstruct;

@Component
public class DatabaseRolesSeeder {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DatabaseRolesSeeder(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @PostConstruct
    public void seed() {
        if (this.userRoleRepository.count() == 0) {
            Role rootAdmin = new Role();
            rootAdmin.setAuthority(ROLE_ADMIN);

            Role admin = new Role();
            admin.setAuthority(ROLE_ADMIN);

            Role moderator = new Role();
            moderator.setAuthority(ROLE_MODERATOR);

            Role user = new Role();
            user.setAuthority(ROLE_USER);

            this.userRoleRepository.save(rootAdmin);
            this.userRoleRepository.save(admin);
            this.userRoleRepository.save(moderator);
            this.userRoleRepository.save(user);
        }
    }
}
