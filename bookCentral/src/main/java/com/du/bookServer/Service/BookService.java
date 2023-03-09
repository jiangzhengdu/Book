package com.du.bookServer.Service;

import com.du.bookServer.domain.Book;
import com.du.bookServer.RESTful.Result.RESTfulResponse;
import com.du.bookServer.RESTful.Result.ResponseData;
import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "books")
public class BookService {
    @Reference
    BookDubboService bookDubboService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private RestTemplate restTemplate = new RestTemplate();

    @Cacheable(key = "#root.methodName")
    public RESTfulResponse getBookList() {
        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/", ResponseData.class);
        System.out.println(responseEntity);
        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookList();
        ResponseData responseRESTful = responseEntity.getBody();
        List<BookDomain> listRPC = responseRPC.getData();
        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
        listRESTful.addAll(listRPC);
        return new ResponseData(listRESTful);
    }
    @Cacheable(key = "T(String).valueOf(#root.methodName).concat('-').concat(#name)")
    public RESTfulResponse getBookByName(String name) {
        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/name/{1}", ResponseData.class, name);
//        System.out.println("restful result!-----");
//        System.out.println(responseEntity);
        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookByName(name);
//        System.out.println("rpc result!-----");
//        System.out.println(responseRPC);
        ResponseData responseRESTful = responseEntity.getBody();
        List<BookDomain> listRPC = responseRPC.getData();
        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
        listRESTful.addAll(listRPC);
//        System.out.println("this is result!");
//        System.out.println(new ResponseData(listRESTful));
        return new ResponseData(listRESTful);
    }
    @Cacheable(key = "T(String).valueOf(#root.methodName).concat('-').concat(#author)")
    public RESTfulResponse getBookByAuthor(String author) {
        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/author/{1}", ResponseData.class, author);
//        System.out.println("restful result!-----");
//        System.out.println(responseEntity);
        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookByAuthor(author);
//        System.out.println("rpc result!-----");
//        System.out.println(responseRPC);
        ResponseData responseRESTful = responseEntity.getBody();
        List<BookDomain> listRPC = responseRPC.getData();
        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
        listRESTful.addAll(listRPC);
        return new ResponseData(listRESTful);
    }

    public RESTfulResponse updateBook(Book book) {
        if (!(book.getSource() == 1 || book.getSource() == 0)) {
            return new RESTfulResponse("500", "book source wrong!");
        }
        deleteCache();
        // the book is from RESTful
        if (book.getSource() == 0) {
            restTemplate.put("http://localhost:8081/RESTfulbook/{1}",book, book.getId());
        }
        // the book is from RPC
        else if (book.getSource() == 1) {
            BookDomain bookDomain = new BookDomain(book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getInventory(), book.getSource());
            bookDubboService.updateBook(bookDomain);
        }
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey("books::getBookList"))) {
            stringRedisTemplate.delete("books::getBookList");
        }
        // delay delete
        try{
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        deleteCache();
        return new RESTfulResponse("200", "book update success!");
    }

    public void deleteCache() {
        // first delete getBookList key
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey("books::getBookList"))) {
            stringRedisTemplate.delete("books::getBookList");
        }
        Set<String> nameKey = stringRedisTemplate.keys("books::getBookByName-"  + "*");
        if (nameKey != null && nameKey.size() > 0) {
            stringRedisTemplate.delete(nameKey);
        }

        Set<String> authorKey = stringRedisTemplate.keys("books::getBookByAuthor-"  + "*");
        if (authorKey != null && authorKey.size() > 0) {
            stringRedisTemplate.delete(authorKey);
        }
    }
}
