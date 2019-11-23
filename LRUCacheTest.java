import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class LRUCacheTest {

    LRUCache<String, String> cache;

    @Before
    public void setUp() throws Exception {
        cache = new LRUCache<>(3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_set() {
        cache.set("hello", "world");
        cache.set("jack", "reacher");
        cache.set("tom", "jerry");
        cache.set("will", "smith");
        Assert.assertEquals(null, cache.get("hello"));
    }

    @Test
    public void test_getCacheSize() {
        cache.set("hello", "world");
        cache.set("jack", "reacher");
        cache.set("tom", "jerry");
        cache.set("will", "smith");
        Assert.assertEquals(cache.getCacheSize(), 3);
    }

    @Test
    public void test_clearCache() {
        cache.set("hello", "world");
        cache.set("jack", "reacher");
        cache.set("tom", "jerry");
        Assert.assertEquals(cache.getCacheSize(), 3);
        cache.clearCache();
        Assert.assertEquals(cache.getCacheSize(), 0);
    }

    @Test
    public void test_mru() {
        cache.set("hello", "world");
        cache.set("jack", "reacher");
        cache.set("tom", "jerry");
        cache.set("will", "smith");

        cache.get("tom");
        cache.get("jack");
        cache.get("tom");
        Assert.assertEquals(cache.getMostRecentlyUsedElement(), "jerry");
    }
}
