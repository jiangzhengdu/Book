package com.du.bookServer.Service;

import com.du.bookServer.domain.Book;
import com.du.bookServer.RESTful.Result.RESTfulResponse;
import com.du.bookServer.RESTful.Result.ResponseData;
import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import com.du.bookServer.domain.LoginUser;
import com.du.bookServer.domain.User;
import com.du.bookServer.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "books")
public class BookService {
    @Reference
    BookDubboService bookDubboService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private UserMapper userMapper;

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

    @Transactional(rollbackFor=Exception.class)
    public RESTfulResponse updateBook(Book book) {
        if (!(book.getSource() == 1 || book.getSource() == 0)) {
            return new RESTfulResponse("500", "book source wrong!");
        }

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        User user = userMapper.queryById(id.intValue());
        if (user.getBalance() < book.getPrice()) {
            return new RESTfulResponse("400", "Insufficient balance! failed!");
        }

        deleteCache();
//        book.setInventory(book.getInventory() - 1);
        // the book is from RESTful
        if (book.getSource() == 0) {
            ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/{1}", ResponseData.class, book.getId());
            Map map =(Map)responseEntity.getBody().getData();

            Book bookNew = new Book((Integer)map.get("id"),(String)map.get("name"), (String)map.get("author"),(double)map.get("price"),(Integer)map.get("inventory")- 1,(Integer)map.get("source"));
            user.setBalance(user.getBalance() - bookNew.getPrice());

            restTemplate.put("http://localhost:8081/RESTfulbook/{1}",bookNew, bookNew.getId());
        }
        // the book is from RPC
        else if (book.getSource() == 1) {
            RpcResult<BookDomain> rpcResult = bookDubboService.getBookById(book.getId());
            BookDomain bookDomain = rpcResult.getData();
            bookDomain.setInventory(bookDomain.getInventory() - 1);
            user.setBalance(user.getBalance() - bookDomain.getPrice());
//            BookDomain bookDomain = new BookDomain(book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getInventory(), book.getSource());
            bookDubboService.updateBook(bookDomain);
        }
        userMapper.updateUser(user);
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey("books::getBookList"))) {
            stringRedisTemplate.delete("books::getBookList");
        }
        // delay delete
        try{
            Thread.sleep(100);
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
