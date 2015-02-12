package com.pg.google.api;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "ApiNode" Node.
 * 
 *
 * @author Procter & Gamble, eBusiness
 */
public class ApiNodeNodeFactory 
        extends NodeFactory<ApiNodeNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiNodeNodeModel createNodeModel() {
        return new ApiNodeNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<ApiNodeNodeModel> createNodeView(final int viewIndex,
            final ApiNodeNodeModel nodeModel) {
        return new ApiNodeNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new ApiNodeNodeDialog();
    }

}

