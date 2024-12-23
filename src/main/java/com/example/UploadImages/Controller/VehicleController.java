package com.example.UploadImages.Controller;

import com.example.UploadImages.Dto.VehicleRequest;
import com.example.UploadImages.Dto.VehicleResponse;
import com.example.UploadImages.Service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/addImages")
    ResponseEntity<?> addImage(@RequestParam(name = "image")MultipartFile image , @RequestParam(name = "name")String name , @RequestParam(name = "year")Integer year ,  @RequestParam(name = "brand") String brand , @RequestParam(name = "price") Double price , @RequestParam(name = "capacity")Integer capacity){

        VehicleRequest vehicleRequest = VehicleRequest.builder()
                .image(image)
                .name(name)
                .year(year)
                .brand(brand)
                .price(price)
                .capacity(capacity)
                .build();

        VehicleResponse vehicleResponse = vehicleService.addVehicleImage(vehicleRequest);

        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }

}
