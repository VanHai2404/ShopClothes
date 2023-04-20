/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package edu.poly.shopclothes;

import edu.poly.shopclothes.dao.NhanVienDao;
import edu.poly.shopclothes.entity.NhanVien;
import edu.poly.shopclothes.helper.DiaLogHelper;
import edu.poly.shopclothes.helper.ShereHelper;
import edu.poly.shopclothes.helper.XLmage;
import java.io.File;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class NhanVienlFrame extends javax.swing.JInternalFrame {

    int index = 0;// vị trí của nhân viên đang hiển thị trên form 
    NhanVienDao dao = new NhanVienDao();

    /**
     * Creates new form NhanVienlFrame
     */
    public NhanVienlFrame() {
        initComponents();
       
        init();
    }

    private void init() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        intiTable();
        fillTable();
       
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

    private void TimKiemNV() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        String TenNV = txtTimKiem.getText();
        List<NhanVien> list = dao.selectByKeyWord(TenNV);
        for (NhanVien nv : list) {
            Object[] row = {
                nv.getMaNV(),
                nv.getHoTen(),
                nv.getMatKhau(),
                nv.isGioiTinh() ? "Nam" : "Nữ",
                nv.isVaiTro() ? "Quản lý" : "Nhân viên",
                nv.getDienThoai(),
                nv.getEmail(),
                nv.getTrangThai()
            };
            model.addRow(row);
        }

    }

    void fillComboBoxLoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboVaiTro.getModel();
        // model.removeAllElements();
        this.TKVaiTro();
    }

    private void TKVaiTro() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        boolean GioiTinh = cboGioiTinh.getSelectedIndex() == 0;
        boolean VaiTro = cboVaiTro.getSelectedIndex() == 0;
        List<NhanVien> list = dao.selectGioiTinh(GioiTinh, VaiTro);
        for (NhanVien nv : list) {
            Object[] row = {
                nv.getMaNV(),
                nv.getHoTen(),
                nv.getMatKhau(),
                nv.isGioiTinh() ? "Nam" : "Nữ",
                nv.isVaiTro() ? "Quản lý" : "Nhân viên",
                nv.getDienThoai(),
                nv.getEmail(),
                nv.getTrangThai()
            };
            model.addRow(row);
        }

    }

    private void intiTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"MaNV", "Họ và Tên", "Giới Tính", "Vai Trò", "SoDT", "Email", "Trạng Thái"});
        tblNhanVien.setModel(model);

    }
//    if (chekPass.isSelected()){
//            txtPass.setEchoChar((char)0);
//            
//        }else{
//            txtPass.setEchoChar('*');
//        }
//    int i = ((int) tblSanPham.getValueAt(row, 2)) - SoLuongSP;
//                    tblSanPham.setValueAt(i, row, 2);

//    void matkhau() {
//        int row = tblNhanVien.getSelectedRow();
//        for (int i = 0; i < row; i++) {
//        //    String matkhau = (String) tblNhanVien.getValueAt(row, 2);
//
//            String kk = "************";
//            //remove white spaces
//           // String mk = matkhau.replaceAll(matkhau, kk);
//
//            ///  public Str replaceAll(String regex, String replacement);
//            tblNhanVien.setValueAt(kk, row, 2);
//   
//
//        }
//    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);

        List<NhanVien> list = dao.selectAll();
        for (NhanVien nv : list) {
            Object[] row = {
                nv.getMaNV(),
                nv.getHoTen(),
              
                nv.isGioiTinh() ? "Nam" : "Nữ",
                nv.isVaiTro() ? "Quản lý" : "Nhân viên",
                nv.getDienThoai(),
                nv.getEmail(),
                nv.getTrangThai()

            };
            model.addRow(row);
        }

    }

    void setModel(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtHoTen.setText(model.getHoTen());
        txtMatkhau.setText(model.getMatKhau());
        rdoNam.setSelected(model.isVaiTro());
        rdoNu.setSelected(!model.isVaiTro());
        rdoQuanly.setSelected(model.isVaiTro());
        rdoNhanvien.setSelected(!model.isVaiTro());
        txtSodt.setText(model.getDienThoai());
        txtEmail.setText(model.getEmail());
        cboTrangThai.setSelectedIndex(0);
        if (model.getHinh() != null) {
            lblHinh.setToolTipText(model.getHinh());
            lblHinh.setIcon(XLmage.read(model.getHinh()));
        }
    }

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setHoTen(txtHoTen.getText());
        model.setMatKhau((new String(txtMatkhau.getPassword())));
        model.setGioiTinh(rdoNam.isSelected());
        model.setVaiTro(rdoQuanly.isSelected());
        model.setDienThoai(txtSodt.getText());
        model.setEmail(txtEmail.getText());
        String variable = cboTrangThai.getSelectedItem().toString();
        model.setTrangThai(variable);
        model.setHinh(lblHinh.getToolTipText());

        return model;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        PanelNhanVien = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimK = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboVaiTro = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtMatkhau = new javax.swing.JPasswordField();
        rdoNu = new javax.swing.JRadioButton();
        rdoQuanly = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNhanvien = new javax.swing.JRadioButton();
        txtSodt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblHinh = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        PanelNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        jPanel2.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 22, 300, 30));

        btnTimK.setBackground(new java.awt.Color(255, 153, 0));
        btnTimK.setText("Tìm");
        btnTimK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKActionPerformed(evt);
            }
        });
        jPanel2.add(btnTimK, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 60, 30));

        PanelNhanVien.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 410, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Theo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Vài Trò");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 70, 20));

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân Viên" }));
        cboVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVaiTroActionPerformed(evt);
            }
        });
        jPanel3.add(cboVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 120, -1));

        jLabel3.setText("Giới Tính");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 20));

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });
        jPanel3.add(cboGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 80, -1));

        PanelNhanVien.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 410, 60));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.setSelectionBackground(new java.awt.Color(255, 153, 0));
        tblNhanVien.setShowGrid(true);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        PanelNhanVien.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 1090, 290));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMoi.setBackground(new java.awt.Color(255, 153, 51));
        btnMoi.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnMoi.setText("Làm Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel4.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 110, -1));

        btnThem.setBackground(new java.awt.Color(255, 153, 51));
        btnThem.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel4.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 110, -1));

        btnSua.setBackground(new java.awt.Color(255, 153, 51));
        btnSua.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel4.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 110, -1));

        btnXoa.setBackground(new java.awt.Color(255, 153, 51));
        btnXoa.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel4.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 110, -1));

        jLabel5.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel5.setText("Họ Tên NV");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 70, 20));

        jLabel14.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel14.setText("Hình Ảnh Nhân Viên ");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 290, 120, 20));

        jLabel15.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel15.setText("Mật Khẩu");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 70, 20));

        jLabel16.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel16.setText("Mã NV");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 70, 20));

        jLabel17.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel17.setText("Giới Tính");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 70, 20));

        jLabel18.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel18.setText("Vài Trò");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 70, 20));

        jLabel19.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel19.setText("SoDT");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 70, 20));

        jLabel20.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel20.setText("Email");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 70, 20));
        jPanel4.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 250, 30));
        jPanel4.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 250, 30));
        jPanel4.add(txtMatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 250, 30));

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        rdoNu.setText("Nữ");
        jPanel4.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        rdoQuanly.setBackground(new java.awt.Color(255, 255, 255));
        rdoQuanly.setText("Quản lý");
        jPanel4.add(rdoQuanly, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 100, -1));

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        rdoNam.setText("Nam");
        jPanel4.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        rdoNhanvien.setBackground(new java.awt.Color(255, 255, 255));
        rdoNhanvien.setText("Nhân  Viên");
        jPanel4.add(rdoNhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 100, -1));
        jPanel4.add(txtSodt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 250, 30));
        jPanel4.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 250, 30));

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/shopclothes/icon/group.png"))); // NOI18N
        lblHinh.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel4.add(lblHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 230, 270));

        jLabel21.setFont(new java.awt.Font("Raleway", 1, 12)); // NOI18N
        jLabel21.setText("Trang Thái");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 70, 20));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Làm Việc", "Nghĩ Việc" }));
        jPanel4.add(cboTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 250, 30));

        PanelNhanVien.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1090, 320));

        getContentPane().add(PanelNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
        this.TKVaiTro();

    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        check();
        if (flag == true) {

            Insert();
            flag = false;
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        check();
        if (flag == true) {
            this.Update();
            flag = false;
        }
        //  txtMaNV.setEnabled(false);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
             check();
        if (flag == true) {
            this.Delete();
            flag = false;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        this.Clear();
        flag = false;
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        if (evt.getClickCount() == 1) {
            this.index = tblNhanVien.rowAtPoint(evt.getPoint());
            this.Edit();
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        this.chonAnh();
    }//GEN-LAST:event_lblHinhMouseClicked

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked

    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void cboVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaiTroActionPerformed
        // TODO add your handling code here:
        this.TKVaiTro();
    }//GEN-LAST:event_cboVaiTroActionPerformed

    private void btnTimKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKActionPerformed
        this.TimKiemNV();
    }//GEN-LAST:event_btnTimKActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JPanel PanelNhanVien;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimK;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNhanvien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoQuanly;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatkhau;
    private javax.swing.JTextField txtSodt;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    void setStatus(boolean insertable) {
        if (tblNhanVien.getRowCount() > 0) {
            txtMaNV.setEditable(!insertable);
            btnThem.setEnabled(!insertable);
            btnSua.setEnabled(!insertable);
            btnXoa.setEnabled(!insertable);
        } else {
            btnThem.setEnabled(!insertable);
            btnSua.setEnabled(insertable);
            btnXoa.setEnabled(insertable);
        }

    }

    void Clear() {
        this.setModel(new NhanVien());
        index = 0;
        this.setStatus(false);
    }

    void Insert() {
        NhanVien model = getModel();//

        try {
            dao.insert(model);
            this.fillTable();
            this.Clear();
            DiaLogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DiaLogHelper.alert(this, "Thêm mới thất bại");
        }

    }

    
    void Delete() {
        if (DiaLogHelper.confirm(this, "Bạn có muốn xoá nhân viên này")) {
            String manv = txtMaNV.getText();
            NhanVien nv = dao.selectById(manv);
            // String manv2 = ShereHelper.USER.getMaNV();
            if (nv != null) {
                try {
                    dao.delete(manv);
                    this.fillTable();
                    this.Clear();
                    DiaLogHelper.alert(this, "Xoá thành công");
                } catch (Exception e) {
                    DiaLogHelper.alert(this, "Nhân viên này khồn thể xoá");
                }
            } else {
                DiaLogHelper.alert(this, "Nhân Viên Ko Tồn Tại ");

            }
        }

    }

    void Edit() {
        try {
            String manv = (String) tblNhanVien.getValueAt(this.index, 0);
            NhanVien model = dao.selectById(manv);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DiaLogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void Update() {
        NhanVien kh = getModel();
        String MaKH = txtMaNV.getText();
        NhanVien khachhang = dao.selectById(MaKH);
     if (DiaLogHelper.confirm(this, "Bạn có muốn cập nhật thông tin nhân viên này")) {
        if (khachhang != null) {
            try {
                dao.update(kh);
                fillTable();
                DiaLogHelper.alert(this, "Cập nhât thông tin khách hàng thành công ! ");
            } catch (Exception e) {
                DiaLogHelper.showErrorDialog(this, e.getMessage(), "Lỗi Truy Vẫn Dữ Liệu !!");
            }

        } else {
            DiaLogHelper.alert(this, "Mã KH Ko Tồn Tại");
        }
    }
    }

    boolean flag = false;

    void check() {
        if (txtMaNV.getText().length() < 3) {
            if (txtMaNV.getText().length() == 0) {
                DiaLogHelper.alert(this, "Mã nhân viên không được để trống");
            }
            if (txtMaNV.getText().length() > 0 && txtMaNV.getText().length() < 3) {
                DiaLogHelper.alert(this, "Mã nhân viên phải nhập ít nhất 3 ký tự");
            }
        } else if (txtMatkhau.getPassword().length < 3) {
            if (txtMatkhau.getPassword().length == 0) {
                DiaLogHelper.alert(this, "Mật khẩu không được để trống");
            }
            if (txtMatkhau.getPassword().length > 0 && txtMatkhau.getPassword().length < 3) {
                DiaLogHelper.alert(this, "Mật khẩu phải nhập ít nhất 3 ký tự");
            }
        } else if (txtHoTen.getText().length() == 0) {
            DiaLogHelper.alert(this, "Họ tên không được để trống");
        } else {
            flag = true;
        }
        //   JOptionPane.showMessageDialog(this,true);

    }

}
