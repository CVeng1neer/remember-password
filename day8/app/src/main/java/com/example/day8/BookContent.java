package com.example.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {
    public static class Book {
        public Integer id;
        public String title;
        public String description;

        public Book(Integer id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
        }

        public String toString() {
            return title;
        }
    }

    public static List<Book> ITEMS = new ArrayList<Book>();
    public static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, Book>();

    static {
        addItem(new Book(1, "疯狂Java讲义", "一本全面、深入的Java学习图书"));
        addItem(new Book(2, "疯狂Android讲义", "Android学习者的首选图书，常年占据电商平台销售榜榜首"));
        addItem(new Book(3, "Kotlin实战", "实战讲解kotlin项目"));
    }
    public static void addItem(Book book){
        ITEMS.add(book);
        ITEM_MAP.put(book.id,book);
    }
}
