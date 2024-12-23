package com.example.UploadImages.Service;

import com.example.UploadImages.Dto.VehicleRequest;
import com.example.UploadImages.Dto.VehicleResponse;
import com.example.UploadImages.Entity.Vehicle;
import com.example.UploadImages.Repository.VehicleRepository;
import com.example.UploadImages.Utils.UploadImage;
import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService{

    private final UploadImage uploadImage;
    private final VehicleRepository vehicleRepository;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public VehicleServiceImpl(VehicleRepository vehicleRepository , UploadImage uploadImage){
        this.vehicleRepository =  vehicleRepository;
        this.uploadImage =  uploadImage;
    }

    @Override
    public VehicleResponse addVehicleImage(VehicleRequest vehicleRequest) {

        try {
            boolean upload = uploadImage.uploadImage(vehicleRequest.getImage());
            if (upload){
                String objectName = "http://127.0.0.1:9000/" + bucketName + "/" + vehicleRequest.getImage().getOriginalFilename();
                Vehicle save = vehicleRepository.save(Vehicle.builder()
                        .name(vehicleRequest.getName())
                        .imageUrl(objectName)
                        .year(vehicleRequest.getYear())
                                .brand(vehicleRequest.getBrand())
                                .price(vehicleRequest.getPrice())
                                .capacity(vehicleRequest.getCapacity())
                        .build());

                return VehicleResponse.builder()
                        .id(save.getId())
                        .name(save.getName())
                        .imageUrl(save.getImageUrl())
                        .year(save.getYear())
                        .brand(save.getBrand())
                        .price(save.getPrice())
                        .capacity(save.getCapacity())
                        .createdAt(save.getCreatedAt())
                        .updatedAt(save.getUpdatedAt())
                        .build();

            }else {
                System.out.println("gagal upload");
                return null;
                // todl -> gagal upload
            }


        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }


    }
}
