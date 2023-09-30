package com.project.buensabor.dto.userDto.UserDtos;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface UserRanking {

    BigInteger getId();

    String getFirst_name();

    String getLast_name();

    BigInteger getBlacklist();

    String getMail();

    BigInteger getTelephone();

    BigInteger getOrders_quantity();

    BigDecimal getTotal_sum();

}
