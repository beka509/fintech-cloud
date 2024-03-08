package kz.fintech.apiservice.services.impl;

import kz.fintech.apiservice.services.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DbServiceImpl implements DbService {
    @Override
    public String test() {
        return "test";
    }
}
