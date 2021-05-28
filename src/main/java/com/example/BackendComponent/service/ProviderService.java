package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Customer;
import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.exception.ProviderAlreadyExistException;
import com.example.BackendComponent.exception.ProviderNotFoundException;
import com.example.BackendComponent.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

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

    public List<Provider> getAllProvider(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 5);
        }else{
            pageable = Pageable.unpaged();
        }
        return providerRepository.findAll(pageable).getContent();
    }

    public List<Provider> searchProvider(String keyword, int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 5);
        }else{
            pageable = Pageable.unpaged();
        }
        return providerRepository.search(keyword,pageable).getContent();
    }

    public List<Provider> searchProviderBy(String name, String address, String phone, String fax, String email, String contact, int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 5);
        }else{
            pageable = Pageable.unpaged();
        }
        return providerRepository.searchProviderBy(name,address,phone,fax,email,contact, pageable).getContent();
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
