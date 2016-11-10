package com.example.views;

import java.nio.charset.Charset;

import io.dropwizard.views.View;

/**
 * Created by justin on 06.07.15.
 */
public class EntryView extends View {

    public EntryView() {
        super("entry.ftl", Charset.forName("UTF-8"));
    }
}