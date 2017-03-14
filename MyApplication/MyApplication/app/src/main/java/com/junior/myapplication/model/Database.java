package com.junior.myapplication.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.junior.myapplication.App;
import com.junior.myapplication.model.entity.Stories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Admin on 02/10/2017.
 */

public class Database {
    private static final String DATABASE_PATH = App.getContext().getApplicationInfo().dataDir +
            "/databases/";

    private static final String DATABASE_NAME = "Story.db";

    private static final String TABLE_NAME_STORY = "stories";
    private static final String COLUMN_STORIES_ID = "id";
    private static final String COLUMN_STORIES_NAME = "name";
    private static final String COLUMN_STORIES_AUTHOR = "author";
    private static final String COLUMN_STORIES_CONTENT = "content";
    private static final String COLUMN_STORIES_FAVORITE = "favorite";

    private SQLiteDatabase sqLiteDatabase;

    public Database() {
    }

    public void copyDataBase() {
        try {

            File file = new File(DATABASE_PATH + DATABASE_NAME);

            if (file.exists()) {
                return;
            } else {
                File folder = new File(DATABASE_PATH);

                if (!folder.exists()) {
                    folder.mkdir();
                }

                InputStream input = App.getContext().getAssets().open(DATABASE_NAME);
                OutputStream output = new FileOutputStream(file);
                byte[] bytes = new byte[1024];

                int length;
                while ((length = input.read(bytes)) > 0) {
                    output.write(bytes, 0, length);
                }
                output.flush();
                input.close();
                output.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATABASE_PATH +
                    DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }

    }

    public void closeDataBase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public long insertStory(Stories stories) {
        openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STORIES_NAME, stories.getName());
        contentValues.put(COLUMN_STORIES_AUTHOR, stories.getAuthor());
        contentValues.put(COLUMN_STORIES_CONTENT, stories.getContent());
        contentValues.put(COLUMN_STORIES_FAVORITE, stories.getFavorite());

        long storyId = sqLiteDatabase.insert(TABLE_NAME_STORY, null, contentValues);

        closeDataBase();
        return storyId;
    }

    public void deleteStory(long storyID) {
        openDatabase();
        sqLiteDatabase.delete(TABLE_NAME_STORY, "id=?", new String[]{String.valueOf(storyID)});
        closeDataBase();
    }

    public void UpdateStory(Stories stories) {
        openDatabase();
        String whereArgs = String.valueOf(stories.getId());
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STORIES_NAME, stories.getName());
        contentValues.put(COLUMN_STORIES_AUTHOR, stories.getAuthor());
        contentValues.put(COLUMN_STORIES_CONTENT, stories.getContent());
        contentValues.put(COLUMN_STORIES_FAVORITE, stories.getFavorite());

        sqLiteDatabase.update(TABLE_NAME_STORY, contentValues, "id=?",
                new String[]{whereArgs});
        closeDataBase();
    }

    public ArrayList<Stories> getStories() {
        openDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME_STORY
                + " ORDER BY " + COLUMN_STORIES_NAME + " ASC ";
        // + COLUMN_STORIES_FAVORITE + " DESC";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor == null) {
            closeDataBase();
            return null;
        }

        ArrayList<Stories> stories = new ArrayList<>();
        if (cursor.getCount() == 0) {
            closeDataBase();
            cursor.close();
            return stories;
        }

        int indexID = cursor.getColumnIndex(COLUMN_STORIES_ID);
        int indexName = cursor.getColumnIndex(COLUMN_STORIES_NAME);
        int indexAuthor = cursor.getColumnIndex(COLUMN_STORIES_AUTHOR);
        int indexContent = cursor.getColumnIndex(COLUMN_STORIES_CONTENT);
        int indexFavorite = cursor.getColumnIndex(COLUMN_STORIES_FAVORITE);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(indexID);
            String name = cursor.getString(indexName);
            String author = cursor.getString(indexAuthor);
            String content = cursor.getString(indexContent);
            int favorite = cursor.getInt(indexFavorite);

            stories.add(new Stories(id, name, author, content, favorite));
            cursor.moveToNext();
        }
        cursor.close();
        closeDataBase();
        return stories;
    }

}