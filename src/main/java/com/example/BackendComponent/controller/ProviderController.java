package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /*@Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }*/

    @GetMapping(path="/providers")
    public List<Provider> getAllProvider(){return providerService.getAllProvider(null);}

    @GetMapping(path="/providers/{id}")
    public Provider getProviderByID(@PathVariable Long id){return providerService.getProviderByID(id);}

    @PostMapping(path="/providers")
    public Provider addProvider(@RequestBody Provider provider){return providerService.addProvider(provider);}

    @PutMapping(path="/providers")
    public Provider updateProvider(@RequestBody Provider provider){
        return providerService.updateProvider(provider);
    }

    @DeleteMapping(path="/providers/{id}")
    public Provider deleteProvider(@PathVariable Long id){return providerService.deleteProvider(id);}

    @RequestMapping(path="/providers/search")
    public List<Provider> searchProvider(@Param("keyword") String keyword){
        return providerService.getAllProvider(keyword);
    }
}
