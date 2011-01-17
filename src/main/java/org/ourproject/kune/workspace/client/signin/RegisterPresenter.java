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
package org.ourproject.kune.workspace.client.signin;

import org.ourproject.kune.platf.client.PlatfMessages;
import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.ui.noti.NotifyUser;
import org.ourproject.kune.platf.client.ui.noti.NotifyUser.Level;

import cc.kune.common.client.errors.UIException;
import cc.kune.core.client.errors.EmailAddressInUseException;
import cc.kune.core.client.errors.GroupNameInUseException;
import cc.kune.core.client.i18n.I18nUITranslationService;
import cc.kune.core.client.rpcservices.UserServiceAsync;
import cc.kune.core.client.state.Session;
import cc.kune.core.client.state.StateManager;
import cc.kune.core.shared.dto.I18nCountryDTO;
import cc.kune.core.shared.dto.I18nLanguageDTO;
import cc.kune.core.shared.dto.SubscriptionMode;
import cc.kune.core.shared.dto.TimeZoneDTO;
import cc.kune.core.shared.dto.UserDTO;
import cc.kune.core.shared.dto.UserInfoDTO;

import com.calclab.suco.client.ioc.Provider;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RegisterPresenter extends SignInAbstractPresenter implements Register {

    private RegisterView view;
    private final Provider<UserServiceAsync> userServiceProvider;
    private final Provider<SignIn> signInProvider;

    public RegisterPresenter(final Session session, final StateManager stateManager,
            final I18nUITranslationService i18n, final Provider<UserServiceAsync> userServiceProvider,
            final Provider<SignIn> signInProvider) {
        super(session, stateManager, i18n);
        this.userServiceProvider = userServiceProvider;
        this.signInProvider = signInProvider;
    }

    public void doRegister() {
        signInProvider.get().hide();
        if (!session.isLogged()) {
            NotifyUser.showProgressProcessing();
            view.show();
            view.center();
            NotifyUser.hideProgress();
        } else {
            stateManager.restorePreviousToken();
        }
    }

    public View getView() {
        return view;
    }

    public void init(final RegisterView view) {
        this.view = view;
        super.view = view;
    }

    public void onFormRegister() {
        if (view.isRegisterFormValid()) {
            view.maskProcessing();

            final I18nLanguageDTO language = new I18nLanguageDTO();
            language.setCode(view.getLanguage());

            final I18nCountryDTO country = new I18nCountryDTO();
            country.setCode(view.getCountry());

            final TimeZoneDTO timezone = new TimeZoneDTO();
            timezone.setId(view.getTimezone());

            final boolean wantHomepage = view.wantPersonalHomepage();

            final UserDTO user = new UserDTO(view.getLongName(), view.getShortName(), view.getRegisterPassword(),
                    view.getEmail(), language, country, timezone, null, true, SubscriptionMode.manual, "blue");
            final AsyncCallback<UserInfoDTO> callback = new AsyncCallback<UserInfoDTO>() {
                public void onFailure(final Throwable caught) {
                    view.unMask();
                    if (caught instanceof EmailAddressInUseException) {
                        view.setErrorMessage(i18n.t(PlatfMessages.EMAIL_IN_USE), Level.error);
                    } else if (caught instanceof GroupNameInUseException) {
                        view.setErrorMessage(i18n.t(PlatfMessages.NAME_IN_USE), Level.error);
                    } else {
                        view.setErrorMessage(i18n.t("Error during registration."), Level.error);
                        throw new UIException("Other kind of exception in user registration", caught);

                    }
                }

                public void onSuccess(final UserInfoDTO userInfoDTO) {
                    onSignIn(userInfoDTO);
                    stateManager.gotoToken(userInfoDTO.getHomePage());
                    view.hide();
                    view.unMask();
                    if (wantHomepage) {
                        view.showWelcolmeDialog();
                    } else {
                        view.showWelcolmeDialogNoHomepage();
                    }
                }
            };
            userServiceProvider.get().createUser(user, wantHomepage, callback);
        }
    }
}
