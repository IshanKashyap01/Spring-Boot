package com.CN.Gym.service;


import com.CN.Gym.dto.GymDto;
import com.CN.Gym.exception.GymNotFoundException;
import com.CN.Gym.model.Gym;
import com.CN.Gym.model.User;
import com.CN.Gym.repository.GymRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/*
This is the service class for Gym, you need to complete the class by doing the following:
a. Use appropriate annotations.
b. Complete the methods given below.
c. Autowire the necessary dependencies.
*/
@Service
public class GymService
{
    private final GymRepository repository;
    private final UserService service;

    public GymService(GymRepository repository, UserService service)
    {
        this.repository = repository;
        this.service = service;
    }

    public List<Gym> getAllGyms()
    {
        return repository.findAll();
    }

    public Gym getGymById(Long id)
    {
        return repository.findById(id)
        .orElseThrow(() -> new GymNotFoundException("gym not found"));
    }

    @Transactional
    public void deleteGymById(Long id)
    {
        getGymById(id);
        repository.deleteById(id);
    }

    public void updateGym(GymDto dto, Long id)
    {
        Gym gym = getGymById(id);
        gym.setAddress(dto.getAddress());
        gym.setContactNo(dto.getContactNo());
        gym.setName(dto.getName());
        gym.setMembershipPlans(dto.getMembershipPlans());
        gym.setFacilities(dto.getFacilities());
        repository.save(gym);
    }

    public void createGym(GymDto dto)
    {
        Gym gym = new Gym(null, dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getMembershipPlans(), dto.getFacilities(), new ArrayList<>());
        repository.save(gym);
    }

    @Transactional
    public void addMember(Long userId, Long gymId)
    {
        Gym gym = getGymById(gymId);
        User user = service.getUserById(userId);
        user.setGym(gym);
        gym.getMembers().add(user);
    }

    @Transactional
    public void deleteMember(Long userId, Long gymId)
    {
        Gym gym = getGymById(gymId);
        User user = gym.getMembers().stream()
        .filter(m -> m.getId().equals(userId))
        .findFirst()
        .orElse(null);
        if(user != null) 
        {
            gym.getMembers().remove(user);
            user.setGym(null);
        }
    }
}