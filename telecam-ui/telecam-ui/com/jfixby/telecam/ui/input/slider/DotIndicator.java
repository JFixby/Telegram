
package com.jfixby.telecam.ui.input.slider;

import com.jfixby.r3.api.ui.unit.layer.Layer;
import com.jfixby.r3.api.ui.unit.raster.Raster;
import com.jfixby.r3.api.ui.unit.update.UnitClocks;
import com.jfixby.r3.api.ui.unit.update.OnUpdateListener;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.sys.Sys;

public class DotIndicator extends DotComponent implements OnUpdateListener {

	private final ReadOnlyFloat2 leftOffset;
	private final ReadOnlyFloat2 rightOffset;
	OnUpdateListener animator = this;
	private final CanvasPosition tmp;
	private double componentWidth;

	public DotIndicator (final Slider slider, final DotLeft left, final DotRigh right) {
		super(slider);
		this.leftOffset = left.getOriginalOffset();
		this.rightOffset = right.getOriginalOffset();

		this.tmp = Geometry.newCanvasPosition();
	}

	@Override
	public void setup (final Raster findComponent, final Layer root) {
		super.setup(findComponent, root);
// root.attachComponent(this.animator);
		this.componentWidth = this.rightOffset.getX() - this.leftOffset.getX();
	}

	@Override
	public void onUpdate (final UnitClocks unit_clock) {
		final double time = 2 * Sys.SystemTime().currentTimeMillis() / 1000d;
		final double alpha = (Math.sin(time));
		this.setSliderState(alpha);
	}

	public void setSliderState (final double position) {// [-1;+1]
		this.setOffsetX(position * this.componentWidth / 2);
	}

	public double getComponentWidth () {
		return this.componentWidth;
	}

}
