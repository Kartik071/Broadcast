package com.example.broadcastrec;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Contacts extends BaseObservable {
    private String name;

    public Contacts(String name){
        this.name=name;
    }
@Bindable
    public String getName() {
        return name;
    }
@Bindable
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
