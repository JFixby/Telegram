
package com.jfixby.telecam.game;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.r3.api.game.GameLogicComponent;
import com.jfixby.r3.api.ui.UI;

public class TelecamCore implements GameLogicComponent {

	public static final AssetID unit_id = Names.newAssetID("com.jfixby.telecam.ui.core.TelecamUnit");

	@Override
	public void startGame () {

		UI.loadUnit(unit_id);
		UI.allowUserInput();

	}
}
