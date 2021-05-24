package com.example.BackendComponent.repository;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.SaleInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
    @Query("SELECT sa FROM SaleInvoice sa WHERE (coalesce(?1) IS NULL OR sa.saleDate >= cast(?1 as date)) AND" +
            "(coalesce(?2) IS NULL OR sa.saleDate <= CAST(?2 AS date))")
    Page<SaleInvoice> searchSaleBy(@Param("fromdate") @Temporal Date fromdate, @Param("todate") @Temporal Date todate, Pageable pageable);

    @Query("SELECT sa FROM SaleInvoice sa WHERE (coalesce(?1) IS NULL OR sa.saleDate >= cast(?1 as date)) AND" +
            "(coalesce(?2) IS NULL OR sa.saleDate <= CAST(?2 AS date)) AND" +
            "(?3 IS NULL OR sa.saleStaff.staffID=?3)")
    Page<SaleInvoice> searchSaleByStaff(@Param("fromdate") @Temporal Date fromdate, @Param("todate") @Temporal Date todate, @Param("staffid") Long staffid, Pageable pageable);

    @Query("SELECT sa FROM SaleInvoice sa WHERE (coalesce(?1) IS NULL OR sa.saleDate >= cast(?1 as date)) AND" +
            "(coalesce(?2) IS NULL OR sa.saleDate <= CAST(?2 AS date)) AND" +
            "(?3 IS NULL OR sa.customer.customerID=?3)")
    Page<SaleInvoice> searchSaleByCustomer(@Param("fromdate") @Temporal Date fromdate, @Param("todate") @Temporal Date todate, @Param("custid") Long custid, Pageable pageable);
}
