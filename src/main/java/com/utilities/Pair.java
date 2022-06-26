package main.java.com.utilities;

/**
   * The type Pair.
   * A simple pair class to store two values.
   * @param <K> the type parameter representing the first value
   * @param <V> the type parameter representing the second value
   */
  public class Pair<K, V> {
    public K first;
    public V second;

    public Pair(K key, V value) {
      this.first  = key;
      this.second = value;
    }

    public <K, V> Pair<K, V> of(K a, V b) {
      return new Pair<>(a, b);
    }

    // Pair<?, ?> pair = (Pair<?, ?>) o;

  }
