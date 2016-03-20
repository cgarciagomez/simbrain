/*
 * Part of Simbrain--a java-based neural network kit
 * Copyright (C) 2005,2007 The Authors.  See http://www.simbrain.net/credits
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.simbrain.network.gui.dialogs.neuron.rule_panels;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.simbrain.network.gui.dialogs.neuron.AbstractNeuronRulePanel;
import org.simbrain.network.gui.dialogs.neuron.NoiseGeneratorPanel;
import org.simbrain.network.neuron_update_rules.AbstractSigmoidalRule;
import org.simbrain.util.LabelledItemPanel;
import org.simbrain.util.math.SquashingFunction;
import org.simbrain.util.widgets.NStateDropDown;
import org.simbrain.util.widgets.TristateDropDown;

/**
 * A rule panel containing all the variables and methods which would be shared
 * between rules panels for discrete and continuous time sigmoidal rule panels.
 * 
 * @author Zach Tosi
 * @author Jeff Yoshimi
 */
public abstract class AbstractSigmoidalRulePanel
        extends AbstractNeuronRulePanel {

    //TODO: Clean up top
    
    /** Implementation combo box. */
    protected NStateDropDown cbImplementation;

    /** Bias field. */
    protected JTextField tfBias;

    /** Slope field. */
    protected JTextField tfSlope;

    /** Tabbed pane. */
    protected JTabbedPane tabbedPane = new JTabbedPane();

    /** Main tab. */
    protected LabelledItemPanel mainTab = new LabelledItemPanel();
    
    /** Add noise combo box. */
    protected TristateDropDown isAddNoise;

    /**
     * Construct the abstract panel.
     */
    protected AbstractSigmoidalRulePanel() {
        // TODO: Maybe pull isAddNoise back to the super-level
        cbImplementation = registerNStateDropDown(
                (r) -> ((AbstractSigmoidalRule) r).getSquashFunctionInt(),
                (r, val) -> ((AbstractSigmoidalRule) r)
                        .setSquashFunctionInt((int) val));
        cbImplementation.setItems(SquashingFunction.names());
        tfSlope = registerTextField(
                (r) -> ((AbstractSigmoidalRule) r).getSlope(),
                (r, val) -> ((AbstractSigmoidalRule) r).setSlope((double) val));
        tfBias = registerTextField((r) -> ((AbstractSigmoidalRule) r).getBias(),
                (r, val) -> ((AbstractSigmoidalRule) r).setBias((double) val));
        isAddNoise = registerTriStateDropDown(
                (r) -> ((AbstractSigmoidalRule) r).getAddNoise(),
                (r, val) -> ((AbstractSigmoidalRule) r)
                        .setAddNoise((Boolean) val));
        // below is superclass. Maybe call superclass method to make that more
        // clear?
        noisePanel = new NoiseGeneratorPanel(); // TODO: This is ugly. Fix.
    }

    /**
     * TODO. 
     *
     * @return the cbImplementation
     */
    public NStateDropDown getCbImplementation() {
        return cbImplementation;
    }
}
