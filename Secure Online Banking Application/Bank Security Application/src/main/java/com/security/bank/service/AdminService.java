package com.security.bank.service;

import com.security.bank.entity.*;
import com.security.bank.dto.AdminDto;
import com.security.bank.repository.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class AdminService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    // @Transactional
    // public void register(AdminDto dto)
    // {
    //     User user = new User();
    //     user.setDetails(dto.getName(), dto.getUsername(), dto.getAddress(), dto.getNumber(), dto.getIdentityProof());
    //     user.setPassword(encoder.encode(dto.getPassword()));
    //     Role role = roleRepository.findByRoleName("ROLE_ADMIN");
    //     if(role == null)
    //     {
    //         role = new Role();
    //         role.setRoleName("ROLE_ADMIN");
    //         role = roleRepository.save(role);
    //     }
    //     user.setRoles(role);
    //     userRepository.save(user);
    // }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUserByName(String username)
    {
        return userRepository.findByUsername(username).get();
    }

    public void deleteUser(Long userId)
    {
        userRepository.deleteById(userId);
    }

    // @Transactional
    // public String deactivateAccount(Long userId, Long accountId)
    // {
    //     User user = userRepository.findById(userId).get();
    //     Account account = accountRepository.findById(accountId).get();
    //     if(user == null || account == null)
    //     {
    //         return "ERROR";
    //     }
    //     account.setStatus("INACTIVE");
    //     return "Deactivated Account for User with id: " + userId;
    // }

    // @Transactional
    // public String activateAccount(Long userId, Long accountId)
    // {
    //     User user = userRepository.findById(userId).get();
    //     Account account = accountRepository.findById(accountId).get();
    //     if(user == null || account == null)
    //     {
    //         return "ERROR";
    //     }
    //     account.setStatus("ACTIVE");
    //     return "Activated Account for User with id: " + userId;
    // }

    public List<Account> getAllActiveAccounts()
    {
        return accountRepository.findAllActiveAccounts();
    }

    public List<Account> getAllInactiveAccounts()
    {
        return accountRepository.findAllInactiveAccounts();
    }

    public List<Account> getAllAccountsOfType(AccountType accountType)
    {
        return accountRepository.findAllByAccountType(accountType);
    }

    public List<Account> getAllAccountsOfBranchType(BranchType branchType)
    {
        return accountRepository.findAllByBranchType(branchType);
    }

    public void register(AdminDto adminDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(adminDto.getPassword());
        Role role = new Role();
        role.setRoleName("ROLE_ADMIN");
        User saveUser = new User();
        saveUser.setName(adminDto.getName());
        saveUser.setPassword(encodedPassword);
        saveUser.setUsername(adminDto.getUsername());
        saveUser.setIdentityProof(adminDto.getIdentityProof());
        saveUser.setNumber(adminDto.getNumber());
        saveUser.setRoles(role);
        saveUser.setAddress(adminDto.getAddress());
        userRepository.save(saveUser);
    }


 public String deactivateUser(Long userId,Long accountId) {
        if(userRepository.existsById(userId) && accountRepository.existsById(accountId)){
            User user = userRepository.findById(userId).get();
            Account account = accountRepository.findById(accountId).get();
            if(user.getAccountList().contains(account)){
                System.out.println("Account Found");
                account.setStatus("INACTIVE");
                accountRepository.save(account);
            }
            return "Deactivated Account for User with id: "+userId;
        }
        return "ERROR";
    }



    public String activateAccount(Long userId, Long accountId) {
        if(userRepository.existsById(userId) && accountRepository.existsById(accountId)){
            User user = userRepository.findById(userId).get();
            Account account = accountRepository.findById(accountId).get();
            if(user.getAccountList().contains(account) && account.getStatus().equals("INACTIVE")){
                System.out.println("1 Account Found");
                account.setStatus("ACTIVE");
                accountRepository.save(account);
                return "Activated Account for User with id: "+userId;
            }
        }
        return "ERROR";
    }
}