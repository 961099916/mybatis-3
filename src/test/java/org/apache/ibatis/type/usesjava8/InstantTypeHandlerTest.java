/**
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.type.usesjava8;

import org.apache.ibatis.type.BaseTypeHandlerTest;
import org.apache.ibatis.type.InstantTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstantTypeHandlerTest extends BaseTypeHandlerTest {

    private static final TypeHandler<Instant> TYPE_HANDLER = new InstantTypeHandler();
    private static final Instant INSTANT = Instant.now();
    private static final Timestamp TIMESTAMP = Timestamp.from(INSTANT);

    @Override
    @Test
    public void shouldSetParameter() throws Exception {
        TYPE_HANDLER.setParameter(ps, 1, INSTANT, null);
        verify(ps).setTimestamp(1, TIMESTAMP);
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByName() throws Exception {
        when(rs.getTimestamp("column")).thenReturn(TIMESTAMP);
        assertEquals(INSTANT, TYPE_HANDLER.getResult(rs, "column"));
    }

    @Override
    @Test
    public void shouldGetResultNullFromResultSetByName() throws Exception {
        when(rs.getTimestamp("column")).thenReturn(null);
        when(rs.wasNull()).thenReturn(true);
        assertNull(TYPE_HANDLER.getResult(rs, "column"));
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByPosition() throws Exception {
        when(rs.getTimestamp(1)).thenReturn(TIMESTAMP);
        assertEquals(INSTANT, TYPE_HANDLER.getResult(rs, 1));
    }

    @Override
    @Test
    public void shouldGetResultNullFromResultSetByPosition() throws Exception {
        when(rs.getTimestamp(1)).thenReturn(null);
        when(rs.wasNull()).thenReturn(true);
        assertNull(TYPE_HANDLER.getResult(rs, 1));
    }

    @Override
    @Test
    public void shouldGetResultFromCallableStatement() throws Exception {
        when(cs.getTimestamp(1)).thenReturn(TIMESTAMP);
        assertEquals(INSTANT, TYPE_HANDLER.getResult(cs, 1));
    }

    @Override
    @Test
    public void shouldGetResultNullFromCallableStatement() throws Exception {
        when(cs.getTimestamp(1)).thenReturn(null);
        when(cs.wasNull()).thenReturn(true);
        assertNull(TYPE_HANDLER.getResult(cs, 1));
    }
}
