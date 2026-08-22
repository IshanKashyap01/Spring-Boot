package com.devtools.solution.actuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "database")
public class CustomEndpont1
{
    @ReadOperation
    public Map<String, String> getDbInfo()
    {
        Map<String, String> map = new HashMap<>();
        map.put("Database", "MySQL");
        return map;
    }
}