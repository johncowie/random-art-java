package uk.co.johncowie.art.random;

import org.junit.Test;

import static junit.framework.Assert.*;

public class RandomSetTest {

    @Test
    public void aTest() {
        RandomSet<Integer> rs = new RandomSet<Integer>("bob");
        for(int i = 1; i < 10001; i++) {
            rs.add(i);
            if ((i % 2) == 0) {
                rs.removeRandom();
            }
        }
        assertEquals(rs.size(), 5000);
    }

    @Test
    public void testInsert() {
        RandomSet<String> rs = new RandomSet<String>("boisad");
        checkSize(rs, 0);

        rs.add("a");
        checkSize(rs, 1);
        assertTrue(rs.contains("a"));

        rs.add("b");
        checkSize(rs, 2);
        assertTrue(rs.contains("b"));
    }

    @Test
    public void testRemove() {
        RandomSet<String> rs = new RandomSet<String>("sdf");
        rs.add("a");
        rs.remove("a");
        rs.add("a");
        rs.add("b");
        rs.add("c");

        rs.remove("b");
        checkSize(rs, 2);

        assertFalse(rs.contains("b"));
        assertTrue(rs.contains("a"));
        assertTrue(rs.contains("c"));
    }

    @Test
    public void testRemoveRandom() {
        RandomSet<String> rs = new RandomSet<String>("sdf");
        rs.add("a");
        rs.add("b");
        rs.add("c");
        rs.removeRandom();
        rs.removeRandom();
        rs.removeRandom();
        checkSize(rs, 0);
    }

    private void checkSize(RandomSet<?> rs, int size) {
        assertEquals(size, rs.size());
    }

}
