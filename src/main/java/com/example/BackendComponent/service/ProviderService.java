package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.exception.ProviderAlreadyExistException;
import com.example.BackendComponent.exception.ProviderNotFoundException;
import com.example.BackendComponent.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    //private final OrderService orderService;

    /*@Autowired
    public ProviderService(ProviderRepository providerRepository, OrderService orderService) {
        this.providerRepository = providerRepository;
        this.orderService = orderService;
    }*/

    public Provider addProvider(Provider provider){
        if(!providerRepository.existsById(provider.getProviderID())){
            providerRepository.save(provider);
        }else {
            throw new ProviderAlreadyExistException(provider.getProviderID());
        }
        return provider;
    }

    public Provider updateProvider(Provider provider){
        if(providerRepository.existsById(provider.getProviderID())){
            providerRepository.save(provider);
        }else{
            throw new ProviderNotFoundException(provider.getProviderID());
        }
        return provider;
    }

    public List<Provider> getAllProvider(String keyword){
        if (keyword != null){
            return providerRepository.search(keyword);
        }
        return providerRepository.findAll();
    }

    public Provider getProviderByID(Long id){
        return providerRepository.findById(id).orElseThrow(()->
                new ProviderNotFoundException(id));
    }

    public Provider deleteProvider(Long id){
        Provider providerToDelete = getProviderByID(id);
        providerRepository.delete(providerToDelete);
        return providerToDelete;
    }

    /*public Provider updateProvider(Long id, Provider newProvider){
        Provider providerToUpdate = getProviderByID(id);
        providerToUpdate.setproviderName(newProvider.getproviderName());
        providerToUpdate.setproviderAddress(newProvider.getproviderAddress());
        providerToUpdate.setproviderFax(newProvider.getproviderFax());
        providerToUpdate.setproviderPhone(newProvider.getproviderPhone());
        providerToUpdate.setproviderContactPerson(newProvider.getproviderContactPerson());
        providerToUpdate.setProviderEmail(newProvider.getProviderEmail());
        return providerToUpdate;
    }*/
}
