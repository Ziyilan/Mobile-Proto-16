package jason.hw3;

import android.provider.BaseColumns;

/**
 * Created by zlan on 9/26/16.
 */
public class todoSchema {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private todoSchema() {}

    /* Inner class that defines the table contents */
    public static class todoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todoTable";
        public static final String columnOne = "content";
        public static final String columnTwo = "status";
    }
}
