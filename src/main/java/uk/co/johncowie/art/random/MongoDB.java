package uk.co.johncowie.art.random;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class MongoDB {

    Datastore ds;

    private static MongoDB instance;

    private MongoDB(String db) throws Exception {
        ds = new Morphia().createDatastore(new Mongo("localhost", 27017), db);
    }

    public static MongoDB getInstance(String db) {
        if(instance == null) {
            try {
                instance = new MongoDB(db);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Datastore getDS() {
        return ds;
    }

}
