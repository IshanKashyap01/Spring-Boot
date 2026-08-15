package com.CN.Gym.controller;

import com.CN.Gym.dto.GymDto;
import com.CN.Gym.model.Gym;
import com.CN.Gym.service.GymService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gym")
public class GymController
{
    private final GymService service;

    public GymController(GymService service)
    {
        this.service = service;
    }
    // 1. GET “/gym/all”: This API allows the admin to fetch all the gym records and
    // returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Gym> getAllGyms()
    {
        return service.getAllGyms();
    }
    // 2. GET “/gym/{id}” (@PathVariable Long id): This API allows the user to fetch the
    // tax record by its ID and
    // returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Gym getGymById(@PathVariable Long id)
    {
        return service.getGymById(id);
    }
    // 3. POST /gym/create: This API allows the admin to create a gym record and
    // returns a CREATED HTTP status.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createGym(@RequestBody GymDto dto)
    {
        service.createGym(dto);
    }
    // 4. PUT "/gym/{id}" (@RequestBody GymDto gymDto, @PathVariable Long id): This API
    // allows admins to update a gym record by its ID and
    // returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateGym(@RequestBody GymDto dto, @PathVariable Long id)
    {
        service.updateGym(dto, id);
    }
    // 5. DELETE "/gym/{id}" (@PathVariable Long id): This API lets admins delete a gym
    // record by its ID and
    // returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteGym(@PathVariable Long id)
    {
        service.deleteGymById(id);
    }
    // 6. POST "/gym/addMember" (@RequestParam Long userId, @RequestParam Long gymId):
    // This API allows the admin to add users to a particular gym by passing userId and
    // gymId as requestParam. 
    // It returns a CREATED HTTP status.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addMember")
    public void addMember(@RequestParam Long userId, @RequestParam Long gymId)
    {
        service.addMember(userId, gymId);
    }
    // 7. DELETE "/gym/deleteMember" (@PathParam("userId") Long userId, @PathParam("gymId") Long gymId):
    // This API allows the admin to delete users to a particular gym by passing userId
    // and gymId as path params.
    // It returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/deleteMember")
    public void deleteMember(@RequestParam Long userId, @RequestParam Long gymId)
    {
        service.deleteMember(userId, gymId);
    }
}