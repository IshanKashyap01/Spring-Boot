package com.example.EventRegistration;
/*
    1. Create custom init() and destroy() methods.
    2. The init() method should print the message "Graduation Ceremony bean created!!".
    3. The destroy() method should print the message "Graduation Ceremony bean destroyed".
*/

import java.util.ArrayList;
import java.util.List;

public class GraduationCeremonyEvent implements CollegeEvent
{
    private final String name;
    private final String address;
    private final String time;
    private final String date;
    private int count;
    private List<Attendee> eventAttendees;

    public GraduationCeremonyEvent()
    {
        this.name = "Graduation Ceremony";
        this.address = "Auditorium";
        this.time = "10AM";
        this.date = "12 Nov 2023";
        this.count = 0;
        this.eventAttendees = new ArrayList<>();
    }

    public void init()
    {
        System.out.println("Graduation Ceremony bean created!!");
    }

    public void destroy()
    {
        System.out.println("Graduation Ceremony bean destroyed");
    }

    @Override
    public void registerStudent(Attendee user)
    {
        eventAttendees.add(user);
        this.count++;
    }

    @Override
    public List<Attendee> getAllAttendees()
    {
        return this.eventAttendees;
    }

    @Override
    public void printEventDetails()
    {
        System.out.println("The " + this.name + " details are as follows:");
        System.out.println("Venue: " + this.address);
        System.out.println("Time: " + this.time);
        System.out.println("Date: " + this.date);
    }

    @Override
    public int getAttendeeCount()
    {
        return this.count;
    }

    @Override
    public void setAttendee(Attendee attendee)
    {
        registerStudent(attendee);
    }
}