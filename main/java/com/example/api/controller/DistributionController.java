package com.example.api.controller;

import com.example.api.annotation.Log;
import com.example.api.model.entity.Distribution;
import com.example.api.model.enums.BusincessType;
import com.example.api.repository.DriverRepository;
import com.example.api.repository.VehicleRepository;
import com.example.api.service.DistributionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distribution")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_DELIVERY_ADMIN')")
public class DistributionController {

    @Resource
    private DistributionService distributionService;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    //新增配送
    @Log(moudle = "配送管理",type = BusincessType.INSERT)
    @PostMapping("")
    public Distribution save(@RequestBody Distribution distribution) throws Exception {

        return distributionService.save(distribution);
    }

    @Log(moudle = "配送管理",type = BusincessType.QUERY)
    @GetMapping("")
    public List<Distribution> findAll() {
        return distributionService.findAll();
    }

    @GetMapping("can")
    public Map<String, Object> can() {
        Map<String, Object> map = new HashMap<>();
        map.put("drivers", driverRepository.findAll());
        map.put("vehicles", vehicleRepository.findAll());
        return map;
    }

    @DeleteMapping("")
    public void delete(String id) {
        distributionService.delete(id);
    }

}
