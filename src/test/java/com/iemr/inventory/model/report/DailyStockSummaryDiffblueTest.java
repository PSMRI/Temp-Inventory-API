package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DailyStockSummaryDiffblueTest {
    /**
     * Method under test: {@link DailyStockSummary#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"date\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"quantityReceived\""
                        + ":null,\"openingStock\":null,\"quantityDispensed\":null,\"adjustmentReceipt\":null,\"adjustmentIssue\":null,"
                        + "\"closingStock\":null}",
                (new DailyStockSummary()).toString());
    }
}
