package com.du.bookServer.bookRPCAPI.book.service;

import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;

public interface BookDubboService {
    RpcResult<BookDomain> getBookById(int id);

    RpcResult<BookDomain> getBookList();

}
