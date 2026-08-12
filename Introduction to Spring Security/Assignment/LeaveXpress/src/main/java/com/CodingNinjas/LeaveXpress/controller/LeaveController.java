package com.CodingNinjas.LeaveXpress.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.service.LeaveService;

@RestController
@RequestMapping("/api/leave")
public class LeaveController
{
    private final LeaveService service;

    public LeaveController(LeaveService service)
    {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public LeaveModel getLeaveById(@PathVariable Long id)
    {
        return service.getLeaveRequestById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<LeaveModel> getAllLeaveRequests()
    {
        return service.getAllLeaveRequests();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/accepted")
    public List<LeaveModel> getAllAcceptedLeaveRequests()
    {
        return service.getAllAcceptedLeaveRequests();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rejected")
    public List<LeaveModel> getAllRejectedLeaveRequests()
    {
        return service.getAllRejectedLeaveRequests();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/status/{id}")
    public boolean getLeaveRequestStatus(@PathVariable Long id)
    {
        return service.getLeaveRequestStatus(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateLeaveRequest(@PathVariable Long id, @RequestBody LeaveDto dto)
    {
        service.updateLeaveRequest(id, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteLeaveRequest(@PathVariable Long id)
    {
        service.deleteLeaveRequest(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/apply")
    public void applyLeaveRequest(@RequestBody LeaveDto dto)
    {
        service.applyLeaveRequest(dto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/accept/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void acceptLeaveRequest(@PathVariable Long id)
    {
        service.acceptLeaveRequest(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public void rejectLeaveRequest(@PathVariable Long id)
    {
        service.rejectLeaveRequest(id);
    }
}