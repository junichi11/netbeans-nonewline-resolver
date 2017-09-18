/*
 * Copyright 2017 junichi11.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.junichi11.netbeans.modules.nonewline.resolver.options;

final class NoNewlineResolverOptionsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 9109931111485033945L;

    private final NoNewlineResolverOptionsPanelController controller;

    NoNewlineResolverOptionsPanel(NoNewlineResolverOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enabledCheckBox = new javax.swing.JCheckBox();
        addNewlineCheckBox = new javax.swing.JCheckBox();
        leaveOneNewlineCheckBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(enabledCheckBox, org.openide.util.NbBundle.getMessage(NoNewlineResolverOptionsPanel.class, "NoNewlineResolverOptionsPanel.enabledCheckBox.text")); // NOI18N
        enabledCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enabledCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addNewlineCheckBox, org.openide.util.NbBundle.getMessage(NoNewlineResolverOptionsPanel.class, "NoNewlineResolverOptionsPanel.addNewlineCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(leaveOneNewlineCheckBox, org.openide.util.NbBundle.getMessage(NoNewlineResolverOptionsPanel.class, "NoNewlineResolverOptionsPanel.leaveOneNewlineCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enabledCheckBox)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addNewlineCheckBox)
                            .addComponent(leaveOneNewlineCheckBox))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(enabledCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addNewlineCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leaveOneNewlineCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void enabledCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enabledCheckBoxActionPerformed
        setComponentsEnabled();
    }//GEN-LAST:event_enabledCheckBoxActionPerformed

    private void setComponentsEnabled() {
        boolean isEnabled = enabledCheckBox.isSelected();
        addNewlineCheckBox.setEnabled(isEnabled);
        leaveOneNewlineCheckBox.setEnabled(isEnabled);
    }

    void load() {
        NoNewlineResolverOptions options = NoNewlineResolverOptions.getInstance();
        enabledCheckBox.setSelected(options.isEnabled());
        addNewlineCheckBox.setSelected(options.addNewline());
        leaveOneNewlineCheckBox.setSelected(options.leaveOneNewline());
        setComponentsEnabled();
    }

    void store() {
        NoNewlineResolverOptions options = NoNewlineResolverOptions.getInstance();
        options.setEnabled(enabledCheckBox.isSelected());
        options.setAddNewLine(addNewlineCheckBox.isSelected());
        options.setLeaveOneNewline(leaveOneNewlineCheckBox.isSelected());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addNewlineCheckBox;
    private javax.swing.JCheckBox enabledCheckBox;
    private javax.swing.JCheckBox leaveOneNewlineCheckBox;
    // End of variables declaration//GEN-END:variables
}
