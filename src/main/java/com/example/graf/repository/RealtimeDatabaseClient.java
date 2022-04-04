package com.example.graf.repository;

import com.example.graf.model.Material;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "rtdb", url = "https://graf-24561-default-rtdb.firebaseio.com/.json")
public interface RealtimeDatabaseClient {
    @GetMapping
    List<Material> getMaterials();
}
