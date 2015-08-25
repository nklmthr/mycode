package com.intuit.cto.fds.dash.web.shared;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImageResources extends ClientBundle{
	
public static final ImageResources images =  GWT.create(ImageResources.class);

    @Source("images/Dash-Logo.png") 
    public ImageResource getDashLogo();
    
    @Source("images/IntuitLogo.png")
    public ImageResource getIntuitLogo();
    
    @Source("images/processing2.gif")
	public ImageResource getprocessingIcon();

}
