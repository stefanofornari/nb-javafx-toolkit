# MappedList

`MappedList<E, F>` is a custom `TransformationList` implementation that creates a mapped view of an observable list. It transforms each element of a source `ObservableList<F>` into type `E` using a provided mapping function.

## Overview

`MappedList` extends JavaFX's `TransformationList<E, F>` and provides a lazy, live view of the source list where each element is transformed on-demand. Changes to the source list (additions, removals, updates, permutations) are automatically reflected in the mapped list.

## Key Features

- **Lazy Evaluation**: Items are mapped only when accessed, not upfront
- **Live Updates**: All changes to the source list propagate to the mapped list
- **Type Safety**: Generic type parameters ensure type-safe transformations
- **Observable**: Maintains JavaFX observable list contract

## Usage

### Basic Example

```java
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ste.netbeans.javafx.collections.MappedList;

// Source list of objects
ObservableList<Person> people = FXCollections.observableArrayList(
    new Person("Alice", 30),
    new Person("Bob", 25)
);

// Create a mapped list that extracts just the names
MappedList<String, Person> names = new MappedList<>(
    people, 
    Person::getName
);

// names now contains ["Alice", "Bob"]
// and will stay in sync with the people list
```

### With Custom Objects

```java
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
}

// Create source list
ObservableList<Person> people = FXCollections.observableArrayList(
    new Person("Alice", 30),
    new Person("Bob", 25),
    new Person("Charlie", 35)
);

// Map to names
MappedList<String, Person> names = new MappedList<>(people, Person::getName);

// Map to ages
MappedList<Integer, Person> ages = new MappedList<>(people, Person::getAge);
```

## How It Works

The `MappedList` applies the mapper function to each element of the source list when that element is accessed via `get(int index)`. This means:

1. **Memory Efficient**: No intermediate storage for mapped values
2. **Fast**: Mapping happens only when needed
3. **Consistent**: Always reflects the current state of the source

## Change Propagation

All list change operations are supported:

- **Add**: When items are added to the source, they appear in the mapped list
- **Remove**: When items are removed from the source, they disappear from the mapped list
- **Update**: When items in the source are replaced, the mapped list reflects the new mapped values
- **Permute**: When items are reordered in the source, the mapped list maintains the same order

## Constructors

```java
MappedList(ObservableList<F> source, Function<? super F, ? extends E> mapper)
```

**Parameters:**
- `source`: The backing observable list
- `mapper`: Function that transforms a source element of type F to type E

## Methods

Inherits all methods from `TransformationList<E, F>` and `ObservableList<E>`. Key methods:

- `get(int index)`: Returns the mapped value at the specified index
- `size()`: Returns the size of the source list
- `getSource()`: Returns the source observable list

## Use Cases

- Extracting a single property from a list of objects
- Transforming data types (e.g., Integer to String)
- Creating derived views of data for display
- Implementing computed properties on lists

## Thread Safety

Like all JavaFX observable collections, `MappedList` is designed to be used on the JavaFX Application Thread. Modifications from other threads should be wrapped in `Platform.runLater()`.
