/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.jackson.utils;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.FloatNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NullNode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonObjectUtilsTest {

    @Test
    void testPojo() {
        assertEquals(ObjectMapperFactory.get().createObjectNode().put("name", "javierito"), JsonObjectUtils.fromValue(new Person("javierito")));
    }

    @Test
    void testArrayOfPojo() {
        assertEquals(ObjectMapperFactory.get().createArrayNode().add(ObjectMapperFactory.get().createObjectNode().put("name", "javierito"))
                .add(ObjectMapperFactory.get().createObjectNode().put("name", "fulanito")), JsonObjectUtils.fromValue(Arrays.asList(new Person("javierito"), new Person("fulanito"))));
    }

    @Test
    void testJavaArray() {
        assertEquals(Arrays.asList(Collections.singletonMap("name", "javierito"), Collections.singletonMap("name", "fulanito")), JsonObjectUtils.toJavaValue(ObjectMapperFactory.get().createArrayNode()
                .add(ObjectMapperFactory.get().createObjectNode().put("name", "javierito")).add(ObjectMapperFactory.get().createObjectNode().put("name", "fulanito"))));
    }

    @Test
    void testJavaObject() {
        assertEquals(Collections.singletonMap("name", "javierito"), JsonObjectUtils.toJavaValue(ObjectMapperFactory.get().createObjectNode().put("name", "javierito")));
    }

    @Test
    void testNullObject() {
        assertNull(JsonObjectUtils.toJavaValue(NullNode.getInstance()));
    }

    @Test
    void testJavaInt() {
        assertEquals(5, JsonObjectUtils.toJavaValue(new IntNode(5)));
    }

    @Test
    void testJavaDouble() {
        assertEquals(5.0, JsonObjectUtils.toJavaValue(new DoubleNode(5.0f)));
    }

    @Test
    void testJavaFloat() {
        assertEquals(5.0f, JsonObjectUtils.toJavaValue(new FloatNode(5.0f)));
    }

    @Test
    void testJavaByteArray() {
        byte[] bytes = { 1, 2, 3, 4 };
        assertArrayEquals(bytes, (byte[]) JsonObjectUtils.toJavaValue(BinaryNode.valueOf(bytes)));
    }
}
