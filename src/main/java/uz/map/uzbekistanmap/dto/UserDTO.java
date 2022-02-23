package uz.map.uzbekistanmap.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String name;
    private List<Integer> carsId;
    private String  street;
    private Integer houseNumber;
    private Integer districtId;
}
