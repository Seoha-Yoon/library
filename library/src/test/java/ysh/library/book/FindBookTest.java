package ysh.library.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import ysh.library.repository.BookRepository;

@SpringBootTest
@Transactional
public class FindBookTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void test() throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        bookRepository.findOne((long)9);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        bookRepository.findByBookName("9791197063268");
        stopWatch2.stop();
        System.out.println(stopWatch2.prettyPrint());

        long time1 = stopWatch.getTotalTimeMillis();
        long time2 = stopWatch2.getTotalTimeMillis();

        if(time1 < time2){
            System.out.println("인덱스 활용해서 검색하는 것이 "+time2/time1+"배 더 빠름");
       }

        Assertions.assertEquals(time2 > time1, true);

    }
}
