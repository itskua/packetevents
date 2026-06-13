package com.github.retrooper.packetevents.wrapper.play.server;

import com.github.retrooper.packetevents.manager.server.ServerVersion;
import com.github.retrooper.packetevents.protocol.attribute.Attributes;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WrapperPlayServerUpdateAttributesTest {

    @Test
    void requireAttributeForWriteThrowsClearErrorForNullAttribute() {
        WrapperPlayServerUpdateAttributes.Property property = new WrapperPlayServerUpdateAttributes.Property(null, 1.0, Collections.emptyList());

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> WrapperPlayServerUpdateAttributes.requireAttributeForWrite(property, 2, ServerVersion.V_1_20_5));

        assertTrue(exception.getMessage().contains("index 2"));
        assertTrue(exception.getMessage().contains(ServerVersion.V_1_20_5.name()));
    }

    @Test
    void requireAttributeForWriteReturnsAttributeWhenPresent() {
        WrapperPlayServerUpdateAttributes.Property property = new WrapperPlayServerUpdateAttributes.Property(
                Attributes.MAX_HEALTH, 1.0, Collections.emptyList());

        assertSame(Attributes.MAX_HEALTH,
                WrapperPlayServerUpdateAttributes.requireAttributeForWrite(property, 0, ServerVersion.V_1_20_5));
        assertEquals(Attributes.MAX_HEALTH, property.getAttribute());
    }
}
