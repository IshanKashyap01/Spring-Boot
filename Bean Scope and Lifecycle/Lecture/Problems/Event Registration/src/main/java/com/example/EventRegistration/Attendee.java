package com.example.EventRegistration;

public interface Attendee
{
    /**
     * sets the details of the Attendee.
     * @param name name of the attendee
     * @param department dept of the attendee
     * @param batch batch of the attendee
     */
    void setAttendeeDetails(String name, String department, int batch);
    /**
     * prints the Registration Confirmation
     * <br>
     * For ex. "Hi John, your registration for the Graduation Ceremony is successful"
     */
    void printRegistrationConfirmation();
    /**
     * returns the Attendee's name.
    */
    String getAttendeeName();
}