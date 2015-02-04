/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.reactivestreams.client;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import static com.mongodb.assertions.Assertions.notNull;


class DistinctPublisherImpl<T> implements DistinctPublisher<T> {

    private final com.mongodb.async.client.DistinctIterable<T> wrapped;

    DistinctPublisherImpl(final com.mongodb.async.client.DistinctIterable<T> wrapped) {
        this.wrapped = notNull("wrapped", wrapped);
    }

    @Override
    public DistinctPublisher<T> filter(final Object filter) {
        wrapped.filter(filter);
        return this;
    }

    @Override
    public DistinctPublisher<T> maxTime(final long maxTime, final TimeUnit timeUnit) {
        wrapped.maxTime(maxTime, timeUnit);
        return this;
    }

    @Override
    public void subscribe(final Subscriber<? super T> s) {
        new MongoIterablePublisher<T>(wrapped).subscribe(s);
    }
}
