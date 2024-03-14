package com.iemr.inventory.service.dispenseagainst_rx;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.repo.dispenseagainst_rx.PrescribedDrugDetailsRepo;

import java.sql.Timestamp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DispenseAgainstRXimpl.class, PrescribedDrugDetailsRepo.class})
@ExtendWith(SpringExtension.class)
class DispenseAgainstRXimplDiffblueTest {
    @Autowired
    private DispenseAgainstRXimpl dispenseAgainstRXimpl;

    /**
     * Method under test:
     * {@link DispenseAgainstRXimpl#getPrescribedMedicines(String)}
     */
    @Test
    void testGetPrescribedMedicines() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertNull((new DispenseAgainstRXimpl()).getPrescribedMedicines(""));
        assertNull((new DispenseAgainstRXimpl()).getPrescribedMedicines(null));
    }

    /**
     * Method under test:
     * {@link DispenseAgainstRXimpl#getPrescribedMedicines(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPrescribedMedicines2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@14f0d7a5 testClass = com.iemr.inventory.service.dispenseagainst_rx.DiffblueFakeClass3, locations = [], classes = [com.iemr.inventory.service.dispenseagainst_rx.DispenseAgainstRXimpl, com.iemr.inventory.repo.dispenseagainst_rx.PrescribedDrugDetailsRepo], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@180f9cd4, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@97a9035, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@5d7a42], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        dispenseAgainstRXimpl.getPrescribedMedicines("Request OBJ");
    }

    /**
     * Method under test:
     * {@link DispenseAgainstRXimpl#calculateExpiryDateInDays(Timestamp)}
     */
    @Test
    void testCalculateExpiryDateInDays() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertNull((new DispenseAgainstRXimpl()).calculateExpiryDateInDays(null));
    }

    /**
     * Method under test:
     * {@link DispenseAgainstRXimpl#calculateExpiryDateInDays(Timestamp)}
     */
    @Test
    void testCalculateExpiryDateInDays2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        DispenseAgainstRXimpl dispenseAgainstRXimpl = new DispenseAgainstRXimpl();
        Timestamp expDate = mock(Timestamp.class);
        when(expDate.getTime()).thenReturn(10L);

        // Act
        dispenseAgainstRXimpl.calculateExpiryDateInDays(expDate);

        // Assert
        verify(expDate).getTime();
    }

    /**
     * Method under test:
     * {@link DispenseAgainstRXimpl#calculateExpiryDateInDays(Timestamp)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateExpiryDateInDays3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@50778bac testClass = com.iemr.inventory.service.dispenseagainst_rx.DiffblueFakeClass1, locations = [], classes = [com.iemr.inventory.service.dispenseagainst_rx.DispenseAgainstRXimpl, com.iemr.inventory.repo.dispenseagainst_rx.PrescribedDrugDetailsRepo], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@180f9cd4, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@97a9035, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@5d7a42], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        dispenseAgainstRXimpl.calculateExpiryDateInDays(mock(Timestamp.class));
    }
}
