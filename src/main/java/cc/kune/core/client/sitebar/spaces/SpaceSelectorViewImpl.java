/*
 *
 * Copyright (C) 2007-2011 The kune development team (see CREDITS for details)
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
package cc.kune.core.client.sitebar.spaces;

import cc.kune.common.client.tooltip.Tooltip;
import cc.kune.core.client.sitebar.spaces.SpaceSelectorPresenter.SpaceSelectorView;
import cc.kune.core.shared.i18n.I18nTranslationService;
import cc.kune.gspace.client.GSpaceArmor;
import cc.kune.gspace.client.resources.GSpaceArmorResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class SpaceSelectorViewImpl extends ViewImpl implements SpaceSelectorView {
  interface SpaceSelectorViewImplUiBinder extends UiBinder<Widget, SpaceSelectorViewImpl> {
  }
  private static SpaceSelectorViewImplUiBinder uiBinder = GWT.create(SpaceSelectorViewImplUiBinder.class);
  @UiField
  ToggleButton groupButton;
  private final Tooltip groupSpaceTooltip;
  @UiField
  ToggleButton homeButton;
  private final Tooltip homeSpaceTooltip;
  @UiField
  HorizontalPanel panel;
  @UiField
  ToggleButton publicButton;
  private final Tooltip publicSpaceTooltip;
  @UiField
  ToggleButton userButton;
  private final Tooltip userSpaceTooltip;

  @Inject
  public SpaceSelectorViewImpl(final GSpaceArmor armor, final I18nTranslationService i18n,
      final GSpaceArmorResources res) {
    armor.getSitebar().insert(uiBinder.createAndBindUi(this), 0);
    // homeButton.setVisible(false);
    homeSpaceTooltip = Tooltip.to(homeButton, i18n.t("Your home page in this site"));
    userSpaceTooltip = Tooltip.to(userButton,
        i18n.t("User space: it shows a list of all your documents and contents "
            + "in which you participate"));
    groupSpaceTooltip = Tooltip.to(groupButton, i18n.t("Group and personal space: Where you can create "
        + "and publish contents for your personal or group web spaces"));
    publicSpaceTooltip = Tooltip.to(publicButton,
        i18n.t("Public space: In this space you can see a preview of how the Personal o"
            + "r Group Space looks like on the web, outside this site"));
  }

  @Override
  public Widget asWidget() {
    return panel;
  }

  @Override
  public HasClickHandlers getGroupBtn() {
    return groupButton;
  }

  @Override
  public HasClickHandlers getHomeBtn() {
    return homeButton;
  }

  @Override
  public HasClickHandlers getPublicBtn() {
    return publicButton;
  }

  @Override
  public HasClickHandlers getUserBtn() {
    return userButton;
  }

  @Override
  public void setGroupBtnDown(final boolean down) {
    groupButton.setDown(down);
  }

  @Override
  public void setHomeBtnDown(final boolean down) {
    homeButton.setDown(down);
  }

  @Override
  public void setPublicBtnDown(final boolean down) {
    publicButton.setDown(down);
  }

  @Override
  public void setUserBtnDown(final boolean down) {
    userButton.setDown(down);
  }

  public void showGroupSpaceTooltip() {
    groupSpaceTooltip.showTemporally();
  }

  public void showHomeSpaceTooltip() {
    homeSpaceTooltip.showTemporally();
  }

  public void showPublicSpaceTooltip() {
    publicSpaceTooltip.showTemporally();
  }

  public void showUserSpaceTooltip() {
    userSpaceTooltip.showTemporally();
  }
}
