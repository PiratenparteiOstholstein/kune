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
 \*/
package org.ourproject.kune.platf.client.ui;

import com.gwtext.client.widgets.QuickTip;
import com.gwtext.client.widgets.QuickTips;

public class QuickTipsHelper {

    public QuickTipsHelper() {
        QuickTips.init();
        final QuickTip quickTipInstance = QuickTips.getQuickTip();
        quickTipInstance.setInterceptTitles(true);
        quickTipInstance.setDismissDelay(7000);
        quickTipInstance.setHideDelay(400);
        quickTipInstance.setMinWidth(100);
        quickTipInstance.setShowDelay(1500);
    }
}