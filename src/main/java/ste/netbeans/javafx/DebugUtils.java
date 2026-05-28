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

package ste.netbeans.javafx;

/**
 *
 */
import javafx.scene.Node;
import javafx.scene.Parent;

public class DebugUtils {

    public static void dump(final Node node) {
        dump(node, 0);
    }
    public static void dump(final Node node, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "Node: " + node.getTypeSelector() + " | ID: " + node.getId());
        System.out.println(node.getStyleClass());

        if (node instanceof Parent) {
            for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
                dump(child, depth + 1);
            }
        }
    }
}