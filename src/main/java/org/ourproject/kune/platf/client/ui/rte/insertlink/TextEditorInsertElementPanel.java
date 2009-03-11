/*
 *
 * Copyright (C) 2007-2009 The kune development team (see CREDITS for details)
 * This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.ourproject.kune.platf.client.ui.rte.insertlink;

import org.ourproject.kune.platf.client.i18n.I18nTranslationService;
import org.ourproject.kune.platf.client.services.Images;
import org.ourproject.kune.workspace.client.options.AbstractOptionsPanel;

import com.calclab.suco.client.events.Listener0;

public class TextEditorInsertElementPanel extends AbstractOptionsPanel implements TextEditorInsertElementView {
    public static final String TEXT_EDT_INSERT_DIALOG = "k-ted-iep-dialog";
    public static final String TEXT_EDT_INSERT_DIALOG_ERROR_ID = "k-ted-iep-dialog-err";
    private final TextEditorInsertElementGroup textEditorInsertElementGroup;

    public TextEditorInsertElementPanel(final TextEditorInsertElementPresenter presenter, Images images,
            I18nTranslationService i18n, final TextEditorInsertElementGroup textEditorInsertElementGroup) {
        super(TEXT_EDT_INSERT_DIALOG, i18n.tWithNT("Insert a link",
                "Option in a text editor to insert links and other elements"), 380, HEIGHT + 90, 380, HEIGHT + 90,
                true, images, TEXT_EDT_INSERT_DIALOG_ERROR_ID);
        super.setIconCls("k-link-icon");
        this.textEditorInsertElementGroup = textEditorInsertElementGroup;
        super.addHideListener(new Listener0() {
            public void onEvent() {
                textEditorInsertElementGroup.resetAll();
            }
        });
    }

    @Override
    public void createAndShow() {
        textEditorInsertElementGroup.createAll();
        super.createAndShow();
    }
}