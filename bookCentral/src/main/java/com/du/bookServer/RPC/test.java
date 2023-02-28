package com.du.bookServer.RPC;

import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class test {
    @Reference
    BookDubboService bookDubboService;

    @GetMapping("getBook")
    public RpcResult getOrder() {
        return bookDubboService.getBookById(1);
    }
}
