
package com.jfixby.telecam.ui.input.cropper;

import com.jfixby.r3.api.ui.unit.input.CustomInput;
import com.jfixby.r3.api.ui.unit.input.TouchArea;
import com.jfixby.r3.api.ui.unit.raster.Raster;
import com.jfixby.scarabei.api.geometry.ORIGIN_RELATIVE_HORIZONTAL;
import com.jfixby.scarabei.api.geometry.ORIGIN_RELATIVE_VERTICAL;
import com.jfixby.scarabei.api.geometry.Rectangle;

public class CropperButtonReset {

	private CustomInput btn;
	private final Cropper master;

	private Raster icon;
	private TouchArea touch;
	private double baseOffsetX;

	public CropperButtonReset (final Cropper cropper, final CropperButtonCancel btnCancel) {
		this.master = cropper;

	}

	public void setup (final CustomInput btn) {
		this.btn = btn;
		btn.setDebugRenderFlag(false);
		this.icon = btn.listOptions().getLast();
		this.icon.setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);

		this.touch = btn.listTouchAreas().getLast();

		this.touch.shape().setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);

		this.baseOffsetX = this.icon.getPositionX();

	}

	public void update (final Rectangle viewport_update) {
		this.icon.setPositionY(this.master.getBackground().getGrayPosition().getY());
		this.touch.shape().setPositionY(this.master.getBackground().getGrayPosition().getY());

	}

	public double getX () {
		return this.btn.getPositionX();
	}

	public double getY () {
		return this.btn.getPositionY();
	}

	public double getBaseOffsetX () {
		return this.baseOffsetX;
	}

	public void hide () {
		this.btn.hide();
	}

}
