package com.iemr.inventory.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class DataNotFoundDiffblueTest {

    @Test
    void testNewDataNotFound() {
        // Arrange and Act
        DataNotFound actualDataNotFound = new DataNotFound("Msg");

        // Assert
        assertEquals("Msg", actualDataNotFound.getLocalizedMessage());
        assertEquals("Msg", actualDataNotFound.getMessage());
        assertNull(actualDataNotFound.getCause());
        assertEquals(0, actualDataNotFound.getSuppressed().length);
    }
}


