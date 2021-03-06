
package com.jfixby.telecam.ui.actions;

import com.jfixby.r3.api.ui.unit.animation.Animation;
import com.jfixby.r3.api.ui.unit.animation.AnimationLifecycleListener;
import com.jfixby.telecam.ui.TelecamUnit;

public class GoAcceptDeclinePhoto extends TelecamUIAction implements AnimationLifecycleListener {
	final AnimationLifecycleListener animation_done_listener = this;
	private boolean done;

	@Override
	public void push (final TelecamUnit ui) {

	}

	@Override
	public void start (final TelecamUnit ui) {
		this.done = false;
		ui.animateAcceptDecline(this);

	}

	@Override
	public boolean isDone (final TelecamUnit ui) {

		if (this.done) {
			ui.bindAcceptDecline(TelecamUIAction.doDiscardPhoto, TelecamUIAction.doDiscardPhoto);
			ui.showGoCrop();

		}

		return this.done;
	}

	@Override
	public void onAnimationDone (final Animation animation, final int loopIndex) {
		this.done = true;
	}

	@Override
	public void onAnimationStart (final Animation animation) {
	}

	@Override
	public void onLoop (final Animation animation, final int loopIndex) {
	}
}
