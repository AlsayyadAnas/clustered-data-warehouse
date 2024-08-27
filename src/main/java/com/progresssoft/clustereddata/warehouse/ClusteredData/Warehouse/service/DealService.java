package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.service;

import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.exception.BusinessException;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.exception.DuplicateDealRecordException;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.dto.DealDTO;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.entity.DealEntity;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.repository.DealRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DealService {


    private final DealRepository dealRepository;
    private final ModelMapper modelMapper;


    public DealService(DealRepository dealRepository, ModelMapper modelMapper) {
        this.dealRepository = dealRepository;
        this.modelMapper = modelMapper;
    }

    public void saveDealDetails(DealDTO dealDTO) {

        try {
            log.info("New Deal details received with Id:{}", dealDTO.getDealUniqueId());
            validateDealDate(dealDTO);
            dealRepository.save(modelMapper.map(dealDTO, DealEntity.class));
        } catch (BusinessException e) {
            log.error("Add deal request rejected due to issue with the request data", e);
            throw e;
        } catch (Exception e) {
            log.error("Technical error while saving deal details", e);
            throw new RuntimeException();
        }
    }

    public List<DealDTO> getDealDetails() {

        try {
            return dealRepository.findAll().stream()
                    .map(entity -> modelMapper.map(entity, DealDTO.class)).toList();
        } catch (Exception e) {
            log.error("Error while getting deal details", e);
            throw new RuntimeException();
        }
    }

    private void validateDealDate(DealDTO dealDTO) {
        dealRepository.findByDealUniqueId(dealDTO.getDealUniqueId())
                .ifPresent(entity -> {
                    throw new DuplicateDealRecordException();
                });
    }
}
