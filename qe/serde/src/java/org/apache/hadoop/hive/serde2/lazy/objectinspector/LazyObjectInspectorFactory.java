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

package org.apache.hadoop.hive.serde2.lazy.objectinspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.hadoop.hive.serde2.objectinspector.ByteArrayObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.UnionStructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector.PrimitiveCategory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableBooleanObjectInspector;
import org.apache.hadoop.io.Text;

public class LazyObjectInspectorFactory {

  static ConcurrentHashMap<ArrayList<Object>, LazySimpleStructObjectInspector> cachedLazySimpleStructObjectInspector = new ConcurrentHashMap<ArrayList<Object>, LazySimpleStructObjectInspector>();

  public static LazySimpleStructObjectInspector getLazySimpleStructObjectInspector(
      List<String> structFieldNames,
      List<ObjectInspector> structFieldObjectInspectors, byte separator,
      Text nullSequence, boolean lastColumnTakesRest, boolean escaped,
      byte escapeChar, boolean gbkcoding) {
    ArrayList<Object> signature = new ArrayList<Object>();
    signature.add(structFieldNames);
    signature.add(structFieldObjectInspectors);
    signature.add(Byte.valueOf(separator));
    signature.add(nullSequence.toString());
    signature.add(Boolean.valueOf(lastColumnTakesRest));
    signature.add(Boolean.valueOf(escaped));
    signature.add(Byte.valueOf(escapeChar));
    signature.add(Boolean.valueOf(gbkcoding));
    LazySimpleStructObjectInspector result = cachedLazySimpleStructObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazySimpleStructObjectInspector(structFieldNames,
          structFieldObjectInspectors, separator, nullSequence,
          lastColumnTakesRest, escaped, escapeChar, gbkcoding);
      cachedLazySimpleStructObjectInspector.put(signature, result);
    }
    return result;
  }

  static ConcurrentHashMap<ArrayList<Object>, LazyListObjectInspector> cachedLazySimpleListObjectInspector = new ConcurrentHashMap<ArrayList<Object>, LazyListObjectInspector>();

  public static LazyListObjectInspector getLazySimpleListObjectInspector(
      ObjectInspector listElementObjectInspector, byte separator,
      Text nullSequence, boolean escaped, byte escapeChar) {
    ArrayList<Object> signature = new ArrayList<Object>();
    signature.add(listElementObjectInspector);
    signature.add(Byte.valueOf(separator));
    signature.add(nullSequence.toString());
    signature.add(Boolean.valueOf(escaped));
    signature.add(Byte.valueOf(escapeChar));
    LazyListObjectInspector result = cachedLazySimpleListObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazyListObjectInspector(listElementObjectInspector,
          separator, nullSequence, escaped, escapeChar);
      cachedLazySimpleListObjectInspector.put(signature, result);
    }
    return result;
  }

  public static ByteArrayObjectInspector getLazyByteArrayObjectInspector(){
    return new LazyByteArrayObjectInspector();
  }
  
  
  static ConcurrentHashMap<ArrayList<Object>, LazyMapObjectInspector> cachedLazySimpleMapObjectInspector = new ConcurrentHashMap<ArrayList<Object>, LazyMapObjectInspector>();

  public static LazyMapObjectInspector getLazySimpleMapObjectInspector(
      ObjectInspector mapKeyObjectInspector,
      ObjectInspector mapValueObjectInspector, byte itemSeparator,
      byte keyValueSeparator, Text nullSequence, boolean escaped,
      byte escapeChar) {
    ArrayList<Object> signature = new ArrayList<Object>();
    signature.add(mapKeyObjectInspector);
    signature.add(mapValueObjectInspector);
    signature.add(Byte.valueOf(itemSeparator));
    signature.add(Byte.valueOf(keyValueSeparator));
    signature.add(nullSequence.toString());
    signature.add(Boolean.valueOf(escaped));
    signature.add(Byte.valueOf(escapeChar));
    LazyMapObjectInspector result = cachedLazySimpleMapObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazyMapObjectInspector(mapKeyObjectInspector,
          mapValueObjectInspector, itemSeparator, keyValueSeparator,
          nullSequence, escaped, escapeChar);
      cachedLazySimpleMapObjectInspector.put(signature, result);
    }
    return result;
  }

  static ConcurrentHashMap<List<Object>, LazyUnionObjectInspector> cachedLazyUnionObjectInspector = new ConcurrentHashMap<List<Object>, LazyUnionObjectInspector>();

  public static LazyUnionObjectInspector getLazyUnionObjectInspector(
      List<ObjectInspector> ois, byte separator, Text nullSequence,
      boolean escaped, byte escapeChar) {
    List<Object> signature = new ArrayList<Object>();
    signature.add(ois);
    signature.add(Byte.valueOf(separator));
    signature.add(nullSequence.toString());
    signature.add(Boolean.valueOf(escaped));
    signature.add(Byte.valueOf(escapeChar));
    LazyUnionObjectInspector result = cachedLazyUnionObjectInspector
        .get(signature);
    if (result == null) {
      result = new LazyUnionObjectInspector(ois, separator, nullSequence,
          escaped, escapeChar);
      cachedLazyUnionObjectInspector.put(signature, result);
    }
    return result;
  }

}
