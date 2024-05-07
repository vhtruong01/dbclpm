package com.water.dbclpm;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.LoaiHoDan;
import com.water.dbclpm.entity.NVHanhChinh;
import com.water.dbclpm.entity.NhanVien;
import com.water.dbclpm.service.UserService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void getHoDanTheoSdt_testChuan() {
        // ton tai ho dan co sdt
        String sdt = "0999999999";
        HoDan hoDan = userService.getHoDanTheoSdt(sdt);
        Assertions.assertNotNull(hoDan);
        Assertions.assertEquals(sdt, hoDan.getSdt());
    }

    @Test
    public void getHoDanTheoSdt_testNgoaiLe() {
        // khong ton tai ho dan co sdt
        String sdt = "0987654321";
        HoDan hoDan = userService.getHoDanTheoSdt(sdt);
        Assertions.assertNull(hoDan);
    }

    @Test
    public void getNhanVienBySdt_testChuan() {
        // ton tai nhan vien co sdt
        String sdt = "0123456789";
        NhanVien nv = userService.getNhanVienBySdt(sdt);
        Assertions.assertNotNull(nv);
        Assertions.assertEquals(sdt, nv.getSdt());
    }

    @Test
    public void getNhanVienBySdt_testNgoaiLe() {
        // khong ton tai nhan vien co sdt
        String sdt = "0987654321";
        NhanVien nv = userService.getNhanVienBySdt(sdt);
        Assertions.assertNull(nv);
    }

    @Test
    public void save_testChuan() {
        // them ho dan co ma nhan vien va ma loai ho dan hop le
        HoDan hoDan=new HoDan();
        NVHanhChinh nvHanhChinh=new NVHanhChinh();
        LoaiHoDan loaiHoDan=new LoaiHoDan();
        loaiHoDan.setLoai_id(1);
        nvHanhChinh.setNhanvien_id(1);
        hoDan.setTenChuHo("Nguyen van A");
        hoDan.setSdt("011223344");
        hoDan.setQuanHuyen("A B");
        hoDan.setXaPhuong("C D");
        hoDan.setNgayduyet(new Date());
        hoDan.setNvHanhChinh(nvHanhChinh);
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertTrue(userService.save(hoDan));
    }
    @Test
    public void save_testNgoaiLe1() {
        // them ho dan co ma nhan vien khong hop le, ma loai ho dan hop le
        HoDan hoDan=new HoDan();
        NVHanhChinh nvHanhChinh=new NVHanhChinh();
        LoaiHoDan loaiHoDan=new LoaiHoDan();
        loaiHoDan.setLoai_id(1);
        nvHanhChinh.setNhanvien_id(10);
        hoDan.setTenChuHo("Nguyen van A");
        hoDan.setSdt("011223344");
        hoDan.setQuanHuyen("A B");
        hoDan.setXaPhuong("C D");
        hoDan.setNgayduyet(new Date());
        hoDan.setNvHanhChinh(nvHanhChinh);
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertFalse(userService.save(hoDan));
    }
    @Test
    public void save_testNgoaiLe2() {
        // them ho dan co ma nhan vien hop le, ma loai ho dan khong hop le
        HoDan hoDan=new HoDan();
        NVHanhChinh nvHanhChinh=new NVHanhChinh();
        LoaiHoDan loaiHoDan=new LoaiHoDan();
        loaiHoDan.setLoai_id(88);
        nvHanhChinh.setNhanvien_id(1);
        hoDan.setTenChuHo("Nguyen van A");
        hoDan.setSdt("011223344");
        hoDan.setQuanHuyen("A B");
        hoDan.setXaPhuong("C D");
        hoDan.setNgayduyet(new Date());
        hoDan.setNvHanhChinh(nvHanhChinh);
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertFalse(userService.save(hoDan));
    }
    @Test
    public void save_testNgoaiLe3() {
        // them ho dan co ma nhan vien va ma loai ho dan khong hop le
        HoDan hoDan=new HoDan();
        NVHanhChinh nvHanhChinh=new NVHanhChinh();
        LoaiHoDan loaiHoDan=new LoaiHoDan();
        loaiHoDan.setLoai_id(987);
        nvHanhChinh.setNhanvien_id(111);
        hoDan.setTenChuHo("Nguyen van A");
        hoDan.setSdt("011223344");
        hoDan.setQuanHuyen("A B");
        hoDan.setXaPhuong("C D");
        hoDan.setNgayduyet(new Date());
        hoDan.setNvHanhChinh(nvHanhChinh);
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertFalse(userService.save(hoDan));
    }
}
