package com.example.day8;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.day8.BookDetailFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // 获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // 开启一个FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 获取布局容器数组
        int[] containerIds = {R.id.fragment_container1, R.id.fragment_container2, R.id.fragment_container3};

        for (int i = 0; i < BookContent.ITEMS.size(); i++) {
            BookContent.Book book = BookContent.ITEMS.get(i);

            // 创建一个BookDetailFragment实例
            BookDetailFragment fragment = new BookDetailFragment();

            // 创建bundle，向fragment传入参数
            Bundle arguments = new Bundle();
            arguments.putInt(BookDetailFragment.ITEM_ID, book.id);
            fragment.setArguments(arguments);

            // 将BookDetailFragment添加到布局中
            transaction.add(containerIds[i % containerIds.length], fragment);
        }
        // 提交事务
        transaction.commit();
    }
}