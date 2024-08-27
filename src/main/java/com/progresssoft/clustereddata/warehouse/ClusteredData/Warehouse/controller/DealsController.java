package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.controller;

import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.dto.DealDTO;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.service.DealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(@Tag(name = "Store Deals", description = "Store deals management API's\""))
@RestController
@RequestMapping("/deals")
@Validated
public class DealsController {

    private final DealService dealService;

    public DealsController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping(name = "Save Deal")
    public ResponseEntity<String> saveDeal(@Valid @RequestBody DealDTO dealDTO) {

        dealService.saveDealDetails(dealDTO);
        return ResponseEntity.ok().body(dealDTO.getDealUniqueId());
    }

    @GetMapping(name = "Get deals details")
    public ResponseEntity<List<DealDTO>> getDeal() {

        List<DealDTO> dealDetails = dealService.getDealDetails();
        return ResponseEntity.ok().body(dealDetails);
    }


}
