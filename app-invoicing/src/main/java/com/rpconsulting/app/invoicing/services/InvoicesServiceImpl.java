package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationDetailDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceDetailResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceDetailSummaryDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoiceResponseDto;
import com.rpconsulting.app.invoicing.dtos.invoices.InvoicesListFilterDto;
import com.rpconsulting.app.invoicing.errors.exceptions.AlreadyExistsException;
import com.rpconsulting.app.invoicing.errors.exceptions.NotFoundException;
import com.rpconsulting.app.invoicing.repositories.InvoiceDetailRepository;
import com.rpconsulting.app.invoicing.repositories.InvoiceRepository;
import com.rpconsulting.app.invoicing.repositories.SupplierRepository;
import com.rpconsulting.app.invoicing.repositories.entities.Invoice;
import com.rpconsulting.app.invoicing.repositories.entities.InvoiceDetail;
import com.rpconsulting.app.invoicing.repositories.entities.Supplier;
import com.rpconsulting.app.invoicing.repositories.projections.InvoiceDetailProjection;
import com.rpconsulting.app.invoicing.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class InvoicesServiceImpl implements InvoicesService {

    private final InvoiceRepository invoiceRepository;
    private final SupplierRepository supplierRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;

    @Override
    @Transactional
    public InvoiceCreationResponseDto create(InvoiceCreationRequestDto request) {
        validateIfExists(request.getIssuer().getNumber(), request.getSequence());

        Supplier supplier = supplierRepository
                .findFirstByNumber(request.getIssuer().getNumber())
                .orElseThrow(() -> new NotFoundException(format("La empresa identificada con el Nº {0} no existe", request.getIssuer().getNumber())));

        Invoice invoice = invoiceRepository.save(toEntity(request, supplier));

        List<InvoiceDetail> details = request
                .getDetails()
                .stream()
                .map(detail -> toEntity(invoice, detail))
                .collect(Collectors.toList());

        invoiceDetailRepository.saveAll(details);

        return toCreationResponse(invoice);
    }

    @Override
    public InvoiceResponseDto findById(Long id) {
        Invoice invoice = invoiceRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(format("La factura con ID {0} no exixte", id)));

        return toResponse(invoice, invoice.getDetails());
    }

    @Override
    public Page<InvoiceDetailSummaryDto> findAllDetails(InvoicesListFilterDto filters, Pageable pageable) {
        Page<InvoiceDetailProjection> page = invoiceDetailRepository.findAllDetails(filters.getSupplierNumber(), filters.getSupplierName(), pageable);
        return new PageImpl<>(
                page.stream().map(this::toDto).collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    private InvoiceDetailSummaryDto toDto(InvoiceDetailProjection projection) {
        InvoiceDetailSummaryDto dto = new InvoiceDetailSummaryDto();
        dto.setId(projection.getId());
        dto.setCode(projection.getCode());
        dto.setInvoiceId(projection.getInvoiceId());
        dto.setSupplierNumber(projection.getSupplierNumber());
        dto.setSupplierName(projection.getSupplierName());
        dto.setUnitPrice(projection.getUnitPrice());
        dto.setName(projection.getName());
        return dto;
    }

    private void validateIfExists(String issuer, String sequence) {
        Optional<Invoice> existingInvoice = invoiceRepository.findFirstBySequence(issuer, sequence);
        if (existingInvoice.isPresent()) {
            throw new AlreadyExistsException(
                    format("La factura con el número de sencuena {0} ya exixte", sequence)
            );
        }
    }

    private Invoice toEntity(InvoiceCreationRequestDto request, Supplier supplier) {
        Invoice invoice = new Invoice();

        invoice.setIssueDate(DateUtils.toLocalDate(request.getIssueDate()));
        invoice.setSequence(request.getSequence());
        invoice.setIssuerNumber(request.getIssuer().getNumber());
        invoice.setIssuerName(request.getIssuer().getName());
        invoice.setReceiverName(request.getReceiver().getName());
        invoice.setReceiverNumber(request.getReceiver().getNumber());
        invoice.setReceiverTelephoneNumber(request.getReceiver().getTelephoneNumber());
        invoice.setReceiverEmails(String.join(";", request.getReceiver().getEmails()));
        invoice.setTaxAmount(getTaxAmount(request.getDetails()));
        invoice.setTotal(getTotal(request.getDetails()));
        invoice.setDiscount(getDiscount(request.getDetails()));
        invoice.setSupplier(supplier);

        return invoice;
    }

    private InvoiceDetail toEntity(Invoice invoice, InvoiceCreationDetailDto detailDto) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();

        invoiceDetail.setInvoice(invoice);
        invoiceDetail.setCode(detailDto.getCode());
        invoiceDetail.setName(detailDto.getName());
        invoiceDetail.setUnitPrice(detailDto.getUnitPrice());
        invoiceDetail.setQty(detailDto.getQty());
        invoiceDetail.setTaxAmount(detailDto.getTaxAmount());
        invoiceDetail.setDiscount(detailDto.getDiscount());

        return invoiceDetail;
    }

    private BigDecimal getDiscount(List<InvoiceCreationDetailDto> details) {
        return details
                .stream()
                .map(InvoiceCreationDetailDto::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private BigDecimal getTotal(List<InvoiceCreationDetailDto> details) {
        return details
                .stream()
                .map(detail ->
                        calculateSubTotal(
                                detail.getUnitPrice(),
                                detail.getQty(),
                                detail.getTaxAmount(),
                                detail.getDiscount()
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTaxAmount(List<InvoiceCreationDetailDto> details) {
        return details
                .stream()
                .map(InvoiceCreationDetailDto::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private InvoiceCreationResponseDto toCreationResponse(Invoice invoice) {
        InvoiceCreationResponseDto response = new InvoiceCreationResponseDto();

        response.setId(invoice.getId());
        response.setDiscount(invoice.getDiscount());
        response.setTotal(invoice.getTotal());
        response.setTaxAmount(invoice.getTaxAmount());

        return response;
    }

    private InvoiceResponseDto toResponse(Invoice invoice, List<InvoiceDetail> details) {
        return InvoiceResponseDto
                .builder()
                .id(invoice.getId())
                .issueDate(DateUtils.toString(invoice.getIssueDate()))
                .sequence(invoice.getSequence())
                .issuerNumber(invoice.getIssuerNumber())
                .issuerName(invoice.getIssuerName())
                .receiverName(invoice.getReceiverName())
                .receiverNumber(invoice.getReceiverNumber())
                .receiverTelephoneNumber(invoice.getReceiverTelephoneNumber())
                .receiverEmails(Arrays.asList(invoice.getReceiverEmails().split(";")))
                .taxAmount(invoice.getTaxAmount())
                .discount(invoice.getDiscount())
                .total(invoice.getTotal())
                .details(details.stream().map(this::toResponse).collect(Collectors.toList()))
                .build();
    }

    private InvoiceDetailResponseDto toResponse(InvoiceDetail detail) {
        return InvoiceDetailResponseDto
                .builder()
                .code(detail.getCode())
                .name(detail.getName())
                .unitPrice(detail.getUnitPrice())
                .qty(detail.getQty())
                .taxAmount(detail.getTaxAmount())
                .discount(detail.getDiscount())
                .subTotal(
                        calculateSubTotal(
                                detail.getUnitPrice(),
                                detail.getQty(),
                                detail.getTaxAmount(),
                                detail.getDiscount()
                        )
                )
                .build();
    }

    private BigDecimal calculateSubTotal(
            BigDecimal unitPrice,
            BigDecimal qty,
            BigDecimal taxAmount,
            BigDecimal discount) {
        return unitPrice.multiply(qty).add(taxAmount).subtract(discount);
    }

}
