package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

public class BusinessTest {

    @Test
    public void testConstructor() {
        // Given
        String businessName = "Acme Corp";

        // When
        Business business = new Business(businessName);

        // Then
        Assert.assertNotNull(business);
    }

    @Test
    public void testGetBusinessName() {
        // Given
        String expectedBusinessName = "Tech Solutions Inc";
        Business business = new Business(expectedBusinessName);

        // When
        String actualBusinessName = business.getBusinessName();

        // Then
        Assert.assertEquals(expectedBusinessName, actualBusinessName);
    }

    @Test
    public void testSetBusinessName() {
        // Given
        Business business = new Business("Acme Corp");
        String newBusinessName = "Global Industries";

        // When
        business.setBusinessName(newBusinessName);

        // Then
        Assert.assertEquals(newBusinessName, business.getBusinessName());
    }
}
