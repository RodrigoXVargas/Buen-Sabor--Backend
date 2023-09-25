package com.project.buensabor.dto.productDto.ProductDtos;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ProductRanking {

    BigInteger getId();

    String getName();

    Boolean getActive();

    BigDecimal getCost();

    BigDecimal getPrice();

    BigInteger getQuantity_sold();

    BigDecimal getTotal_cost();

    BigDecimal getTotal_profit();
}
