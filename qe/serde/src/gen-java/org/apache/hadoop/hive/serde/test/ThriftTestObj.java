/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.serde.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import org.apache.log4j.Logger;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.protocol.*;

public class ThriftTestObj implements TBase, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("ThriftTestObj");
  private static final TField FIELD1_FIELD_DESC = new TField("field1",
      TType.I32, (short) 1);
  private static final TField FIELD2_FIELD_DESC = new TField("field2",
      TType.STRING, (short) 2);
  private static final TField FIELD3_FIELD_DESC = new TField("field3",
      TType.LIST, (short) 3);

  public int field1;
  public static final int FIELD1 = 1;
  public String field2;
  public static final int FIELD2 = 2;
  public List<InnerStruct> field3;
  public static final int FIELD3 = 3;

  private final Isset __isset = new Isset();

  private static final class Isset implements java.io.Serializable {
    public boolean field1 = false;
  }

  public static final Map<Integer, FieldMetaData> metaDataMap = Collections
      .unmodifiableMap(new HashMap<Integer, FieldMetaData>() {
        {
          put(FIELD1, new FieldMetaData("field1",
              TFieldRequirementType.DEFAULT, new FieldValueMetaData(TType.I32)));
          put(FIELD2, new FieldMetaData("field2",
              TFieldRequirementType.DEFAULT, new FieldValueMetaData(
                  TType.STRING)));
          put(FIELD3, new FieldMetaData("field3",
              TFieldRequirementType.DEFAULT, new ListMetaData(TType.LIST,
                  new StructMetaData(TType.STRUCT, InnerStruct.class))));
        }
      });

  static {
  }

  public ThriftTestObj() {
  }

  public ThriftTestObj(int field1, String field2, List<InnerStruct> field3) {
    this();
    this.field1 = field1;
    this.__isset.field1 = true;
    this.field2 = field2;
    this.field3 = field3;
  }

  public ThriftTestObj(ThriftTestObj other) {
    __isset.field1 = other.__isset.field1;
    this.field1 = other.field1;
    if (other.isSetField2()) {
      this.field2 = other.field2;
    }
    if (other.isSetField3()) {
      List<InnerStruct> __this__field3 = new ArrayList<InnerStruct>();
      for (InnerStruct other_element : other.field3) {
        __this__field3.add(new InnerStruct(other_element));
      }
      this.field3 = __this__field3;
    }
  }

  @Override
  public ThriftTestObj clone() {
    return new ThriftTestObj(this);
  }

  public int getField1() {
    return this.field1;
  }

  public void setField1(int field1) {
    this.field1 = field1;
    this.__isset.field1 = true;
  }

  public void unsetField1() {
    this.__isset.field1 = false;
  }

  public boolean isSetField1() {
    return this.__isset.field1;
  }

  public void setField1IsSet(boolean value) {
    this.__isset.field1 = value;
  }

  public String getField2() {
    return this.field2;
  }

  public void setField2(String field2) {
    this.field2 = field2;
  }

  public void unsetField2() {
    this.field2 = null;
  }

  public boolean isSetField2() {
    return this.field2 != null;
  }

  public void setField2IsSet(boolean value) {
    if (!value) {
      this.field2 = null;
    }
  }

  public int getField3Size() {
    return (this.field3 == null) ? 0 : this.field3.size();
  }

  public java.util.Iterator<InnerStruct> getField3Iterator() {
    return (this.field3 == null) ? null : this.field3.iterator();
  }

  public void addToField3(InnerStruct elem) {
    if (this.field3 == null) {
      this.field3 = new ArrayList<InnerStruct>();
    }
    this.field3.add(elem);
  }

  public List<InnerStruct> getField3() {
    return this.field3;
  }

  public void setField3(List<InnerStruct> field3) {
    this.field3 = field3;
  }

  public void unsetField3() {
    this.field3 = null;
  }

  public boolean isSetField3() {
    return this.field3 != null;
  }

  public void setField3IsSet(boolean value) {
    if (!value) {
      this.field3 = null;
    }
  }

  public void setFieldValue(int fieldID, Object value) {
    switch (fieldID) {
    case FIELD1:
      if (value == null) {
        unsetField1();
      } else {
        setField1((Integer) value);
      }
      break;

    case FIELD2:
      if (value == null) {
        unsetField2();
      } else {
        setField2((String) value);
      }
      break;

    case FIELD3:
      if (value == null) {
        unsetField3();
      } else {
        setField3((List<InnerStruct>) value);
      }
      break;

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  public Object getFieldValue(int fieldID) {
    switch (fieldID) {
    case FIELD1:
      return new Integer(getField1());

    case FIELD2:
      return getField2();

    case FIELD3:
      return getField3();

    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  public boolean isSet(int fieldID) {
    switch (fieldID) {
    case FIELD1:
      return isSetField1();
    case FIELD2:
      return isSetField2();
    case FIELD3:
      return isSetField3();
    default:
      throw new IllegalArgumentException("Field " + fieldID + " doesn't exist!");
    }
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ThriftTestObj)
      return this.equals((ThriftTestObj) that);
    return false;
  }

  public boolean equals(ThriftTestObj that) {
    if (that == null)
      return false;

    boolean this_present_field1 = true;
    boolean that_present_field1 = true;
    if (this_present_field1 || that_present_field1) {
      if (!(this_present_field1 && that_present_field1))
        return false;
      if (this.field1 != that.field1)
        return false;
    }

    boolean this_present_field2 = true && this.isSetField2();
    boolean that_present_field2 = true && that.isSetField2();
    if (this_present_field2 || that_present_field2) {
      if (!(this_present_field2 && that_present_field2))
        return false;
      if (!this.field2.equals(that.field2))
        return false;
    }

    boolean this_present_field3 = true && this.isSetField3();
    boolean that_present_field3 = true && that.isSetField3();
    if (this_present_field3 || that_present_field3) {
      if (!(this_present_field3 && that_present_field3))
        return false;
      if (!this.field3.equals(that.field3))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true) {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) {
        break;
      }
      switch (field.id) {
      case FIELD1:
        if (field.type == TType.I32) {
          this.field1 = iprot.readI32();
          this.__isset.field1 = true;
        } else {
          TProtocolUtil.skip(iprot, field.type);
        }
        break;
      case FIELD2:
        if (field.type == TType.STRING) {
          this.field2 = iprot.readString();
        } else {
          TProtocolUtil.skip(iprot, field.type);
        }
        break;
      case FIELD3:
        if (field.type == TType.LIST) {
          {
            TList _list0 = iprot.readListBegin();
            this.field3 = new ArrayList<InnerStruct>(_list0.size);
            for (int _i1 = 0; _i1 < _list0.size; ++_i1) {
              InnerStruct _elem2;
              _elem2 = new InnerStruct();
              _elem2.read(iprot);
              this.field3.add(_elem2);
            }
            iprot.readListEnd();
          }
        } else {
          TProtocolUtil.skip(iprot, field.type);
        }
        break;
      default:
        TProtocolUtil.skip(iprot, field.type);
        break;
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    oprot.writeFieldBegin(FIELD1_FIELD_DESC);
    oprot.writeI32(this.field1);
    oprot.writeFieldEnd();
    if (this.field2 != null) {
      oprot.writeFieldBegin(FIELD2_FIELD_DESC);
      oprot.writeString(this.field2);
      oprot.writeFieldEnd();
    }
    if (this.field3 != null) {
      oprot.writeFieldBegin(FIELD3_FIELD_DESC);
      {
        oprot.writeListBegin(new TList(TType.STRUCT, this.field3.size()));
        for (InnerStruct _iter3 : this.field3) {
          _iter3.write(oprot);
        }
        oprot.writeListEnd();
      }
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ThriftTestObj(");
    boolean first = true;

    sb.append("field1:");
    sb.append(this.field1);
    first = false;
    if (!first)
      sb.append(", ");
    sb.append("field2:");
    if (this.field2 == null) {
      sb.append("null");
    } else {
      sb.append(this.field2);
    }
    first = false;
    if (!first)
      sb.append(", ");
    sb.append("field3:");
    if (this.field3 == null) {
      sb.append("null");
    } else {
      sb.append(this.field3);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
  }

  @Override
  public int compareTo(Object arg0) {
    return 0;
  }

  @Override
  public TFieldIdEnum fieldForId(int fieldId) {
    return null;
  }

  @Override
  public boolean isSet(TFieldIdEnum field) {
    return false;
  }

  @Override
  public Object getFieldValue(TFieldIdEnum field) {
    return null;
  }

  @Override
  public void setFieldValue(TFieldIdEnum field, Object value) {

  }

  @Override
  public TBase deepCopy() {
    return null;
  }

  @Override
  public void clear() {

  }

}
