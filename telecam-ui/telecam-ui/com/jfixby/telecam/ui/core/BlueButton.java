
package com.jfixby.telecam.ui.core;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.geometry.CanvasPosition;
import com.jfixby.cmns.api.geometry.ORIGIN_RELATIVE_HORIZONTAL;
import com.jfixby.cmns.api.geometry.ORIGIN_RELATIVE_VERTICAL;
import com.jfixby.cmns.api.log.L;
import com.jfixby.r3.api.ui.unit.input.CustomInput;
import com.jfixby.r3.api.ui.unit.input.MouseEventListener;
import com.jfixby.r3.api.ui.unit.input.MouseMovedEvent;
import com.jfixby.r3.api.ui.unit.input.TouchArea;
import com.jfixby.r3.api.ui.unit.input.TouchDownEvent;
import com.jfixby.r3.api.ui.unit.input.TouchDraggedEvent;
import com.jfixby.r3.api.ui.unit.input.TouchUpEvent;
import com.jfixby.r3.api.ui.unit.layer.Layer;
import com.jfixby.r3.api.ui.unit.raster.Raster;

public class BlueButton implements MouseEventListener, CollectionScanner<TouchArea> {

	private Layer root;
	private CustomInput input;
	private Raster white;
	private Raster blue;
	private Raster dark_blue;
	private Collection<TouchArea> touchAreas;
	private final CollectionScanner<TouchArea> touchAreasAligner = this;
	private CanvasPosition position;

	public BlueButton (final UserPanel userPanel) {
	}

	public void setup (final Layer root) {
		this.root = root;
		this.input = (CustomInput)root.listChildren().getElementAt(0);
		this.input.setInputListener(this);
		this.input.setDebugRenderFlag(false);
		final Collection<Raster> options = this.input.listOptions();
		this.white = options.getElementAt(0);
		this.blue = options.getElementAt(1);
		this.dark_blue = options.getElementAt(2);
		this.touchAreas = this.input.listTouchAreas();

	}

	public void update (final CanvasPosition position) {
		this.position = position;
		this.white.setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);
		this.blue.setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);
		this.dark_blue.setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);
		this.white.setPosition(position);
		this.blue.setPosition(position);
		this.dark_blue.setPosition(position);
		Collections.scanCollection(this.touchAreas, this.touchAreasAligner);
	}

	@Override
	public boolean onMouseMoved (final MouseMovedEvent input_event) {
		return false;
	}

	@Override
	public boolean onTouchDown (final TouchDownEvent input_event) {
		return false;
	}

	@Override
	public boolean onTouchUp (final TouchUpEvent input_event) {
		L.d("click!");
		return true;
	}

	@Override
	public boolean onTouchDragged (final TouchDraggedEvent input_event) {
		return false;
	}

	@Override
	public void scanElement (final TouchArea element, final int index) {
		element.shape().setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);
		element.shape().setPosition(this.position);
	}

}
