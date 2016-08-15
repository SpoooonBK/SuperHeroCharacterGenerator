package net.estebanrodriguez.superherocharactergenerator;

/**
 * Created by Tara on 8/11/2016.
 */
public class DieRoller {

    public static int roll(int die){

        return (int)(Math.random()*die) + 1;
    }
}
