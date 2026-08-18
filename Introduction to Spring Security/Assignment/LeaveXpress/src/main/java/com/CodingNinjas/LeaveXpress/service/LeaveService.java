package com.CodingNinjas.LeaveXpress.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.exception.LeaveNotFoundException;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.repository.LeaveRepository;

@Service
public class LeaveService
{
    private final LeaveRepository repository;

    public LeaveService(LeaveRepository repository)
    {
        this.repository = repository;
    }

    public LeaveModel getLeaveRequestById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new LeaveNotFoundException("leave not found"));
    }

    public List<LeaveModel> getAllLeaveRequests()
    {
        return repository.findAll();
    }

    public List<LeaveModel> getAllAcceptedLeaveRequests()
    {
        return repository.findAllByIsAcceptedTrue();
    }

    public List<LeaveModel> getAllRejectedLeaveRequests()
    {
        return repository.findAllByIsAcceptedFalse();
    }

    public boolean getLeaveRequestStatus(long id)
    {
        return getLeaveRequestById(id).isAccepted();
    }

    @Transactional
    public void updateLeaveRequest(long id, LeaveDto dto)
    {
        LeaveModel model = getLeaveRequestById(id);
        updateLeaveModel(model, dto);
    }

    public void deleteLeaveRequest(long id)
    {
        repository.deleteById(id);
    }

    public void applyLeaveRequest(LeaveDto dto)
    {
        repository.save(toModel(dto));
    }

    @Transactional
    public void acceptLeaveRequest(long id)
    {
        LeaveModel leaveRequest = getLeaveRequestById(id);
        leaveRequest.setAccepted(true);
    }

    @Transactional
    public void rejectLeaveRequest(long id)
    {
        LeaveModel leaveRequest = getLeaveRequestById(id);
        leaveRequest.setAccepted(false);
    }

    private LeaveModel toModel(LeaveDto dto)
    {
        return new LeaveModel(null, dto.getType(), dto.getStartDate(), dto.getEndDate(), dto.getDescription(), false);
    }

    private void updateLeaveModel(LeaveModel model, LeaveDto dto)
    {
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setDescription(dto.getDescription());
        model.setType(dto.getType());
    }
}