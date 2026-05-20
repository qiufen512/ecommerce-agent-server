package com.ecommerce.model.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Python Service Request DTO
 */
@Data
public class PythonRequest {

    private String sessionId;
    private String userId;
    private String userInput;   // Python API field name is user_input
    private List<Map<String, String>> history;

}
