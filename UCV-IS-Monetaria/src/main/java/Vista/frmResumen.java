package Vista;

public class frmResumen extends javax.swing.JInternalFrame {
    
    public frmResumen() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlbBienvenida = new javax.swing.JLabel();
        jlbIngresos = new javax.swing.JLabel();
        jlbTextotabla = new javax.swing.JLabel();
        jlbGastos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblResumen = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jlbBienvenida.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbBienvenida.setForeground(new java.awt.Color(0, 0, 0));
        jlbBienvenida.setText("Bienvenida");

        jlbIngresos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbIngresos.setForeground(new java.awt.Color(0, 0, 0));
        jlbIngresos.setText("Ingresos");

        jlbTextotabla.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbTextotabla.setForeground(new java.awt.Color(0, 0, 0));
        jlbTextotabla.setText("textotabla");

        jlbGastos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlbGastos.setForeground(new java.awt.Color(0, 0, 0));
        jlbGastos.setText("Gastos");

        jtblResumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Categoria", "Nombre Producto", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(jtblResumen);

        btnCerrar.setBackground(new java.awt.Color(255, 0, 0));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnCerrar.setText("X");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlbBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbTextotabla, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jlbIngresos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                                .addComponent(jlbGastos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbBienvenida)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jlbIngresos)
                .addGap(18, 18, 18)
                .addComponent(jlbGastos)
                .addGap(18, 18, 18)
                .addComponent(jlbTextotabla)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel jlbBienvenida;
    public javax.swing.JLabel jlbGastos;
    public javax.swing.JLabel jlbIngresos;
    public javax.swing.JLabel jlbTextotabla;
    public javax.swing.JTable jtblResumen;
    // End of variables declaration//GEN-END:variables
}
