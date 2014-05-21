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

package org.apache.hadoop.hive.ql.udf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.ByteWritable;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.io.ShortWritable;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class UDFToFloat extends UDF {

  private static Log LOG = LogFactory.getLog(UDFToFloat.class.getName());

  FloatWritable floatWritable = new FloatWritable();

  public UDFToFloat() {
  }

  public FloatWritable evaluate(NullWritable i) {
    return null;
  }

  public FloatWritable evaluate(BooleanWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set(i.get() ? (float) 1.0 : (float) 0.0);
      return floatWritable;
    }
  }

  public FloatWritable evaluate(ByteWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set((float) i.get());
      return floatWritable;
    }
  }

  public FloatWritable evaluate(ShortWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set((float) i.get());
      return floatWritable;
    }
  }

  public FloatWritable evaluate(IntWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set((float) i.get());
      return floatWritable;
    }
  }

  public FloatWritable evaluate(LongWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set((float) i.get());
      return floatWritable;
    }
  }

  public FloatWritable evaluate(DoubleWritable i) {
    if (i == null) {
      return null;
    } else {
      floatWritable.set((float) i.get());
      return floatWritable;
    }
  }

  public FloatWritable evaluate(Text i) {
    if (i == null) {
      return null;
    } else {
      try {
        floatWritable.set(Float.valueOf(i.toString()));
        return floatWritable;
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  public FloatWritable evaluate(TimestampWritable i) {
    if (i == null) {
      return null;
    } else {
      try {
        floatWritable.set((float) i.getDouble());
        return floatWritable;
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

}
