
package com.jfixby.telecam.ui.core;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.r3.api.ui.AnimationsMachine;
import com.jfixby.r3.api.ui.InputManager;
import com.jfixby.r3.api.ui.UI;
import com.jfixby.r3.api.ui.unit.ComponentsFactory;
import com.jfixby.r3.api.ui.unit.RootLayer;
import com.jfixby.r3.api.ui.unit.Unit;
import com.jfixby.r3.api.ui.unit.UnitManager;
import com.jfixby.r3.api.ui.unit.camera.ScreenChangeListener;
import com.jfixby.r3.api.ui.unit.camera.ScreenDimentions;
import com.jfixby.r3.api.ui.unit.layer.Layer;
import com.jfixby.r3.ext.api.scene2d.Scene;
import com.jfixby.r3.ext.api.scene2d.Scene2D;
import com.jfixby.r3.ext.api.scene2d.Scene2DSpawningConfig;

public class TelecamUnit extends Unit implements InputManager, ScreenChangeListener {
	private UnitManager unitManager;
	private RootLayer root_layer;
	private Scene scene;
	public static final AssetID scene_asset_id = Names.newAssetID("com.jfixby.telecam.scene-base.psd");
	final AnimationsMachine animations_machine = UI.newAnimationsMachine();
	private Layer userPanelLayer;
	private UserPanel userPanel;
	private final ScreenChangeListener screenChangeListener = this;

	@Override
	public void onCreate (final UnitManager unitManager) {
		super.onCreate(unitManager);
		final ComponentsFactory components_factory = unitManager.getComponentsFactory();
		this.unitManager = unitManager;
		this.root_layer = unitManager.getRootLayer();
		this.root_layer.closeInputValve();
		this.root_layer.setName("GameMainUI");

		this.root_layer.attachComponent(this.screenChangeListener);

		final Scene2DSpawningConfig config = Scene2D.newSceneSpawningConfig();
		config.setStructureID(scene_asset_id);

		this.scene = Scene2D.spawnScene(components_factory, config);
		this.root_layer.attachComponent(this.scene);

		this.animations_machine.activate();

		this.userPanel = new UserPanel();
		this.userPanel.setup(this.scene.findLayer("user-panel").getElementAt(0), this.scene.getCamera());

	}

	@Override
	public void enableInput () {
		this.root_layer.openInputValve();
	}

	@Override
	public void onScreenChanged (final ScreenDimentions viewport_update) {
		this.userPanel.updateScreen(viewport_update);
	}

}
