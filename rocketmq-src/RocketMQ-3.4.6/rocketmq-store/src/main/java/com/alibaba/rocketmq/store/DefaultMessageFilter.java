/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.alibaba.rocketmq.store;

import com.alibaba.rocketmq.common.protocol.heartbeat.SubscriptionData;


/**
 * @author shijia.wxr
 */
public class DefaultMessageFilter implements MessageFilter {

    @Override
    public boolean isMessageMatched(SubscriptionData subscriptionData, long tagsCode) {
        if (null == subscriptionData) {
            return true;
        }

        if (subscriptionData.isClassFilterMode())
            return true;

        if (subscriptionData.getSubString().equals(SubscriptionData.SUB_ALL)) {
            return true;
        }

        //�����ƥ����ƺܼ� ��ֻҪtags ��hashcodeһ�£� ����Ϊƥ��; Ȼ�����Ϣ���͵�client�Ժ���msg tag�ľ�ȷƥ�䡣
        return subscriptionData.getCodeSet().contains((int) tagsCode);
    }

}