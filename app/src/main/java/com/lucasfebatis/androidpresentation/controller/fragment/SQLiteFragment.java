package com.lucasfebatis.androidpresentation.controller.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasfebatis.androidpresentation.R;
import com.lucasfebatis.androidpresentation.controller.adapter.MyAdapter;
import com.lucasfebatis.androidpresentation.sqlite.PostContract;
import com.lucasfebatis.androidpresentation.sqlite.PostDbHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteFragment extends Fragment {

    PostDbHelper mDbHelper;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_base, container, false);

        mDbHelper = new PostDbHelper(getActivity());

        excluirTodos();

        inserir();
        inserir();
        inserir();

        List list = ler();

        setupRecyclerView(view, list);

        return view;
    }

    private void setupRecyclerView(View view, List list) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }

    void inserir() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PostContract.PostEntry.COLUMN_NAME_TITLE, "Titulo do Post");
        values.put(PostContract.PostEntry.COLUMN_NAME_SUBTITLE, "Subtitulo do Post");
        long newRowId = db.insert(PostContract.PostEntry.TABLE_NAME, null, values);
    }

    List ler() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                PostContract.PostEntry._ID,
                PostContract.PostEntry.COLUMN_NAME_TITLE,
                PostContract.PostEntry.COLUMN_NAME_SUBTITLE
        };

        String selection = PostContract.PostEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "Titulo do Post" };
        String sortOrder =
                PostContract.PostEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = db.query(
                PostContract.PostEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        List postObjects = new ArrayList<>();
        while(c.moveToNext()) {
            PostContract.PostObject postObject = new PostContract.PostObject();

            long itemId = c.getLong(
                    c.getColumnIndexOrThrow(PostContract.PostEntry._ID));

            String itemTitulo = c.getString(
                    c.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_NAME_TITLE));

            String itemSubTitulo = c.getString(
                    c.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_NAME_SUBTITLE));

            postObject.id = itemId;
            postObject.titulo = itemTitulo;
            postObject.subtitulo = itemSubTitulo;

            postObjects.add(postObject);

        }

        c.close();


        return postObjects;

    }

    void atualizar() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(PostContract.PostEntry.COLUMN_NAME_TITLE, "Novo Titulo do Post");
        String selection = PostContract.PostEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "Titulo do Post" };
        int count = db.update(
                PostContract.PostEntry.TABLE_NAME ,
                values,
                selection,
                selectionArgs);
    }

    void excluir() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection = PostContract.PostEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { "Titulo do Post" };
        db.delete(PostContract.PostEntry.TABLE_NAME, selection, selectionArgs);
    }

    void excluirTodos() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String dropQuery = "DELETE FROM " + PostContract.PostEntry.TABLE_NAME;
        db.execSQL(dropQuery);
    }

}