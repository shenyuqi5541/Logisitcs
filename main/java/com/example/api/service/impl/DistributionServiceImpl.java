package com.example.api.service.impl;

import com.example.api.model.entity.Distribution;
import com.example.api.model.entity.Driver;
import com.example.api.model.entity.Vehicle;
import com.example.api.repository.DistributionRepository;
import com.example.api.repository.DriverRepository;
import com.example.api.repository.VehicleRepository;
import com.example.api.service.DistributionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DistributionServiceImpl implements DistributionService {

    @Resource
    private DistributionRepository distributionRepository;

    @Resource
    private DriverRepository driverRepository;

    @Resource
    private VehicleRepository vehicleRepository;

    //新增配送
    @Override
    public Distribution save(Distribution distribution) throws Exception {
        Optional<Distribution> byId = distributionRepository.findById(distribution.getId());
        //配送ID为空，则申请配送
        if (byId == null ||  ObjectUtils.isEmpty(byId)) return distributionRepository.save(distribution);
        //配送ID不为空，修改对应状态
        Optional<Driver> driver = driverRepository.findById(distribution.getDid());
        Optional<Vehicle> vehicle = vehicleRepository.findById(distribution.getVid());
        if(distribution.getStatus() == 0 && (driver.get().isDriving() || vehicle.get().isDriving())){
            throw new Exception("司机或货车状态不可用");
        } else if (distribution.getStatus() == 1){
            //审核通过，修改司机和车辆状态都为运输中
            driverRepository.updateDriving(true, distribution.getDid());
            vehicleRepository.updateDriving(true, distribution.getVid());
        }else if(distribution.getStatus() == 2){
            //运输完成，修改司机和车辆状态为休息中
            driverRepository.updateDriving(false, distribution.getDid());
            vehicleRepository.updateDriving(false, distribution.getVid());
        }
        return distributionRepository.save(distribution);
    }

    @Override
    public List<Distribution> findAll() {
        return distributionRepository.findAll();
    }

    @Override
    public void delete(String id) {
        distributionRepository.deleteById(id);
    }

}
