package com.du.bookServer.Service;

import com.du.bookServer.RESTful.Result.RESTfulResponse;
import com.du.bookServer.RESTful.Result.ResponseData;
import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@CacheConfig(cacheNames = "books")
public class BookService {
    @Reference
    BookDubboService bookDubboService;

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
        System.out.println("restful result!-----");
        System.out.println(responseEntity);
        RpcResult<List<BookDomain>> responseRPC = bookDubboService.getBookByName(name);
        System.out.println("rpc result!-----");
        System.out.println(responseRPC);
        ResponseData responseRESTful = responseEntity.getBody();
        List<BookDomain> listRPC = responseRPC.getData();
        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
        listRESTful.addAll(listRPC);
        System.out.println("this is result!");
        System.out.println(new ResponseData(listRESTful));
        return new ResponseData(listRESTful);
    }
}
