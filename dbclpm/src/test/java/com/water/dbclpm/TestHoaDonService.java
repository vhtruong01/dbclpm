package com.water.dbclpm;

import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.entity.NVGhiNuoc;
import com.water.dbclpm.service.CongToService;
import com.water.dbclpm.service.HoaDonService;
import com.water.dbclpm.service.NhanVienService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class TestHoaDonService {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private CongToService congToService;

    @Test
    public void luuHoaDon_testChuan1() {
        // tao hoa don co tong tien = 0, ma nhan vien va ma dong ho hop le
        int tongTien = 0;
        String trangThai="Đã nộp";
        NVGhiNuoc nvGhiNuoc=nhanVienService.getNvGhiNuoc(2);
        CongTo congTo=congToService.getCongToByHoDanId(1);
        Date date=new Date();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(tongTien);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setCongTo(congTo);
        hoaDon.setNvGhiNuoc(nvGhiNuoc);
        hoaDon.setThoiGian(date);
        Assertions.assertTrue(hoaDonService.luuHoaDon(hoaDon));
        HoaDon hoaDonUpdate=hoaDonService.getHoaDon(hoaDon.getHoadon_id());
        Assertions.assertEquals(congTo,hoaDonUpdate.getCongTo());
        Assertions.assertEquals(trangThai,hoaDonUpdate.getTrangThai());
        Assertions.assertEquals(nvGhiNuoc,hoaDonUpdate.getNvGhiNuoc());
        Assertions.assertEquals(tongTien,hoaDonUpdate.getTongTien());
        Assertions.assertEquals(date,hoaDonUpdate.getThoiGian());
    }

    @Test
    public void luuHoaDon_testChuan2() {
        // tao hoa don co tong tien > 0, ma nhan vien va ma dong ho hop le
        int tongTien = 12345000;
        String trangThai="Chưa nộp";
        NVGhiNuoc nvGhiNuoc=nhanVienService.getNvGhiNuoc(2);
        CongTo congTo=congToService.getCongToByHoDanId(3);
        Date date=new Date();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(tongTien);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setCongTo(congTo);
        hoaDon.setNvGhiNuoc(nvGhiNuoc);
        hoaDon.setThoiGian(date);
        Assertions.assertTrue(hoaDonService.luuHoaDon(hoaDon));
        HoaDon hoaDonUpdate=hoaDonService.getHoaDon(hoaDon.getHoadon_id());
        Assertions.assertEquals(congTo,hoaDonUpdate.getCongTo());
        Assertions.assertEquals(trangThai,hoaDonUpdate.getTrangThai());
        Assertions.assertEquals(nvGhiNuoc,hoaDonUpdate.getNvGhiNuoc());
        Assertions.assertEquals(tongTien,hoaDonUpdate.getTongTien());
        Assertions.assertEquals(date,hoaDonUpdate.getThoiGian());
    }

    @Test
    public void luuHoaDon_testNgoaiLe1() {
        // tao hoa don co ma nhan vien khong hop le, ma dong ho hop le
        int tongTien = 10000;
        String trangThai="Chưa nộp";
        NVGhiNuoc nvGhiNuoc=nhanVienService.getNvGhiNuoc(3);
        CongTo congTo=congToService.getCongToByHoDanId(1);
        Date date=new Date();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(tongTien);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setCongTo(congTo);
        hoaDon.setNvGhiNuoc(nvGhiNuoc);
        hoaDon.setThoiGian(date);
        Assertions.assertFalse(hoaDonService.luuHoaDon(hoaDon));
    }

    @Test
    public void luuHoaDon_testNgoaiLe2() {
        // tao hoa don co ma nhan vien hop le, ma dong ho khong hop le
        int tongTien = 10000;
        String trangThai="Chưa nộp";
        NVGhiNuoc nvGhiNuoc=nhanVienService.getNvGhiNuoc(2);
        CongTo congTo=congToService.getCongToByHoDanId(9999);
        Date date=new Date();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(tongTien);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setCongTo(congTo);
        hoaDon.setNvGhiNuoc(nvGhiNuoc);
        hoaDon.setThoiGian(date);
        Assertions.assertFalse(hoaDonService.luuHoaDon(hoaDon));
    }
    @Test
    public void luuHoaDon_testNgoaiLe3() {
        // tao hoa don co ma nhan vien khong hop le, ma dong ho khong hop le
        int tongTien = 10000;
        String trangThai="Chưa nộp";
        NVGhiNuoc nvGhiNuoc=nhanVienService.getNvGhiNuoc(9999);
        CongTo congTo=congToService.getCongToByHoDanId(9999);
        Date date=new Date();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(tongTien);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setCongTo(congTo);
        hoaDon.setNvGhiNuoc(nvGhiNuoc);
        hoaDon.setThoiGian(date);
        Assertions.assertFalse(hoaDonService.luuHoaDon(hoaDon));
    }

    @Test
    public void luuHoaDon_testChuan3() {
        // thanh toan hoa don
        int hoaDonId = 17;
        HoaDon hoaDon = hoaDonService.getHoaDon(hoaDonId);
        hoaDon.setTrangThai("Đã nộp");
        Assertions.assertTrue(hoaDonService.luuHoaDon(hoaDon));
        hoaDon = hoaDonService.getHoaDon(hoaDonId);
        Assertions.assertEquals("Đã nộp", hoaDon.getTrangThai());
    }

    @Test
    public void getHoaDon_testChuan1() {
        // hoa don chua thanh toan trong han dong
        int idHoaDon = 4;
        HoaDon hoaDon = hoaDonService.getHoaDon(idHoaDon);
        Assertions.assertNotNull(hoaDon);
        Assertions.assertEquals(idHoaDon, hoaDon.getHoadon_id());
        Assertions.assertEquals("Chưa nộp", hoaDon.getTrangThai());
        Assertions.assertEquals(0, nhanVienService.getngayCham(hoaDon));
    }

    @Test
    public void getHoaDon_testChuan2() {
        // hoa don da thanh toan
        int idHoaDon = 8;
        HoaDon hoaDon = hoaDonService.getHoaDon(idHoaDon);
        Assertions.assertNotNull(hoaDon);
        Assertions.assertEquals(idHoaDon, hoaDon.getHoadon_id());
        Assertions.assertEquals("Đã nộp", hoaDon.getTrangThai());
    }

    @Test
    public void getHoaDon_testChuan3() {
        // hoa don chua thanh toan qua thoi gian dong
        int idHoaDon = 11;
        HoaDon hoaDon = hoaDonService.getHoaDon(idHoaDon);
        Assertions.assertNotNull(hoaDon);
        Assertions.assertEquals(idHoaDon, hoaDon.getHoadon_id());
        Assertions.assertEquals("Chưa nộp", hoaDon.getTrangThai());
        Assertions.assertNotEquals(0, nhanVienService.getngayCham(hoaDon));
    }

    @Test
    public void getHoaDon_testNgoaiLe() {
        // khong ton tai hoa don
        int idHoaDon = -1;
        HoaDon hoaDon = hoaDonService.getHoaDon(idHoaDon);
        Assertions.assertNull(hoaDon);
    }

    @Test
    public void getDsHoaDonByMaHD_testChuan1() {
        // ho dan khong co hoa don
        int idHoDan = 5;
        int soHoaDon = 0;
        List<HoaDon> dsHoaDon = hoaDonService.getDsHoaDonByMaHD(idHoDan);
        Assertions.assertNotNull(dsHoaDon);
        Assertions.assertEquals(soHoaDon, dsHoaDon.size());
    }

    @Test
    public void getDsHoaDonByMaHD_testChuan2() {
        // ho dan co 1 hoa don
        int idHoDan = 3;
        List<HoaDon> dsHoaDon = hoaDonService.getDsHoaDonByMaHD(idHoDan);
        Assertions.assertNotNull(dsHoaDon);
        Assertions.assertTrue(dsHoaDon.size() == 1);
        Assertions.assertEquals(idHoDan,
                dsHoaDon.get(0).getCongTo().getHoDan().getHodan_id());
    }

    @Test
    public void getDsHoaDonByMaHD_testChuan3() {
        // ho dan co nhieu hoa don
        int idHoDan = 15;
        List<HoaDon> dsHoaDon = hoaDonService.getDsHoaDonByMaHD(idHoDan);
        Assertions.assertNotNull(dsHoaDon);
        Assertions.assertTrue(dsHoaDon.size() > 1);
        for (HoaDon hoaDon : dsHoaDon)
            Assertions.assertEquals(idHoDan,
                    hoaDon.getCongTo().getHoDan().getHodan_id());
    }

    @Test
    public void getDsHoaDonByMaHD_testNgoaiLe() {
        // khong ton tai ho dan
        int idHoDan = 999;
        List<HoaDon> dsHoaDon = hoaDonService.getDsHoaDonByMaHD(idHoDan);
        Assertions.assertEquals(0,dsHoaDon.size());
    }
}
