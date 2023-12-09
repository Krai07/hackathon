package com.mindcraft.in.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcraft.in.services.ProcurementService;

@RestController
public class ProcurementController {
    
    private final ProcurementService procurementService;

    public ProcurementController(ProcurementService procurementService){
        this.procurementService = procurementService;
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.GET)
    public List<Map<String, Object>> getProcurement(){
        List<Map<String, Object>> orders = procurementService.getProcurement();
        Map<String, Object> response = new HashMap<>();
        if (orders.isEmpty()){
            response.put("status", "error");
            response.put("message", "no orders found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all orders displayed");
        }
        return orders;
    } 
    
    @RequestMapping(value = "/getAddedProcurement", method = RequestMethod.GET)
    public List<Map<String, Object>> getAddedProcurement(){
        List<Map<String, Object>> orders = procurementService.getAddedProcurement();
        Map<String, Object> response = new HashMap<>();
        if (orders.isEmpty()){
            response.put("status", "error");
            response.put("message", "no orders found");
        }
        else{
            response.put("status", "success");
            response.put("message", "all orders displayed");
        }
        return orders;
    } 
}
