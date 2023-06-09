package com.rpconsulting.app.invoicing.services;

import com.rpconsulting.app.invoicing.dtos.InvoiceCreationRequestDto;
import com.rpconsulting.app.invoicing.dtos.InvoiceCreationResponseDto;
import com.rpconsulting.app.invoicing.dtos.InvoiceDetailDto;
import com.rpconsulting.app.invoicing.repositories.InvoiceDetailRepository;
import com.rpconsulting.app.invoicing.repositories.InvoiceRepository;
import com.rpconsulting.app.invoicing.repositories.entities.Invoice;
import com.rpconsulting.app.invoicing.repositories.entities.InvoiceDetail;
import com.rpconsulting.app.invoicing.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoicesServiceImpl implements InvoicesService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;

    @Override
    @Transactional
    public InvoiceCreationResponseDto create(InvoiceCreationRequestDto request) {
        Invoice invoice = invoiceRepository.save(toEntity(request));

        //YA TIENE UN ID GENERADO POR LA BD

        List<InvoiceDetail> details = request
                .getDetails()
                .stream()
                .map(detail -> toEntity(invoice, detail))
                .collect(Collectors.toList());

        invoiceDetailRepository.saveAll(details);

        return toResponse(invoice);
    }

    private Invoice toEntity(InvoiceCreationRequestDto request) {
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

        return invoice;
    }

    private InvoiceDetail toEntity(Invoice invoice, InvoiceDetailDto detailDto) {
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

    private BigDecimal getDiscount(List<InvoiceDetailDto> details) {
        return details
                .stream()
                .map(InvoiceDetailDto::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private BigDecimal getTotal(List<InvoiceDetailDto> details) {
        return details
                .stream()
                .map(this::calculateSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateSubTotal(InvoiceDetailDto detail) {
        return
                (
                        detail.getUnitPrice().multiply(detail.getQty())
                )
                        .add(detail.getTaxAmount())
                        .subtract(detail.getDiscount());
    }

    private BigDecimal getTaxAmount(List<InvoiceDetailDto> details) {
        return details
                .stream()
                .map(InvoiceDetailDto::getTaxAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private InvoiceCreationResponseDto toResponse(Invoice invoice) {
        InvoiceCreationResponseDto response = new InvoiceCreationResponseDto();

        response.setId(invoice.getId());
        response.setDiscount(invoice.getDiscount());
        response.setTotal(invoice.getTotal());
        response.setTaxAmount(invoice.getTaxAmount());

        return response;
    }

}
