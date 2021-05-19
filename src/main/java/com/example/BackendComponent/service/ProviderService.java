package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.exception.ProviderNotFoundException;
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
    private final OrderService orderService;

    @Autowired
    public ProviderService(ProviderRepository providerRepository, OrderService orderService) {
        this.providerRepository = providerRepository;
        this.orderService = orderService;
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

    public Provider updateprovider(Long id, Provider newProvider){
        Provider providerToUpdate = getproviderByID(id);
        providerToUpdate.setproviderName(newProvider.getproviderName());
        providerToUpdate.setproviderAddress(newProvider.getproviderAddress());
        providerToUpdate.setproviderFax(newProvider.getproviderFax());
        providerToUpdate.setproviderPhone(newProvider.getproviderPhone());
        providerToUpdate.setproviderContactPerson(newProvider.getproviderContactPerson());
        providerToUpdate.setProviderEmail(newProvider.getProviderEmail());
        return providerToUpdate;
    }

    public Provider addProviderToOrder(Long providerID, Long orderID){
        Provider provider = getproviderByID(providerID);
        Order order = orderService.getOrderByID(orderID);
        provider.addProviderOrder(order);
        order.setOrderProvider(provider);
        return provider;
    }

    public Provider removeProviderFromOrder(Long providerID, Long orderID){
        Provider provider = getproviderByID(providerID);
        Order order = orderService.getOrderByID(orderID);
        provider.deleteProviderOrder(order);
        order.deleteOrderProvider();
        return provider;
    }
}
