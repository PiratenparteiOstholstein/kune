package cc.kune.blogs.client.actions;

import cc.kune.gspace.client.actions.AbstractNewMenu;
import cc.kune.gspace.client.actions.NewMenuProvider;

import com.google.inject.Inject;

public class BlogsNewMenu extends NewMenuProvider {

  @Inject
  public BlogsNewMenu(final AbstractNewMenu menu) {
    super(menu);
  }

}
