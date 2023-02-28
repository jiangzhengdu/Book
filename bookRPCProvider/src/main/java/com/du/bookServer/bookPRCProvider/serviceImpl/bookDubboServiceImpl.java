package com.du.bookServer.bookPRCProvider.serviceImpl;

import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class bookDubboServiceImpl implements BookDubboService {
    @Override
    public RpcResult<BookDomain> getBookById(int id) {
        return RpcResult.success(new BookDomain(10,"book name", 1.1, 2));
    }

    @Override
    public RpcResult<BookDomain> getBookList() {
        return null;
    }
}
