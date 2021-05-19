package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProviderController {
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @RequestMapping(path="/providers")
    public List<Provider> getAllprovider(){return providerService.getAllprovider();}

    @RequestMapping(path="/providers/{id}")
    public Provider getproviderByID(@PathVariable Long id){return providerService.getproviderByID(id);}

    @PostMapping(path="/providers")
    public Provider addprovider(@RequestBody Provider provider){return providerService.addprovider(provider);}

    @PutMapping(path="/providers/{id}")
    public Provider updateprovider(@PathVariable Long id, @RequestBody final Provider provider){
        return providerService.updateprovider(id, provider);
    }

    @DeleteMapping(path="/providers/{id}")
    public Provider deleteprovider(@PathVariable Long id){return providerService.deleteprovider(id);}

    @PostMapping(path="/providers/{provider_id}/orders/{order_id}/add")
    public Provider addProviderToOrder(@PathVariable Long provider_id, @PathVariable Long order_id){
        return providerService.addProviderToOrder(provider_id, order_id);
    }

    @DeleteMapping(path="/providers/{provider_id}/orders/{order_id}/delete")
    public Provider deleteProviderFromOrder(@PathVariable Long provider_id, @PathVariable Long order_id){
        return providerService.removeProviderFromOrder(provider_id, order_id);
    }
}
