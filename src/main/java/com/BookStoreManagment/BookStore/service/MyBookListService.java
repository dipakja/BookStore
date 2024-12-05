package com.BookStoreManagment.BookStore.service;

import com.BookStoreManagment.BookStore.Repository.MyBookRepository;
import com.BookStoreManagment.BookStore.entity.MyBookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

    @Autowired
    private MyBookRepository mybook;
    public void SaveMyBook(MyBookList book)
    {
        mybook.save(book);
    }
    public List<MyBookList> getAllMyBooks()
    {
        return mybook.findAll();
    }

    public void deleteById(int id)
    {
        mybook.deleteById(id);
    }
}
