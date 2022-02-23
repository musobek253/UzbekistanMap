package uz.map.uzbekistanmap.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private Integer houseNumber;
    private Integer districtId;
}
