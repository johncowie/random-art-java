package uk.co.johncowie.art.random;

import java.util.*;

public class RandomSet<T> implements Collection<T> {

    private final Random random;
    private final Map<T, Integer> map = new HashMap<T, Integer>();
    private final List<T> array = new ArrayList<T>();

    public RandomSet(String seed) {
        this.random = new Random(seed.hashCode());
    }

    public boolean add(T val) {
        if(!map.containsKey(val)) {
            array.add(val);
            map.put(val, array.size() - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addAll(Collection<? extends T> ts) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
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

    @Override
    public boolean remove(Object t) {
        if(map.containsKey(t)) {
            int pos = map.remove(t);
            T moved = array.get(array.size()-1);
            if (!moved.equals(t)) {
                array.set(pos, moved);
                map.put(moved, pos);
            }
            array.remove(array.size()-1);
            return true;
        }
        return false;
    }

    public boolean contains(Object val) {
        return map.containsKey(val);
    }

    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return array.toArray(ts);
    }

    @Override
    public String toString() {
        return array.toString();
    }

}
