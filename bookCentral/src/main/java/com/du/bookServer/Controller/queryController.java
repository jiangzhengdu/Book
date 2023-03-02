package com.du.bookServer.Controller;

import com.du.bookServer.RESTful.Result.RESTfulResponse;
import com.du.bookServer.RESTful.Result.ResponseData;
import com.du.bookServer.Service.BookService;
import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/book/getBookList")
public class queryController {
    @Reference
    BookDubboService bookDubboService;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("")
    public RESTfulResponse getBookList() {
//        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/", ResponseData.class);
//        System.out.println(responseEntity);
//        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookList();
//        ResponseData responseRESTful = responseEntity.getBody();
//        List<BookDomain> listRPC = responseRPC.getData();
//        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
//        listRESTful.addAll(listRPC);
//        return new ResponseData(listRESTful);
        return bookService.getBookList();
    }
    @Autowired
    BookService bookService;
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public RESTfulResponse getBookByName(@PathVariable("name") String name) {
//        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/name/{1}", ResponseData.class, name);
//        System.out.println("restful result!-----");
//        System.out.println(responseEntity);
//        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookByName(name);
//        System.out.println("rpc result!-----");
//        System.out.println(responseRPC);
//        ResponseData responseRESTful = responseEntity.getBody();
//        List<BookDomain> listRPC = responseRPC.getData();
//        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
//        listRESTful.addAll(listRPC);
//        return new ResponseData(listRESTful);
        return bookService.getBookByName(name);
    }

    @RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
    public RESTfulResponse getBookByAuthor(@PathVariable("author") String author) {
        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/author/{1}", ResponseData.class, author);
        System.out.println("restful result!-----");
        System.out.println(responseEntity);
        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookByAuthor(author);
        System.out.println("rpc result!-----");
        System.out.println(responseRPC);
        ResponseData responseRESTful = responseEntity.getBody();
        List<BookDomain> listRPC = responseRPC.getData();
        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
        listRESTful.addAll(listRPC);
        return new ResponseData(listRESTful);
    }





}
