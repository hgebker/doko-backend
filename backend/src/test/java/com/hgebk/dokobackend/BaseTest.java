package com.hgebk.dokobackend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.MockitoAnnotations.openMocks;

public class BaseTest {
    private AutoCloseable closeable;

    @BeforeEach
    public void initMocks() {
        closeable = openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
}
