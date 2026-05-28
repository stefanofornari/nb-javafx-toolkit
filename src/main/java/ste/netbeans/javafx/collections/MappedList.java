/*
 * Copyright 2026 ste.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ste.netbeans.javafx.collections;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import java.util.function.Function;

public class MappedList<E, F> extends TransformationList<E, F> {

    private final Function<? super F, ? extends E> mapper;

    public MappedList(ObservableList<F> source, Function<? super F, ? extends E> mapper) {
        super(source);
        this.mapper = mapper;
    }

    @Override
    public E get(int index) {
        return mapper.apply(getSource().get(index));
    }

    @Override
    public int size() {
        return getSource().size();
    }

    @Override
    public int getSourceIndex(int index) {
        return index; // 1:1 index alignment
    }

    @Override
    public int getViewIndex(int index) {
        return index; // 1:1 reverse index alignment
    }

    @Override
    protected void sourceChanged(ListChangeListener.Change<? extends F> c) {
        beginChange();
        while (c.next()) {
            if (c.wasPermutated()) {
                // To track items swapping positions (like a sort execution on the backing list),
                // we build an array of length `to - from`. Since our mapping doesn't alter index logic,
                // we can translate the source permutation index properties directly.
                int from = c.getFrom();
                int to = c.getTo();
                int[] perm = new int[to - from];
                for (int i = from; i < to; i++) {
                    perm[i - from] = c.getPermutation(i);
                }
                nextPermutation(from, to, perm);
            } else if (c.wasUpdated()) {
                nextUpdate(c.getFrom());
            } else {
                if (c.wasRemoved()) {
                    nextRemove(c.getFrom(), c.getRemoved().stream().map(mapper).toList());
                }
                if (c.wasAdded()) {
                    nextAdd(c.getFrom(), c.getTo());
                }
            }
        }
        endChange();
    }
}