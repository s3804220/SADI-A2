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
    public Provider getproviderByID(@PathVariable Long id){return providerService.getProviderByID(id);}

    @PostMapping(path="/providers")
    public Provider addprovider(@RequestBody Provider provider){return providerService.addProvider(provider);}

    @PutMapping(path="/providers/{id}")
    public Provider updateprovider(@PathVariable Long id, @RequestBody final Provider provider){
        return providerService.updateProvider(id, provider);
    }

    @DeleteMapping(path="/providers/{id}")
    public Provider deleteprovider(@PathVariable Long id){return providerService.deleteProvider(id);}
}
