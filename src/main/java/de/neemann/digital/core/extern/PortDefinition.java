/*
 * Copyright (c) 2018 Helmut Neemann.
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digital.core.extern;

import de.neemann.digital.core.ObservableValue;
import de.neemann.digital.core.ObservableValues;
import de.neemann.digital.core.element.PinDescription;
import de.neemann.digital.core.element.PinDescriptions;
import de.neemann.digital.core.element.PinInfo;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The list of ports used by an external component
 */
public class PortDefinition {
    private final ArrayList<Port> ports;

    /**
     * Creates a ew instance
     *
     * @param portDescription a comma separated list of port names
     */
    public PortDefinition(String portDescription) {
        ports = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(portDescription, ",");
        while (st.hasMoreTokens())
            ports.add(new Port(st.nextToken().trim()));
    }

    /**
     * Creates the output values to be used by the model
     *
     * @return the output values
     */
    public ObservableValues createOutputs() {
        ObservableValues.Builder builder = new ObservableValues.Builder();
        for (Port p : ports)
            builder.add(new ObservableValue(p.getName(), p.getBits()));
        return builder.build();
    }

    /**
     * Creates the pin descriptions
     *
     * @param direction the direction to use for the pin descriptions
     * @return the pin descriptions
     */
    public PinDescriptions getPinDescriptions(PinDescription.Direction direction) {
        PinInfo[] infos = new PinInfo[ports.size()];
        for (int i = 0; i < infos.length; i++)
            infos[i] = new PinInfo(ports.get(i).getName(), "", direction);
        return new PinDescriptions(infos);
    }

    /**
     * Getter for a single port
     *
     * @param i the port number
     * @return the port
     */
    public Port getPort(int i) {
        return ports.get(i);
    }
}