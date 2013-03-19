package uk.co.johncowie.art.random;

import org.junit.Test;

import static junit.framework.Assert.*;

public class RandomSetTest {

    @Test
    public void aTest() {
        RandomSet<Integer> rs = new RandomSet<Integer>("bob");
        for(int i = 1; i < 10001; i++) {
            rs.insert(i);
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

        rs.insert("a");
        checkSize(rs, 1);
        assertTrue(rs.contains("a"));

        rs.insert("b");
        checkSize(rs, 2);
        assertTrue(rs.contains("b"));
    }

    @Test
    public void testRemove() {
        RandomSet<String> rs = new RandomSet<String>("sdf");
        rs.insert("a");
        rs.remove("a");
        rs.insert("a");
        rs.insert("b");
        rs.insert("c");

        rs.remove("b");
        checkSize(rs, 2);

        assertFalse(rs.contains("b"));
        assertTrue(rs.contains("a"));
        assertTrue(rs.contains("c"));
    }

    @Test
    public void testRemoveRandom() {
        RandomSet<String> rs = new RandomSet<String>("sdf");
        rs.insert("a");
        rs.insert("b");
        rs.insert("c");
        System.out.println(rs);
        System.out.println(rs.getMap());
        rs.removeRandom();
        System.out.println(rs);
        System.out.println(rs.getMap());
        rs.removeRandom();
        System.out.println(rs);
        System.out.println(rs.getMap());
        rs.removeRandom();
        System.out.println(rs);
        System.out.println(rs.getMap());
        checkSize(rs, 0);
    }

    private void checkSize(RandomSet<?> rs, int size) {
        assertEquals(size, rs.size());
        assertEquals(size, rs.getList().size());
        assertEquals(size, rs.getMap().size());
    }

}
