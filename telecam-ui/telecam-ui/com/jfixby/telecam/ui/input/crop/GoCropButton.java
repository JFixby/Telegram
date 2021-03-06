
package com.jfixby.telecam.ui.input.crop;

import com.jfixby.r3.api.ui.UI;
import com.jfixby.r3.api.ui.unit.input.CustomInput;
import com.jfixby.r3.api.ui.unit.input.MouseMovedEvent;
import com.jfixby.r3.api.ui.unit.input.TouchArea;
import com.jfixby.r3.api.ui.unit.input.TouchDownEvent;
import com.jfixby.r3.api.ui.unit.input.TouchDraggedEvent;
import com.jfixby.r3.api.ui.unit.input.TouchUpEvent;
import com.jfixby.r3.api.ui.unit.layer.Layer;
import com.jfixby.r3.api.ui.unit.raster.Raster;
import com.jfixby.r3.api.ui.unit.user.MouseInputEventListener;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionScanner;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.ORIGIN_RELATIVE_HORIZONTAL;
import com.jfixby.scarabei.api.geometry.ORIGIN_RELATIVE_VERTICAL;
import com.jfixby.telecam.ui.UserInputBar;
import com.jfixby.telecam.ui.actions.TelecamUIAction;

public class GoCropButton extends MouseInputEventListener implements CollectionScanner<TouchArea> {

	private Layer root;
	private CustomInput input;
	private Raster icon;

	private Collection<TouchArea> touchAreas;
	private final CollectionScanner<TouchArea> touchAreasAligner = this;
	private CanvasPosition position;

	public GoCropButton (final UserInputBar userPanel) {
	}

	public void setup (final Layer root) {
		this.root = root;

		this.input = (CustomInput)root.listChildren().getElementAt(0);

		this.input.setInputListener(this);
		this.input.setDebugRenderFlag(false);
		final Collection<Raster> options = this.input.listOptions();

		this.icon = options.getElementAt(0);

		this.touchAreas = this.input.listTouchAreas();

	}

	public void update (final CanvasPosition position) {
		this.position = position;
		this.icon.setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);

		this.icon.setPosition(position);

		Collections.scanCollection(this.touchAreas, this.touchAreasAligner);
	}

	@Override
	public boolean onMouseMoved (final MouseMovedEvent input_event) {
		return false;
	}

	@Override
	public boolean onTouchDown (final TouchDownEvent input_event) {
		UI.pushAction(TelecamUIAction.goCropper);
		return true;
	}

	@Override
	public boolean onTouchUp (final TouchUpEvent input_event) {
		return false;
	}

	@Override
	public boolean onTouchDragged (final TouchDraggedEvent input_event) {
		return false;
	}

	@Override
	public void scanElement (final TouchArea element, final long index) {
		element.shape().setOriginRelative(ORIGIN_RELATIVE_HORIZONTAL.CENTER, ORIGIN_RELATIVE_VERTICAL.CENTER);
		element.shape().setPosition(this.position);

	}

	public void hide () {
		this.root.hide();
	}

	public void show () {
		this.root.show();
	}

}
