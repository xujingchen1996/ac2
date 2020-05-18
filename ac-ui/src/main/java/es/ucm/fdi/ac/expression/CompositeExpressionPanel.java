/**
 * AC - A source-code copy detector
 *
 *     For more information please visit:  http://github.com/manuel-freire/ac
 *
 * ****************************************************************************
 *
 * This file is part of AC, version 2.x
 *
 * AC is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * AC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AC.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * AC - A source-code copy detector
 *
 *     For more information please visit:  http://github.com/manuel-freire/ac
 *
 * ****************************************************************************
 *
 * This file is part of AC, version 2.0
 *
 * AC is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * AC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AC.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * AC - A source-code copy detector
 * <p>
 * For more information please visit:  http://github.com/manuel-freire/ac
 * <p>
 * ****************************************************************************
 * <p>
 * This file is part of AC, version 2.0
 * <p>
 * AC is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * <p>
 * AC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with AC.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * CompositeExpressionPanel.java
 *
 * Created on September 14, 2006, 1:05 AM
 */

package es.ucm.fdi.ac.expression;

import java.awt.Container;
import java.util.ArrayList;
import javax.swing.*;

import static es.ucm.fdi.util.I18N.m;

/**
 * Composite expressions combine multiple smaller expressions under a single 
 * moniker. They can be dragged and dropped around too. And they accept dragees
 * into their area.
 *
 * @author mfreire
 */
public class CompositeExpressionPanel extends ExpressionPanel {

	private CompositeExpression e;

	private Expression selectedSubexpression;

	/**
	 * Creates new form CompositeExpressionPanel
	 */
	public CompositeExpressionPanel(CompositeExpressionPanel parentPanel) {
		super(parentPanel);
		initComponents();
		if (parentPanel == null) {
			Container p = jbDelete.getParent();
			p.remove(jbDelete);
			p.validate();
			p.repaint();
		} else {
			Container p = jbConfirm.getParent();
			p.remove(jbConfirm);
			p.validate();
			p.repaint();
		}
	}

	public void setExpression(Expression e) {

        this.e = (CompositeExpression)e;

        ArrayList<String> headers = e.getHeaders();
        ComboBoxModel<String> model = new DefaultComboBoxModel<>(headers.toArray(new String[headers.size()]));
        jcbExpType.setModel(model);
        jcbExpType.setSelectedItem(e.getHeader());

        for (ExpressionPanel p : getSubs(false)) {
            jpMain.remove(p);
        }

        for (Expression c : this.e.getChildren()) {
            addChildSub(c);
        }

        jpMain.validate();

        repaint();
    }

	public CompositeExpression getExpression() {
		return e;
	}

	private ArrayList<ExpressionPanel> getSubs(boolean onlyIfSelected) {
		ArrayList<ExpressionPanel> sel = new ArrayList<ExpressionPanel>();
		for (int i = 0; i < jpMain.getComponentCount(); i++) {
			ExpressionPanel p = (ExpressionPanel) jpMain.getComponent(i);
			if (p.isSelected() || !onlyIfSelected)
				sel.add(p);
		}
		return sel;
	}

	private void addChildSub(Expression c) {
		ExpressionPanel p = (c instanceof CompositeExpression) ? new CompositeExpressionPanel(
				this)
				: new AtomicExpressionPanel(this);
		p.setExpression(c);

		jpMain.add(p, jpMain.getComponentCount());
	}

	public void deleteSubexp(ExpressionPanel subexp) {
		jpMain.remove(subexp);
		e.removeChild(subexp.getExpression());
		refresh();
		CompositeExpressionPanel p = parentPanel;
		while (p != null) {
			p.refresh();
			p = p.parentPanel;
		}
	}

	private void refresh() {
		jpMain.validate();
		jpAlmostMain.validate();
		jpMain.repaint();
		jpAlmostMain.repaint();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jpAlmostMain = new javax.swing.JPanel();
		jpMain = new javax.swing.JPanel();
		jpAddSub = new javax.swing.JPanel();
		jpFillBottom = new javax.swing.JPanel();
		jpButtons = new javax.swing.JPanel();
		jbDelete = new javax.swing.JButton();
		jbTest = new javax.swing.JButton();
		jcbExpType = new javax.swing.JComboBox();
		jbAddSimple = new javax.swing.JButton();
		jbAddComposite = new javax.swing.JButton();
		jpFillTop = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jbConfirm = new javax.swing.JButton();

		setLayout(new java.awt.GridBagLayout());

		jpAlmostMain.setLayout(new java.awt.GridBagLayout());

		jpMain.setLayout(new javax.swing.BoxLayout(jpMain,
				javax.swing.BoxLayout.Y_AXIS));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		jpAlmostMain.add(jpMain, gridBagConstraints);

		jpAddSub.setLayout(new java.awt.GridBagLayout());

		jpFillBottom.setLayout(null);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
		gridBagConstraints.weighty = 1.0;
		jpAddSub.add(jpFillBottom, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jpAlmostMain.add(jpAddSub, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 5);
		add(jpAlmostMain, gridBagConstraints);

		jpButtons.setLayout(new java.awt.GridBagLayout());

		jbDelete.setText(m("Filter.ExpressionRemove"));
		jbDelete.setToolTipText(m("Filter.CompositeRemoveTooltip"));
		jbDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbDeleteActionPerformed(evt);
			}
		});
		jbDelete.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jbDeleteMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				jbDeleteMouseReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 5;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jpButtons.add(jbDelete, gridBagConstraints);

		jbTest.setText(m("Filter.ExpressionTest"));
		jbTest.setToolTipText(m("Filter.ExpressionTestTooltip"));
		jbTest.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbTestActionPerformed(evt);
			}
		});
		jbTest.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jbTestMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				jbTestMouseReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 0);
		jpButtons.add(jbTest, gridBagConstraints);

		jcbExpType.setModel(new DefaultComboBoxModel<String>());
		jcbExpType.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jcbExpTypeActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jpButtons.add(jcbExpType, gridBagConstraints);

		jbAddSimple.setText("+");
		jbAddSimple.setToolTipText(m("Filter.InsertCondition.SimpleTooltip"));
		jbAddSimple.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbAddSimpleActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
		jpButtons.add(jbAddSimple, gridBagConstraints);

		jbAddComposite.setText("+...");
		jbAddComposite
				.setToolTipText(m("Filter.InsertCondition.CompositeTooltip"));
		jbAddComposite.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbAddCompositeActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
		jpButtons.add(jbAddComposite, gridBagConstraints);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		jpButtons.add(jpFillTop, gridBagConstraints);

		jLabel1.setText(m("Filter.InsertCondition"));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
		jpButtons.add(jLabel1, gridBagConstraints);

		jbConfirm.setText(m("Filter.ConfirmCondition"));
		jbConfirm.setToolTipText(m("Filter.ConfirmConditionTooltip"));
		jbConfirm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbConfirmActionPerformed(evt);
			}
		});
		jbConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jbConfirmMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				jbConfirmMouseReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 7;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 6);
		jpButtons.add(jbConfirm, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
		add(jpButtons, gridBagConstraints);
	}// </editor-fold>//GEN-END:initComponents

	private void jbConfirmMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbConfirmMouseReleased
		// TODO add your handling code here:
		setSelected(false);
	}//GEN-LAST:event_jbConfirmMouseReleased

	private void jbConfirmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbConfirmMousePressed
		// TODO add your handling code here:
		setSelected(true);
	}//GEN-LAST:event_jbConfirmMousePressed

	private void jbConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfirmActionPerformed
		// TODO add your handling code here:
		test(false);
	}//GEN-LAST:event_jbConfirmActionPerformed

	private void jbTestMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbTestMousePressed
		// TODO add your handling code here:
		setSelected(true);
	}//GEN-LAST:event_jbTestMousePressed

	private void jbTestMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbTestMouseReleased
		// TODO add your handling code here:
		setSelected(false);
	}//GEN-LAST:event_jbTestMouseReleased

	private void jbDeleteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbDeleteMouseReleased
		// TODO add your handling code here:
		setSelected(false);
	}//GEN-LAST:event_jbDeleteMouseReleased

	private void jbDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbDeleteMousePressed
		// TODO add your handling code here:
		setSelected(true);
	}//GEN-LAST:event_jbDeleteMousePressed

	private void jbAddCompositeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddCompositeActionPerformed
		// TODO add your handling code here:
		Expression c = e.addChild(true);
		addChildSub(c);
		refresh();
		CompositeExpressionPanel p = parentPanel;
		while (p != null) {
			p.refresh();
			p = p.parentPanel;
		}
	}//GEN-LAST:event_jbAddCompositeActionPerformed

	private void jcbExpTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbExpTypeActionPerformed
		// TODO add your handling code here:
		e.setHeader((String) jcbExpType.getSelectedItem());
	}//GEN-LAST:event_jcbExpTypeActionPerformed

	private void jbTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTestActionPerformed
		// TODO add your handling code here:
		test(true);
	}//GEN-LAST:event_jbTestActionPerformed

	private void jbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeleteActionPerformed
		// TODO add your handling code here:
		parentPanel.deleteSubexp(this);
		refresh();
		CompositeExpressionPanel p = parentPanel;
		while (p != null) {
			p.refresh();
			p = p.parentPanel;
		}
	}//GEN-LAST:event_jbDeleteActionPerformed

	private void jbAddSimpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddSimpleActionPerformed
		// TODO add your handling code here:
		Expression c = e.addChild(false);
		addChildSub(c);
		refresh();
		CompositeExpressionPanel p = parentPanel;
		while (p != null) {
			p.refresh();
			p = p.parentPanel;
		}
	}//GEN-LAST:event_jbAddSimpleActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JButton jbAddComposite;
	private javax.swing.JButton jbAddSimple;
	private javax.swing.JButton jbConfirm;
	private javax.swing.JButton jbDelete;
	private javax.swing.JButton jbTest;
	private javax.swing.JComboBox<String> jcbExpType;
	private javax.swing.JPanel jpAddSub;
	private javax.swing.JPanel jpAlmostMain;
	private javax.swing.JPanel jpButtons;
	private javax.swing.JPanel jpFillBottom;
	private javax.swing.JPanel jpFillTop;
	private javax.swing.JPanel jpMain;
	// End of variables declaration//GEN-END:variables

}
