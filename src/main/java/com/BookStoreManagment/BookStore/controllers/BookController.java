package com.BookStoreManagment.BookStore.controllers;

import com.BookStoreManagment.BookStore.entity.Book;
import com.BookStoreManagment.BookStore.entity.MyBookList;
import com.BookStoreManagment.BookStore.service.BookService;
import com.BookStoreManagment.BookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")
    public String home(){
        return  "home";
    }

    @GetMapping("/book_register")
    public String bookRegister()
    {
        return "bookRegister";
    }


    @GetMapping("/available_books")
    public ModelAndView getAllBook()
    {
        List<Book> list=service.getAllBook();
//        ModelAndView m = new ModelAndView();
//        m.setViewName("bookList");
//        m.addObject("book",list);
        return  new ModelAndView("bookList","book",list);
    }

@PostMapping("/save")
    public  String addBook(@ModelAttribute Book b)
{
    service.save(b);
    return  "redirect:/available_books";
}

@GetMapping("/my_books")
    public String getMyBook(Model model)
{
    List<MyBookList> list=myBookService.getAllMyBooks();
//    model.addAttributes("book",list);
    model.addAttribute("book",list);
    return "myBooks";
}

@RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
Book b = service.getBookById(id);
MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
    myBookService.SaveMyBook(mb);
        return  "redirect:/my_books";
}
@RequestMapping("/editBook/{id}")
public String editBook(@PathVariable("id")int id,Model model){
Book b = service.getBookById(id);
model.addAttribute("book",b);
        return "bookEdit";
}

@RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id)
{
    service.deleteById(id);
    return "redirect:/available_books";
}



}
