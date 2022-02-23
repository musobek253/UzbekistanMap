package uz.map.uzbekistanmap.dto;

import lombok.Data;

@Data
public class CarDTO {

    private String model;
    private String stateNumber;
    private String madeYear;
    private String type;
    private Integer regionId;
}
