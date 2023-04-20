/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package edu.poly.shopclothes;

import edu.poly.shopclothes.dao.DanhMucDao;
import edu.poly.shopclothes.dao.SanPhamDao;
import edu.poly.shopclothes.entity.DanhMucSP;
import edu.poly.shopclothes.entity.KhachHang;
import edu.poly.shopclothes.entity.SanPham;
import edu.poly.shopclothes.helper.DiaLogHelper;
import edu.poly.shopclothes.helper.ShereHelper;
import edu.poly.shopclothes.helper.XLmage;
import java.awt.Image;
import java.awt.geom.Arc2D;
import java.io.File;
import java.net.URL;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class SanPhamlFrame extends javax.swing.JInternalFrame {

    private int index = 0;
    DanhMucDao dmDao = new DanhMucDao();
    SanPhamDao spDao = new SanPhamDao();

    /**
     * Creates new form SanPhamlFrame
     */
    public SanPhamlFrame() {
        initComponents();

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        
        
        ui.setNorthPane(null);
        fillComboBoxLoaiSP();
        fillComboBoxLoai();
        initTable();
        fillTable();

    }

    private void TimLoaiSP() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        DanhMucSP danhmuc = (DanhMucSP) cboLoai.getSelectedItem();
        List<SanPham> list = spDao.TimkiemTheoID_DanhMuc(danhmuc.getID_DanhMuc());
        System.out.println("Loại SP" + danhmuc.getID_DanhMuc());
        for (SanPham sp : list) {

            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getSize(), sp.getNhaSanXuat(), sp.getGiaBan(), sp.getGiaBan(), sp.getChatLieu(), sp.getTrangThai(), sp.getID_DanhMuc()};
            model.addRow(row);
        }

    }

    private void TimKiemTen() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        String TenSP = txtTimKiem.getText();
        List<SanPham> list = spDao.TimKiemSP(TenSP);
        for (SanPham sp : list) {

            Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getSize(), sp.getNhaSanXuat(), sp.getGiaBan(), sp.getGiaBan(), sp.getChatLieu(), sp.getTrangThai(), sp.getID_DanhMuc()};
            model.addRow(row);
        }

    }

    private void initTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Số Lương", "SIZE", "NhaSanXuat", "Giá Nhập", "Giá Bán", "Chất Liệu", "Trang Thái", "Danh Muc"});
        tblSanPham.setModel(model);
    }

    private void chonAnh() {
        if (FileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = FileChooser.getSelectedFile();
            XLmage.save(file);
            ImageIcon icon = XLmage.read(file.getName());
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName());

        }

    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = spDao.selectAll();
            for (SanPham sp : list) {
                Object[] row = {sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getSize(), sp.getNhaSanXuat(), sp.getGiaBan(), sp.getGiaBan(), sp.getChatLieu(), sp.getTrangThai(), sp.getID_DanhMuc()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DiaLogHelper.showErrorDialog(this, e.getMessage(), "Lỗi Table");
        }
    }

    private void setForm(SanPham sp) {
        txtTenSP.setText(sp.getTenSP());
        txtSoLuong.setText(sp.getSoLuong() + "");
        txtSize.setText(sp.getNhaSanXuat());
        if (sp.getHinh() != null) {
            lblHinh.setToolTipText(sp.getHinh());
            lblHinh.setIcon(XLmage.read(sp.getHinh()));
        }
        txtMaSP.setText(sp.getMaSP() + " ");

        txtGiaNhap.setText(sp.getGiaNhap() + " ");
        txtGiaBan.setText(sp.getGiaBan() + " ");
        txtChatLieu.setText(sp.getChatLieu());
        txtNhaSanX.setText(sp.getNhaSanXuat());
        txtMoTa.setText(sp.getMoTa());
        txtTrangThai.setText(sp.getTrangThai());
//cboGioiTinh.setSelectedIndex(kh.isGioiTinh() ? 0:1);
        Integer manv = (Integer) tblSanPham.getValueAt(this.index, 9);
        DanhMucSP model = dmDao.selectById(manv);
        //  cboSanPham.setSelectedIndex(sp.getID_DanhMuc());
        cboSanPham.setSelectedIndex(model.getID_DanhMuc() - 1);
        //  cboChuyende.setSelectedItem(daoo.selectById(model.getMaCD()));

    }

    private SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setTenSP(txtTenSP.getText());
        sp.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sp.setSize(txtSize.getText());
        sp.setNhaSanXuat(txtNhaSanX.getText());
        sp.setHinh(lblHinh.getToolTipText());
        sp.setGiaNhap(Float.valueOf(txtGiaNhap.getText()));
        sp.setGiaBan(Float.valueOf(txtGiaBan.getText()));
        sp.setChatLieu(txtChatLieu.getText());
        sp.setMoTa(txtMoTa.getText());
        sp.setTrangThai(txtTrangThai.getText());

        DanhMucSP danhmuc = (DanhMucSP) cboSanPham.getSelectedItem();
        sp.setID_DanhMuc(danhmuc.getID_DanhMuc());
        return sp;

    }

    private SanPham getUpdate() {
        SanPham sp = new SanPham();
        Integer manv = (Integer) tblSanPham.getValueAt(this.index, 0);
        sp.setMaSP(manv);
        sp.setTenSP(txtTenSP.getText());
        sp.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sp.setSize(txtSize.getText());
        sp.setNhaSanXuat(txtNhaSanX.getText());
        sp.setHinh(lblHinh.getToolTipText());
        sp.setGiaNhap(Float.valueOf(txtGiaNhap.getText()));
        sp.setGiaBan(Float.valueOf(txtGiaBan.getText()));
        sp.setChatLieu(txtChatLieu.getText());
        sp.setMoTa(txtMoTa.getText());
        sp.setTrangThai(txtTrangThai.getText());

        DanhMucSP danhmuc = (DanhMucSP) cboSanPham.getSelectedItem();
        sp.setID_DanhMuc(danhmuc.getID_DanhMuc());
        System.out.println("SP: " + danhmuc.getID_DanhMuc());
        return sp;

    }

//        KhoaHoc getModel() {
//        KhoaHoc model = new KhoaHoc();
//        ChuyenDe chuyenDe = (ChuyenDe) cboChuyende.getSelectedItem();
//        model.setMaCD(chuyenDe.getMaCD());
//        model.setNgayKG(XDate.toDate(txtNgayKhaiG.getText(),"MM/dd/yyyy"));
//        model.setHocPhi(Double.valueOf(txtHocPhi.getText()));
//        model.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
//        model.setGhiChu(txtGhiChu.getText());
//        model.setMaNV(ShereHelper.USER.getMaNV());
//        model.setNgayTao(XDate.toDate(txtNgayTao.getText(),"MM/dd/yyyy"));
//        model.setMaKH(Integer.valueOf(cboChuyende.getToolTipText()));
//
//        return model;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtNhaSanX = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtSize = new javax.swing.JTextField();
        txtChatLieu = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        cboSanPham = new javax.swing.JComboBox<>();
        btnThemLoai = new javax.swing.JButton();
        lblHinh = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox<>();
        btnTim1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1095, 715));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem.setBackground(new java.awt.Color(255, 153, 51));
        btnThem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, -1));

        btnSua.setBackground(new java.awt.Color(255, 153, 51));
        btnSua.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 70, -1));

        btnXoa.setBackground(new java.awt.Color(255, 153, 51));
        btnXoa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 70, -1));

        btnMoi.setBackground(new java.awt.Color(255, 153, 51));
        btnMoi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 70, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 100, 280));

        jLabel16.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel16.setText("Tên SP");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 20));

        jLabel17.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel17.setText("Mã SP");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 20));

        jLabel18.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel18.setText("Giá Nhập ");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 70, 20));

        jLabel19.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel19.setText("Giá Bán");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 70, 20));

        jLabel20.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel20.setText("Loại Sản Phẩm ");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 110, 20));

        jLabel21.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel21.setText("Số Lượng ");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 70, 20));

        jLabel22.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel22.setText("Size ");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 70, 20));

        jLabel23.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel23.setText("Chất Liệu");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 70, 20));

        jLabel24.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel24.setText("Hình ảnh sản Phẩm");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, 120, 20));

        jLabel25.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel25.setText("Trang Thái");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 70, 20));
        jPanel2.add(txtNhaSanX, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 170, 30));
        jPanel2.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 150, 30));

        txtMaSP.setEditable(false);
        txtMaSP.setForeground(new java.awt.Color(255, 102, 0));
        jPanel2.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 150, 30));
        jPanel2.add(txtGiaNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, 30));
        jPanel2.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, 30));
        jPanel2.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 170, 30));
        jPanel2.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 170, 30));
        jPanel2.add(txtChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 170, 30));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 220, -1));

        cboSanPham.setMaximumRowCount(15);
        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cboSanPham.setToolTipText("0");
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });
        jPanel2.add(cboSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 150, 30));

        btnThemLoai.setBackground(new java.awt.Color(255, 153, 51));
        btnThemLoai.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnThemLoai.setText("Thêm");
        btnThemLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiActionPerformed(evt);
            }
        });
        jPanel2.add(btnThemLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 70, 30));

        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/shopclothes/icon/3d-plastic-people-girls-with-shopping-cart.png"))); // NOI18N
        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel2.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 250, 260));

        jLabel27.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel27.setText("Nhà Sản Xuất ");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 90, 20));

        jLabel28.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel28.setText("Mô Tả");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 70, 20));
        jPanel2.add(txtTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 170, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1090, 360));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 240, 30));

        btnTim.setBackground(new java.awt.Color(255, 153, 51));
        btnTim.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        jPanel4.add(btnTim, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, 30));

        jLabel26.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel26.setText("Loại Sản Phẩm");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 100, 20));

        cboLoai.setMaximumRowCount(15);
        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cboLoai.setToolTipText("1");
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });
        jPanel4.add(cboLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 230, 30));

        btnTim1.setBackground(new java.awt.Color(255, 153, 51));
        btnTim1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnTim1.setText("Tìm");
        btnTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnTim1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1070, 70));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.setGridColor(new java.awt.Color(0, 0, 0));
        tblSanPham.setSelectionBackground(new java.awt.Color(255, 102, 0));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 1070, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiActionPerformed
        LoaiSPJFrame loai = new LoaiSPJFrame();
        loai.setVisible(true);
    }//GEN-LAST:event_btnThemLoaiActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed

    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        this.chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        chek();
        if (flat == true) {
            this.insert();
            flat = false;
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        chek();
        if (flat == true) {
            this.update();
            flat = false;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblSanPham.rowAtPoint(evt.getPoint());
            this.Edit();
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtSize.setText("");
        txtMaSP.setText("");
////        if (sp.getHinh() != null) {
////            lblHinh.setToolTipText(sp.getHinh());
////            lblHinh.setIcon(XLmage.read(sp.getHinh()));
////        }
//        
        lblHinh.setText("");
////        public static Image getIcon(){
////      URL url =  XLmage.class.getResource("/edu/poly/shopclothes/icons/3d-plastic-people-girls-with-shopping-cart.png");
////        return  new ImageIcon(url).getImage();
////    }

        txtGiaNhap.setText(" ");
        txtGiaBan.setText(" ");
        txtChatLieu.setText("");
        txtNhaSanX.setText("");
        txtMoTa.setText("");
        txtTrangThai.setText("");
//cboGioiTinh.setSelectedIndex(kh.isGioiTinh() ? 0:1);
        Integer manv = (Integer) tblSanPham.getValueAt(this.index, 9);
        DanhMucSP model = dmDao.selectById(manv);
        cboSanPham.setSelectedIndex(model.getID_DanhMuc());
        //  cboChuyende.setSelectedItem(daoo.selectById(model.getMaCD()));       
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        this.TimLoaiSP();
    }//GEN-LAST:event_btnTimActionPerformed

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        // TODO add your handling code here:
        // this.TimLoaiSP();

    }//GEN-LAST:event_cboLoaiActionPerformed

    private void btnTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim1ActionPerformed
        // TODO add your handling code here:
        this.TimKiemTen();
    }//GEN-LAST:event_btnTim1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemLoai;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTim1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtChatLieu;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNhaSanX;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables

    private void insert() {
        SanPham kh = getForm();

//           try {
        spDao.insert(kh);
        DiaLogHelper.alert(this, "Thêm Mới Sản Phẩm Thành  Công");
        fillTable();
//     } catch (Exception e) {
//         DiaLogHelper.alert(this,e.getMessage());
//     }

    }

    private void delete() {
        if (DiaLogHelper.confirm(this, "Bạn có muốn xoá Sản Phẩm này")) {
            try {
                Integer masp = (Integer) tblSanPham.getValueAt(this.index, 0);
                spDao.delete(masp);
                DiaLogHelper.alert(this, "Xóa Thành Công !!");

                fillTable();

            } catch (Exception e) {
                DiaLogHelper.alert(this,"Xóa Thành Công !");
            }
        }

    }
//    private void update(){
//        SanPham sp = getForm();
//        try {
//            spDao.update(sp);
//           DiaLogHelper.alert(this,"Cập nhật Thành Công !!");
//         fillTable();
//        } catch (Exception e) {
//            DiaLogHelper.alert(this,e.getMessage());
//            
//        }
//    }

    private void update() {
        if (DiaLogHelper.confirm(this, "Bạn có muốn Cập nhật thông tin Sản Phẩm này")) {
               SanPham sanpham = getUpdate();
        //  Integer manv = (Integer) tblSanPham.getValueAt(this.index, 0);
        //SanPham sp = spDao.selectById(manv);

        spDao.update(sanpham);
        fillTable();
        DiaLogHelper.alert(this, "Cập nhât thông tin Sản Phẩm Thành công ! ");

        }
     
    }

    void Edit() {
//        try {
        Integer manv = (Integer) tblSanPham.getValueAt(this.index, 0);
        SanPham model = spDao.selectById(manv);
        if (model != null) {

            this.setForm(model);

        }
//        } catch (Exception e) {
//            DiaLogHelper.alert(this, e.getMessage());
//        }
    }

    void fillComboBoxLoaiSP() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        try {
            List<DanhMucSP> list = dmDao.selectAll();

            for (DanhMucSP cd : list) {
                model.addElement(cd);
            }

        } catch (Exception e) {
            DiaLogHelper.alert(this, "Lỗi Try Vẫn");
        }

    }

    void fillComboBoxLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboLoai.getModel();
        model.removeAllElements();
        try {
            List<DanhMucSP> list = dmDao.selectAll();

            for (DanhMucSP cd : list) {
                model.addElement(cd);
            }

        } catch (Exception e) {
            DiaLogHelper.alert(this, "Lỗi Try Vẫn");
        }

    }

    boolean flat = false;

    void chek() {
        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên Sản Phẩm Ko đc bỏ trồng !! ");
        } else if (txtSoLuong.getText().equals("")) {
            DiaLogHelper.alert(this, "Số Lượng ko đc bỏ trống !! ");

        } else if (txtGiaNhap.getText().equals("")) {
            DiaLogHelper.alert(this, "Giá nhập ko đc bỏ trống !!");
        } else if (txtSize.getText().equals("")) {
            DiaLogHelper.alert(this, " Size không đc bỏ trồng ");
        } else {
            flat = true;
        }

    }

}
