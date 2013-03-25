package uk.co.johncowie.art.random.numbers;

/**
 * Created with IntelliJ IDEA.
 * User: jcowie
 * Date: 29/10/2012
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public interface NumberGenerator {
    public double getRandom(double min, double max);

    public int getRandomInt(int min, int max);

}
