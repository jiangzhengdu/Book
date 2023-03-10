package com.du.bookServer.bookPRCProvider.serviceImpl;

import com.du.bookServer.bookPRCProvider.mapper.BookMapper;
import com.du.bookServer.bookRPCAPI.book.domain.BookDomain;
import com.du.bookServer.bookRPCAPI.book.result.RpcResult;
import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection"})
public class bookDubboServiceImpl implements BookDubboService {

    @Autowired
    BookMapper bookMapper;
    @Override
    public RpcResult<BookDomain> getBookById(int id) {
        BookDomain bookDomain = bookMapper.queryById(id);
        return RpcResult.success(bookDomain);
    }

    @Override
    public RpcResult getBookList() {
        List<BookDomain> list = bookMapper.queryAll();
        return RpcResult.success(list);
    }

    @Override
    public RpcResult getBookByName(String name) {
        List<BookDomain> list = bookMapper.queryByName(name);
        return RpcResult.success(list);
    }

    @Override
    public RpcResult getBookByAuthor(String author) {
        List<BookDomain> list = bookMapper.queryByAuthor(author);
        return RpcResult.success(list);
    }

    @Override
    public RpcResult<String> updateBook(BookDomain book) {
        bookMapper.updateById(book);
        return RpcResult.success("done");
    }


}
