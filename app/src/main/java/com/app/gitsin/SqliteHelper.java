package com.app.gitsin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    final String TAG = "SQLITE3 GITSIN";

    public SqliteHelper(@Nullable Context context) {
        super(context, "gitsin", null, 1);
        Log.d(TAG, "CALLED DATABASE gitsin");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // gitsin 데이터베이스에 table이 존재하는지 검색
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM sqlite_master WHERE type='table'", null);
        Log.d(TAG, "table cursor: " + cursor.getCount());
        // 아무 테이블이 없을 때 기본값이 1인듯
        if (cursor.getCount() == 1) {
            // 업적 테이블 생성 + insert
            achTableCreate(sqLiteDatabase);
        }
        cursor.close();
    }

    // 업적 테이블 관련
    private void achTableCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE achievement (achDrawble char(17), achTitle char(20), achDetail char(50), achMax INTEGER)");
        Log.d(TAG, "CREATED TABLE achievement");
        String achSQL = "INSERT INTO achievement VALUES " +
                "('hof_01', '귀농', '모내기 1회', 1)," +
                "('hof_02', '초보 농사꾼', '10일 연속 모내기 달성', 10)," +
                "('hof_03', '프로 농장주', '100일 연속 모내기 달성', 100)," +
                "('hof_04', '친구칭긔', '친구 1명 추가하기', 1)," +
                "('hof_05', '팀 프로젝트', '친구 5명 추가하기', 5)," +
                "('hof_06', '깃신 인싸', '친구 20명 추가하기', 20)";
        sqLiteDatabase.execSQL(achSQL);
        Log.d(TAG, "VALUES INSERTED INTO achievement");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
