import java.util.*;

/**
 *
 * @param <K> Key
 * @param <V> Value
 *
 * @author Yash Vyas
 */
public class LRUCache<K, V> {

    private int size;
    private Map<K, V> map;
    private LimitedSizeQueue<V> queue;

    /**
     * Constructor
     * @param size
     */
    public LRUCache(int size) {
        this.size = size;
        queue = new LimitedSizeQueue<>(size);
        map = new HashMap<>();
    }

    /**
     * Set element in the cache.
     * This would remove the element from the cache which
     * has been not used in the longest time.
     * @param k Key
     * @param v Value
     */
    public void set(K k, V v) {
        if (k == null && v == null) return;
        // create space by removing older element
        if (queue.size() > 0) {
            V el = queue.getLeastUsed();
            map.remove(el);
        }
        map.put(k, v);
        queue.add(v);
    }

    /**
     * Get the element from the cache.
     * @param k Key
     * @return v value
     */
    public V get(K k) {
        if (map.containsKey(k)) {
            V v = map.get(k);
            // Update queue
            queue.remove(v);
            queue.add(v);
        }
        return null;
    }

    /**
     * Returns most recently used element.
     * @return
     */
    public V getMostRecentlyUsedElement() {
        return this.queue.getMostRecentlyUsed();
    }

    public int getCacheSize() {
        return this.getCacheAsList().size();
    }

    public void clearCache() {
        if (this.queue.size() == 0 && map.size() == 0) return;
        this.queue = new LimitedSizeQueue<>(size);
        this.map = new HashMap<>();
    }

    public List<V> getCacheAsList() {
        return Collections.unmodifiableList(this.queue);
    }
}

/**
 * Internal queue which retains the order of the element
 * by usage.
 * @param <V>
 */
class LimitedSizeQueue<V> extends ArrayList<V> {

    private int maxSize;

    public LimitedSizeQueue(int size){
        this.maxSize = size;
    }

    public boolean add(V v){
        boolean r = super.add(v);
        if (size() > maxSize){
            removeRange(0, size() - maxSize);
        }
        return r;
    }

    public V getMostRecentlyUsed() {
        return get(size() - 1);
    }

    public V getLeastUsed() {
        return get(0);
    }
}
