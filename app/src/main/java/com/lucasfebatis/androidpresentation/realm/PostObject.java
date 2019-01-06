package com.lucasfebatis.androidpresentation.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PostObject extends RealmObject {

    @PrimaryKey
    public Long id;

    public String titulo;
    public String subtitulo;
}
