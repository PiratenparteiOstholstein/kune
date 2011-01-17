package cc.kune.common.client.actions.gxtui;

import cc.kune.common.client.actions.ui.AbstractChildGuiItem;
import cc.kune.common.client.actions.ui.AbstractGuiItem;
import cc.kune.common.client.actions.ui.descrip.AbstractGuiActionDescrip;
import cc.kune.common.client.actions.ui.descrip.ToolbarSeparatorDescriptor;
import cc.kune.common.client.actions.ui.descrip.ToolbarSeparatorDescriptor.Type;

public class GxtToolbarSeparatorGui extends AbstractChildGuiItem {

    @Override
    public AbstractGuiItem create(final AbstractGuiActionDescrip descriptor) {
        super.create(descriptor);
        final GxtToolbarGui toolbar = (GxtToolbarGui) parent;

        final Type type = ((ToolbarSeparatorDescriptor) descriptor).getSeparatorType();
        switch (type) {
        case fill:
            toolbar.addFill();
            break;
        case spacer:
            toolbar.addSpacer();
            break;
        case separator:
            toolbar.addSeparator();
            break;
        default:
            break;
        }
        return toolbar;
    }

    @Override
    protected void setEnabled(final boolean enabled) {
        // do nothing
    }

    @Override
    protected void setIconStyle(final String style) {
        // do nothing
    }

    @Override
    protected void setText(final String text) {
        // do nothing
    }

    @Override
    protected void setToolTipText(final String text) {
        // do nothing
    }

    @Override
    public boolean shouldBeAdded() {
        return false;
    }
}
