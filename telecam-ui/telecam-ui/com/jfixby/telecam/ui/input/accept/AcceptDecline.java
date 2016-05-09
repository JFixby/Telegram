
package com.jfixby.telecam.ui.input.accept;

import com.jfixby.cmns.api.geometry.CanvasPosition;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.r3.api.ui.UIAction;
import com.jfixby.r3.api.ui.unit.layer.Layer;
import com.jfixby.telecam.ui.TelecamUnit;
import com.jfixby.telecam.ui.UserInputBar;

public class AcceptDecline {
	private Layer root;
	private final AcceptButton acceptButton;
	private final DeclineButton declineButton;
	private final UserInputBar master;
	private UIAction<TelecamUnit> yesAction;
	private UIAction<TelecamUnit> noAction;

	public AcceptDecline (final UserInputBar userPanel) {
		this.master = userPanel;
		this.acceptButton = new AcceptButton(this);
		this.declineButton = new DeclineButton(this);
	}

	public void setup (final Layer root) {
		this.root = root;

		{
			final Layer button_root = this.root.findComponent("accept");
			this.acceptButton.setup(button_root);
		}
		{
			final Layer button_root = this.root.findComponent("decline");
			this.declineButton.setup(button_root);
		}
	}

	public void update (final CanvasPosition position, final Rectangle viewport_update) {
		this.acceptButton.update(position, viewport_update);
		this.declineButton.update(position, viewport_update);
	}

	public TelecamUnit getUnit () {
		return this.master.getUnit();
	}

	public void hide () {
		this.root.hide();
	}

	public void show () {
		this.root.show();
	}

	public void bindNoAction (final UIAction<TelecamUnit> noAction) {
		this.noAction = noAction;
	}

	public void bindYesAction (final UIAction<TelecamUnit> yesAction) {
		this.yesAction = yesAction;

	}

	public UIAction<TelecamUnit> getYesAction () {
		return this.yesAction;
	}

	public UIAction<TelecamUnit> getNoAction () {
		return this.noAction;
	}
}
