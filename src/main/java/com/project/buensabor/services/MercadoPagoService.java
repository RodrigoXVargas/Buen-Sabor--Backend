package com.project.buensabor.services;


import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.repositories.OrderProductsRepository;
import com.project.buensabor.repositories.OrderRepository;
import com.project.buensabor.repositories.ProductRepository;
import com.project.buensabor.services.interfaces.OrderProductsService;
import com.project.buensabor.services.interfaces.OrderService;
import com.project.buensabor.services.interfaces.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MercadoPagoService {

    @Value("${mercadopago.access_token}")
    private String mpAccessToken;

    @Value("${mercadopago.back_url.success}")
    private String mpSuccessBackUrl;

    @Value("${mercadopago.back_url.pending}")
    private String mpPendingBackUrl;

    @Value("${mercadopago.back_url.failure}")
    private String mpFailureBackUrl;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostConstruct
    public void initMPConfig(){
        MercadoPagoConfig.setAccessToken(mpAccessToken);
    }

    public Preference createPreference(Long idOrder) throws CustomException {
        try{
            List<PreferenceItemRequest> items = getItemsPreference(idOrder);
            List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
            excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

            PreferencePaymentMethodsRequest paymentMethods =
                    PreferencePaymentMethodsRequest.builder()
                            .excludedPaymentTypes(excludedPaymentTypes)
                            .installments(1)
                            .build();


            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .externalReference(String.valueOf(idOrder))
                    .paymentMethods(paymentMethods)
                    .autoReturn("approved")
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success(mpSuccessBackUrl+idOrder)
                                    .pending(mpPendingBackUrl)
                                    .failure(mpFailureBackUrl)
                                    .build()
                    )
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);
            return preference;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public List<PreferenceItemRequest> getItemsPreference(Long idOrder) throws CustomException {
        try{
            List<PreferenceItemRequest> items = new ArrayList<>();
            List<OProductsWithoutOrderDto> productsCant = orderServiceImpl.getOrderProductsByOrder(idOrder);
            for (OProductsWithoutOrderDto productCant: productsCant) {
                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .id(String.valueOf(productCant.getProduct().getId()))
                        .title(productCant.getProduct().getName())
                        .quantity(productCant.getCant().intValue())
                        .currencyId("ARS")
                        .unitPrice(BigDecimal.valueOf(productCant.getProduct().getPrice()))
                        .build();
                items.add(itemRequest);
            }
            return items;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

}
