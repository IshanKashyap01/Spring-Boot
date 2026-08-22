package Telecom.SubscriptionService.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import Telecom.SubscriptionService.client.SupportClient;
import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.dto.UserDto;
import Telecom.SubscriptionService.feign.SupportService;
import Telecom.SubscriptionService.model.User;
import Telecom.SubscriptionService.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository repository;
    private final SupportService client;

    public User getUserById(Long userId)
    {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

    @Transactional
    public ResponseMessage createUser(UserDto dto)
    {
        User user = new User();
        user.setAccount(dto.getAccount());
        user.setAddress(dto.getAddress());
        user.setContact(dto.getContact());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.getSubscriptionList().addAll(dto.getSubscriptionList());
        repository.save(user);
        return new ResponseMessage("User created Successfully");
    }

    public User getUserByName(String name)
    {
        return repository.findByName(name);
    }

    public User getUserByEmail(String email)
    {
        return repository.findByEmail(email);
    }

    @Transactional
    public ResponseMessage updateUser(Long id, UserDto dto)
    {
        User user = getUserById(id);
        if(dto.getAccount() != null)
        {
            user.setAccount(dto.getAccount());
        }
        if(dto.getAddress() != null)
        {
            user.setAddress(dto.getAddress());
        }
        if(dto.getContact() != null)
        {
            user.setContact(dto.getContact());
        }
        if(dto.getEmail() != null)
        {
            user.setEmail(dto.getEmail());
        }
        if(dto.getName() != null)
        {
            user.setName(dto.getName());
        }
        if(dto.getSubscriptionList() != null)
        {
            user.setSubscriptionList(dto.getSubscriptionList());
        }
        return new ResponseMessage("User Updated Successfully");
    }

    @Transactional
    public ResponseMessage deleteUser(Long id)
    {
        repository.deleteById(id);
        return new ResponseMessage("User Successfully Deleted");
    }

    public List<Object> getAllUserTickets(Long userId)
    {
        return client.getUserTickets(userId);
    }
}