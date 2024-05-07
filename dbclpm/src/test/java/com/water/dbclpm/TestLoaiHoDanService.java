package com.water.dbclpm;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.water.dbclpm.entity.LoaiHoDan;
import com.water.dbclpm.service.LoaiHoDanService;

@SpringBootTest
public class TestLoaiHoDanService {
    @Autowired
    private LoaiHoDanService loaiHoDanService;

    @Test
    public void getDsLoaiHoDan_testChuan() {
        // lay danh sach cac loai ho dan
        int tongLoaiHoDan = 2;
        List<LoaiHoDan> dsLoaiHoDan = loaiHoDanService.getDsLoaiHoDan();
        Assertions.assertNotNull(dsLoaiHoDan);
        Assertions.assertEquals(tongLoaiHoDan, dsLoaiHoDan.size());
    }
}
