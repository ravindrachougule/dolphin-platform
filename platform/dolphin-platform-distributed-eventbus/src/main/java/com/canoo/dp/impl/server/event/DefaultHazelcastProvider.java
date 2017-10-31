/*
 * Copyright 2015-2017 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.dp.impl.server.event;

import com.canoo.platform.remoting.server.event.MessageEvent;
import com.google.gson.Gson;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import org.apiguardian.api.API;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apiguardian.api.API.Status.INTERNAL;

@API(since = "0.x", status = INTERNAL)
public class DefaultHazelcastProvider implements HazelcastProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultHazelcastProvider.class);

    private HazelcastInstance hazelcastInstance;

    public synchronized HazelcastInstance getHazelcastInstance(HazelcastConfig configuration) {
        if (hazelcastInstance == null) {
            final String serverName = configuration.getServerName();
            final String serverPort = configuration.getServerPort();
            final String groupName = configuration.getGroupName();

            LOG.debug("Hazelcast server name: {}", serverName);
            LOG.debug("Hazelcast server port: {}", serverPort);
            LOG.debug("Hazelcast group name: {}", groupName);

            final ClientConfig clientConfig = new ClientConfig();
            clientConfig.getNetworkConfig().addAddress(serverName + ":" + serverPort);
            clientConfig.getGroupConfig().setName(groupName);
            final SerializerConfig dolphinEventSerializerConfig = new SerializerConfig();
            dolphinEventSerializerConfig.setImplementation(new EventStreamSerializer(new Gson()));
            dolphinEventSerializerConfig.setClass(EventStreamSerializer.class);
            dolphinEventSerializerConfig.setTypeClass(MessageEvent.class);
            dolphinEventSerializerConfig.setTypeClassName(MessageEvent.class.getName());
            clientConfig.getSerializationConfig().getSerializerConfigs().add(dolphinEventSerializerConfig);
        }
        return hazelcastInstance;
    }
}
