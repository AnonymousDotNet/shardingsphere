/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.mode.manager.cluster.event.subscriber.deliver;

import org.apache.shardingsphere.infra.metadata.database.schema.QualifiedDataSource;
import org.apache.shardingsphere.mode.event.deliver.datasource.qualified.QualifiedDataSourceDeletedEvent;
import org.apache.shardingsphere.mode.repository.cluster.ClusterPersistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeliverQualifiedDataSourceSubscriberTest {
    
    private DeliverQualifiedDataSourceSubscriber subscriber;
    
    @Mock
    private ClusterPersistRepository repository;
    
    @BeforeEach
    void setUp() {
        subscriber = new DeliverQualifiedDataSourceSubscriber(repository);
    }
    
    @Test
    void assertDeleteStorageNodeDataSourceDataSourceState() {
        subscriber.delete(new QualifiedDataSourceDeletedEvent(new QualifiedDataSource("foo_db", "foo_group", "foo_ds")));
        verify(repository).delete("/nodes/qualified_data_sources/foo_db.foo_group.foo_ds");
    }
}