package project.keyappsk.dataInit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.keyappsk.global.config.DataInitializer;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class dataInitTest {

    @Autowired
    private DataInitializer dataInitializer;

    @DisplayName("member init Test")
    @Test
    @Transactional
    public void memberInit(){
        dataInitializer.memberInit();
    }
    @DisplayName("data init test")
    @Test
    @Transactional
    public void storeInit() throws IOException {
        dataInitializer.dataInit();
    }
}
