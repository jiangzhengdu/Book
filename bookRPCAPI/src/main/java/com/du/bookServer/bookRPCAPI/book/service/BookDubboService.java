package com.du.bookServer.bookRPCAPI.book.service;

import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;

import java.util.List;

public interface BookDubboService {
    RpcResult<BookDomain> getBookById(int id);

    RpcResult<List<BookDomain>> getBookList();
    RpcResult<List<BookDomain>> getBookByName(String name);
    RpcResult<List<BookDomain>> getBookByAuthor(String author);



}
