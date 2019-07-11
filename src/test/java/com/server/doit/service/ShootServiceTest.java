package com.server.doit.service;


import com.server.doit.domain.dto.ShootDto;
import com.server.doit.domain.service.ShootService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShootServiceTest {
    @Autowired
    private ShootService shootService;

    @Test
    public void createShoot() {
        ShootDto shootDto = ShootDto.builder()
                .build();
    }
}
