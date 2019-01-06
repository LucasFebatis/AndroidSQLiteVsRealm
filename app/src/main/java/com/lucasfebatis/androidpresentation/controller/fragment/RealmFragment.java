package com.lucasfebatis.androidpresentation.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasfebatis.androidpresentation.R;
import com.lucasfebatis.androidpresentation.controller.adapter.MyAdapter;
import com.lucasfebatis.androidpresentation.realm.PostObject;
import com.lucasfebatis.androidpresentation.realm.RealmHelper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmFragment extends Fragment {

    Realm realm;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_base, container, false);

         realm = RealmHelper.obterBanco();

        excluirTodos();

         inserir(1L);
         inserir(2L);
         inserir(3L);

        atualizar(2L);

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

    void inserir(Long id) {
        realm.beginTransaction();

        PostObject postObject = new PostObject();
        postObject.id = id;
        postObject.titulo = "Titulo do Post";
        postObject.subtitulo = "Subtitulo do Post";

        realm.insert(postObject);

        postObject.subtitulo = "Subtitulo do Post 6";

        realm.commitTransaction();
    }

    List ler() {

        realm.beginTransaction();

        final RealmResults<PostObject> postObjects = realm.where(PostObject.class).findAll();

        realm.commitTransaction();

        return postObjects;

    }

    void atualizar(Long id) {
        realm.beginTransaction();

        final RealmResults<PostObject> postObjects = realm.where(PostObject.class).equalTo("id", id).findAll();

        for(PostObject postObject : postObjects) {
            postObject.titulo = "Titulo do Post 2";
        }

        realm.commitTransaction();
    }

    void excluirTodos() {

        realm.beginTransaction();

        final RealmResults<PostObject> postObjects = realm.where(PostObject.class).findAll();

        for(PostObject postObject : postObjects) {
            postObject.deleteFromRealm();
        }

        realm.commitTransaction();

    }

}