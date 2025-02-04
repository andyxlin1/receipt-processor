package com.assessment.receipt_processor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.receipt_processor.models.request.Receipt;
import com.assessment.receipt_processor.models.response.ReceiptPointsResponse;
import com.assessment.receipt_processor.models.response.ReceiptResponse;
import com.assessment.receipt_processor.services.ReceiptService;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity processReceipt (@RequestBody Receipt receipt) {
        ReceiptResponse response = receiptService.storeReceipt(receipt);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The receipt is invalid.");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity getPoints(@PathVariable String id) {
        ReceiptPointsResponse response = receiptService.getPoints(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No receipt found for that ID.");
        }
    }
}
