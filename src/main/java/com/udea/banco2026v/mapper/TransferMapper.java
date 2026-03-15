package com.udea.banco2026v.mapper;

import com.udea.banco2026v.dto.TransactionDTO;
import com.udea.banco2026v.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferMapper {
    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    TransactionDTO toDTO(Transaction Transaction);
}
