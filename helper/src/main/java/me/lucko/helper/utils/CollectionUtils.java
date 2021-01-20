/*
 * This file is part of helper, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.helper.utils;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class CollectionUtils {

    @Deprecated
    public static <T> List<List<T>> divideIterable(Iterable<T> source, int subListSize) {
        return Lists.partition(Lists.newArrayList(source), subListSize);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNonEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNonEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean contains(Object[] array, Object objToFind) {
        if (array == null) return false;

        if (objToFind == null) {
            for (Object o : array) {
                if (o == null) return true;
            }
        } else {
            for (Object o : array) {
                if (objToFind.equals(o)) return true;
            }
        }

        return false;
    }

    public static int size(Collection<?> collection) {
        if (isEmpty(collection)) return 0;
        return collection.size();
    }

    public static int size(Map<?, ?> map) {
        if (isEmpty(map)) return 0;
        return map.size();
    }

    private CollectionUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}