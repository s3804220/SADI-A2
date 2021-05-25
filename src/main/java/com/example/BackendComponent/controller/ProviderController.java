package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping(path="/providers")
    public List<Provider> getAllProvider(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return providerService.getAllProvider(thePage,pageable);
    }

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

    @GetMapping(path="/providers/search")
    public List<Provider> searchProvider(@RequestParam("keyword") String keyword, @RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return providerService.searchProvider(keyword,thePage,pageable);
    }

    @GetMapping(path="/providers/search/filter")
    public List<Provider> searchProviderBy(@RequestParam Optional<String> name, @RequestParam Optional<String> address, @RequestParam Optional<String> phone, @RequestParam Optional<String> fax, @RequestParam Optional<String> email, @RequestParam Optional<String> contact, @RequestParam Optional<Integer> page){
        String newname, newaddress, newphone, newfax, newemail, newcontact;
        newname = name.orElse(null);
        newaddress = address.orElse(null);
        newphone = phone.orElse(null);
        newfax = fax.orElse(null);
        newemail = email.orElse(null);
        newcontact = contact.orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return providerService.searchProviderBy(newname,newaddress,newphone,newfax,newemail,newcontact,thePage,pageable);
    }
}
