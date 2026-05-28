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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.BDDAssertions.then;

class MappedListTest {

    private class ListItem {
        final public String name;
        final public int value;

        public ListItem(final String n, final int v) {
            this.name = n;
            this.value = v;
        }

        public String name() {
            return name;
        }
    }

    private ObservableList<ListItem> backingList;
    private MappedList<String, ListItem> mappedList;

    @BeforeEach
    void beforeEach() {
        backingList = FXCollections.observableArrayList(
            new ListItem("one", 1),
            new ListItem("two", 2)
        );
        mappedList = new MappedList<>(backingList, ListItem::name);
    }

    @Test
    void reflect_initial_mapped_values_lazily() {
        then(mappedList).containsExactly("one", "two");
    }

    @Test
    void _automatically_reflect_additions_to_backing_list() {
        // When
        backingList.add(new ListItem("three", 3));

        // Then
        then(mappedList).containsExactly("one", "two", "three");
    }

    @Test
    void automatically_reflect_removals_from_backing_list() {
        // When
        backingList.remove(0); // Prune one4

        // Then
        then(mappedList).containsExactly("two");
    }

    @Test
    void track_replacements_in_backing_list() {
        // When
        backingList.set(1, new ListItem("four", 4));

        // Then
        then(mappedList).containsExactly("one","four");
    }
}