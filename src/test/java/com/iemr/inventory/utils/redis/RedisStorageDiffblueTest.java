package com.iemr.inventory.utils.redis;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RedisStorage.class})
@ExtendWith(SpringExtension.class)
class RedisStorageDiffblueTest {
    @Autowired
    private RedisStorage redisStorage;

    /**
     * Method under test: {@link RedisStorage#getObject(String, Boolean, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetObject() throws RedisSessionException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        String key = "";
        Boolean extendExpirationTime = null;
        int expirationTime = 0;

        // Act
        String actualObject = this.redisStorage.getObject(key, extendExpirationTime, expirationTime);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link RedisStorage#deleteObject(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteObject() throws RedisSessionException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        String key = "";

        // Act
        Long actualDeleteObjectResult = this.redisStorage.deleteObject(key);

        // Assert
        // TODO: Add assertions on result
    }
}
