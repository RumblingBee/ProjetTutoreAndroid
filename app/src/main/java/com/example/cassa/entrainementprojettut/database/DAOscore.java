package com.example.cassa.entrainementprojettut.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.cassa.entrainementprojettut.PlayerUtils.Score;
import com.example.cassa.entrainementprojettut.database.DBOpenHelper.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 07/02/18.
 */

public class DAOscore extends DAOconnection {

    private static DAOscore instance;


    private DAOscore(Context context) {
        super(context);
    }

    public static DAOscore getInstance(Context context){
        if(instance==null){
            instance=new DAOscore(context);
        }
        return instance;
    }

    public Score findScoreForAGame(String gameName,int difficulty){
        Score res=null;
        String query="select player, score "+
                " from "+Constants.FOURTH_TABLE+" score "+
                " JOIN "+Constants.FIRST_TABLE+" game on game.idGame=score.idGame "+
                " WHERE "+Constants.COL_GAME_NAME_TABLE_1 +" =? and "+Constants.COL_DIFFICULTY_TABLE_1+"=?";
        Log.d("score",query);
        Cursor c=sqLiteDatabase.rawQuery(query,new String[]{gameName,String.valueOf(difficulty)});
        while (c.moveToNext()){
            String playerName=c.getString(0);
            long score=c.getLong(1);
            res=new Score(gameName,playerName,score,difficulty);
            for (int i = 0; i<c.getColumnCount(); i++) {
                Log.d("score_db_col", c.getColumnName(i));
            }
        }
        c.close();
        return res;
    }

    private void updateScore(Score score){
        ContentValues value = new ContentValues();

         value.put(Constants.COL_SCORE_TABLE_4, score.getValue());
         value.put(Constants.COL_PLAYER_TABLE_4,score.getPlayerName());
         sqLiteDatabase.update(Constants.FOURTH_TABLE,
                 value,
                 Constants.COL_GAME_NAME_TABLE_1 + " = ? and "+Constants.COL_DIFFICULTY_TABLE_1+" = ?",
                 new String[] {score.getGameName(),String.valueOf(score.getDifficulty())});
    }

    public void timeScoreBreaked(Score score){
        Score actualScore=findScoreForAGame(score.getGameName(),score.getDifficulty());
        if(actualScore.getValue()>score.getValue() || actualScore.getValue()==0){
            updateScore(score);
        }
    }

    public void pointScoreBreaked(Score score){
        Score actualScore=findScoreForAGame(score.getGameName(),score.getDifficulty());
        if(actualScore.getValue()<score.getValue() || actualScore.getValue()==0){
            updateScore(score);
        }
    }
}
