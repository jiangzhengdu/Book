package com.du.bookServer.Controller;

import com.du.bookServer.Model.Book;
import com.du.bookServer.RESTful.Result.RESTfulResponse;
import com.du.bookServer.RESTful.Result.ResponseData;
import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/book")
public class test {
    @Reference
    BookDubboService bookDubboService;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("getBookList")
    public RESTfulResponse getOrder() {
        ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/", ResponseData.class);
        System.out.println(responseEntity);
        RpcResult<List<BookDomain >> responseRPC = bookDubboService.getBookList();
        ResponseData responseRESTful = responseEntity.getBody();
        List<BookDomain> listRPC = responseRPC.getData();
        List<BookDomain> listRESTful = (List<BookDomain>)responseRESTful.getData();
        listRESTful.addAll(listRPC);
        return new ResponseData(listRESTful);
    }


}
