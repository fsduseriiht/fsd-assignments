package com.cts.fsd.login.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.fsd.entity.Role;
import com.cts.fsd.entity.User;
import com.cts.fsd.repo.RoleRepository;
import com.cts.fsd.repo.UserRepository;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    
    private RoleRepository roleRepository;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
    	
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        
    }

    public User findUserByEmail(String email) {
        
    	return userRepository.findByEmail(email);
    	
    }

    public User saveUser(User user) {
    	
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = findByRole(user.getType());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
        
    }
    public Role findByRole(String role) {
    	
    	String roleToSearch = "USER";
    	
    	if (getTypeOfRoles().stream().anyMatch(role::equalsIgnoreCase)) {
    		roleToSearch = role.toUpperCase();
		}
    	
    	return roleRepository.findByRole(roleToSearch);
    }
    
    public List<String> getTypeOfRoles() {
    	List<String> roleList = new ArrayList<String>();
    	
    	roleList.add("ADMIN");
    	roleList.add("USER");
    	
    	return roleList;
    }

}