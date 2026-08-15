package com.CN.Gym.dto;

import lombok.Data;

/* This is the Gym Dto class, and you need to complete the class by doing the following:
a. Add the following attributes:
1. String name
2. String address
3. Long contactNo
4. String membershipPlans
5. String facilities
6. List<User> members = new ArrayList<>()
b. Use lombok annotations for getter, setters and constructors
*/
@Data
public class GymDto
{
    private String name;
    private String address;
    private Long contactNo;
    private String membershipPlans;
    private String facilities;
}