package RPC;

import com.du.bookServer.bookRPCAPI.book.service.BookDubboService;
import com.du.bookServer.bookServerApplication;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = bookServerApplication.class)
public class test {

    @Reference
    BookDubboService bookDubboService;

    @Test
    public void test() {
        System.out.println(bookDubboService.getBookById(1));
    }
}
