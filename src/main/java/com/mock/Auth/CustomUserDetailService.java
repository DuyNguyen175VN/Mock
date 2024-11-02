package com.mock.Auth;


import com.mock.entities.Staff;
import com.mock.repositoty.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService {

    @Autowired
    StaffRepository staffRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Staff staff = staffRepository.findByUsername(username);
                if(staff == null) {
                    throw new UsernameNotFoundException("Staff not found");
                }
                return new CustomUserDetail(staff);
            }
        };
    }

}