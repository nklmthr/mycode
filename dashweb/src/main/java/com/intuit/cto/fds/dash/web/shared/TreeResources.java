package com.intuit.cto.fds.dash.web.shared;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Tree.Resources;

public interface TreeResources extends Resources {
	@Source("images/treeOpen.png")
	ImageResource treeOpen();

	@Source("images/treeClosed.png")
	ImageResource treeClosed();

	@Source("images/treeLeaf.gif")
	ImageResource treeLeaf();
}
