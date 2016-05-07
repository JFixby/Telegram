
package com.jfixby.telecam.ui;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.log.L;
import com.jfixby.r3.api.ui.AnimationsMachine;
import com.jfixby.r3.api.ui.InputManager;
import com.jfixby.r3.api.ui.UI;
import com.jfixby.r3.api.ui.unit.ComponentsFactory;
import com.jfixby.r3.api.ui.unit.RootLayer;
import com.jfixby.r3.api.ui.unit.Unit;
import com.jfixby.r3.api.ui.unit.UnitManager;
import com.jfixby.r3.api.ui.unit.camera.ScreenChangeListener;
import com.jfixby.r3.api.ui.unit.camera.ScreenDimentions;
import com.jfixby.r3.api.ui.unit.raster.Raster;

public class TelecamDebugUnit extends Unit implements InputManager, ScreenChangeListener {
	private UnitManager unitManager;
	private RootLayer root_layer;
	public static final AssetID scene_asset_id = Names.newAssetID("com.jfixby.telecam.scene-base.psd");
	final AnimationsMachine animations_machine = UI.newAnimationsMachine();
	private final ScreenChangeListener screenChangeListener = this;
	Rectangle screenDimentions = Geometry.newRectangle();
	FixedFloat2 sceneOriginalDimentions;

	@Override
	public void onCreate (final UnitManager unitManager) {
		super.onCreate(unitManager);
		final ComponentsFactory components_factory = unitManager.getComponentsFactory();
		this.unitManager = unitManager;
		this.root_layer = unitManager.getRootLayer();
		this.root_layer.closeInputValve();
		this.root_layer.setName("GameMainUI");

		this.root_layer.attachComponent(this.screenChangeListener);

		final AssetID asset_id = Names.newAssetID("com.jfixby.r3.fokker.render.raster_is_missing");
		final Raster raster = components_factory.getRasterDepartment().newRaster(asset_id);
		this.root_layer.attachComponent(raster);
		raster.setSize(300, 300);
		this.animations_machine.activate();

	}

	@Override
	public void enableInput () {
		this.root_layer.openInputValve();
	}

	@Override
	public void onScreenChanged (final ScreenDimentions viewport_update) {
		L.d(viewport_update);

	}

	public FixedFloat2 getOriginalSceneDimentions () {
		return this.sceneOriginalDimentions;
	}

	public TelecamDebugUnit getUnit () {
		return this;
	}

	public UnitManager getUnitManager () {
		return this.unitManager;
	}

}