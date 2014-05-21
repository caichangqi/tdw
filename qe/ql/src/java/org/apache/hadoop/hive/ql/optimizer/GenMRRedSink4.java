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

package org.apache.hadoop.hive.ql.optimizer;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.io.Serializable;

import org.apache.hadoop.hive.ql.exec.Operator;
import org.apache.hadoop.hive.ql.exec.ReduceSinkOperator;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.plan.mapredWork;
import org.apache.hadoop.hive.ql.lib.Node;
import org.apache.hadoop.hive.ql.lib.NodeProcessor;
import org.apache.hadoop.hive.ql.lib.NodeProcessorCtx;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.optimizer.GenMRProcContext.GenMapRedCtx;
import org.apache.hadoop.hive.ql.parse.ParseContext;

public class GenMRRedSink4 implements NodeProcessor {

  public GenMRRedSink4() {
  }

  public Object process(Node nd, Stack<Node> stack, NodeProcessorCtx opProcCtx,
      Object... nodeOutputs) throws SemanticException {
    ReduceSinkOperator op = (ReduceSinkOperator) nd;
    GenMRProcContext ctx = (GenMRProcContext) opProcCtx;

    ParseContext parseCtx = ctx.getParseCtx();

    Operator<? extends Serializable> reducer = op.getChildOperators().get(0);
    Map<Operator<? extends Serializable>, GenMapRedCtx> mapCurrCtx = ctx
        .getMapCurrCtx();
    GenMapRedCtx mapredCtx = mapCurrCtx.get(op.getParentOperators().get(0));
    Task<? extends Serializable> currTask = mapredCtx.getCurrTask();
    mapredWork plan = (mapredWork) currTask.getWork();
    HashMap<Operator<? extends Serializable>, Task<? extends Serializable>> opTaskMap = ctx
        .getOpTaskMap();
    Task<? extends Serializable> opMapTask = opTaskMap.get(reducer);

    ctx.setCurrTask(currTask);

    if (opMapTask == null) {
      if (plan.getReducer() == null)
        GenMapRedUtils.initMapJoinPlan(op, ctx, true, false, true, -1);
      else
        GenMapRedUtils.splitPlan(op, ctx);
    } else {
      assert plan.getReducer() != reducer;
      GenMapRedUtils.joinPlan(op, currTask, opMapTask, ctx, -1, false, true,
          false);
    }

    mapCurrCtx.put(op, new GenMapRedCtx(ctx.getCurrTask(), ctx.getCurrTopOp(),
        ctx.getCurrAliasId()));

    ctx.setCurrMapJoinOp(null);
    return null;
  }
}
