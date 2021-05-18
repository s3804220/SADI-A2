package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.exception.ProviderNotFoundException;
import com.example.BackendComponent.repository.ProviderRepository;
import com.example.BackendComponent.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider addprovider(Provider provider){
        return providerRepository.save(provider);
    }

    public List<Provider> getAllprovider(){
        return StreamSupport
                .stream(providerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Provider getproviderByID(Long id){
        return providerRepository.findById(id).orElseThrow(()->
                new ProviderNotFoundException(id));
    }

    public Provider deleteprovider(Long id){
        Provider providerToDelete = getproviderByID(id);
        providerRepository.delete(providerToDelete);
        return providerToDelete;
    }

    public Provider updateprovider(Long id, Provider newprovider){
        Provider providerToUpdate = getproviderByID(id);
        providerToUpdate.setproviderName(newprovider.getproviderName());
        providerToUpdate.setproviderAddress(newprovider.getproviderAddress());
        providerToUpdate.setproviderFax(newprovider.getproviderFax());
        providerToUpdate.setproviderPhone(newprovider.getproviderPhone());
        providerToUpdate.setproviderContactPerson(newprovider.getproviderContactPerson());
        return providerToUpdate;
    }
}
