package uz.map.uzbekistanmap.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private String mess;
    private boolean isSuccess;

    public ApiResponse(String mess, boolean isSuccess) {
        this.mess = mess;
        this.isSuccess = isSuccess;
    }
}
