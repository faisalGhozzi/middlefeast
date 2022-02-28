package com.esprit.app.gui;

import java.io.IOException;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.esprit.app.gui.course.CourseForm;

public abstract class SideMenu extends Form {
    public SideMenu(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenu(String title) {
        super(title);
    }

    public SideMenu() {
    }

    public SideMenu(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("user.png");
       // Image mask = res.getImage("round-mask.png");
       // mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        //profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("Hello User", profilePic, "SideMenuTitle");
       // profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        /*Label u = new Label("Hello User");
        sidemenuTop.add(BorderLayout.CENTER, u);*/
        
        sidemenuTop.setUIID("SidemenuTop");


        
        getToolbar().addComponentToSideMenu(sidemenuTop);

        /*getToolbar().addCommandToSideMenu("  Add Product", null,	e-> new AddProductForm(this, res).show());
        getToolbar().addCommandToSideMenu("  List Produts", null,  e-> {
			try {
				new ProductsListForm(this,res).show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});*/
       
        //getToolbar().addCommandToSideMenu("  Speedometer", null,  e-> new SpeedometerForm(this, res).show());
        getToolbar().addCommandToSideMenu("  Logout", null,  e -> new SignInForm(res).show());
        getToolbar().addCommandToSideMenu("  Tutorials", null, e -> new CourseForm(res).show());
        /*getToolbar().addCommandToSideMenu("  Wishlist", null,  e -> new WishlistForm(res).show());
        getToolbar().addCommandToSideMenu("  Cart", null,  e -> new PanierForm(res).show());
        getToolbar().addCommandToSideMenu("  My Orders", null,  e -> new CommandeForm(res).show());
        //getToolbar().addCommandToSideMenu("  Logout", null,  e -> new LoginForm(res).show());*/
    }

    protected abstract void showOtherForm(Resources res);
}
