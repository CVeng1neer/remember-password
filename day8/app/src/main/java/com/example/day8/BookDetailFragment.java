package com.example.day8;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BookDetailFragment extends Fragment {
    public static final String ITEM_ID = "item_id";
    //保存该Fragment显示的book对象
    BookContent.Book book;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            setArguments(savedInstanceState);
        }
        if (getArguments() != null && getArguments().containsKey(ITEM_ID)) {
            book = BookContent.ITEM_MAP.get(getArguments().getInt(ITEM_ID));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStats){
        View rootView = inflater.inflate(R.layout.fragment_book_detail,container,false);
        if(book !=null){
            ((TextView) rootView.findViewById(R.id.book_title)).setText(book.title);
            ((TextView) rootView.findViewById(R.id.book_description)).setText(book.description);
        }
        return rootView;
    }
}
