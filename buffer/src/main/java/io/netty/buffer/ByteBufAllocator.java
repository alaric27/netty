/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.buffer;

/**
 * 内存分配器
 *
 * 如何决定分配什么类型？
 *  1、根据子类决定分配池化还是非池化内存，
 *    PooledByteBufAllocator：池化内存分配器，UnpooledByteBufAllocator:非池化内存分配器
 *  2、根据具体的方法分配堆内存还是直接内存
 *      heapBuffer():分配堆内存，directBuffer():分配直接内存
 *  3、根据是否能拿到jdk底层的unsafe对象来决定分配unsafe类型
 *      如果能拿到JDK底层Unsafe对象，则分配Unsafe否则分配非Unsafe内存
 *
 * Implementations are responsible to allocate buffers. Implementations of this interface are expected to be
 * thread-safe.
 */
public interface ByteBufAllocator {

    ByteBufAllocator DEFAULT = ByteBufUtil.DEFAULT_ALLOCATOR;

    /**
     * 分配内存，堆内存还是直接内存依赖具体的实现
     * Allocate a {@link ByteBuf}. If it is a direct or heap buffer
     * depends on the actual implementation.
     */
    ByteBuf buffer();

    /**
     * Allocate a {@link ByteBuf} with the given initial capacity.
     * If it is a direct or heap buffer depends on the actual implementation.
     */
    ByteBuf buffer(int initialCapacity);

    /**
     * Allocate a {@link ByteBuf} with the given initial capacity and the given
     * maximal capacity. If it is a direct or heap buffer depends on the actual
     * implementation.
     */
    ByteBuf buffer(int initialCapacity, int maxCapacity);

    /**
     * 偏向于分配直接内存
     * Allocate a {@link ByteBuf}, preferably a direct buffer which is suitable for I/O.
     */
    ByteBuf ioBuffer();

    /**
     * Allocate a {@link ByteBuf}, preferably a direct buffer which is suitable for I/O.
     */
    ByteBuf ioBuffer(int initialCapacity);

    /**
     * Allocate a {@link ByteBuf}, preferably a direct buffer which is suitable for I/O.
     */
    ByteBuf ioBuffer(int initialCapacity, int maxCapacity);

    /**
     * 分配堆内存
     * Allocate a heap {@link ByteBuf}.
     */
    ByteBuf heapBuffer();

    /**
     * Allocate a heap {@link ByteBuf} with the given initial capacity.
     */
    ByteBuf heapBuffer(int initialCapacity);

    /**
     * Allocate a heap {@link ByteBuf} with the given initial capacity and the given
     * maximal capacity.
     */
    ByteBuf heapBuffer(int initialCapacity, int maxCapacity);

    /**
     * 分配直接内存
     * Allocate a direct {@link ByteBuf}.
     */
    ByteBuf directBuffer();

    /**
     * Allocate a direct {@link ByteBuf} with the given initial capacity.
     */
    ByteBuf directBuffer(int initialCapacity);

    /**
     * Allocate a direct {@link ByteBuf} with the given initial capacity and the given
     * maximal capacity.
     */
    ByteBuf directBuffer(int initialCapacity, int maxCapacity);

    /**
     *
     * 聚合ByteBuf,依赖具体实现来决定是堆内存还是直接内存
     * Allocate a {@link CompositeByteBuf}.
     * If it is a direct or heap buffer depends on the actual implementation.
     */
    CompositeByteBuf compositeBuffer();

    /**
     * Allocate a {@link CompositeByteBuf} with the given maximum number of components that can be stored in it.
     * If it is a direct or heap buffer depends on the actual implementation.
     */
    CompositeByteBuf compositeBuffer(int maxNumComponents);

    /**
     * Allocate a heap {@link CompositeByteBuf}.
     */
    CompositeByteBuf compositeHeapBuffer();

    /**
     * Allocate a heap {@link CompositeByteBuf} with the given maximum number of components that can be stored in it.
     */
    CompositeByteBuf compositeHeapBuffer(int maxNumComponents);

    /**
     * Allocate a direct {@link CompositeByteBuf}.
     */
    CompositeByteBuf compositeDirectBuffer();

    /**
     * Allocate a direct {@link CompositeByteBuf} with the given maximum number of components that can be stored in it.
     */
    CompositeByteBuf compositeDirectBuffer(int maxNumComponents);

    /**
     * Returns {@code true} if direct {@link ByteBuf}'s are pooled
     */
    boolean isDirectBufferPooled();

    /**
     * Calculate the new capacity of a {@link ByteBuf} that is used when a {@link ByteBuf} needs to expand by the
     * {@code minNewCapacity} with {@code maxCapacity} as upper-bound.
     */
    int calculateNewCapacity(int minNewCapacity, int maxCapacity);
 }
