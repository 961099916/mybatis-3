/**
 * Copyright 2009-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.ibatis.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class EnumTypeHandlerTest extends BaseTypeHandlerTest {

    private static final TypeHandler<MyEnum> TYPE_HANDLER = new EnumTypeHandler<MyEnum>(MyEnum.class);

    @Override
    @Test
    public void shouldSetParameter() throws Exception {
        TYPE_HANDLER.setParameter(ps, 1, MyEnum.ONE, null);
        verify(ps).setString(1, "ONE");
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByName() throws Exception {
        when(rs.getString("column")).thenReturn("ONE");
        when(rs.wasNull()).thenReturn(false);
        assertEquals(MyEnum.ONE, TYPE_HANDLER.getResult(rs, "column"));
    }

    @Override
    @Test
    public void shouldGetResultNullFromResultSetByName() throws Exception {
        when(rs.getString("column")).thenReturn(null);
        when(rs.wasNull()).thenReturn(true);
        assertNull(TYPE_HANDLER.getResult(rs, "column"));
    }

    @Override
    @Test
    public void shouldGetResultFromResultSetByPosition() throws Exception {
        when(rs.getString(1)).thenReturn("ONE");
        when(rs.wasNull()).thenReturn(false);
        assertEquals(MyEnum.ONE, TYPE_HANDLER.getResult(rs, 1));
    }

    @Override
    @Test
    public void shouldGetResultNullFromResultSetByPosition() throws Exception {
        when(rs.getString(1)).thenReturn(null);
        when(rs.wasNull()).thenReturn(true);
        assertNull(TYPE_HANDLER.getResult(rs, 1));
    }

    @Override
    @Test
    public void shouldGetResultFromCallableStatement() throws Exception {
        when(cs.getString(1)).thenReturn("ONE");
        when(cs.wasNull()).thenReturn(false);
        assertEquals(MyEnum.ONE, TYPE_HANDLER.getResult(cs, 1));
    }

    @Override
    @Test
    public void shouldGetResultNullFromCallableStatement() throws Exception {
        when(cs.getString(1)).thenReturn(null);
        when(cs.wasNull()).thenReturn(true);
        assertNull(TYPE_HANDLER.getResult(cs, 1));
    }

    @Test
    public void shouldSetNullParameter() throws Exception {
        TYPE_HANDLER.setParameter(ps, 1, null, JdbcType.VARCHAR);
        verify(ps).setNull(1, JdbcType.VARCHAR.TYPE_CODE);
    }

    enum MyEnum {
        ONE, TWO
    }

}
