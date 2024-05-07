package com.water.dbclpm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.water.dbclpm.entity.CongTo;
import com.water.dbclpm.entity.HoDan;
import com.water.dbclpm.entity.HoaDon;
import com.water.dbclpm.entity.LoaiHoDan;
import com.water.dbclpm.entity.NVGhiNuoc;
import com.water.dbclpm.service.CongToService;
import com.water.dbclpm.service.impl.NhanVienServiceImpl;

@SpringBootTest
public class TestNhanVienService {
    @Autowired
    private NhanVienServiceImpl nhanVienService;
    @Autowired
    private CongToService congToService;

    private void fakeCalendar(String curDate) {
        try {
            nhanVienService.calendar = Calendar.getInstance();
            nhanVienService.calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(curDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isUpdated_testChuan1() {
        // cong to lan dau cap nhat so nuoc
        CongTo congTo = congToService.getCongToByqcode("TXB0005");
        Assertions.assertFalse(nhanVienService.isUpdated(congTo));
    }

    @Test
    public void isUpdated_testChuan2() {
        // cong to da cap nhat so nuoc
        CongTo congTo = congToService.getCongToByqcode("HDML0002");
        Assertions.assertTrue(nhanVienService.isUpdated(congTo));
    }

    @Test
    public void isUpdated_testChuan3() {
        // cong to chua cap nhat so nuoc
        CongTo congTo = congToService.getCongToByqcode("DDA0004");
        Assertions.assertFalse(nhanVienService.isUpdated(congTo));
    }

    @Test
    public void getNvGhiNuoc_testChuan() {
        // ton tai nhan vien ghi nuoc
        int idNvGhiNuoc = 2;
        NVGhiNuoc nvGhiNuoc = nhanVienService.getNvGhiNuoc(idNvGhiNuoc);
        Assertions.assertNotNull(nvGhiNuoc);
        Assertions.assertEquals(idNvGhiNuoc, nvGhiNuoc.getNhanvien_id());
    }

    @Test
    public void getNvGhiNuoc_testNgoaiLe() {
        // khong ton tai nhan vien ghi nuoc
        int idNvGhiNuoc = 1;
        NVGhiNuoc nvGhiNuoc = nhanVienService.getNvGhiNuoc(idNvGhiNuoc);
        Assertions.assertNull(nvGhiNuoc);
    }

    @Test
    public void getTongTien_testChuan1() {
        // ho dan khac, so nuoc = 0
        HoDan hoDan = new HoDan();
        int tong = 0;
        int soNuoc = 0;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ dân khác");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan2() {
        // ho ngheo, can ngheo, gia dinh chinh sach so nuoc = 0
        HoDan hoDan = new HoDan();
        int tong = 0;
        int soNuoc = 0;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ nghèo, gia đình chính sách");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan3() {
        // ho dan khac, so nuoc >0 va <=10
        HoDan hoDan = new HoDan();
        int tong = 48875;
        int soNuoc = 5;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ dân khác");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan4() {
        // ho ngheo, can ngheo, gia dinh chinh sach so nuoc >0 va <=10
        HoDan hoDan = new HoDan();
        int tong = 41212;
        int soNuoc = 6;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ nghèo, gia đình chính sách");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan5() {
        // ho dan khac, so nuoc >10 va <=20
        HoDan hoDan = new HoDan();
        int tong = 109135;
        int soNuoc = 11;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ dân khác");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan6() {
        // ho ngheo, can ngheo, gia dinh chinh sach so nuoc >10 va <=20
        HoDan hoDan = new HoDan();
        int tong = 171154;
        int soNuoc = 19;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ nghèo, gia đình chính sách");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan7() {
        // ho dan khac, so nuoc >20 va <=30
        HoDan hoDan = new HoDan();
        int tong = 358800;
        int soNuoc = 28;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ dân khác");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan8() {
        // ho ngheo, can ngheo, gia dinh chinh sach so nuoc >20 va <=30
        HoDan hoDan = new HoDan();
        int tong = 274539;
        int soNuoc = 25;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ nghèo, gia đình chính sách");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan9() {
        // ho dan khac, so nuoc >30
        HoDan hoDan = new HoDan();
        int tong = 2600150;
        int soNuoc = 101;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ dân khác");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testChuan10() {
        // ho ngheo, can ngheo, gia dinh chinh sach so nuoc >30
        HoDan hoDan = new HoDan();
        int tong = 1111739;
        int soNuoc = 54;
        LoaiHoDan loaiHoDan = new LoaiHoDan();
        loaiHoDan.setTen("Hộ nghèo, gia đình chính sách");
        hoDan.setLoaiHoDan(loaiHoDan);
        Assertions.assertEquals(tong, nhanVienService.getTongTien(hoDan, soNuoc));
    }

    @Test
    public void getTongTien_testNgoaiLe() {
        // khong ton tai ho dan
        HoDan hoDan = new HoDan();
        Assertions.assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                nhanVienService.getTongTien(hoDan, 0);
            }
        });
    }

    @Test
    public void getngayCham_testChuan1() {
        // hoa don khong cham ngay
        int ngay = 0;
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTrangThai("Chưa nộp");
        Date thoiGianTao = null;
        try {
            thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-04-2024");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setThoiGian(thoiGianTao);
        fakeCalendar("10-04-2024");
        Assertions.assertEquals(ngay, nhanVienService.getngayCham(hoaDon));
    }

    @Test
    public void getngayCham_testChuan2() {
        // hoa don nop vao ngay 23
        int ngay = 1;
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTrangThai("Chưa nộp");
        Date thoiGianTao = null;
        try {
            thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-04-2024");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setThoiGian(thoiGianTao);
        fakeCalendar("23-04-2024");
        Assertions.assertEquals(ngay, nhanVienService.getngayCham(hoaDon));
    }

    @Test
    public void getngayCham_testChuan3() {
        // hoa don nop sau ngay 23
        int ngay = 5;
        HoaDon hoaDon = new HoaDon();
        Date thoiGianTao = null;
        try {
            thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-04-2024");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setThoiGian(thoiGianTao);
        fakeCalendar("27-04-2024");
        Assertions.assertEquals(ngay, nhanVienService.getngayCham(hoaDon));
    }

    @Test
    public void getngayCham_testChuan4() {
        // hoa don nop cham vao thang sau
        int ngay = 29;
        HoaDon hoaDon = new HoaDon();
        Date thoiGianTao = null;
        try {
            thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-03-2024");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setThoiGian(thoiGianTao);
        fakeCalendar("20-04-2024");
        Assertions.assertEquals(ngay, nhanVienService.getngayCham(hoaDon));
    }

    @Test
    public void getPhiPhat_testChuan1() {
        // hoa don khong cham ngay
        int phiPhat = 0;
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(100000);
        try {
            Date thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-04-2024");
            hoaDon.setThoiGian(thoiGianTao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fakeCalendar("20-04-2024");
        Assertions.assertEquals(phiPhat, nhanVienService.getPhiPhat(hoaDon));
    }

    @Test
    public void getPhiPhat_testChuan2() {
        // hoa don cham 1 ngay
        int phiPhat = 1000;
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(1800000);
        try {
            Date thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-04-2024");
            hoaDon.setThoiGian(thoiGianTao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fakeCalendar("23-04-2024");
        Assertions.assertEquals(phiPhat, nhanVienService.getPhiPhat(hoaDon));
    }

    @Test
    public void getPhiPhat_testChuan3() {
        // hoa don cham nhieu ngay
        int phiPhat = 20000;
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongTien(900000);
        try {
            Date thoiGianTao = new SimpleDateFormat("dd-MM-yyyy").parse("15-03-2024");
            hoaDon.setThoiGian(thoiGianTao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fakeCalendar("01-05-2024");
        Assertions.assertEquals(phiPhat, nhanVienService.getPhiPhat(hoaDon));
    }

    @Test
    public void genCongToQCode_testChuan1() {
        // co ten quan, ten huyen, id do dai <=4
        String quan = "Aaa";
        String huyen = "bBBB";
        int id = 126;
        String qcode = "AB0126";
        Assertions.assertEquals(qcode, nhanVienService.genCongToQCode(quan, huyen, id));
    }

    @Test
    public void genCongToQCode_testChuan2() {
        // co ten quan, ten huyen, id do dai >4
        String quan = "CCCC";
        String huyen = "cccc";
        int id = 100000000;
        String qcode = "CC100000000";
        Assertions.assertEquals(qcode, nhanVienService.genCongToQCode(quan, huyen, id));
    }

    @Test
    public void genCongToQCode_testChuan3() {
        // ten quan co chu cai Đ, co ten huyen va co id
        String quan = "Đống Đa ";
        String huyen = "AAA";
        int id = 12345;
        String qcode = "DDA12345";
        Assertions.assertEquals(qcode, nhanVienService.genCongToQCode(quan, huyen, id));
    }

    @Test
    public void genCongToQCode_testChuan4() {
        // ten huyen co chu cai Đ, co ten quan va co id
        String quan = "abcd đ đ aaaaa";
        String huyen = "bb cc aaaa";
        int id = 193;
        String qcode = "ADDABCA0193";
        Assertions.assertEquals(qcode, nhanVienService.genCongToQCode(quan, huyen, id));
    }

    @Test
    public void genCongToQCode_testChuan5() {
        // ten quan va huyen co chu cai Đ, co id
        String quan = "abcd đ đ aaaaa";
        String huyen = "đ đmjdjddj";
        int id = 22;
        String qcode = "ADDADD0022";
        Assertions.assertEquals(qcode, nhanVienService.genCongToQCode(quan, huyen, id));
    }
}
