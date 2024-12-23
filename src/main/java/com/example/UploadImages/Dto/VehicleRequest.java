package com.example.UploadImages.Dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class VehicleRequest {

    private String id;
    private MultipartFile image;
    private String name;
    private Integer year;
    private String brand;
    private Double price;
    private Integer capacity;

}
