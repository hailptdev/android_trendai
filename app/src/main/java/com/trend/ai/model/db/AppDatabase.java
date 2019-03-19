package com.trend.ai.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.trend.ai.model.data.Article;
import com.trend.ai.model.data.Source;

/**
 * @author ihsan on 12/19/17.
 */

@Database(entities = {Source.class, Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SourceDao sourceDao();

    public abstract ArticleDao articleDao();
}
