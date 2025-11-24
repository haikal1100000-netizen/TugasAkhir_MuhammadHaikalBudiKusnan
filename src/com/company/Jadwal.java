package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jadwal {
    private JButton Submit;
    private JTable Jadwal;
    private JLabel labelMatkul;
    private JLabel labelHari;
    private JLabel labelMulai;
    private JLabel labelSelesai;
    private JTextField textMatkul;
    private JTextField textMulai;
    private JTextField textSelesai;
    private JPanel Panel;
    private JButton Edit;
    private JButton Hapus;
    private JComboBox comboBoxHari;
    private JTextField textPencarian;
    private JButton Search;
    private JLabel Pencarian;
    private JButton LihatJadwal;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Tugas Akhir");
        frame.setContentPane(new Jadwal().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setResizable(true);
    }

    public Jadwal() {
        Jadwal.setVisible(true);
        String[] kolom = {"Mata Kuliah", "Hari", "Mulai", "Selesai"};
        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(kolom, 0);
        Jadwal.setModel(model);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String matkul = textMatkul.getText();
                String hari = comboBoxHari.getSelectedItem().toString();
                String mulai = textMulai.getText();
                String selesai = textSelesai.getText();

                if (matkul.isEmpty() || mulai.isEmpty() || selesai.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
                    return;
                }

                if (!mulai.matches("\\d{2}:\\d{2}") || !selesai.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(null,
                            "Format waktu harus HH:mm (contoh 08:30)");
                    return;
                }

                javax.swing.table.DefaultTableModel model =
                        (javax.swing.table.DefaultTableModel) Jadwal.getModel();

                model.addRow(new Object[]{matkul, hari, mulai, selesai});

                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");

                textMatkul.setText("");
                textMulai.setText("");
                textSelesai.setText("");
                comboBoxHari.setSelectedIndex(0);
            }
        });
        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = Jadwal.getSelectedRow();

                if (baris == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang mau diedit!");
                    return;
                }

                String matkul = textMatkul.getText();
                String hari = comboBoxHari.getSelectedItem().toString();
                String mulai = textMulai.getText();
                String selesai = textSelesai.getText();

                if (matkul.isEmpty() || mulai.isEmpty() || selesai.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Isi semua field sebelum edit!");
                    return;
                }

                if (!mulai.matches("\\d{2}:\\d{2}") || !selesai.matches("\\d{2}:\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Format waktu harus HH:mm (contoh 08:30)");
                    return;
                }

                model.setValueAt(matkul, baris, 0);
                model.setValueAt(hari, baris, 1);
                model.setValueAt(mulai, baris, 2);
                model.setValueAt(selesai, baris, 3);

                JOptionPane.showMessageDialog(null, "Data berhasil diedit!");
            }
        });
        Hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int baris = Jadwal.getSelectedRow();

                if (baris == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
                    return;
                }

                int konfirmasi = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin menghapus data?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    javax.swing.table.DefaultTableModel model =
                            (javax.swing.table.DefaultTableModel) Jadwal.getModel();

                    model.removeRow(baris);

                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                }
            }
        });

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cari = textPencarian.getText().toLowerCase();
                javax.swing.table.DefaultTableModel model =
                        (javax.swing.table.DefaultTableModel) Jadwal.getModel();

                boolean ditemukan = false;

                for (int i = 0; i < model.getRowCount(); i++) {

                    for (int j = 0; j < model.getColumnCount(); j++) {
                        String value = model.getValueAt(i, j).toString().toLowerCase();

                        if (value.contains(cari)) {
                            Jadwal.setRowSelectionInterval(i, i); // pilih baris yang ditemukan
                            ditemukan = true;
                            break;
                        }
                    }

                    if (ditemukan) break;
                }

                if (!ditemukan) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
                }
            }
        });

        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int baris = Jadwal.getSelectedRow();

                        if (baris == -1) {
                            JOptionPane.showMessageDialog(null, "Pilih baris yang mau diedit!");
                            return;
                        }

                        String matkul = textMatkul.getText();
                        String hari = comboBoxHari.getSelectedItem().toString();
                        String mulai = textMulai.getText();
                        String selesai = textSelesai.getText();

                        if (matkul.isEmpty() || mulai.isEmpty() || selesai.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Isi semua field sebelum edit!");
                            return;
                        }

                        if (!mulai.matches("\\d{2}:\\d{2}") || !selesai.matches("\\d{2}:\\d{2}")) {
                            JOptionPane.showMessageDialog(null, "Format waktu harus HH:mm, contoh 08:30");
                            return;
                        }

                        // Update tabel
                        model.setValueAt(matkul, baris, 0);
                        model.setValueAt(hari,     baris, 1);
                        model.setValueAt(mulai,    baris, 2);
                        model.setValueAt(selesai,  baris, 3);

                        JOptionPane.showMessageDialog(null, "Data berhasil diedit!");
                    }
                });

            }
        });
        LihatJadwal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Membuat dialog popup
                JDialog dialog = new JDialog();
                dialog.setTitle("Keseluruhan Jadwal");

                javax.swing.table.DefaultTableModel model =
                        (javax.swing.table.DefaultTableModel) Jadwal.getModel();

                JTable tabelView = new JTable(model);

                JScrollPane scrollPane = new JScrollPane(tabelView);

                dialog.add(scrollPane);

                dialog.setSize(600, 400);
                dialog.setModal(true); // Mengunci form utama
                dialog.setVisible(true);
            }
        });

    }
}