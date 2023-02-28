package com.du.book.RESTful.controller;

import com.du.book.RESTful.mapper.BookMapper;
import com.du.book.RESTful.model.Book;
import com.du.book.RESTful.result.ExceptionMsg;
import com.du.book.RESTful.result.Response;
import com.du.book.RESTful.result.ResponseData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/RESTfulbook")
public class BookController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }
    @Autowired
    BookMapper bookMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getBookList() throws IOException {
        List<Book> books = bookMapper.queryAll();
        return  new ResponseData(ExceptionMsg.SUCCESS, books);
    }
    @ApiOperation(value="id", notes = "get book by id")
    @ApiImplicitParam(name = "id", value = "book id", required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData findBook(@PathVariable("id")Integer id) throws IOException {
        Book book = bookMapper.queryById(id);
        if (book != null) {
            return new ResponseData(ExceptionMsg.SUCCESS, book);
        }
        else {
            return new ResponseData(ExceptionMsg.FAILED, book);
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseData findBookByName(@PathVariable("name") String name) throws IOException {
        List<Book> books = bookMapper.queryByName(name);
        if (books != null) {
            return new ResponseData(ExceptionMsg.SUCCESS, books);
        }
        else {
            return new ResponseData(ExceptionMsg.FAILED, books);
        }
    }

    /**
     * add book
     * @param book
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseData add(Book book){
        System.out.println(book);
        bookMapper.add(book);
//        System.out.println(book);
        return new ResponseData(ExceptionMsg.SUCCESS, book);
    }

    /**
     *  Update book
     * @param book
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseData update(@RequestBody Book book){
        bookMapper.updateById(book);
        return new ResponseData(ExceptionMsg.SUCCESS, book);
    }
}
