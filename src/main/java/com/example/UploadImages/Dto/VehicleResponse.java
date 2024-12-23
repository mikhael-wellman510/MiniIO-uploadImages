package com.example.UploadImages.Dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class VehicleResponse {

    private String id;
    private String name;
    private String imageUrl;
    private Integer year;
    private String brand;
    private Double price;
    private Integer capacity;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
