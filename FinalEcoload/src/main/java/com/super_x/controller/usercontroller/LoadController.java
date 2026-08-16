package com.super_x.controller.usercontroller;



import java.util.List;

import com.super_x.dao.userdao.LoadDao;
import com.super_x.model.usermodel.Load;

public class LoadController {

    LoadDao loadDao = new LoadDao();

    public void saveLoad(
            String userId,
            String transporterName,
            String pickupLocation,
            String destination,
            String loadType,
            double weight,
            String weightUnit,
            String truckType,
            double offerPrice,
            String pickupDate,
            String pickupTime,
            String deliveryDate,
            String deliveryTime,
            String receiverName
    ) {

        Load load = new Load();

        load.setUserId(userId);
        load.setPickupLocation(pickupLocation);
        load.setDestination(destination);
        load.setLoadType(loadType);
        load.setWeight(weight);
        load.setWeightUnit(weightUnit);
        load.setTruckType(truckType);
        load.setOfferPrice(offerPrice);
        load.setPickupDate(pickupDate);
        load.setPickupTime(pickupTime);
        load.setDeliveryDate(deliveryDate);
        load.setDeliveryTime(deliveryTime);
        load.setReceiverName(receiverName);
        load.setTransporterName(transporterName);

        loadDao.addLoad(load);
    }

    public List<Load> getAllLoads() {

        return loadDao.fetchAllLoads();
    }
}