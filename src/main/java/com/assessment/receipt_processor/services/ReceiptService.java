package com.assessment.receipt_processor.services;

import org.springframework.stereotype.Service;

import com.assessment.receipt_processor.models.request.Receipt;
import com.assessment.receipt_processor.models.response.ReceiptPointsResponse;
import com.assessment.receipt_processor.models.response.ReceiptResponse;
import com.assessment.receipt_processor.utils.ReceiptUtils;

import java.util.HashMap;
import java.util.UUID;

@Service
public class ReceiptService {
    private final HashMap<String, Receipt> receiptDB = new HashMap<>();

    public ReceiptResponse storeReceipt(Receipt receipt) {
        String uuid = UUID.randomUUID().toString();
        receiptDB.put(uuid, receipt);
        if (!isValidReceipt(receipt)) {
            return null;
        }
        return new ReceiptResponse(uuid);
    }

    public ReceiptPointsResponse getPoints(String uuid) {
        Receipt receipt = receiptDB.getOrDefault(uuid, null);
        if (receipt != null) {
            int points = 0;
            points += ReceiptUtils.countAlphaNumeric(receipt.getRetailer());
            points += ReceiptUtils.isRoundDollarAmount(receipt.getTotal());
            points += ReceiptUtils.isMultipleOfQuarter(receipt.getTotal());
            points += ReceiptUtils.countItemPoints(receipt.getItems());
            points += ReceiptUtils.isPurchaseDateOdd(receipt.getPurchaseDate());
            points += ReceiptUtils.isBetween2And4PM(receipt.getPurchaseTime());

            return new ReceiptPointsResponse(points);
        }
        return null;
    }
    // in a real world setting/prod environment, I would use a global handler with @ExceptionHandler. I would also
    // use annotations in the receipt class to enforce null checks
    private boolean isValidReceipt(Receipt receipt) {
        return receipt != null &&
               receipt.getRetailer() != null && !receipt.getRetailer().trim().isEmpty() &&
               receipt.getPurchaseDate() != null &&
               receipt.getPurchaseTime() != null &&
               receipt.getTotal() != null &&
               !receipt.getItems().isEmpty();
    }
}
