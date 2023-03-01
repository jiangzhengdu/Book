package RESTful;

import com.du.bookServer.Model.Book;
import com.du.bookServer.RESTful.Result.ResponseData;
import com.du.bookServer.bookServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootTest(classes = bookServerApplication.class)
public class RESTfulTest {

    private RestTemplate restTemplate = new RestTemplate();
    @Test
    public void nparameters() {
//        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/", String.class);
        System.out.println(responseEntity.getBody());

    }

    @Test
    public void withparameters1() {
//        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/name/{1}", String.class, 10);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void withparameters2() {
//        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/RESTfulbook/{id}", String.class, map);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void withparameters3() {
//        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        ResponseData res  = restTemplate.getForObject("http://localhost:8081/RESTfulbook/{id}", ResponseData.class, map);
        System.out.println(res.getData());
    }

    @Test
    public void postForEntity() {
        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//        paramMap.add("id", 9);
        paramMap.add("name", "queryController restful book name 1");
        paramMap.add("price", 1.11);
        paramMap.add("inventory", 2);
        ResponseEntity<ResponseData> responseEntity = restTemplate.postForEntity("http://localhost:8081/RESTfulbook/", paramMap, ResponseData.class);
        System.out.println(responseEntity.getBody().getData());
    }

    @Test
    public void put() {
        Book book = new Book();
        book.setId(6);
        book.setName("queryController name");
        book.setPrice(9.9);
        book.setInventory(1);
        restTemplate.put("http://localhost:8081/RESTfulbook/{1}", book, Book.class,book.getId());
    }

}
