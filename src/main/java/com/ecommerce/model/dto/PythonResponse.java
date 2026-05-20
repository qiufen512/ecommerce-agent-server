package com.ecommerce.model.dto;

import lombok.Data;
import java.util.List;

/**
 * Python Service Response DTO
 */
@Data
public class PythonResponse {
    private String reply;
    private String intent;
    private Double confidence;
    private Boolean humanFlag;
    private ComplianceCheck complianceCheck;

    @Data
    public static class ComplianceCheck {
        private Boolean pass;
        private List<String> risks;
    }
}
