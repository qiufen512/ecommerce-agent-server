package com.ecommerce.model.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class PythonRequest {

    private String sessionId;
    private String userId;
    private String userInput;   // Python 接口字段名为 user_input
    private List<Map<String, String>> history;

}
