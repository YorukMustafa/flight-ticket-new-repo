package com.flightapp.flightticketsystem.config;


import com.flightapp.flightticketsystem.entities.Role;
import com.flightapp.flightticketsystem.entities.User;
import com.flightapp.flightticketsystem.repository.RoleRepository;
import com.flightapp.flightticketsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Component //bu anotasyon sayesinde spring uygulama başlarken bu sınıfı ayağa kaldırır
@RequiredArgsConstructor// lombok final olarak tanımlanan değişkenler için constructor oluşturur
public class DatabaseSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(roleRepository.findByRoleName("Admin").isEmpty()){

            Role admin = new Role();
            admin.setRoleName("Admin");
            roleRepository.save(admin);
            System.out.println("Admin rolü başarıyla eklendi");
        }

        if(roleRepository.findByRoleName("Customer").isEmpty()){
            Role customer = new Role();
            customer.setRoleName("Customer");
            roleRepository.save(customer);
            System.out.println("Customer rolü başarıyla eklendi");
        }

        // Varsayılan Admin Kullanıcısını Ekleme
        if (userRepository.findByEmail("admin@flight.com").isEmpty()) {
            User adminUser = new User();
            adminUser.setEmail("admin@flight.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setFirstName("Sistem");
            adminUser.setLastName("Yöneticisi");

            roleRepository.findByRoleName("Admin").ifPresent(role -> adminUser.getRoles().add(role));
            
            userRepository.save(adminUser);
            System.out.println("Varsayılan Admin kullanıcısı (admin@flight.com / admin123) başarıyla eklendi");
        }
    }
}
