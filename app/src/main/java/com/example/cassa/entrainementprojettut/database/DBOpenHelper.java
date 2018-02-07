package com.example.cassa.entrainementprojettut.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by clement on 07/02/18.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    public static class Constants implements BaseColumns{

        public static final String DATABASE_NAME="androidGames.db";

        public static final int DATABASE_VERSION = 1;

        public static final String FIRST_TABLE = "Games";

        public static final String COL_ID_TABLE_1 = "idGame";

        public static final String COL_NAME_TABLE_1 = "gameName";

        public static final String SECOND_TABLE = "Difficulties";

        public static final String COL_ID_TABLE_2 = "idDifficulty";

        public static final String THIRD_TABLE = "gameSet";

        public static final String COL_ID_TABLE_3 = "idGameSet";

        public static final String COL_GAME_TABLE_3 = "idGame";

        public static final String COL_DIFFICULTY_TABLE_3 = "idDifficulty";

        public static final String FOURTH_TABLE = "Score";

        public static final String COL_ID_TABLE_4= "idScore";

        public static final String COL_PLAYER_TABLE_4 = "player";

        public static final String COL_SCORE_TABLE_4 = "score";

        public static final String COL_GAMESET_TABLE_4 = "idGameSet";

        private static final String DATABASE_CREATE_FIRST_TABLE = "create table "
                + Constants.FIRST_TABLE + "(" + Constants.COL_ID_TABLE_1
                + " integer primary key autoincrement, " + Constants.COL_NAME_TABLE_1
                + " TEXT) ";

        private static final String DATABASE_CREATE_SECOND_TABLE = "create table "
                + Constants.SECOND_TABLE + "(" + Constants.COL_ID_TABLE_2
                + " integer primary key autoincrement)";

        private static final String DATABASE_CREATE_THIRD_TABLE = "create table "
                + Constants.THIRD_TABLE + "(" + Constants.COL_ID_TABLE_3
                + " integer primary key autoincrement, " + Constants.COL_GAME_TABLE_3
                + " INTEGER, "+Constants.COL_DIFFICULTY_TABLE_3+"INTEGER," +
                "FOREIGN KEY("+Constants.COL_GAME_TABLE_3+") references "+Constants.FIRST_TABLE+"("+
                Constants.COL_ID_TABLE_1+")," +
                "FOREIGN KEY("+Constants.COL_DIFFICULTY_TABLE_3+") references "+Constants.SECOND_TABLE+"("+
                Constants.COL_ID_TABLE_2+")) ";

        private static final String DATABASE_CREATE_FOURTH_TABLE = "create table "
                + Constants.FOURTH_TABLE + "(" + Constants.COL_ID_TABLE_4
                + " integer primary key autoincrement, " + Constants.COL_GAMESET_TABLE_4
                + " INTEGER, "+Constants.COL_PLAYER_TABLE_4+"TEXT DEFAULT 'DEV',"
                +Constants.COL_SCORE_TABLE_4+" INTEGER DEFAULT 0,"+
                "FOREIGN KEY("+Constants.COL_GAMESET_TABLE_4+") references "+Constants.THIRD_TABLE+"("+
                Constants.COL_ID_TABLE_3+"),";

        private static final String DATABASE_INIT_FIRST_TABLE="insert into "+FIRST_TABLE+
                "("+COL_NAME_TABLE_1+") VALUES ('operation')" +
                ",('MysteryWord'),('flag'),('invertFlag'),('geography'),('piano')";

        private static final String DATABASE_INIT_SECOND_TABLE="insert into "+SECOND_TABLE+
                "("+COL_ID_TABLE_2+") VALUES (default)" +
                ",(default),(default),(default),(default)";

        private static final String DATABASE_INIT_THIRD_TABLE="insert into "+THIRD_TABLE+
                "("+COL_GAME_TABLE_3+","+COL_DIFFICULTY_TABLE_3+") VALUES (1,1)" +
                ",(1,2),(1,3),(1,4),(1,5)," +
                "(2,1),(2,2),(2,3),(2,4),(2,5)," +
                "(3,1),(3,2),(3,3)," +
                "(4,1),(4,2),(4,3)," +
                "(5,1),(5,2),(5,3)," +
                "(6,1),(6,2),(6,3)";

        private static final String DATABASE_INIT_FOURTH_TABLE="insert into "+FOURTH_TABLE+
                "("+COL_GAMESET_TABLE_4+") VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12)," +
                "(13),(14),(15),(16),(17),(18),(19),(20),(21),(22)";

        private static final String DROP_FOURTH_TABLE="DROP TABLE IF EXISTS "+FOURTH_TABLE+";";
        private static final String DROP_THIRD_TABLE="DROP TABLE IF EXISTS "+THIRD_TABLE+";";
        private static final String DROP_SECOND_TABLE="DROP TABLE IF EXISTS "+SECOND_TABLE+";";
        private static final String DROP_FIRST_TABLE="DROP TABLE IF EXISTS "+FIRST_TABLE+";";
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Constants.DATABASE_CREATE_FIRST_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_CREATE_SECOND_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_CREATE_THIRD_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_CREATE_FOURTH_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_INIT_FIRST_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_INIT_SECOND_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_INIT_THIRD_TABLE);
        sqLiteDatabase.execSQL(Constants.DATABASE_INIT_FOURTH_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Constants.DROP_FOURTH_TABLE);
        sqLiteDatabase.execSQL(Constants.DROP_THIRD_TABLE);
        sqLiteDatabase.execSQL(Constants.DROP_FIRST_TABLE);
        sqLiteDatabase.execSQL(Constants.DROP_SECOND_TABLE);
    }
}
