package org.ourproject.kune.platf.client.ui.rte;

import org.ourproject.kune.platf.client.View;
import org.ourproject.kune.platf.client.actions.ActionCollection;
import org.ourproject.kune.platf.client.actions.ActionDescriptor;
import org.ourproject.kune.platf.client.actions.ActionEnableCondition;
import org.ourproject.kune.platf.client.actions.ActionShortcut;
import org.ourproject.kune.platf.client.actions.ActionToolbarMenuDescriptor;
import org.ourproject.kune.platf.client.actions.ActionToolbarPosition;
import org.ourproject.kune.platf.client.actions.ContentEditorActionRegistry;
import org.ourproject.kune.platf.client.dto.AccessRolDTO;
import org.ourproject.kune.platf.client.services.I18nTranslationService;
import org.ourproject.kune.platf.client.state.Session;
import org.ourproject.kune.workspace.client.site.Site;

import com.calclab.suco.client.events.Listener0;

public class RTEditorPresenter implements RTEditor {

    private static final String EDIT_MENU = "Edit";
    private static final String INSERT_MENU = "Insert";
    private RTEditorView view;
    private boolean extended;
    private AccessRolDTO accessRol;
    private final I18nTranslationService i18n;
    private final Session session;
    private final ActionCollection<Object> actions;

    public RTEditorPresenter(ContentEditorActionRegistry contentEditorActionRegistry, I18nTranslationService i18n,
            Session session) {
        this.i18n = i18n;
        this.session = session;
        extended = true;
        accessRol = AccessRolDTO.Editor;
        actions = createDefActions();
    }

    public void addAction(ActionDescriptor<Object> action) {
        actions.add(action);
    }

    public void addActions(ActionCollection<Object> actions) {
        this.actions.addAll(actions);
    }

    public void editContent(String content) {
        view.setActions(actions);

    }

    public View getView() {
        return view;
    }

    public void init(RTEditorView view) {
        this.view = view;
    }

    public void setAccessRol(AccessRolDTO accessRol) {
        this.accessRol = accessRol;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    private ActionCollection<Object> createDefActions() {
        ActionCollection<Object> defActions = new ActionCollection<Object>();

        ActionToolbarMenuDescriptor<Object> undo = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.undo();
                    }
                });
        undo.setShortcut(new ActionShortcut(true, 'Z'));
        undo.setTextDescription(i18n.t("Undo"));
        undo.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> redo = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.redo();
                    }
                });
        redo.setShortcut(new ActionShortcut(true, 'Y'));
        redo.setTextDescription(i18n.t("Redo"));
        redo.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> copy = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.copy();
                    }
                });
        copy.setShortcut(new ActionShortcut(true, 'C'));
        copy.setTextDescription(i18n.t("Copy"));
        copy.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> cut = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.cut();
                    }
                });
        cut.setShortcut(new ActionShortcut(true, 'X'));
        cut.setTextDescription(i18n.t("Cut"));
        cut.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> paste = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.paste();
                    }
                });
        paste.setShortcut(new ActionShortcut(true, 'V'));
        paste.setTextDescription(i18n.t("Paste"));
        paste.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> selectAll = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.selectall();
                    }
                });
        selectAll.setShortcut(new ActionShortcut(true, 'A'));
        selectAll.setTextDescription(i18n.t("Select all"));
        selectAll.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> editHtml = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        Site.info("In dev");
                    }
                });
        editHtml.setTextDescription(i18n.t("Edit HTML"));
        editHtml.setParentMenuTitle(i18n.t(EDIT_MENU));

        ActionToolbarMenuDescriptor<Object> comment = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.addComment(session.getCurrentUser().getShortName());
                    }
                });
        comment.setShortcut(new ActionShortcut(true, 'M'));
        comment.setTextDescription(i18n.t("Comment..."));
        comment.setParentMenuTitle(i18n.t(INSERT_MENU));
        comment.setEnableCondition(isInsertHtmlSupported());

        ActionToolbarMenuDescriptor<Object> hr = new ActionToolbarMenuDescriptor<Object>(accessRol,
                ActionToolbarPosition.topbar, new Listener0() {
                    public void onEvent() {
                        view.inserthorizontalrule();
                    }
                });
        hr.setShortcut(new ActionShortcut(true, 'M'));
        hr.setTextDescription(i18n.t("Horizontal line"));
        hr.setParentMenuTitle(i18n.t(INSERT_MENU));
        hr.setEnableCondition(isExtended());

        defActions.add(undo);
        defActions.add(redo);
        defActions.add(copy);
        defActions.add(cut);
        defActions.add(paste);
        defActions.add(selectAll);
        defActions.add(editHtml);
        defActions.add(comment);
        defActions.add(hr);
        return defActions;
    }

    private ActionEnableCondition<Object> isExtended() {
        return new ActionEnableCondition<Object>() {
            public boolean mustBeEnabled(Object param) {
                return extended && view.canBeExtended();
            }
        };
    }

    private ActionEnableCondition<Object> isInsertHtmlSupported() {
        return new ActionEnableCondition<Object>() {
            public boolean mustBeEnabled(Object param) {
                return true;
            }
        };
    }
}
