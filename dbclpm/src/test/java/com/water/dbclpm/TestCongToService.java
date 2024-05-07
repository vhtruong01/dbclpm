package com.water.dbclpm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.service.CongToService;
import com.water.dbclpm.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class TestCongToService {

    @Autowired
    private CongToService congToService;
    @Autowired 
    private UserService userService;

    @Test
    public void getCongToByqcode_testChuan() {
        // ton tai cong to co ma qcode
        String qCode = "HDML0001";
        CongTo congTo = congToService.getCongToByqcode(qCode);
        Assertions.assertNotNull(congTo);
        Assertions.assertEquals(qCode, congTo.getQcode());
    }

    @Test
    public void getCongToByqcode_testNgoaiLe1() {
        // khong ton tai cong to co ma qcode
        String qCode = "abcxyz";
        CongTo congTo = congToService.getCongToByqcode(qCode);
        Assertions.assertNull(congTo);
    }

    @Test
    public void getCongToByqcode_testNgoaiLe2() {
        // ma qcode trong
        String qCode = "";
        CongTo congTo = congToService.getCongToByqcode(qCode);
        Assertions.assertNull(congTo);
    }

    @Test
    public void getCongToByHoDanId_testChuan() {
        // ton tai cong to co ho dan id
        int hoDanId = 15;
        CongTo congTo = congToService.getCongToByHoDanId(hoDanId);
        Assertions.assertNotNull(congTo);
        Assertions.assertEquals(hoDanId, congTo.getHoDan().getHodan_id());
    }

    @Test
    public void getCongToByHoDanId_testNgoaiLe() {
        // khong ton tai cong to co ho dan id
        int hoDanId = 100;
        CongTo congTo = congToService.getCongToByHoDanId(hoDanId);
        Assertions.assertNull(congTo);
    }

    @Test
    public void save_testChuan1() {
        // cap nhat cong to co so nuoc = 0
        CongTo congTo = congToService.getCongToByqcode("HDML0001");
        int soNuoc = 0;
        int soMoi = congTo.getSoCu() + soNuoc;
        congTo.setSoMoi(soMoi);
        Assertions.assertTrue(congToService.save(congTo));
        CongTo congToUpdate = congToService.getCongToByqcode("HDML0001");
        Assertions.assertEquals(soMoi, congToUpdate.getSoMoi());
    }

    @Test
    public void save_testChuan2() {
        // cap nhat cong to co so nuoc > 0
        CongTo congTo = congToService.getCongToByqcode("HDML0002");
        int soNuoc = 22;
        int soMoi = soNuoc + congTo.getSoCu();
        congTo.setSoMoi(soMoi);
        Assertions.assertTrue(congToService.save(congTo));
        CongTo congToUpdate = congToService.getCongToByqcode("HDML0002");
        Assertions.assertEquals(soMoi, congToUpdate.getSoMoi());
    }
    @Test
    public void save_testChuan3() {
        // them moi cong to ton tai ho dan
        CongTo congTo = new CongTo();
        HoDan hoDan=userService.getHoDanTheoSdt("1111");
        congTo.setQcode("aaaaa");
        congTo.setHoDan(hoDan);
        Assertions.assertTrue(congToService.save(congTo));
    }

    @Test
    public void save_testNgoaiLe() {
        // them moi cong to khong ton tai ho dan
        CongTo congTo = new CongTo();
        HoDan hoDan=new HoDan();
        hoDan.setHodan_id(999);
        congTo.setQcode("aaaaa");
        congTo.setHoDan(hoDan);
        Assertions.assertFalse(congToService.save(congTo));
    }
}
