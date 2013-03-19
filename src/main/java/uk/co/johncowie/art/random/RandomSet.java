package uk.co.johncowie.art.random;

import java.util.*;

public class RandomSet<T> {

    private final Random random;
    private final Map<T, Integer> map = new HashMap<T, Integer>();
    private final List<T> array = new ArrayList<T>();

    public RandomSet(String seed) {
        this.random = new Random(seed.hashCode());
    }

    public void insert(T val) {
        if(!map.containsKey(val)) {
            array.add(val);
            map.put(val, array.size() - 1);
        }
    }

    public T removeRandom() {
        T t = getRandom();
        remove(t);
        return t;
    }

    public T getRandom() {
        return array.get(randomInt());
    }

    private int randomInt() {
        return (int)(Math.floor(random.nextDouble()*array.size()));
    }

    public void remove(T t) {
        if(map.containsKey(t)) {
            int pos = map.remove(t);
            T moved = array.get(array.size()-1);
            if (!moved.equals(t)) {
                array.set(pos, moved);
                map.put(moved, pos);
            }
            array.remove(array.size()-1);
        } else {
            throw new RuntimeException(t + " doesn't exist!");
        }
    }

    public boolean contains(T val) {
        return map.containsKey(val);
    }

    public int size() {
        return map.size();
    }

    public List<T> getList() {
        return new ArrayList<T>(array);
    }

    public Map<T, Integer> getMap() {
        return new HashMap<T, Integer>(map);
    }

    @Override
    public String toString() {
        return array.toString();
    }

}
