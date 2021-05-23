package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.exception.SalesInvoiceNotFoundException;
import com.example.BackendComponent.repository.SalesInvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class SalesInvoiceService {
    private final SalesInvoiceRepository invoiceRepository;

    public SalesInvoiceService (SalesInvoiceRepository salesInvoiceRepository)  {
        this.invoiceRepository = salesInvoiceRepository;
    }
}
